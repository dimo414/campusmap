package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Gatke Hall:
 * 
 * THIS JAVADOC OR SOME OTHER COMMENT SHOULD CONTAIN DETAILS LIKE POSITION AND DIMENSIONS OF THE BUILDING
 */
public class Gatke extends Building{
	
	private double posEast = 7547654.973;
	private double posNorth = 473298.811;
	private double posElevation = 0; // TODO Get Elevation of building
	
	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{45/2,98/2};
	}

	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		gl.glRotated(90, 0, 1, 0);
		
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(45, 50, 98);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}