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
				gl.glTranslated(-(68.0/2), 0, 151.0/2);
			// End universal positioning
			
			
			gl.glRotated(90, 0, 1, 0);
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(32, 50, 30);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(21,0,-(30));
			gl.glScaled(97, 50, 53);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(21-9,0,-(30));
			gl.glScaled(9, 50, 15);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();	
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(-(4),0,-(30+15));
			gl.glScaled(25, 50, 31);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
			glPos = Util.coordToGL(posEast, posNorth, posElevation);
		}
	
}