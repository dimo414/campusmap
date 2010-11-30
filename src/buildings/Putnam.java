package buildings;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import util.Util;

/**
 * <h3>Putnam University Center</h3>
 * 
 * <h4>Identifying Location - Southwest Corner of Balcony</h4>
 * <p><strong>7546493.302-E 472808.16-N</strong></p>
 * 
 * <h4>Approximate Footprint</h4>
 * <p>95'7" on the west and east side.
 * 167'7" on the north and south side.
 * 2nd story balcony has width of 12'11" except 23'2" over bookstore and bistro.
 * Roof overhang is 13'4.5".
 * </p>
 * 
 * @author Brian Forbis
 */
public class Putnam extends Building {
	private double posEast = 7546493.302;
	private double posNorth = 472808.16;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	private GLUT glut = new GLUT();
	
	
	private double balcony = 12+11./12;
	private double roofOverhang = 13+4.5/12;
	private double width = 167+7./12;
	private double length = 95+7./12;
	
	private double height = 20;
	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);
	}

	@Override
	public void draw(GL gl) {//TODO find a better way to position the building at the origin or not
		gl.glPushMatrix();
		
		// Universal positioning
		if(!drawOrigin){
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
			gl.glRotated(buildingRotation, 0, 1, 0);
		}
		else
			// this is the appx centerpoint of the building
			gl.glTranslated(-Util.feetToGL(width/2), 0, Util.feetToGL(length/2));
		// End universal positioning
		gl.glTranslated(Util.feetToGL(balcony), 0, -Util.feetToGL(balcony));
		//First Floor
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(Util.feetToGL(width), Util.feetToGL(height), Util.feetToGL(length));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //left square
		gl.glTranslated(0, 0, -Util.feetToGL(length));
		gl.glScaled(Util.feetToGL(47+9./12), Util.feetToGL(height), Util.feetToGL(22+1./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //right square
		gl.glTranslated(Util.feetToGL(71+10./12), 0, -Util.feetToGL(95+7./12));
		gl.glScaled(Util.feetToGL(95+9./12), Util.feetToGL(height), Util.feetToGL(22+1./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //staircase
		gl.glTranslated(Util.feetToGL(167+7./12), 0, -Util.feetToGL(72));
		gl.glScaled(Util.feetToGL(12), Util.feetToGL(height), Util.feetToGL(35+8./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		//Second Floor
		gl.glTranslated(0, Util.feetToGL(height), 0);
		
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(Util.feetToGL(width), Util.feetToGL(height), Util.feetToGL(length));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		double b = 1; //Balcony Width
		//Balconies
		gl.glPushMatrix(); //Balcony 3
		gl.glTranslated(-Util.feetToGL(balcony), -Util.feetToGL(b), Util.feetToGL(balcony));
		gl.glScaled(Util.feetToGL(193+5./12), Util.feetToGL(b), Util.feetToGL(balcony));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 1
		gl.glTranslated(0, -Util.feetToGL(b), -Util.feetToGL(length));
		gl.glScaled(Util.feetToGL(50), Util.feetToGL(b), Util.feetToGL(23+2./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 2
		gl.glTranslated(-Util.feetToGL(balcony), -Util.feetToGL(b), 0);
		gl.glScaled(Util.feetToGL(balcony), Util.feetToGL(b), Util.feetToGL(108+6./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 4
		gl.glTranslated(Util.feetToGL(width), -Util.feetToGL(b), 0);
		gl.glScaled(Util.feetToGL(balcony), Util.feetToGL(b), Util.feetToGL(108+6./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 5
		gl.glTranslated(Util.feetToGL(72), -Util.feetToGL(b), -Util.feetToGL(length));
		gl.glScaled(Util.feetToGL(98), Util.feetToGL(b), Util.feetToGL(23+2./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 6
		gl.glTranslated(Util.feetToGL(50), -Util.feetToGL(b), -Util.feetToGL(length));
		gl.glScaled(Util.feetToGL(22), Util.feetToGL(b), Util.feetToGL(balcony));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		//3rd Floor
		gl.glTranslated(0, Util.feetToGL(height), 0);
		
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(Util.feetToGL(width), Util.feetToGL(height), Util.feetToGL(length));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		gl.glPushMatrix();//Roof
		gl.glTranslated(-Util.feetToGL(roofOverhang), Util.feetToGL(height-b), Util.feetToGL(roofOverhang));
		gl.glScaled(Util.feetToGL(194+4./12), Util.feetToGL(b), Util.feetToGL(122+4./12));
		gl.glTranslated(.5, .5, -.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
//		// main building
//		gl.glPushMatrix();
//		gl.glScaled(Util.feetToGL(186.1), Util.feetToGL(40), Util.feetToGL(186.1));
//		gl.glTranslated(.5,.5,-.5);
//		glut.glutSolidCube(1);
//		gl.glPopMatrix();
		
//		// north outcrop - this position has not been measured yet - numbers are guesses
//		gl.glPushMatrix();
//		gl.glTranslated(Util.feetToGL(60),0,-Util.feetToGL(186.1));
//		gl.glRotated(45, 0, 1, 0);
//		gl.glScaled(Util.feetToGL(30), Util.feetToGL(40), Util.feetToGL(30));
//		gl.glTranslated(0, .5, 0);
//		glut.glutSolidCube(1);
//		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}
