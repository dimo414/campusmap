package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Ford Hall</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p>Total guess at this point.  Need to redo: <strong>7547364.645E 473365.148N</strong></p>
 *  
 * @author Michael Diamond
 */
public class Ford extends Building {
	private double posEast = 7547364.645; // TODO improve this
	private double posNorth = 473365.148; // TODO improve this
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.feetToGL(73),Util.feetToGL(97,5)/2};
	
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
				
		Shape.Cube.setColor(Building.brick);
		// short part of west wing
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(5),0,-Util.feetToGL(24,6));
		gl.glScaled(Util.feetToGL(5), Util.feetToGL(40), Util.feetToGL(76,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// most of west wing
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(30,8), Util.feetToGL(40), Util.feetToGL(100,11));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// rest of building
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(30,8),0,-Util.feetToGL(41,5));
		gl.glScaled(Util.feetToGL(59+58,10), Util.feetToGL(40), Util.feetToGL(59,6));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// theater
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(30+59,8+10),0,-Util.feetToGL(3,6));
		gl.glScaled(Util.feetToGL(58), Util.feetToGL(18), Util.feetToGL(37,11));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
