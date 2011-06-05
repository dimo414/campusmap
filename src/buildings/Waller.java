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
	private double[] midpoint = new double[]{44/2,84.2/2};
	
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
		Shape.RightTriangle.setColor(Building.brick);
		Shape.UnitTriangle.setColor(Building.brickRoofTriangle);
		// south wing
		gl.glPushMatrix();
		gl.glScaled(44, 50, Util.f(20,1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// center
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(20,1),0,-Util.f(20,1));
		gl.glScaled(Util.f(84,2), 50, 44);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();

		// north wing
		gl.glPushMatrix();
		gl.glTranslated(0,0,-Util.f(64,1));
		gl.glScaled(44, 50, Util.f(20,1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// east-west roof
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(23,3),50,-Util.f(67,3));
		gl.glScaled(Util.f(90,6), 15, Util.f(50,4));
		gl.glRotated(-90, 0, 1, 0);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		// north-south roof
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(3,2),50,Util.f(3,2));
		gl.glScaled(Util.f(50,4), 15, Util.f(90,6));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		// cupola - porch
		gl.glPushMatrix();
		gl.glTranslated(Util.f(11,9),50,-Util.f(31,10));
		gl.glScaled(Util.f(20,6), 15, Util.f(20,6));
		Shape.Octagon.setColor(Building.tileRoof);
		Shape.Octagon.draw(gl);
		gl.glPopMatrix();
		
		// cupola - base of tower
		gl.glPushMatrix();
		gl.glTranslated(16,65,-Util.f(36,1));
		gl.glScaled(12, 10, 12);
		Shape.Octagon.setColor(Building.white);
		Shape.Octagon.draw(gl);
		gl.glPopMatrix();
		
		// cupola head
		gl.glPushMatrix();
		gl.glTranslated(15,75,-Util.f(35,1));
		gl.glScaled(14, 5, 14);
		Shape.OctaPyramid.setColor(Building.white);
		Shape.OctaPyramid.draw(gl);
		gl.glPopMatrix();
		
		// spacer between stairs and hall proper
		gl.glPushMatrix();
		gl.glTranslated(Util.f(12,6), 0, Util.f(1,5));
		gl.glScaled(Util.f(4,6),12,Util.f(1,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(27, 0, Util.f(1,5));
		gl.glScaled(Util.f(4,6),12,Util.f(1,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// west half of south stairs
		// I cannot figure out why, but by my math these components are 3'8" and 11'1" left of origin
		// however visually it works at 2'3"
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(2,3), 0, Util.f(10,10));
		gl.glScaled(Util.f(19,3), 6, Util.f(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(9,8), 0, 18);
		gl.glScaled(Util.f(7,5), 6, Util.f(16,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(2,3), 0, 18);
		gl.glScaled(Util.f(10,10), 6, 8);
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Util.f(2,3), 6, Util.f(9,5));
		gl.glScaled(Util.f(10,10), 6, 8);
		gl.glTranslated(1, 0, 0);
		gl.glScaled(-1,1,1);
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		
		// east half of south stairs
		gl.glPushMatrix();
		gl.glTranslated(27, 0, Util.f(10,10));
		gl.glScaled(Util.f(19,3), 6, Util.f(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(46,3), 0, 18);
		gl.glScaled(Util.f(7,5), 6, Util.f(16,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(35,5), 0, 18);
		gl.glScaled(Util.f(10,10), 6, 8);
		gl.glTranslated(1, 0, 0);
		gl.glScaled(-1,1,1);
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(35,5), 6, Util.f(9,5));
		gl.glScaled(Util.f(10,10), 6, 8);
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		
		// south porch
		gl.glPushMatrix();
		gl.glTranslated(Util.f(8,7), 6, Util.f(10,10));
		gl.glScaled(Util.f(8,5), 6, Util.f(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(27, 6, Util.f(10,10));
		gl.glScaled(Util.f(8,5), 6, Util.f(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(17, 10, Util.f(10,10));
		gl.glScaled(10, 2, Util.f(10,10));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// south overhang
		gl.glPushMatrix();
		gl.glTranslated(Util.f(13,2), 22, 13);
		gl.glScaled(Util.f(17,10), 4, 13);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(13,8), 12, Util.f(10,10));
		gl.glScaled(Util.f(1,8), 10, Util.f(1,8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(28,10), 12, Util.f(10,10));
		gl.glScaled(Util.f(1,8), 10, Util.f(1,8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}