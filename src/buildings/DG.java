package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Delta Gamma House</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7546257.405E 472563.988N</strong></p>
 *  
 * @author Michael Diamond
 */
public class DG extends Building {
	private double posEast = 7546257.405;
	private double posNorth = 472563.988;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.feetToGL(78)/2,Util.feetToGL(110,7)/2};
	
	@Override
	public void init(GL gl) {
	}

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
		
		// south wing
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(78), Util.feetToGL(30), Util.feetToGL(34));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// middle
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.feetToGL(34));
		gl.glScaled(Util.feetToGL(78-36,0-5), Util.feetToGL(30), Util.feetToGL(41,6));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// north wing
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.feetToGL(34+41,6));
		gl.glScaled(Util.feetToGL(78), Util.feetToGL(30), Util.feetToGL(36));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
