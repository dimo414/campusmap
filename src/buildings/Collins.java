package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Collins Hall
 * 
 * @author Jose Alvarado
 */
public class Collins extends Building{

	//1 - bottom cube of collins, 2 - middle cube of collins, 3 - top cube of collins
			
		private double posEast = 7546646.193;
		private double posNorth = 473439.612;
		private double posElevation = 0; // TODO Get Elevation of building

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{40/2,40/2};
		}
		
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			
			gl.glRotated(90, 0, 1, 0);
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(48, 50, 28);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(-(37),0,-(28));
			gl.glScaled(47, 50, 58);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(48,0,46);
			gl.glScaled(59, 50, 142);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
					
			gl.glPopMatrix();
		}
}