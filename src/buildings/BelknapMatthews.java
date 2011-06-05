package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Belknap, Matthews, Tera, and Beta Theta Pi</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7547273.609E 472892.399N</strong></p>
 *  
 * @author Michael Diamond
 */
public class BelknapMatthews extends Building {
	private double posEast = 7547273.609;
	private double posNorth = 472892.399;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.f(207,2)/2,Util.f(39+155+31,2+8)/2};
	
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
		// belknap
		gl.glPushMatrix();
		gl.glScaled(Util.f(39,2), 30, Util.f(207,2));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// matthews
		gl.glPushMatrix();
		gl.glTranslated(Util.f(39,2),0,-(59.8));
		gl.glScaled(155, 40, Util.f(95,4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// tera/beta
		gl.glPushMatrix();
		gl.glTranslated(Util.f(39+155,2),0,0);
		gl.glScaled(Util.f(31,8), 30, Util.f(207,2));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}