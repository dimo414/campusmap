package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Smith Fine Arts Building</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7546638.852E 473065.593N</strong></p>
 *  
 * @author Michael Diamond
 */
public class Smith extends Building {
	private double posEast = 7546638.852;
	private double posNorth = 473065.593;
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
		
		Shape.Cube.setColor(Building.brick);
		// main
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(177,10), Util.feetToGL(50), Util.feetToGL(82,6));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAW
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.feetToGL(82,6));
		gl.glScaled(Util.feetToGL(64,4), Util.feetToGL(50), Util.feetToGL(33,10));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();

		// FAE back
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(177,10),0,-Util.feetToGL(19,8));
		gl.glScaled(Util.feetToGL(46,6), Util.feetToGL(50), Util.feetToGL(62,8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAE front
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(160),0,-Util.feetToGL(82,6));
		gl.glScaled(Util.feetToGL(64,4), Util.feetToGL(50), Util.feetToGL(16,3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAE front east
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(200,2),0,-Util.feetToGL(98,9));
		gl.glScaled(Util.feetToGL(24,2), Util.feetToGL(50), Util.feetToGL(17,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAE front west
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(160),0,-Util.feetToGL(98,9));
		gl.glScaled(Util.feetToGL(24,2), Util.feetToGL(50), Util.feetToGL(17,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
