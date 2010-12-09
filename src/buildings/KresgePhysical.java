package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Kresge Playhouse / Physical Plant</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p>This is a guess - a better reference point would be the corner of Rogers.  
 * <strong>7547046.11E 472980.9N</strong></p>
 * 
 * @author Brian Forbis
 */
public class KresgePhysical extends Building{
	private double posEast = 7547046.11;
	private double posNorth = 472980.9;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	
	private double pheight = 16;
	private double kheight = 29;
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		// Universal positioning
		if(!drawOrigin){
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
			gl.glRotated(buildingRotation, 0, 1, 0);
		}
		else //TODO Find Centerpoint
			// this is the appx centerpoint of the building
			gl.glTranslated(-Util.feetToGL(50), 0, Util.feetToGL(90));
		// End universal positioning
		
		//Start Drawing, SouthWest Positions
		
		Shape.Cube.setColor(Building.brick);
		//Physical Plant
		gl.glPushMatrix(); //MainBuilding
		gl.glScaled(Util.feetToGL(51), Util.feetToGL(pheight), Util.feetToGL(55));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //East Extrusion (staircase)
		gl.glTranslated(Util.feetToGL(51), 0, 0);
		gl.glScaled(Util.feetToGL(32), Util.feetToGL(pheight), Util.feetToGL(29));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glTranslated(0, 0, -Util.feetToGL(55));
		//Kresge Playhouse
		
		gl.glPushMatrix(); //Southern Portion
		gl.glScaled(Util.feetToGL(119), Util.feetToGL(kheight), Util.feetToGL(50+6./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Middle Portion (w/ Staircase)
		gl.glTranslated(0, 0, -Util.feetToGL(50+6./12));
		gl.glScaled(Util.feetToGL(130+8./12), Util.feetToGL(kheight), Util.feetToGL(37+4./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Northern Portion
		gl.glTranslated(0, 0, -Util.feetToGL(87+10./12));
		gl.glScaled(Util.feetToGL(120), Util.feetToGL(kheight), Util.feetToGL(21+2./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);		
	}

}
