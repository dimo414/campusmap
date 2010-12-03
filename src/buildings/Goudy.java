package buildings;

import javax.media.opengl.GL;

import util.Util;

import com.sun.opengl.util.GLUT;

/**
 * <h3>Arts</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>7546339.868E 473053.088N</strong></p>

 * 
 * @author Brian Forbis
 */
public class Goudy extends Building{
	private double posEast = 7546339.868; //TODO Confirm point
	private double posNorth = 473053.088; //TODO Confirm point
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
		
		gl.glPushMatrix(); //F1 //TODO Change to walls
		gl.glScaled(Util.feetToGL(6), Util.feetToGL(15), Util.feetToGL(40, 4));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();

		gl.glPushMatrix(); //F2 //TODO Change to walls
		gl.glTranslated(Util.feetToGL(6), 0, 0);
		gl.glScaled(Util.feetToGL(33, 4), Util.feetToGL(15), Util.feetToGL(43, 4));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3
		gl.glTranslated(Util.feetToGL(39, 4), 0, 0);
		gl.glScaled(Util.feetToGL(49, 4), Util.feetToGL(height), Util.feetToGL(60));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F4
		gl.glTranslated(Util.feetToGL(88, 8), 0, 0);
		gl.glScaled(Util.feetToGL(47, 8+3./8), Util.feetToGL(height), Util.feetToGL(61, 8));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F5
		gl.glTranslated(Util.feetToGL(113, 4+3./8), 0, -Util.feetToGL(61, 8));
		gl.glScaled(Util.feetToGL(23), Util.feetToGL(height), Util.feetToGL(9, 4+3./8));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F6
		gl.glTranslated(Util.feetToGL(116, 4+3./8), 0, -Util.feetToGL(71, 3./8));
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(height), Util.feetToGL(23, 11));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F7
		gl.glTranslated(Util.feetToGL(136, 4+3./8), 0, 0);
		gl.glScaled(Util.feetToGL(102, 3), Util.feetToGL(height), Util.feetToGL(97, 11+3./8));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F8
		gl.glTranslated(Util.feetToGL(238, 7+3./8), 0, Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(37, 11+5./8), Util.feetToGL(height), Util.feetToGL(103, 11+3./8));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F9
		gl.glTranslated(Util.feetToGL(276, 7), 0, -Util.feetToGL(27, 9));
		gl.glScaled(Util.feetToGL(3), Util.feetToGL(height), Util.feetToGL(42, 5+3./8));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		
		gl.glPushMatrix(); //Northerly Extrusions -F10-F11-F12-F13
		gl.glTranslated(Util.feetToGL(144, 3./8), 0, -Util.feetToGL(97, 11+3./8));
		for(int i = 0; i < 4; i++){
		gl.glPushMatrix();
		gl.glTranslated(i*Util.feetToGL(24, 11+6./8), 0, 0);
		gl.glScaled(Util.feetToGL(15, 11+3./8), Util.feetToGL(height), Util.feetToGL(3));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F14
		gl.glTranslated(Util.feetToGL(178, 8), 0, Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(18, 7.5), Util.feetToGL(height), Util.feetToGL(3));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F15
		gl.glTranslated(Util.feetToGL(209, 11.5), 0, Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(18, 7.5), Util.feetToGL(height), Util.feetToGL(3));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F16
		gl.glTranslated(Util.feetToGL(243, 11+3./8), 0, Util.feetToGL(6));
		gl.glScaled(Util.feetToGL(27, 3+5./8), Util.feetToGL(height), Util.feetToGL(3));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F17
		gl.glTranslated(Util.feetToGL(243, 11+3./8), 0, -Util.feetToGL(100, 11+3./8));
		gl.glScaled(Util.feetToGL(27, 3+5./8), Util.feetToGL(height), Util.feetToGL(3));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
//		gl.glPushMatrix(); //Kiln
//		gl.glTranslated(Util.feetToGL(144, 3./8), 0, -Util.feetToGL(97, 11+3./8));
//		gl.glScaled(Util.feetToGL(22), Util.feetToGL(height), Util.feetToGL(18+8./12));
//		gl.glTranslated(.5, .5, -.5);
//		glut.glutSolidCube(1);
//		gl.glPopMatrix();
//		
//		gl.glPushMatrix(); //Kiln
//		gl.glTranslated(Util.feetToGL(22), 0, -Util.feetToGL(18+3./12));
//		gl.glScaled(Util.feetToGL(22), Util.feetToGL(height), Util.feetToGL(18+8./12));
//		gl.glTranslated(.5, .5, -.5);
//		glut.glutSolidCube(1);
//		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);		
	}

}
