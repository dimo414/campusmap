package buildings;

import javax.media.opengl.GL;

import util.Shape;

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

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{205/2,238/2};
		}
				
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			
			gl.glRotated(90, 0, 1, 0);
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(109, 50, 29);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(0,0,-(29));
			gl.glScaled(63-36, 50, 20);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(0,0,-(49));
			gl.glScaled(63, 50, 49);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(63,0,-(49+11));
			gl.glScaled(126, 50, 60);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 5th Cube
			gl.glPushMatrix();
			gl.glTranslated(109+23,0,27);
			gl.glScaled(29, 50, 87);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}
}