package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * WISH Hall and Admissions office
 * @author Jose Alvarado
 */
public class WishandAdmission extends Building{

	//1 - left cube of Olin, 2 - right cube of Olin

		private double posEast = 7546608.18;
		private double posNorth = 472485.315;
		private double posElevation = 0; // TODO Get Elevation of building
		
		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast, posNorth, posElevation};
			midpoint = new double[]{92.0/2, 155.0/2};
		}
		
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			
			gl.glRotated(90, 0, 1, 0);
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(31, 50, 19);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(0,0,-(19));
			gl.glScaled(72, 50, 112);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();	
			
			gl.glPopMatrix();
		}
}