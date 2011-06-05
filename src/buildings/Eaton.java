package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Eaton Hall
 * 
 * Identifying Location - Southeast Corner
 * 7547077.509E 473321.981N
 * 
 * @author Jose Alvarado
 */
public class Eaton extends Building{

	private double posEast = 7547077.509;
	private double posNorth = 473321.981;
	private double posElevation = 0; // TODO Get Elevation of building

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{44+15+5+4/2,151/2};
	}
			
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		//TODO When building proper model, consider making the southwest corner the canonical point
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(-151, 50, 44);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(-19,0,-44);
		gl.glScaled(-(151 - 19*2), 50, 15);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(-(19+32),0,-(44+15));
		gl.glScaled(-(151-19*2-33*2), 50, 5);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 4th Cube
		gl.glPushMatrix();
		gl.glTranslated(-(19+32+15),0,-(44+15+5));
		gl.glScaled(17, 50, -4);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}