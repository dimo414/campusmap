package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Eaton Hall
 * 
 * @author Jose
 */
public class Eaton extends Building{

	//1 - bottom cube of eaton, 2 - middle cube of eaton, 3 - top cube of eaton
		
		private double posEast = 7547077.509;
		private double posNorth = 473321.981;
		private double posElevation = 0; // TODO Get Elevation of building
		private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
		private double[] midpoint = new double[]{Util.feetToGL(44+15+5+4)/2,Util.feetToGL(151/2)};
				
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
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(44), Util.feetToGL(50), Util.feetToGL(151));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(44),0,-Util.feetToGL(19));
			gl.glScaled(Util.feetToGL(15), Util.feetToGL(50), Util.feetToGL(151 - 19*2));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(44+15),0,-Util.feetToGL(19+32));
			gl.glScaled(Util.feetToGL(5), Util.feetToGL(50), Util.feetToGL(151-19*2-33*2));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(44+15+5),0,-Util.feetToGL(19+32+15));
			gl.glScaled(Util.feetToGL(4), Util.feetToGL(50), Util.feetToGL(17));
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
		}
}
