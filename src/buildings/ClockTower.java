package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Ford Hall</h3>
 * 
 * <h4>Identifying Location - Relative to Southwest Corner of Library</h4>
 * <p><strong>7546826.465E" 472747.03N"</strong></p>
 *  
 * @author Michael Diamond
 */
public class ClockTower extends Building {
	private double posEast = 7546826.465;
	private double posNorth = 472747.03;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.feetToGL(11,4)/2,Util.feetToGL(11,4)/2};
	
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
			// move to location relative to library
			gl.glTranslated(-Util.feetToGL(27+11,2+4),0,-Util.feetToGL(116-21,4-9));
		}
		else
			// this is the appx centerpoint of the building
			gl.glTranslated(-midpoint[0], 0, midpoint[1]);
		// End universal positioning

		// SW Leg
		gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(43), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0,8),0,-Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(8,6), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// S Face
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0,8),Util.feetToGL(8,6),-Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(10),Util.feetToGL(3,6),Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(0,Util.feetToGL(12),0);
			gl.glScaled(Util.feetToGL(11,4),Util.feetToGL(3),Util.feetToGL(2,11.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(2,3.5),Util.feetToGL(15),-Util.feetToGL(0,6));
			gl.glScaled(Util.feetToGL(6,8.5),Util.feetToGL(26),Util.feetToGL(0,1));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
			Shape.Cube.setColor(Building.brick);
		gl.glPopMatrix();
		// W Face
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0,8),Util.feetToGL(8,6),-Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(2,3.5),Util.feetToGL(3,6),Util.feetToGL(10));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(0,Util.feetToGL(12),0);
			gl.glScaled(Util.feetToGL(2,11.5),Util.feetToGL(3),Util.feetToGL(11,4));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0,6),Util.feetToGL(15),-Util.feetToGL(2,3.5));
			gl.glScaled(Util.feetToGL(0,1),Util.feetToGL(26),Util.feetToGL(6,8.5));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
			Shape.Cube.setColor(Building.brick);
		gl.glPopMatrix();
		
		// NW Leg
		gl.glTranslated(0, 0, -Util.feetToGL(9,.5));
		gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(43), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0,8),0,Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(8,6), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// N Face
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(0,8),Util.feetToGL(8,6),Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(10),Util.feetToGL(3,6),Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(0,Util.feetToGL(12),Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(11,4),Util.feetToGL(3),Util.feetToGL(2,11.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(2,3.5),Util.feetToGL(15),-Util.feetToGL(1,8.5));
			gl.glScaled(Util.feetToGL(6,8.5),Util.feetToGL(26),Util.feetToGL(0,1));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
			Shape.Cube.setColor(Building.brick);
		gl.glPopMatrix();
		
		// NW Leg
		gl.glTranslated(Util.feetToGL(9,.5),0,0);
		gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(43), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(-Util.feetToGL(0,8),0,Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(8,6), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// SE Leg
		gl.glTranslated(0, 0, Util.feetToGL(9,.5));
		gl.glPushMatrix();
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(43), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(-Util.feetToGL(0,8),0,-Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(2,3.5), Util.feetToGL(8,6), Util.feetToGL(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// W Face
		gl.glPushMatrix();
			gl.glTranslated(-Util.feetToGL(0,8),Util.feetToGL(8,6),-Util.feetToGL(0,8));
			gl.glScaled(Util.feetToGL(2,3.5),Util.feetToGL(3,6),Util.feetToGL(10));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(-Util.feetToGL(0,8),Util.feetToGL(12),0);
			gl.glScaled(Util.feetToGL(2,11.5),Util.feetToGL(3),Util.feetToGL(11,4));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(1,8.5),Util.feetToGL(15),-Util.feetToGL(2,3.5));
			gl.glScaled(Util.feetToGL(0,1),Util.feetToGL(26),Util.feetToGL(6,8.5));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
			Shape.Cube.setColor(Building.brick);
		gl.glPopMatrix();
	
		gl.glTranslated(-Util.feetToGL(9,.5),0,0);
				
		// Top brick - the height of this is a guess
		gl.glPushMatrix();
			gl.glTranslated(0, Util.feetToGL(41), 0);
			gl.glScaled(Util.feetToGL(11,4), Util.feetToGL(2), Util.feetToGL(11,4));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();

		// Peak - the height of this is a guess
		gl.glPushMatrix();
			gl.glTranslated(0, Util.feetToGL(43), 0);
			gl.glScaled(Util.feetToGL(11,4), Util.feetToGL(1), Util.feetToGL(11,4));
			Shape.Cube.setColor(Building.oldCopper);
			Shape.Cube.draw(gl);
			Shape.Cube.setColor(Building.brick);
		gl.glPopMatrix();
			
		gl.glPushMatrix();
			gl.glTranslated(0, Util.feetToGL(44), 0);
			gl.glScaled(Util.feetToGL(11,4), Util.feetToGL(5), Util.feetToGL(11,4));
			Shape.Pyramid.setColor(Building.oldCopper);
			Shape.Pyramid.draw(gl);
			Shape.Pyramid.setColor(Building.brick);
		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}

}
