package buildings;

import javax.media.opengl.GL;

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
	
	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{Util.f(35,4)/2,Util.f(25+17,4)/2};
	}

	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		Shape.Cube.setColor(Building.brick);
		Shape.Pyramid.setColor(tileRoof);
		Shape.UnitTriangle.setColor(tileRoof);
		// front - 25'4"x25'4"
		gl.glPushMatrix();
		gl.glScaled(Util.f(25,4), 9, Util.f(25,4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// roof
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(1,6),9,Util.f(1,6));
		gl.glScaled(Util.f(28,4), 5, Util.f(28,4));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		// side - 17'x15'4"
		gl.glPushMatrix();
		gl.glTranslated(Util.f(25,4),0,0);
		gl.glScaled(17, Util.f(6,6), Util.f(15,4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// straight roof
		gl.glPushMatrix();
		gl.glTranslated(Util.f(19,4),Util.f(6,6),Util.f(1,6));
		gl.glScaled(-(17), 5, Util.f(18,4));
		gl.glRotated(90, 0, 1, 0);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		// pyramid roof
		gl.glPushMatrix();
		gl.glTranslated(Util.f(28,10),Util.f(6,6),Util.f(1,6));
		gl.glScaled(15, 5, Util.f(18,4));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		// back - 10'x12'
		gl.glPushMatrix();
		gl.glTranslated(Util.f(30,4),0,10);
		gl.glScaled(12, Util.f(6,6), 10);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// straight roof
		gl.glPushMatrix();
		gl.glTranslated(Util.f(28,10),Util.f(6,6),5);
		gl.glScaled(15, 5, Util.f(12,8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		// pyramid roof
		gl.glPushMatrix();
		gl.glTranslated(Util.f(28,10),Util.f(6,6),Util.f(11,6));
		gl.glScaled(15, 5, 13);
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}