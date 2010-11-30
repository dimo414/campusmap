package buildings;

import javax.media.opengl.GL;

public class LeeAndYork extends Building{

	//1 - top left cube of Lee, 2 - top right cube, 3 - bottom left, 4 - bottom cube, 5 - bottom right cube
	//--------------------------------------------------------------------------------------------------	
		double length1 = 126; 	//126' - 15/16       
		double width1 = 60; 	//60' - 1
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
		double length2 = 29;		//29' - 6 1/8
		double width2 = 87;		//87' - 10 5/8
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
		double[] position2 = { 60, 0, -26};
		double textures2[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
	//----------------------------------------------------------------------------------------------------	
		double length3 = 63;		//63'
		double width3 = 49;		//49 - 4 3/16
		double height3 = 50;
		
		double[][] vertices3 = { { 0, 0, 0 }, { 0, 0, width1 }, { length1, 0, width1 },
				{ length1, 0, 0 }, { 0, height1, 0 }, { 0, height1, width1 },
				{ length1, height1, width1 }, { length1, height1, 0 } };
		int[][] faces3 = { { 4, 0, 1, 5 }, { 5, 1, 2, 6 }, { 6, 2, 3, 7 },
				{ 7, 3, 0, 4 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }; // West, South,
																	// East, North,
																	// Bottom, Top
		double[][] normals3 = { { -1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 },
				{ 0, 0, -1 }, { 0, -1, 0 }, { 0, 1, 0 } };
		double[] position3 = { 22, 0, -126};
		double textures3[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
		//----------------------------------------------------------------------------------------------------
		double length4 = 63 - 36;		//63' - 36'
		double width4 = 20;		//20' - 4 1/6
		double height4 = 50;
		
		double[][] vertices4 = { { 0, 0, 0 }, { 0, 0, width1 }, { length1, 0, width1 },
				{ length1, 0, 0 }, { 0, height1, 0 }, { 0, height1, width1 },
				{ length1, height1, width1 }, { length1, height1, 0 } };
		int[][] faces4 = { { 4, 0, 1, 5 }, { 5, 1, 2, 6 }, { 6, 2, 3, 7 },
				{ 7, 3, 0, 4 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }; // West, South,
																	// East, North,
																	// Bottom, Top
		double[][] normals4 = { { -1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 },
				{ 0, 0, -1 }, { 0, -1, 0 }, { 0, 1, 0 } };
		double[] position4 = { 22 + 49, 0, -126 - 36};
		double textures4[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
	//----------------------------------------------------------------------------------------------------	
		double length5 = 109;		//109' - 8 1/6
		double width5 = 29;		//29' - 6 1/8
		double height5 = 50;
		
		double[][] vertices5 = { { 0, 0, 0 }, { 0, 0, width1 }, { length1, 0, width1 },
				{ length1, 0, 0 }, { 0, height1, 0 }, { 0, height1, width1 },
				{ length1, height1, width1 }, { length1, height1, 0 } };
		int[][] faces5 = { { 4, 0, 1, 5 }, { 5, 1, 2, 6 }, { 6, 2, 3, 7 },
				{ 7, 3, 0, 4 }, { 0, 1, 2, 3 }, { 4, 5, 6, 7 } }; // West, South,
																	// East, North,
																	// Bottom, Top
		double[][] normals5 = { { -1, 0, 0 }, { 0, 0, 1 }, { 1, 0, 0 },
				{ 0, 0, -1 }, { 0, -1, 0 }, { 0, 1, 0 } };
		double[] position5 = { 22+49+20, 0, -26-29-23};
		double textures5[][] = {{0, 1}, {0,0}, {1,0}, {1,1}};
		
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
			//-----------------------------------------------------------------
			gl.glPushMatrix();
			gl.glTranslated(position3[0], position3[1], position3[2]);
			for (int i = 0; i < faces3.length; i++) {
				// gl.glBindTexture(GL.GL_TEXTURE_2D, BUILDING_TEX);
				gl.glBegin(GL.GL_QUADS);
				for (int j = 0; j < 4; j++) {
					gl.glNormal3dv(normals3[i], 0);
					gl.glTexCoord2dv(textures3[j], 0);
					gl.glVertex3dv(vertices3[faces3[i][j]], 0);
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
