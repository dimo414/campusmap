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
	
	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{78/2,Util.f(110,7)/2};
	}

	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		Shape.Cube.setColor(Building.brick);
		// south wing
		gl.glPushMatrix();
		gl.glScaled(78, 30, 34);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// middle
		gl.glPushMatrix();
		gl.glTranslated(0,0,-(34));
		gl.glScaled(Util.f(78-36,0-5), 30, Util.f(41,6));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// north wing
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.f(34+41,6));
		gl.glScaled(78, 30, 36);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}