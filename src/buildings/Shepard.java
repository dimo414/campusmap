package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Shepard Hall
 * @author Jose Alvarado
 */
public class Shepard extends Building{

	//1 - left cube of Olin, 2 - right cube of Olin

	private double posEast = 7546121.405;//121
	private double posNorth = 472643.988;//759
	private double posElevation = 0; // TODO Get Elevation of building

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{92.0/2, 155.0/2};
	}
	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		gl.glRotated(90, 0, 1, 0);
		
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(121, 50, 42);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(73,0,-(42));
		gl.glScaled(23, 50, 20);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();	
		
		gl.glPopMatrix();
	}
}