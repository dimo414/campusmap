package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * <h3>Arts</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>7547138.978E 473205.738N</strong></p>

 * 
 * @author Brian Forbis
 */
public class Art extends Building{
	private double posEast = 7546568.978;
	private double posNorth = 473705.738;
	private double posElevation = 0; // TODO Get Elevation of building
	
	private double height = 45;

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{30,30};	
	}
	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		//Start Drawing, SouthWest Positions
		
		Shape.Cube.setColor(Building.brick);
		gl.glPushMatrix(); //Kiln
		gl.glTranslated(-(22), 0, -(18+3./12));
		gl.glScaled(22, height, 18+8./12);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();

		gl.glPushMatrix(); //MainBuilding
		gl.glScaled(73+2./12, height, 54);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Hallway
		gl.glTranslated(73+2./12, 0, -(20+7./12));
		gl.glScaled(11+6./12, height, 13+11./12);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //RightSide
		gl.glTranslated(84+8./12, 0, 12+11./12);
		gl.glScaled(31+4./12, height, 66+4./12);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Right Extrusion
		gl.glTranslated(116, 0, -(7));
		gl.glScaled(6, height, 26+6./12);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}