package buildings;

import javax.media.opengl.GL;

public class Olin extends Building{

	//1 - left cube of Olin, 2 - right cube of Olin
	//--------------------------------------------------------------------------------------------------	
		double length1 = 81; 	//81' - 8       
		double width1 = 122; 	//122'
		double height1 = 50;

		double[][] vertices1 = { { 0, 0, 0 }, { 0, 0, width1 }, { length1, 0, width1 },
				{ length1, 0, 0 }, { 0, height1, 0 }, { 0, height1, width1 },
				{ length1, height1, width1 }, { length1, height1, 0 } };
		int[][] faces1 = { { 4, 0, 1, 5 }, { 5, 1, 2, 6 }, { 6, 2, 3, 7 },
				{ 7, 3, 0, 4 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }; // West, South,
																	// East, North,
																	// Bottom, Top
		double[][] normals1 = { { -1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 },
				{ 0, 0, -1 }, { 0, -1, 0 }, { 0, 1, 0 } };
		double[] position1 = { 0, 0, 0 };
		double textures1[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
	//----------------------------------------------------------------------------------------------------
		double length2 = 65;		//55' - 0 7/8 + 20' - 2 7/6
		double width2 = 33;		//33' - 5 1/4
		double height2 = 50;
		
		double[][] vertices2 = { { 0, 0, 0 }, { 0, 0, width1 }, { length1, 0, width1 },
				{ length1, 0, 0 }, { 0, height1, 0 }, { 0, height1, width1 },
				{ length1, height1, width1 }, { length1, height1, 0 } };
		int[][] faces2 = { { 4, 0, 1, 5 }, { 5, 1, 2, 6 }, { 6, 2, 3, 7 },
				{ 7, 3, 0, 4 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }; // West, South,
																	// East, North,
																	// Bottom, Top
		double[][] normals2 = { { -1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 },
				{ 0, 0, -1 }, { 0, -1, 0 }, { 0, 1, 0 } };
		double[] position2 = { 122, 0, 11};
		double textures2[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
		
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			gl.glTranslated(position1[0], position1[1], position1[2]);
			for (int i = 0; i < faces1.length; i++) {
				// gl.glBindTexture(GL.GL_TEXTURE_2D, BUILDING_TEX);
				gl.glBegin(GL.GL_QUADS);
				for (int j = 0; j < 4; j++) {
					gl.glNormal3dv(normals1[i], 0);
					gl.glTexCoord2dv(textures1[j], 0);
					gl.glVertex3dv(vertices1[faces1[i][j]], 0);
				}
				gl.glEnd();
			}
			gl.glPopMatrix();
			//-----------------------------------------------------------------
			gl.glPushMatrix();
			gl.glTranslated(position2[0], position2[1], position2[2]);
			for (int i = 0; i < faces2.length; i++) {
				// gl.glBindTexture(GL.GL_TEXTURE_2D, BUILDING_TEX);
				gl.glBegin(GL.GL_QUADS);
				for (int j = 0; j < 4; j++) {
					gl.glNormal3dv(normals2[i], 0);
					gl.glTexCoord2dv(textures2[j], 0);
					gl.glVertex3dv(vertices2[faces2[i][j]], 0);
				}
				gl.glEnd();
			}
			gl.glPopMatrix();	
		}

		@Override
		public void init(GL gl) {
			// TODO Auto-generated method stub
			
		}
}
