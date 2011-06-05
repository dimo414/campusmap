package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Collins Law Center
 * @author Jose Alvarado
 */
public class LawSchool extends Building{

	//1 - left building, 2 - middle, 3 - right
		
		private double posEast = 7546042.785;
		private double posNorth = 473270.915;
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
				gl.glTranslated(-(184.0/2), 0, 177.0/2);
			// End universal positioning
			
		//	gl.glRotated(90, 0, 1, 0);		
			
			Shape.Cube.setColor(Building.brick);
			// 1st Cube
			gl.glPushMatrix();
			gl.glScaled(125, 50, 78);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 2nd Cube
			gl.glPushMatrix();
			gl.glTranslated(23,0,-(78));
			gl.glScaled(76, 50, 144);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			// 3rd Cube
			gl.glPushMatrix();
			gl.glTranslated(23-16,0,-(78+144));
			gl.glScaled(122, 50, 79);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			
			
			gl.glPopMatrix();
		}

		@Override
		public void init(GL gl) {
			glPos = Util.coordToGL(posEast, posNorth, posElevation);
		}
		
}