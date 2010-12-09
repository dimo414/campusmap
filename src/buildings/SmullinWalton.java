package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Smullin / Walton Hall</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>7547138.978E 473205.738N</strong></p>

 * 
 * @author Brian Forbis
 */
public class SmullinWalton extends Building{
	private double posEast = 7547138.978;
	private double posNorth = 473205.738;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	
	private double height = 20;
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
			gl.glTranslated(-Util.feetToGL(30), 0, Util.feetToGL(30));
		// End universal positioning
		
		//Start Drawing, SouthWest Positions
		
		Shape.Cube.setColor(Building.brick);
		gl.glPushMatrix(); //South extrusion
		gl.glTranslated(Util.feetToGL(37+3./12), 0, Util.feetToGL(8+9./12));
		gl.glScaled(Util.feetToGL(65+1./12), Util.feetToGL(height), Util.feetToGL(8+9./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Walton Hall
		gl.glScaled(Util.feetToGL(139+7./12), Util.feetToGL(height), Util.feetToGL(36+7./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Middle Section
		gl.glTranslated(Util.feetToGL(29+11./12), 0, -Util.feetToGL(36+7./12));
		gl.glScaled(Util.feetToGL(59+4./12), Util.feetToGL(height), Util.feetToGL(62+2./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Bike Shed
		gl.glTranslated(Util.feetToGL(89+3./12), 0, -Util.feetToGL(84+7./12));
		gl.glScaled(Util.feetToGL(21+2./12), Util.feetToGL(height), Util.feetToGL(14+2./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Smullin Hall
		gl.glTranslated(-Util.feetToGL(9+8./12), 0, -Util.feetToGL(98+9./12));
		gl.glScaled(Util.feetToGL(166), Util.feetToGL(height), Util.feetToGL(35+6./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //North Extrusion
		gl.glTranslated(Util.feetToGL(26+2./12), 0, -Util.feetToGL(134+3./12));
		gl.glScaled(Util.feetToGL(94+4./12), Util.feetToGL(height), Util.feetToGL(18+6./12));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);		
	}

}
