package buildings;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import util.Util;

/**
 * <h3>Grounds Building</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7547137.071E 472887.578N</strong></p>
 *  
 * @author Michael Diamond
 */
public class Grounds extends Building {
	private double posEast = 7547137.071;
	private double posNorth = 472887.578;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.f(35,4)/2,Util.f(25+17,4)/2};
	private GLUT glut = new GLUT();
	
	@Override
	public void init(GL gl) {
	}

	@Override
	public void draw(GL gl) {//TODO find a better way to position the building at the origin or not
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
		
		// front - 25'4"x25'4"
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(25,4), Util.feetToGL(15), Util.feetToGL(25,4));
		gl.glTranslated(.5,.5,-.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		// side - 17'x15'4"
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(25,4),0,0);
		gl.glScaled(Util.feetToGL(17), Util.feetToGL(15), Util.feetToGL(15,4));
		gl.glTranslated(.5,.5,-.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
		
		// back - 10'x12'
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(30,4),0,Util.feetToGL(10));
		gl.glScaled(Util.feetToGL(12), Util.feetToGL(15), Util.feetToGL(10));
		gl.glTranslated(.5,.5,-.5);
		glut.glutSolidCube(1);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
