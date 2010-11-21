package buildings;

import javax.media.opengl.GL;

public abstract class Building {

	double[][] vertices;
	int [][] faces;
	double [][] normals;
	double [] position;
	
	public abstract void draw(GL gl);
}
