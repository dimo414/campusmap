package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * WestSide - Lausanne, Doney, Campus Safety / ResLife
 * 
 * @author Jose Alvarado
 */
public class WestSide extends Building{

	//1 - Laussane, 2 - Campus Safety, 3 - right cube of Doney

		private double posEast = 7546544.602;
		private double posNorth = 473207.586;
		private double posElevation = 0; // TODO Get Elevation of building
		private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
		private double[] midpoint = new double[]{205/2,238/2};
		
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
			gl.glScaled(161, 50, 40);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(161-59,0,-(40));
			gl.glScaled(59, 50, 76);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(-(184-161),0,-(40+76));
			gl.glScaled(184, 50, 61);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
		}
	
}