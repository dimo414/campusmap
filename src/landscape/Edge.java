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
public class Edge {
	
	Vector2D a;
	Vector2D b;
	
	public Edge(Vector2D a, Vector2D b) {
		this.a = a;
		this.b = b;
	}
	
}
