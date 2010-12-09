package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Alpha Chi Omega Sorority House
 * @author Jose Alvarado
 */
public class AlphaChi extends Building{

	//1 - left building, 2 - middle, 3 - right

		private double posEast = 7546481.18;
		private double posNorth = 472500.315;
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
				gl.glTranslated(-Util.feetToGL(184.0/2), 0, Util.feetToGL(177.0/2));
			// End universal positioning
			
			gl.glRotated(90, 0, 1, 0);		
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(21), Util.feetToGL(50), Util.feetToGL(121));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(21),0,-Util.feetToGL(121-76));
			gl.glScaled(Util.feetToGL(65), Util.feetToGL(50), Util.feetToGL(73));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(21+65),0,-Util.feetToGL(121-85));
			gl.glScaled(Util.feetToGL(33), Util.feetToGL(50), Util.feetToGL(21));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();	
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(21+65),0,-Util.feetToGL(121-30));
			gl.glScaled(Util.feetToGL(26), Util.feetToGL(50), Util.feetToGL(40));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
			glPos = Util.coordToGL(posEast, posNorth, posElevation);
		}
	
}
