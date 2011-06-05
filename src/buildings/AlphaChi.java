package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Alpha Chi Omega Sorority House
 * 
 * Identifying Location - Southeast Corner
 * 7546481.18E 472500.315N
 * 
 * @author Jose Alvarado
 */
public class AlphaChi extends Building{

	//1 - left building, 2 - middle, 3 - right

	private double posEast = 7546481.18;
	private double posNorth = 472500.315;
	private double posElevation = 0; // TODO Get Elevation of building

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{184.0/2, 177.0/2};
	}
	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();	
		//TODO When building proper model, consider making the southwest corner the canonical point
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(-121, 50, 21);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(-(121-76),0,-21);
		gl.glScaled(-73, 50, 65);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(-(121-85),0,-(21+65));
		gl.glScaled(-21, 50, 33);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();	
		
		// 4th Cube
		gl.glPushMatrix();
		gl.glTranslated(-(121-30),0,-(21+65));
		gl.glScaled(-40, 50, 26);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}