package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * WestSide - Lausanne, Doney, Campus Safety / ResLife
 * 
 * Identifying Location - Southeast Corner
 * 7546544.602E 473207.586N
 * 
 * @author Jose Alvarado
 */
public class WestSide extends Building{

	//1 - Laussane, 2 - Campus Safety, 3 - right cube of Doney

		private double posEast = 7546544.602;
		private double posNorth = 473207.586;
		private double posElevation = 0; // TODO Get Elevation of building

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{-205/2,238/2};
		}
		
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			//TODO When building proper model, consider making the southwest corner the canonical point
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(-40, 50, 161);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(-40,0,-(161-59));
			gl.glScaled(-76, 50, 59);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(-(40+76),0,184-161);
			gl.glScaled(-61, 50, 184);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}
	
}