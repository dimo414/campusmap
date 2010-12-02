package buildings;

import javax.media.opengl.GL;

import util.Util;

import com.sun.opengl.util.GLUT;

/**
 * <h3>Rogers Music Center</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>SOMETHING E SOMETHING N</strong></p>

 * 
 * @author Brian Forbis
 */
public class Rogers extends Building{
	private double posEast = 7546930.978; //TODO Find position
	private double posNorth = 473005.738; //TODO Find position
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
		
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(height), Util.feetToGL(170+3./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(30), 0, Util.feetToGL(10));
		gl.glScaled(Util.feetToGL(178+6./12), Util.feetToGL(height), Util.feetToGL(10));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(20), 0, 0);
		gl.glScaled(Util.feetToGL(168+6./12), Util.feetToGL(height), Util.feetToGL(25));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(20), 0, -Util.feetToGL(25));
		gl.glScaled(Util.feetToGL(99), Util.feetToGL(height), Util.feetToGL(116+8./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(20), 0, -Util.feetToGL(141+8./12));
		gl.glScaled(Util.feetToGL(91), Util.feetToGL(height), Util.feetToGL(28+7./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(20), 0, -Util.feetToGL(170+3./12));
		gl.glScaled(Util.feetToGL(79), Util.feetToGL(height), Util.feetToGL(20));
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
