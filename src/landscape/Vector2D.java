package landscape;

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
public class Vector2D {
	
	double x;
	double y;
	
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
	 * @return
	 */
	public double cross(Vector2D v) {		
		return this.y * v.x - this.x * v.y;
	}

	public String toString() {
		return "Vector2D(" + x + ", " + y + ")";
	}

}
