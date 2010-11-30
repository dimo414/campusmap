package buildings;

import javax.media.opengl.GL;

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
	
	private double[] glPos;
	private double posEast = 7547585.465;
	private double posNorth = 473300.03;
	private double posElevation = 0; // TODO Get Elevation of building
	
	
	@Override
	public void init(GL gl) {
		// TODO Auto-generated method stub
		glPos = Util.coordToGL(posEast, posNorth, posElevation);
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
			gl.glTranslated(-Util.feetToGL(45.0/2), 0, Util.feetToGL(98.0/2));
		// End universal positioning
		
		gl.glRotated(90, 0, 1, 0);
		gl.glTranslated(position[0], position[1], position[2]);
		for (int i = 0; i < faces.length; i++) {
			// gl.glBindTexture(GL.GL_TEXTURE_2D, BUILDING_TEX);
			gl.glBegin(GL.GL_QUADS);
			for (int j = 0; j < 4; j++) {
				gl.glNormal3dv(normals[i], 0);
				gl.glTexCoord2dv(textures[j], 0);
				gl.glVertex3dv(vertices[faces[i][j]], 0);
			}
			gl.glEnd();
		}
		gl.glPopMatrix();
		
		
		
	}

}
