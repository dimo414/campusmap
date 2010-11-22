package buildings;

import javax.media.opengl.GL;

/**
 * Abstract implementation of a building.
 * 
 * All buildings should be built from 150ft in vertical elevation.  A building at 163ft should
 * therefore be 13 feet off "the ground".
 */
public abstract class Building {

	double[][] vertices;
	int [][] faces;
	double [][] normals;
	double [] position;
	
	/**
	 * Get's called at GL init time, use this in place of a class constructor
	 * @param gl the OpenGL object
	 */
	public abstract void init(GL gl);
	/**
	 * Called every time the object is to be drawn.  Draws an object at the origin
	 * to be positioned to it's canonical coordinate.
	 * @param gl the OpenGL object
	 */
	public abstract void draw(GL gl);
}
