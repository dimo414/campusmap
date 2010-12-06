package landscape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * DelaunayTriangulator.java
 * 
 * Implementation of a fast randomized incremental 2D Delaunay triangulation
 * algorithm with a run-time complexity of O(n*log(n)) written in java.
 * It enables you to triangulate even very large point sets in almost no time.
 * 
 * Copyright (C) 2010 Johannes Diemke. All rights reserved.
 * 
 * TODO: Implement exact arithmetic for ultimate robustness. 
 * 
 * @author trigger
 */
/*
 * Taken from http://www.informatik.uni-oldenburg.de/~trigger/ Dec. 2010
 * Website releases code for personal and educational use.
 */
public class DelaunayTriangulator {
	
	ArrayList<Vector2D> pointSet;
	TriangleSet triangleSet;
	Triangle superTriangle;
	
	public DelaunayTriangulator(ArrayList<Vector2D> pointSet) {
		if(pointSet.size() < 3)
			throw new RuntimeException("Less than three points in point set.");
		
		this.pointSet = pointSet;
		triangleSet = new TriangleSet();
	}
	
	public void setPointSet(ArrayList<Vector2D> pointSet) {
		this.pointSet = pointSet;
	}
	
	public void shuffle() {
		Collections.shuffle(pointSet);
	}
	
	public void shuffle(int [] permutation) {
		ArrayList<Vector2D> temp = new ArrayList<Vector2D>();
		
		for(int i=0; i < permutation.length; i++) {
			temp.add(pointSet.get(permutation[i]));
		}
		
		pointSet = temp;
	}
	
	public void compute() {
		triangleSet = new TriangleSet();
	
		// start out with a big triangle containing the whole point set
		double maxOfAnyCoordinate = 0.0f;
		
		for(Vector2D vector : getPointSet()) {
			maxOfAnyCoordinate = Math.max(Math.max(vector.x, vector.y), maxOfAnyCoordinate);
		}
		
		// we have to scale the super triangle to be very large.
		// otherwise the triangulation is not convex
		maxOfAnyCoordinate *= 16.0; 
		
		// for incircle test do as if these three points are outside
		// any circle defined by three points in pointSet
		Vector2D p1 = new Vector2D(0.0, 3.0 * maxOfAnyCoordinate);
		Vector2D p2 = new Vector2D(3.0 * maxOfAnyCoordinate, 0.0);
		Vector2D p3 = new Vector2D(-3.0 * maxOfAnyCoordinate, -3.0 * maxOfAnyCoordinate);
		
		superTriangle = new Triangle(p1, p2, p3);
		triangleSet.add(superTriangle);
				
		for(int i=0;i < pointSet.size(); i++) {
			// returns null if the vertex is not inside a triangle
			// basically null means it lies on an edge
			Triangle triangle = triangleSet.findContainingTriangle(pointSet.get(i));
			
			if(triangle == null) {
				// suche kante auf dem es liegt mit fehler intervall
				
				// idee:
				// wenn dieser fall eintritt, so liegt der punkt nicht in
				// einem dreieck oder aufgrund numerische ungenauigkeit wird
				// dies nicht erkann. dieser fall soll einfach als der
				// fall behandelt werden, dass der punkt auf einer kante liegt
				// liegt der punkt auf einer kannte, so liegt er auf den kannten
				// die sich zwei dreiecke teilen. die kante kann gefunden werden
				// indem nun alle kanten (dreiecke und alle ihre kanten)
				// durchiteriert werden und der abstand zu allen kanten berechnet
				// wird. die kante mit dem minimalen abstand zu dem punkt ist die
				// kante auf der der punkt dann liegt, diese wird entfernt und
				// es werden 4 neue kanten eingefügt
			
				// this gives the edge with the smalles distance the vertex
				// since edges not in the circumcircle are asumed to be on edges
				Edge edge = triangleSet.findEdgeThatContains(pointSet.get(i));
								
				Triangle first = findTriangleSharing(edge);		
				Triangle second = findNeighbour(first, edge);
				
				Vector2D nonEdgeFirst = first.nonEdgeVertex(edge);
				Vector2D nonEdgeSecond = second.nonEdgeVertex(edge);
				
				triangleSet.remove(first);
				triangleSet.remove(second);
				
				Triangle tri1 = new Triangle(edge.a, nonEdgeFirst, pointSet.get(i));
				Triangle tri2 = new Triangle(edge.b, nonEdgeFirst, pointSet.get(i));
				Triangle tri3 = new Triangle(edge.a, nonEdgeSecond, pointSet.get(i));
				Triangle tri4 = new Triangle(edge.b, nonEdgeSecond, pointSet.get(i));
				
				triangleSet.add(tri1);
				triangleSet.add(tri2);
				triangleSet.add(tri3);
				triangleSet.add(tri4);
				
				legalizeEdge(tri1, new Edge(edge.a, nonEdgeFirst), pointSet.get(i));
				legalizeEdge(tri2, new Edge(edge.b, nonEdgeFirst), pointSet.get(i));
				legalizeEdge(tri3, new Edge(edge.a, nonEdgeSecond), pointSet.get(i));
				legalizeEdge(tri4, new Edge(edge.b, nonEdgeSecond), pointSet.get(i));
			} else { // case: vertex in triangle
				Vector2D a = triangle.a;
				Vector2D b = triangle.b;
				Vector2D c = triangle.c;
				
				triangleSet.remove(triangle);
				
				Triangle first = new Triangle(a, b, pointSet.get(i));
				Triangle second = new Triangle(b, c, pointSet.get(i));
				Triangle third = new Triangle(c, a, pointSet.get(i));
			
				triangleSet.add(first);
				triangleSet.add(second);
				triangleSet.add(third);
				
				legalizeEdge(first, new Edge(a, b), pointSet.get(i));
				legalizeEdge(second, new Edge(b, c), pointSet.get(i));
				legalizeEdge(third, new Edge(c, a), pointSet.get(i));
			}
		}
		
		// remove all triangles that contain vertices of the super triangle
		triangleSet.removeTrianglesUsing(superTriangle.a);
		triangleSet.removeTrianglesUsing(superTriangle.b);
		triangleSet.removeTrianglesUsing(superTriangle.c);
	}
		
	private void legalizeEdge(Triangle triangle, Edge edge, Vector2D newVertex) {
		Triangle neighbourTriangle = findNeighbour(triangle, edge);
		
		// falls ein neighbour existiert, dann legalizeEdge
		if(neighbourTriangle != null) {
			if(neighbourTriangle.pointInCircumcircle(newVertex)) {
				triangleSet.remove(triangle);
				triangleSet.remove(neighbourTriangle);
				
				Vector2D nonEdge = neighbourTriangle.nonEdgeVertex(edge);
				
				Triangle first = new Triangle(nonEdge, edge.a, newVertex);
				Triangle second = new Triangle(nonEdge, edge.b, newVertex);
				
				triangleSet.add(first);
				triangleSet.add(second);
				
				legalizeEdge(first, new Edge(nonEdge, edge.a), newVertex);
				legalizeEdge(second, new Edge(nonEdge, edge.b), newVertex);
			}
		} 
	}
	
	private Triangle findNeighbour(Triangle tri, Edge edge) {
		for(Triangle triangle : triangleSet.triangleSet) {
			if(triangle.isNeighbour(edge) && triangle != tri) {
				return triangle;
			}
		}
		
		return null;
	}
	
	private Triangle findTriangleSharing(Edge edge) {
		for(Triangle triangle : triangleSet.triangleSet) {
			if(triangle.isNeighbour(edge)) {
				return triangle;
			}
		}
		
		return null;
	}
	
	public ArrayList<Vector2D> getPointSet() {
		return pointSet;
	}
	
	public ArrayList<Triangle> getTriangleSet() {
		return triangleSet.triangleSet;
	}

}

class TriangleSet {
	
	ArrayList<Triangle> triangleSet;
	
	public TriangleSet() {
		this.triangleSet = new ArrayList<Triangle>();
	}
	
	public Edge findEdgeThatContains(Vector2D point) {
		
		ArrayList<EdgeDistancePack> edgeVector = new ArrayList<EdgeDistancePack>();
		
		for(Triangle triangle : triangleSet) {
			edgeVector.add(triangle.findNearestEdge(point));
		}
		
		EdgeDistancePack[] edgeDistancePacks = new EdgeDistancePack[edgeVector.size()];
		edgeVector.toArray(edgeDistancePacks);
		
		// sort by distance
		Arrays.sort(edgeDistancePacks);
		
		return edgeDistancePacks[0].edge;
	}

	public void add(Triangle triangle) {
		this.triangleSet.add(triangle);
	}
	
	public void remove(Triangle triangle) {
		this.triangleSet.remove(triangle);
	}
	
	public void removeTrianglesUsing(Vector2D point) {
		ArrayList<Triangle> removeList = new ArrayList<Triangle>();
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
	
}
