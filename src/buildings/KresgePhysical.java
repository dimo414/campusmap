package buildings;

import javax.media.opengl.GL;

import util.Util;

import com.sun.opengl.util.GLUT;

/**
 * <h3>Kresge Playhouse / Physical Plant</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>SOMETHING-E SOMETHING-N</strong></p>

 * 
 * @author Brian Forbis
 */
public class KresgePhysical extends Building{
	private double posEast = 7547083.302; // TODO Find position East
	private double posNorth = 472960.16; // TODO Find position North
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	private GLUT glut = new GLUT();
	
	private double pheight = 20;
	private double kheight = 40;
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
			gl.glTranslated(-Util.feetToGL(50), 0, Util.feetToGL(90));
		// End universal positioning
		
		//Start Drawing, SouthWest Positions
		
		//Physical Plant
		gl.glPushMatrix(); //MainBuilding
		gl.glScaled(Util.feetToGL(51), Util.feetToGL(pheight), Util.feetToGL(55));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //East Extrusion (staircase)
		gl.glTranslated(Util.feetToGL(51), 0, 0);
		gl.glScaled(Util.feetToGL(32), Util.feetToGL(pheight), Util.feetToGL(29));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glTranslated(0, 0, -Util.feetToGL(55));
		//Kresge Playhouse
		
		gl.glPushMatrix(); //Southern Portion
		gl.glScaled(Util.feetToGL(119), Util.feetToGL(kheight), Util.feetToGL(50+6./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Middle Portion (w/ Staircase)
		gl.glTranslated(0, 0, -Util.feetToGL(50+6./12));
		gl.glScaled(Util.feetToGL(130+8./12), Util.feetToGL(kheight), Util.feetToGL(37+4./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Northern Portion
		gl.glTranslated(0, 0, -Util.feetToGL(87+10./12));
		gl.glScaled(Util.feetToGL(120), Util.feetToGL(kheight), Util.feetToGL(21+2./12));
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
