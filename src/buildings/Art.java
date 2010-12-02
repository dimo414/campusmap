package buildings;

import javax.media.opengl.GL;

import util.Util;

import com.sun.opengl.util.GLUT;

/**
 * <h3>Arts</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>7547138.978E 473205.738N</strong></p>

 * 
 * @author Brian Forbis
 */
public class Art extends Building{
	private double posEast = 7546138.978;
	private double posNorth = 473605.738;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	private GLUT glut = new GLUT();
	
	private double height = 45;
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		// Universal positioning
		if(!drawOrigin){
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
			gl.glRotated(buildingRotation, 0, 1, 0);
		}
		else //TODO Find Centerpoint
			// this is the appx centerpoint of the building
			gl.glTranslated(-Util.feetToGL(30), 0, Util.feetToGL(30));
		// End universal positioning
		
		//Start Drawing, SouthWest Positions
		
		gl.glPushMatrix(); //Kiln
		gl.glTranslated(-Util.feetToGL(22), 0, -Util.feetToGL(18+3./12));
		gl.glScaled(Util.feetToGL(22), Util.feetToGL(height), Util.feetToGL(18+8./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();

		gl.glPushMatrix(); //MainBuilding
		gl.glScaled(Util.feetToGL(73+2./12), Util.feetToGL(height), Util.feetToGL(54));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Hallway
		gl.glTranslated(Util.feetToGL(73+2./12), 0, -Util.feetToGL(20+7./12));
		gl.glScaled(Util.feetToGL(11+6./12), Util.feetToGL(height), Util.feetToGL(13+11./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //RightSide
		gl.glTranslated(Util.feetToGL(84+8./12), 0, Util.feetToGL(12+11./12));
		gl.glScaled(Util.feetToGL(31+4./12), Util.feetToGL(height), Util.feetToGL(66+4./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Right Extrusion
		gl.glTranslated(Util.feetToGL(116), 0, -Util.feetToGL(7));
		gl.glScaled(Util.feetToGL(6), Util.feetToGL(height), Util.feetToGL(26+6./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);		
	}

}
