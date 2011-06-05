package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Baxter Hall and Complex - Sigma Chi, Phi Delt, SAE and Montag
 * 
 * Identifying Location - Southeast Corner
 * 7547580.116E 473020.902N
 * 
 * @author Jose Alvarado
 */
public class Baxter extends Building{

	//1 - bottom left cube of baxter, 2 - middle cube of baxter, 3 - right cube of baxter
	
	private double posEast = 7547580.116;	
	private double posNorth = 473020.902;	
	private double posElevation = 0; // TODO Get Elevation of building

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{205/2,238/2};
	}
	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		//TODO When building proper model, consider making the southwest corner the canonical point
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(-36, 50, 205);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(-36,0,-66);
		gl.glScaled(-202, 50, 134-66);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(-202,0,0);
		gl.glScaled(-36, 50, 66);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}