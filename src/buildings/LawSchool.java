package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Collins Law Center
 * @author Jose Alvarado
 */
public class LawSchool extends Building{

	//1 - left building, 2 - middle, 3 - right
	
	private double posEast = 7546042.785;
	private double posNorth = 473270.915;
	private double posElevation = 0; // TODO Get Elevation of building

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{184.0/2, 177.0/2};
	}
	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
	//	gl.glRotated(90, 0, 1, 0);		
		
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(125, 50, 78);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 2nd Cube
		gl.glPushMatrix();
		gl.glTranslated(23,0,-(78));
		gl.glScaled(76, 50, 144);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// 3rd Cube
		gl.glPushMatrix();
		gl.glTranslated(23-16,0,-(78+144));
		gl.glScaled(122, 50, 79);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		
		gl.glPopMatrix();
	}
}