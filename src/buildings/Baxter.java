package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Baxter Hall and Complex - Sigma Chi, Phi Delt, SAE and Montag
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
		
		gl.glRotated(90, 0, 1, 0);
		
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(205, 50, 36);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(66,0,-(36));
		gl.glScaled(134-66, 50, 202);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(0,0,-(202));
		gl.glScaled(66, 50, 36);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}