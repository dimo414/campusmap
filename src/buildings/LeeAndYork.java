package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Lee and York Halls
 * 
 * @author Jose Alvarado
 */
public class LeeAndYork extends Building{

	//1 - top left cube of Lee, 2 - top right cube, 3 - bottom left, 4 - bottom cube, 5 - bottom right cube
		
		private double posEast = 7546282.605;
		private double posNorth = 472812.244;
		private double posElevation = 0; // TODO Get Elevation of building
		private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
		private double[] midpoint = new double[]{Util.feetToGL(205/2),Util.feetToGL(238/2)};
				
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
			
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(109), Util.feetToGL(50), Util.feetToGL(29));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0),0,-Util.feetToGL(29));
			gl.glScaled(Util.feetToGL(63-36), Util.feetToGL(50), Util.feetToGL(20));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0),0,-Util.feetToGL(49));
			gl.glScaled(Util.feetToGL(63), Util.feetToGL(50), Util.feetToGL(49));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(63),0,-Util.feetToGL(49+11));
			gl.glScaled(Util.feetToGL(126), Util.feetToGL(50), Util.feetToGL(60));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 5th Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(109+23),0,Util.feetToGL(27));
			gl.glScaled(Util.feetToGL(29), Util.feetToGL(50), Util.feetToGL(87));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
		}
}
