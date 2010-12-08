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
		
		// east-west roof
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(23,3),Util.feetToGL(50),-Util.feetToGL(67,3));
		gl.glScaled(Util.feetToGL(90,6), Util.feetToGL(15), Util.feetToGL(50,4));
		gl.glRotated(-90, 0, 1, 0);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		// north-south roof
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(3,2),Util.feetToGL(50),Util.feetToGL(3,2));
		gl.glScaled(Util.feetToGL(50,4), Util.feetToGL(15), Util.feetToGL(90,6));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		// cupola - porch
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(11,9),Util.feetToGL(50),-Util.feetToGL(31,10));
		gl.glScaled(Util.feetToGL(20,6), Util.feetToGL(15), Util.feetToGL(20,6));
		Shape.Octagon.draw(gl);
		gl.glPopMatrix();
		
		// cupola - base of tower
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(16),Util.feetToGL(65),-Util.feetToGL(36,1));
		gl.glScaled(Util.feetToGL(12), Util.feetToGL(10), Util.feetToGL(12));
		Shape.Octagon.setColor(Building.white);
		Shape.Octagon.draw(gl);
		Shape.Octagon.setColor(Building.brick);
		gl.glPopMatrix();
		
		// cupola head
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(15),Util.feetToGL(75),-Util.feetToGL(35,1));
		gl.glScaled(Util.feetToGL(14), Util.feetToGL(5), Util.feetToGL(14));
		Shape.OctaPyramid.setColor(Building.white);
		Shape.OctaPyramid.draw(gl);
		Shape.OctaPyramid.setColor(Building.brick);
		gl.glColor3f(Building.brick[0],Building.brick[1],Building.brick[2]);
		gl.glPopMatrix();
		
		// spacer between stairs and hall proper
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(12,6), 0, Util.feetToGL(1,5));
		gl.glScaled(Util.feetToGL(4,6),Util.feetToGL(12),Util.feetToGL(1,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(27), 0, Util.feetToGL(1,5));
		gl.glScaled(Util.feetToGL(4,6),Util.feetToGL(12),Util.feetToGL(1,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// west half of south stairs
		// I cannot figure out why, but by my math these components are 3'8" and 11'1" left of origin
		// however visually it works at 2'3"
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(2,3), 0, Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(19,3), Util.feetToGL(6), Util.feetToGL(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(9,8), 0, Util.feetToGL(18));
		gl.glScaled(Util.feetToGL(7,5), Util.feetToGL(6), Util.feetToGL(16,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(2,3), 0, Util.feetToGL(18));
		gl.glScaled(Util.feetToGL(10,10), Util.feetToGL(6), Util.feetToGL(8));
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Util.feetToGL(2,3), Util.feetToGL(6), Util.feetToGL(9,5));
		gl.glScaled(Util.feetToGL(10,10), Util.feetToGL(6), Util.feetToGL(8));
		gl.glTranslated(1, 0, 0);
		gl.glScaled(-1,1,1);
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		
		// east half of south stairs
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(27), 0, Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(19,3), Util.feetToGL(6), Util.feetToGL(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(46,3), 0, Util.feetToGL(18));
		gl.glScaled(Util.feetToGL(7,5), Util.feetToGL(6), Util.feetToGL(16,7));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(35,5), 0, Util.feetToGL(18));
		gl.glScaled(Util.feetToGL(10,10), Util.feetToGL(6), Util.feetToGL(8));
		gl.glTranslated(1, 0, 0);
		gl.glScaled(-1,1,1);
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(35,5), Util.feetToGL(6), Util.feetToGL(9,5));
		gl.glScaled(Util.feetToGL(10,10), Util.feetToGL(6), Util.feetToGL(8));
		Shape.RightTriangle.draw(gl);
		gl.glPopMatrix();
		
		// south porch
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(8,7), Util.feetToGL(6), Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(8,5), Util.feetToGL(6), Util.feetToGL(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(27), Util.feetToGL(6), Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(8,5), Util.feetToGL(6), Util.feetToGL(9,5));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(17), Util.feetToGL(10), Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(10), Util.feetToGL(2), Util.feetToGL(10,10));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// south overhang
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(13,2), Util.feetToGL(22), Util.feetToGL(13));
		gl.glScaled(Util.feetToGL(17,10), Util.feetToGL(4), Util.feetToGL(13));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(13,8), Util.feetToGL(12), Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(1,8), Util.feetToGL(10), Util.feetToGL(1,8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(28,10), Util.feetToGL(12), Util.feetToGL(10,10));
		gl.glScaled(Util.feetToGL(1,8), Util.feetToGL(10), Util.feetToGL(1,8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}
