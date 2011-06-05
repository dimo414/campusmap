package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Shepard Hall
 * @author Jose Alvarado
 */
public class Shepard extends Building{

	//1 - left cube of Olin, 2 - right cube of Olin

		private double posEast = 7546121.405;//121
		private double posNorth = 472643.988;//759
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
				gl.glTranslated(-(92.0/2), 0, 155.0/2);
			// End universal positioning
			
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

		@Override
		public void init(GL gl) {
			glPos = Util.coordToGL(posEast, posNorth, posElevation);
		}
	
	
}