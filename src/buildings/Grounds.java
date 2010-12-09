package buildings;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import util.Shape;
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
	private double[] midpoint = new double[]{Util.feetToGL(35,4)/2,Util.feetToGL(25+17,4)/2};
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
		
		Shape.Cube.setColor(Building.brick);
		Shape.Pyramid.setColor(tileRoof);
		Shape.UnitTriangle.setColor(tileRoof);
		// front - 25'4"x25'4"
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(25,4), Util.feetToGL(9), Util.feetToGL(25,4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// roof
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(1,6),Util.feetToGL(9),Util.feetToGL(1,6));
		gl.glScaled(Util.feetToGL(28,4), Util.feetToGL(5), Util.feetToGL(28,4));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		// side - 17'x15'4"
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(25,4),0,0);
		gl.glScaled(Util.feetToGL(17), Util.feetToGL(6,6), Util.feetToGL(15,4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// straight roof
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(19,4),Util.feetToGL(6,6),Util.feetToGL(1,6));
		gl.glScaled(-Util.feetToGL(17), Util.feetToGL(5), Util.feetToGL(18,4));
		gl.glRotated(90, 0, 1, 0);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		// pyramid roof
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(28,10),Util.feetToGL(6,6),Util.feetToGL(1,6));
		gl.glScaled(Util.feetToGL(15), Util.feetToGL(5), Util.feetToGL(18,4));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		// back - 10'x12'
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(30,4),0,Util.feetToGL(10));
		gl.glScaled(Util.feetToGL(12), Util.feetToGL(6,6), Util.feetToGL(10));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// straight roof
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(28,10),Util.feetToGL(6,6),Util.feetToGL(5));
		gl.glScaled(Util.feetToGL(15), Util.feetToGL(5), Util.feetToGL(12,8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		// pyramid roof
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(28,10),Util.feetToGL(6,6),Util.feetToGL(11,6));
		gl.glScaled(Util.feetToGL(15), Util.feetToGL(5), Util.feetToGL(13));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
