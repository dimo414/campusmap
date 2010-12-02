package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Waller Hall</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7546775.346E 473421.257N</strong></p>
 *  
 * @author Michael Diamond
 */
public class Waller extends Building {
	private double posEast = 7546775.346;
	private double posNorth = 473421.257;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.feetToGL(226,10)/2,Util.feetToGL(116,4)/2};
	
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
		gl.glScaled(Util.feetToGL(44), Util.feetToGL(50), Util.feetToGL(20,1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// center
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(20,1),0,-Util.feetToGL(20,1));
		gl.glScaled(Util.feetToGL(84,2), Util.feetToGL(50), Util.feetToGL(44));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();

		// north wing
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.feetToGL(64,1));
		gl.glScaled(Util.feetToGL(44), Util.feetToGL(50), Util.feetToGL(20,1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// tower
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(17),Util.feetToGL(50),-Util.feetToGL(37,1));
		gl.glScaled(Util.feetToGL(10), Util.feetToGL(20), Util.feetToGL(10));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
