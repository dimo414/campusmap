import java.util.Arrays;
import java.util.Vector;

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
public class Triangle {
	
	Vector2D a;
	Vector2D b;
	Vector2D c;
	
	public Triangle(Vector2D a, Vector2D b, Vector2D c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	private Vector2D computeClosestPoint(Vector2D a, Vector2D b, Vector2D c) {
		Vector2D ab = b.sub(a);
		double t = c.sub(a).dot(ab) / ab.dot(ab);
		
		if(t < 0.0) t = 0.0;
		if(t > 1.0) t = 1.0;
		
		return a.add(ab.mult(t)); 
	}
	
	/**
	 * Test if a 2D point lies inside the 2D triangle ABC.
	 * 
	 * Real-Time Collision Detection, chap. 5, p. 206
	 * 
	 * @param point the point to be tested
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
	
	private boolean hasSameSign(double a, double b) {
		return Math.signum(a) == Math.signum(b);
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
	 * 		   the three points a, b, and c of the triangle
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
		
		if(isOrientedCCW())
			return det > 0.0f;
		else
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
		return (a == edge.a || b == edge.a || c == edge.a) && (a == edge.b || b == edge.b || c == edge.b);
	}
	
	public Vector2D nonEdgeVertex(Edge edge) {
		if(a != edge.a && a != edge.b) return a;
		if(b != edge.a && b != edge.b) return b;
		if(c != edge.a && c != edge.b) return c;
		return null;
	}
	
	public boolean hasVertex(Vector2D v1) {
		if(a == v1 || b == v1 || c == v1)
			return true;
		else
			return false; 
	}
	
	public String toString() {
		return "Triangle: " + a + b + c;
	}

	public EdgeDistancePack findNearestEdge(Vector2D point) {
		EdgeDistancePack[] edges = new EdgeDistancePack[3];
		
		edges[0]= new EdgeDistancePack(new Edge(a,b), computeClosestPoint(a, b, point).sub(point).mag());
		edges[1]= new EdgeDistancePack(new Edge(b,c), computeClosestPoint(b, c, point).sub(point).mag());
		edges[2]= new EdgeDistancePack(new Edge(c,a), computeClosestPoint(c, a, point).sub(point).mag());
		
		Arrays.sort(edges);
		return edges[0];
	}

}