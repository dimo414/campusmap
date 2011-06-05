package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Gatke Hall:
 * 
 * THIS JAVADOC OR SOME OTHER COMMENT SHOULD CONTAIN DETAILS LIKE POSITION AND DIMENSIONS OF THE BUILDING
 */
public class Gatke extends Building{
	
	private double posEast = 7547654.973;
	private double posNorth = 473298.811;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{45/2,98/2};
	
	@Override
	public void init(GL gl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();		
		
		// Universal positioning
		if(!drawOrigin){
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
			gl.glRotated(buildingRotation, 0, 1, 0);
		}
		else
			// this is the appx centerpoint of the building
			gl.glTranslated(-midpoint[0], 0, midpoint[1]);
		// End universal positioning
		
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