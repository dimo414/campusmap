package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Sparks Center
 * 
 * Identifying Location - Southwest Corner
 * 7547086.037E 472598.103N
 * 
 * @author Jose Alvarado
 */
public class Sparks extends Building{

	//1 - left cube of sparks, 2 - top cube of sparks, 3 - right cube of sparks		
		private double posEast = 7547086.037;
		private double posNorth = 472598.103;
		private double posElevation = 0; // TODO Get Elevation of building

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{205/2,238/2};
		}
		
		@Override
		public void draw(GL gl) {
		gl.glPushMatrix();
		
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(217-27, 50, 95);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(217-27,0,-(0));
		gl.glScaled(27, 50, 171);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(-(14),0,-(95));
		gl.glScaled(87+122, 50, 89);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}