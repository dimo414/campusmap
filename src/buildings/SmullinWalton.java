package buildings;

import javax.media.opengl.GL;

public class SmullinWalton extends Building{
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GL gl) {
		// TODO Auto-generated method stub
		
	}

}
