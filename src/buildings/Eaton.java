package buildings;

import javax.media.opengl.GL;

import util.Shape;

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

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{44+15+5+4/2,151/2};
		}
				
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			
			gl.glRotated(90, 0, 1, 0);
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(44, 50, 151);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(44,0,-(19));
			gl.glScaled(15, 50, 151 - 19*2);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(44+15,0,-(19+32));
			gl.glScaled(5, 50, 151-19*2-33*2);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(44+15+5,0,-(19+32+15));
			gl.glScaled(4, 50, 17);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}
}