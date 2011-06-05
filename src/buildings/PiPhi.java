package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Pi Beta Phi Sorority House
 * 
 * Identifying Location - Southeast Corner
 * 7546240.405E 472590.988N
 * 
 * @author Jose Alvarado
 */
public class PiPhi extends Building{
		private double posEast = 7546240.405;
		private double posNorth = 472590.988;
		private double posElevation = 0; // TODO Get Elevation of building

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{68.0/2, 151.0/2};
		}
				
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			//TODO When building proper model, consider making the southwest corner the canonical point
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(-30, 50, 32);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(-30,0,-21);
			gl.glScaled(-53, 50, 97);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(-30,0,-(21-9));
			gl.glScaled(-15, 50, 9);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();	
			
			// 4th Cube
			gl.glPushMatrix();
			gl.glTranslated(-(30+15),0,4);
			gl.glScaled(-31, 50, 25);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}
	
}