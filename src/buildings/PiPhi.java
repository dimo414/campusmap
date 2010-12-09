package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Pi Beta Phi Sorority House
 * @author Jose Alvarado
 */
public class PiPhi extends Building{

	//1 - bottom cube of eaton, 2 - middle cube of eaton, 3 - top cube of eato
		
		private double posEast = 7546240.405;
		private double posNorth = 472590.988;
		private double posElevation = 0; // TODO Get Elevation of building
		private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
				
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
				gl.glTranslated(-Util.feetToGL(68.0/2), 0, Util.feetToGL(151.0/2));
			// End universal positioning
			
			
			gl.glRotated(90, 0, 1, 0);
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(32), Util.feetToGL(50), Util.feetToGL(30));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(21),0,-Util.feetToGL(30));
			gl.glScaled(Util.feetToGL(97), Util.feetToGL(50), Util.feetToGL(53));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(21-9),0,-Util.feetToGL(30));
			gl.glScaled(Util.feetToGL(9), Util.feetToGL(50), Util.feetToGL(15));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();	
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(-Util.feetToGL(4),0,-Util.feetToGL(30+15));
			gl.glScaled(Util.feetToGL(25), Util.feetToGL(50), Util.feetToGL(31));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
			glPos = Util.coordToGL(posEast, posNorth, posElevation);
		}
	
}
