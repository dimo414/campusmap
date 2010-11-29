package buildings;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import util.Util;

/**
 * <h3>Mark O. Hatfield Library</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7546826.465E 472747.03N</strong></p>
 * 
 * <h4>Approximate Footprint</h4>
 * <p>187' on the west and north sides, at a right angle.
 * 155' on the east and south sides, meeting at an obtuse angle.
 * 
 * 
 * @author Michael Diamond
 */
public class Library extends Building {
	private double posEast = 7546826.465;
	private double posNorth = 472747.03;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	private GLUT glut = new GLUT();
	
	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);
	}

	@Override
	public void draw(GL gl) {//TODO find a better way to position the building at the origin or not
		gl.glPushMatrix();
		
		gl.glRotated(buildingRotation, 0, 1, 0);
		if(!drawOrigin)
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
		else
			gl.glTranslated(-Util.feetToGL(186.1/2), 0, -Util.feetToGL(186.1/2));
		
		gl.glScaled(Util.feetToGL(186.1), Util.feetToGL(40), Util.feetToGL(186.1));
		
		gl.glTranslated(.5,.5,.5);
		
		glut.glutSolidCube(1);
		
		gl.glPopMatrix();
	}

}
