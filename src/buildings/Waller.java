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
	private double[] midpoint = new double[]{Util.feetToGL(44)/2,Util.feetToGL(84.2)/2};
	
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
		gl.glTranslated(Util.feetToGL(12),Util.feetToGL(50),-Util.feetToGL(32,1));
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(25), Util.feetToGL(20));
		float[][] col = Shape.Cube.getColor();
		Shape.Cube.setColor(new float[]{1,1,1});
		Shape.Cube.draw(gl);
		Shape.Cube.setColor(col);
		gl.glPopMatrix();
		
		// tower head
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(12),Util.feetToGL(75),-Util.feetToGL(32,1));
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(10), Util.feetToGL(20));
		col = Shape.Pyramid.getColor();
		Shape.Pyramid.setColor(new float[]{1,1,1});
		Shape.Pyramid.draw(gl);
		Shape.Pyramid.setColor(col);
		gl.glColor3f(col[0][0],col[0][1],col[0][2]);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
