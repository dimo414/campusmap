package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * Gatke Hall:
 * 
 * THIS JAVADOC OR SOME OTHER COMMENT SHOULD CONTAIN DETAILS LIKE POSITION AND DIMENSIONS OF THE BUILDING
 */
public class Gatke extends Building{

	double length = 45; //45' - 10 3/4
	double width = 98; //98' - 11 4/4
	double height= 40;

	double[][] vertices = { { 0, 0, 0 }, { 0, 0, width }, { length, 0, width },
			{ length, 0, 0 }, { 0, height, 0 }, { 0, height, width },
			{ length, height, width }, { length, height, 0 } };
	int[][] faces = { { 4, 0, 1, 5 }, { 5, 1, 2, 6 }, { 6, 2, 3, 7 },
			{ 7, 3, 0, 4 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }; // West, South,
																// East, North,
																// Bottom, Top
	double[][] normals = { { -1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 },
			{ 0, 0, -1 }, { 0, -1, 0 }, { 0, 1, 0 } };
	double[] position = { 0, 0, 0 };
	double textures[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
	
	private double posEast = 7547654.973;
	private double posNorth = 473298.811;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos = Util.coordToGL(posEast, posNorth, posElevation);
	private double[] midpoint = new double[]{Util.feetToGL(45/2),Util.feetToGL(98/2)};
	
	@Override
	public void init(GL gl) {
		// TODO Auto-generated method stub
		
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
		
		gl.glRotated(90, 0, 1, 0);
		
		Shape.Cube.setColor(Building.brick);
		// 1st Cube
		gl.glPushMatrix();
		gl.glScaled(Util.feetToGL(45), Util.feetToGL(50), Util.feetToGL(98));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}
