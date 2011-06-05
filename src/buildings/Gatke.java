package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Gatke Hall
 * 
 * Identifying Location - Southeast Corner
 * 7547654.973E 473298.811N
 * 
 * @author Jose Alvarado
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
		//TODO When building proper model, consider making the southwest corner the canonical point
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(-98, 50, 45);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}