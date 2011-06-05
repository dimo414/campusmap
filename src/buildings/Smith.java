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
	private double[] midpoint = new double[]{Util.f(226,10)/2,Util.f(116,4)/2};
	
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
		gl.glScaled(Util.f(177,10), 50, Util.f(82,6));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAW
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.f(82,6));
		gl.glScaled(Util.f(64,4), 50, Util.f(33,10));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();

		// FAE back
		gl.glPushMatrix();
		gl.glTranslated(Util.f(177,10),0,-Util.f(19,8));
		gl.glScaled(Util.f(46,6), 50, Util.f(62,8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAE front
		gl.glPushMatrix();
		gl.glTranslated(160,0,-Util.f(82,6));
		gl.glScaled(Util.f(64,4), 50, Util.f(16,3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAE front east
		gl.glPushMatrix();
		gl.glTranslated(Util.f(200,2),0,-Util.f(98,9));
		gl.glScaled(Util.f(24,2), 50, Util.f(17,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// FAE front west
		gl.glPushMatrix();
		gl.glTranslated(160,0,-Util.f(98,9));
		gl.glScaled(Util.f(24,2), 50, Util.f(17,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}