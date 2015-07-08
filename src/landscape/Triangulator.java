package landscape;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import util.Shape;
import util.Util;
import util.Vector;

/**
 * A class which generates a ground area based on a set of points by
 * constructing triangles between adjacent points.  The algorithm used
 * is Delaunay triangulation (http://en.wikipedia.org/wiki/Delaunay_triangulation).
 * Many thanks to Johannes Diemke (http://www.informatik.uni-oldenburg.de/~trigger/)
 * for the algorithm code provided on his website.
 * @author Michael Diamond
 */
public class Triangulator {
    private ArrayList<Vector> cords;
    private ArrayList<int[]> triangles;
    
    /**
     * Takes an ArrayList of vectors and constructs the triangulator object.
     * The third (z) parameter of the vector is not factored into the
     * triangulation algorithm.
     * @param al double precision coordinates in international feet
     */
    public Triangulator(ArrayList<Vector> al){
        cords = new ArrayList<>(al);
        genTriangles();
    }
    
    /**
     * <p>Takes a filename containing 3D coordinates and constructs the
     * triangulator object from them.</p>
     * 
     * <h4>Format:</h4>
     * <p>Each line with content which does not start with a '#' is an
     * coordinate.  It should contain three whitespace separated
     * decimal numbers, x y and z.  The third number, the height, is
     * not factored into the triangulation algorithm.</p>
     * @param file the file name, relative to the source directory
     */
    public Triangulator(String file){
        // FIXME this code is currently not JAR compatible
        File f = new File("src/"+file);
        cords = new ArrayList<>();
        try (Scanner in = new Scanner(f)) {
            int line = 0;
            while(in.hasNextLine()){
                line++;
                String ln = in.nextLine().trim();
                if(ln.length() == 0 || ln.charAt(0) == '#')
                    continue;
                String[] spl = ln.split("\\s+");
                if(spl.length < 2 || spl.length > 3)
                    throw new RuntimeException("Line "+line+" has too little or too much data");
                double[] data = new double[3];
                for(int i = 0; i < data.length; i++){
                    try{
                        data[i] = Double.parseDouble(spl[i]);
                    } catch (NumberFormatException e){
                        throw new NumberFormatException("Line "+line+" contains invalid data: \""+spl[i]+"\"");
                    }
                }
                if(spl.length == 2) // if given 2D coordinates, turn into flat 3D cords
                    data[2] = 0;
                cords.add(new Vector(data));
            }
            
            genTriangles();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to find "+file+" to generate landscape.");
        }
    }
    
    /**
     * Implements Diemke's algorithm to construct a convex set of triangles based on the
     * coordinates provided. 
     */
    private void genTriangles(){
        triangles = new ArrayList<>();
        
        // shuffle the data to help the algorithm
        Collections.shuffle(cords);
        
        // start algorithm - based heavily on Diemke's code
        long start = System.currentTimeMillis();
        
        ArrayList<Vector2D> cords2D = new ArrayList<>();
        // hash map to track the indices of the coordinates we're actually talking about
        HashMap<Vector2D,Integer> cordMap = new HashMap<>();
        for(int i = 0; i < cords.size(); i++){
            Vector v = cords.get(i);
            Vector2D v2 = new Vector2D(v);
            cords2D.add(v2);
            cordMap.put(v2,i);
        }
        TriangleSet triangleSet = new TriangleSet();
        
        //
        // We want to construct a triangle far larger than the coordinate space
        //
        double maxOfAnyCoordinate = 0;
        
        // TODO make this use Math.abs() so it can handle negative cords?
        for(Vector2D vector : cords2D) {
            maxOfAnyCoordinate = Math.max(Math.max(vector.x, vector.y), maxOfAnyCoordinate);
        }
        
        // further scale the triangle to ensure it is larger
        maxOfAnyCoordinate *= 16; 
        
        Vector2D p1 = new Vector2D(0, 3 * maxOfAnyCoordinate);
        Vector2D p2 = new Vector2D(3 * maxOfAnyCoordinate, 0);
        Vector2D p3 = new Vector2D(-3 * maxOfAnyCoordinate, -3 * maxOfAnyCoordinate);
        
        Triangle superTriangle = new Triangle(p1,p2,p3);
        triangleSet.add(superTriangle);
        
        for(int i=0; i < cords2D.size(); i++) {
            // returns null if the vertex is not inside a triangle
            // basically null means it lies on an edge
            Triangle triangle = triangleSet.findContainingTriangle(cords2D.get(i));
            
            if(triangle == null){
                // this gives the edge with the smalles distance the vertex
                // since edges not in the circumcircle are asumed to be on edges
                Edge edge = triangleSet.findEdgeThatContains(cords2D.get(i));
                                
                Triangle first = findTriangleSharing(triangleSet, edge);        
                Triangle second = findNeighbour(triangleSet, first, edge);
                
                Vector2D nonEdgeFirst = first.nonEdgeVertex(edge);
                Vector2D nonEdgeSecond = second.nonEdgeVertex(edge);
                
                triangleSet.remove(first);
                triangleSet.remove(second);
                
                Triangle tri1 = new Triangle(edge.a, nonEdgeFirst, cords2D.get(i));
                Triangle tri2 = new Triangle(edge.b, nonEdgeFirst, cords2D.get(i));
                Triangle tri3 = new Triangle(edge.a, nonEdgeSecond, cords2D.get(i));
                Triangle tri4 = new Triangle(edge.b, nonEdgeSecond, cords2D.get(i));
                
                triangleSet.add(tri1);
                triangleSet.add(tri2);
                triangleSet.add(tri3);
                triangleSet.add(tri4);
                
                legalizeEdge(triangleSet, tri1, new Edge(edge.a, nonEdgeFirst), cords2D.get(i));
                legalizeEdge(triangleSet, tri2, new Edge(edge.b, nonEdgeFirst), cords2D.get(i));
                legalizeEdge(triangleSet, tri3, new Edge(edge.a, nonEdgeSecond), cords2D.get(i));
                legalizeEdge(triangleSet, tri4, new Edge(edge.b, nonEdgeSecond), cords2D.get(i));
            } else { // case: vertex in triangle
                Vector2D a = triangle.a;
                Vector2D b = triangle.b;
                Vector2D c = triangle.c;
                
                triangleSet.remove(triangle);
                
                Triangle first = new Triangle(a, b, cords2D.get(i));
                Triangle second = new Triangle(b, c, cords2D.get(i));
                Triangle third = new Triangle(c, a, cords2D.get(i));
            
                triangleSet.add(first);
                triangleSet.add(second);
                triangleSet.add(third);
                
                legalizeEdge(triangleSet, first, new Edge(a, b), cords2D.get(i));
                legalizeEdge(triangleSet, second, new Edge(b, c), cords2D.get(i));
                legalizeEdge(triangleSet, third, new Edge(c, a), cords2D.get(i));
            }
        }
        
        // remove all triangles that contain vertices of the super triangle
        triangleSet.removeTrianglesUsing(superTriangle.a);
        triangleSet.removeTrianglesUsing(superTriangle.b);
        triangleSet.removeTrianglesUsing(superTriangle.c);
            
        
        System.out.println("Delaunay Triangulation generated in " + (System.currentTimeMillis() - start) + " ms");
        // Done with triangulation generation
        
        // Store generated triangles
        for(Triangle t : triangleSet.triangleSet){
            int t1 = cordMap.get(t.a);
            int t2 = cordMap.get(t.b);
            int t3 = cordMap.get(t.c);
            if(t.isOrientedCCW()){ // Ensure all triangles are counterclockwise
                triangles.add(new int[]{t1,t2,t3});
            } else {
                triangles.add(new int[]{t3,t2,t1});
            }
        }
    }
    
    /**
     * Constructs a util.Shape object to be drawn in OpenGL
     * @return a shape object representing the generated triangulation
     */
    public Shape getShape(){
        float[][] vertices = new float[cords.size()][];
        int[][] faces = new int[triangles.size()][];
        float[][] normals = new float[triangles.size()][];
        
        // construct vertices
        for(int i = 0; i < vertices.length; i++){
            Vector v = cords.get(i);
            vertices[i] = Util.fCoordToGL(v.x(), v.y(), v.z());
        }
        // construct faces
        for(int i = 0; i < triangles.size(); i++){
            faces[i] = triangles.get(i);
        }
        // construct normals
        for(int i = 0; i < faces.length; i++){
            Vector a = cords.get(faces[i][0]);
            Vector b = cords.get(faces[i][1]);
            Vector c = cords.get(faces[i][2]);
            a = a.minus(b);
            c = c.minus(b);
            Vector cross = a.cross(c);
            normals[i] = Util.dToF(new double[]{cross.x(),cross.y(),cross.z()});
        }
        
        return new Shape(vertices,faces,normals);
    }
    
    //
    // TRIANGULATOR HELPER METHODS
    // Many of these methods do not have comments, as it is not completely clear
    // to me what they do beyond the method signature.
    //
    
    private void legalizeEdge(TriangleSet triangleSet, Triangle triangle, Edge edge, Vector2D newVertex) {
        Triangle neighbourTriangle = findNeighbour(triangleSet, triangle, edge);
        
        // if a neighbor exists, then legalizeEdge
        if(neighbourTriangle != null) {
            if(neighbourTriangle.pointInCircumcircle(newVertex)) {
                triangleSet.remove(triangle);
                triangleSet.remove(neighbourTriangle);
                
                Vector2D nonEdge = neighbourTriangle.nonEdgeVertex(edge);
                
                Triangle first = new Triangle(nonEdge, edge.a, newVertex);
                Triangle second = new Triangle(nonEdge, edge.b, newVertex);
                
                triangleSet.add(first);
                triangleSet.add(second);
                
                legalizeEdge(triangleSet, first, new Edge(nonEdge, edge.a), newVertex);
                legalizeEdge(triangleSet, second, new Edge(nonEdge, edge.b), newVertex);
            }
        } 
    }
    
    private static Triangle findNeighbour(TriangleSet triangleSet, Triangle tri, Edge edge) {
        for(Triangle triangle : triangleSet.triangleSet) {
            if(triangle.isNeighbour(edge) && triangle != tri) {
                return triangle;
            }
        }
        
        return null;
    }
    
    private static Triangle findTriangleSharing(TriangleSet triangleSet, Edge edge) {
        for(Triangle triangle : triangleSet.triangleSet) {
            if(triangle.isNeighbour(edge)) {
                return triangle;
            }
        }
        
        return null;
    }
    
    //
    // VECTOR 2D
    //
    /**
     * Internal representation of a 2D vector
     * Has more limited functionality than util.Vector
     */
    private class Vector2D {
        final double x;
        final double y;
        
        public Vector2D(Vector v){
            x = v.x();
            y = v.y();
        }
        
        public Vector2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public Vector2D sub(Vector2D v) {
            return new Vector2D(this.x - v.x, this.y - v.y);
        }
        
        public Vector2D add(Vector2D v) {
            return new Vector2D(this.x + v.x, this.y + v.y);
        }
        
        public Vector2D mult(double s) {
            return new Vector2D(this.x * s, this.y * s);
        }
        
        public double dot(Vector2D v) {
            return this.x * v.x + this.y * v.y;
        }
        
        public double mag() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }
        
        /**
         * Compute the 2D pseudo cross product Dot(Perp(u), v)
         * 
         * @param v
         * @return a pseudo cross product - whatever that is
         */
        public double cross(Vector2D v) {       
            return this.y * v.x - this.x * v.y;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            long temp;
            temp = Double.doubleToLongBits(x);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(y);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vector2D other = (Vector2D) obj;
            if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
                return false;
            if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Vector2D(" + x + ", " + y + ")";
        }
    }
    
    
    //
    // EDGE
    //
    private class Edge {
        Vector2D a, b;
        
        public Edge(Vector2D s, Vector2D f){
            a = s;
            b = f;
        }
    }
        
    //
    // EDGE TO POINT
    //
    private class EdgePointDistance implements Comparable<EdgePointDistance> {
        Edge e;
        Vector2D p;
        double dist;
        
        public EdgePointDistance(Edge ed, Vector2D po) {
            e = ed;
            p = po;
            dist = computeClosestPoint().sub(p).mag();
        }
        
        /**
         * Finds the point along the edge closest to the point
         * @return the closest point on e to p
         */
        private Vector2D computeClosestPoint() {
            Vector2D eV = e.b.sub(e.a);
            double t = p.sub(e.a).dot(eV) / eV.dot(eV);
            
            if(t < 0.0) t = 0.0;
            if(t > 1.0) t = 1.0;
            
            return e.a.add(eV.mult(t));
        }

        @Override
        public int compareTo(EdgePointDistance o) {
            return Double.compare(dist, o.dist);
        }
    }
    
    //
    // TRIANGLE
    //
    /**
     * Internal representation of a triangle
     */
    private class Triangle {
        Vector2D a, b, c;
        
        Triangle(Vector2D p1, Vector2D p2, Vector2D p3){
            a = p1;
            b = p2;
            c = p3;
        }
        
        /**
         * Test if a 2D point lies inside the 2D triangle ABC.
         * 
         * Real-Time Collision Detection, chap. 5, p. 206
         * 
         * @param p the point to be tested
         * @return true iff the point lies inside the 2D triangle ABC
         */
        public boolean contains(Vector2D p) {
            double pab = p.sub(a).cross(b.sub(a));
            double pbc = p.sub(b).cross(c.sub(b)); 
            
            if(!hasSameSign(pab, pbc))
                return false;
            
            double pca = p.sub(c).cross(a.sub(c));
            
            if(!hasSameSign(pab, pca))
                return false;
            
            return true;
        }
        
        private boolean hasSameSign(double num1, double num2) {
            return Math.signum(num1) == Math.signum(num2);
        }
        
        /**
         * Let the triangle ABC appear in counterclockwise (CCW) order.
         * Then when det > 0, d lies inside the circumcircle through
         * the three points a, b and c. If instead det < 0, d lies outside
         * the circumcircle. When det = 0, the four points are cocircular.
         * If the triangle is oriented clockwise (CW) the result is reversed. 
         * 
         * Real-Time Collision Detection, chap. 3, p. 34
         * 
         * @return true iff the point d lies inside the circumcircle through
         *         the three points a, b, and c of the triangle
         */
        public boolean pointInCircumcircle(Vector2D d) {
            double a11 = a.x - d.x;
            double a21 = b.x - d.x;
            double a31 = c.x - d.x;
            
            double a12 = a.y - d.y;
            double a22 = b.y - d.y;
            double a32 = c.y - d.y;
            
            double a13 = (a.x - d.x) * (a.x - d.x) + (a.y - d.y) * (a.y - d.y);
            double a23 = (b.x - d.x) * (b.x - d.x) + (b.y - d.y) * (b.y - d.y);
            double a33 = (c.x - d.x) * (c.x - d.x) + (c.y - d.y) * (c.y - d.y);
            
            double det = a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32 -
                         a13 * a22 * a31 - a12 * a21 * a33 - a11 * a23 * a32;
            
            if(isOrientedCCW()) {
                return det > 0.0f;
            }
            return det < 0.0f;
        }
        
        /**
         * Let A, B and C be three 2D points. If det > 0, C lies to the left
         * of the directed line AB. Equivalently the triangle ABC is
         * oriented counterclockwise. When det < 0, C lies to the right of
         * the directed line AB, and the triangle ABC is oriented clockwise.
         * When det = 0, the three points are collinear.
         * 
         * Real-Time Collision Detection, chap. 3, p. 32
         * 
         * @return true iff the triangle ABC is oriented counterclockwise (CCW)
         */
        public boolean isOrientedCCW() {
            double a11 = a.x - c.x;
            double a21 = b.x - c.x;
            
            double a12 = a.y - c.y;
            double a22 = b.y - c.y;
            
            double det = a11 * a22 - a12 * a21;
            
            return det > 0.0f;
        }

        public boolean isNeighbour(Edge edge) {
            return (a.equals(edge.a) || b.equals(edge.a) || c.equals(edge.a)) && (a.equals(edge.b) || b.equals(edge.b) || c.equals(edge.b));
        }
        
        public Vector2D nonEdgeVertex(Edge edge) {
            if(!a.equals(edge.a) && !a.equals(edge.b)) return a;
            if(!b.equals(edge.a) && !b.equals(edge.b)) return b;
            if(!c.equals(edge.a) && !c.equals(edge.b)) return c;
            return null;
        }
        
        public boolean hasVertex(Vector2D v1) {
            return a.equals(v1) || b.equals(v1) || c.equals(v1); 
        }

        public EdgePointDistance findNearestEdge(Vector2D point) {
            EdgePointDistance[] edges = {
                new EdgePointDistance(new Edge(a,b), point),
                new EdgePointDistance(new Edge(b,c), point),
                new EdgePointDistance(new Edge(c,a), point)};
            
            Arrays.sort(edges);
            return edges[0];
        }
        
        @Override
        public String toString() {
            return "Triangle: " + a + " " + b + " " + c;
        }
    }
    
    
    //
    // TRIANGLE SET
    //
    private class TriangleSet {
        
        ArrayList<Triangle> triangleSet;
        
        public TriangleSet() {
            this.triangleSet = new ArrayList<>();
        }
        
        public Edge findEdgeThatContains(Vector2D point) {
            
            ArrayList<EdgePointDistance> edgeVector = new ArrayList<>();
            
            for(Triangle triangle : triangleSet) {
                edgeVector.add(triangle.findNearestEdge(point));
            }
            
            EdgePointDistance[] edgeDistancePacks = new EdgePointDistance[edgeVector.size()];
            edgeVector.toArray(edgeDistancePacks);
            
            // sort by distance
            Arrays.sort(edgeDistancePacks);
            
            return edgeDistancePacks[0].e;
        }

        public void add(Triangle triangle) {
            this.triangleSet.add(triangle);
        }
        
        public void remove(Triangle triangle) {
            this.triangleSet.remove(triangle);
        }
        
        public void removeTrianglesUsing(Vector2D point) {
            ArrayList<Triangle> removeList = new ArrayList<>();
            for(Triangle triangle : triangleSet) {
                if(triangle.hasVertex(point)) {
                    removeList.add(triangle);
                }
            }
            
            triangleSet.removeAll(removeList);
        }
        
        public Triangle findContainingTriangle(Vector2D point) {
            for(Triangle triangle : triangleSet) {
                if(triangle.contains(point)) {
                    return triangle;
                }
            }
            return null;
        }
        
        @Override
        public String toString(){
            return triangleSet.toString();
        }
    }
}
