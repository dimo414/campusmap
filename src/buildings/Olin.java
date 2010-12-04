package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Olin Hall
 * 
 * @author Jose Alvarado
 */
public class Olin extends Building{

	//1 - left cube of Olin, 2 - right cube of Olin
		
		private double posEast = 7546446.576;
		private double posNorth = 473514.619;
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
			
//			gl.glRotated(90, 0, 1, 0);
			
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(53+20), Util.feetToGL(50), Util.feetToGL(33));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(-Util.feetToGL(13),0,-Util.feetToGL(33));
			gl.glScaled(Util.feetToGL(81), Util.feetToGL(50), Util.feetToGL(122));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
		}
}
