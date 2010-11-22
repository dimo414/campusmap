package buildings;

import javax.media.opengl.GL;

public class Putnam extends Building {

	double length = 167 + 4. / 12;
	double width = 95 + 2. / 12;
	double height;

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

	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		gl.glTranslated(position[0], position[1], position[2]);
		// gl.glBindTexture(GL.GL_TEXTURE_2D, BUILDING_TEX);
		gl.glBegin(GL.GL_QUADS);
		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < 4; j++) {
				gl.glNormal3dv(normals[i], 0);
				gl.glTexCoord2dv(textures[j], 0);
				gl.glVertex3dv(vertices[faces[i][j]], 0);
			}
		}
		gl.glEnd();
		gl.glPopMatrix();
	}

}
