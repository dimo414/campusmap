package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Collins Hall
 * 
 * Identifying Location - Southeast Corner
 * 7546646.193E 473439.612N
 * 
 * @author Jose Alvarado
 */
public class Collins extends Building{
		
	private double posEast = 7546646.193;
	private double posNorth = 473439.612;
	private double posElevation = 0; // TODO Get Elevation of building

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{40/2,40/2};
	}
	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		//TODO When building proper model, consider making the southwest corner the canonical point
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(-28, 50, 48);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(-28,0,37);
		gl.glScaled(-58, 50, 47);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(46,0,-48);
		gl.glScaled(-142, 50, 59);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}
}