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
public class EdgeDistancePack implements Comparable<EdgeDistancePack>{
	
	Edge edge;
	double distance;
	
	public EdgeDistancePack(Edge edge, double distance) {
		this.edge = edge;
		this.distance = distance;
	}

	@Override
	public int compareTo(EdgeDistancePack o) {
		if(o.distance == distance)
			return 0;
		else if(o.distance < distance)
			return 1;
		else
			return -1;
	}

}
