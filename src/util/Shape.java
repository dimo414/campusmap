package util;

import java.nio.FloatBuffer;

import javax.media.opengl.GL;

import com.sun.opengl.util.BufferUtil;

/**
 * <p>Utility class for creating simple shapes like cubes more efficiently (and simply)
 * than immediate mode.  Currently, this uses Vertex Arrays - Vertex Buffer objects should
 * potentially be easy to drop in.  Furthermore, you are currently limited to either triangle
 * or quad based shapes.  This also could potentially be improved on if needed.</p>
 * 
 * <h3>Usage</h3>
 * <p>Either use one of the existing static objects (see list below) or construct your own shape object.</p>
 * 
 * <h3>Shapes</h3>
 * <h4>Cube</h4>
 * <p>A unit cube drawn from the origin to 1,1,-1</p>
 * 
 * @author Michael Diamond
 */
public class Shape {
	// choices for the sides parameter
	/** Indicates the shape consists of triangles */
	public static final int TRIANGLE = 3;
	/** Indicates the shape consists of quads */
	public static final int QUADS = 4;
	
	//   v6------v7
	//  /|      /|
	// v2------v3|
	// | |     | |
	// | v4----|-v5
	// |/      |/
	// v0------v1
	
	private static float[][] CUBE_VERT = {{0,0,0}, {1,0,0},	{0,1,0}, {1,1,0},
		{0,0,-1}, {1,0,-1}, {0,1,-1}, {1,1,-1}}; 
	private static float[][] CUBE_COL = {{.7f,.7f,.7f}, {.7f,.7f,.7f},	{.7f,.7f,.7f}, {.7f,.7f,.7f},
		{.7f,.7f,.7f}, {.7f,.7f,.7f}, {.7f,.7f,.7f}, {.7f,.7f,.7f}};
	private static int[][] CUBE_FACE = {{0,1,3,2}, {1,5,7,3}, {5,4,6,7}, {4,0,2,6}, {2,3,7,6}, {0,1,5,4}};
	private static float[][] CUBE_NORM = {{0,0,1}, {1,0,0}, {0,0,-1}, {-1,0,0}, {0,1,0}, {0,-1,0}};
	/**
	 * A unit cube with standard normals drawn from the origin (v0: 0,0,0) to (v7: 1,1,-1)
	 */
	public static final Shape Cube = new Shape(QUADS, CUBE_VERT, CUBE_COL, CUBE_FACE, CUBE_NORM);
	
	
	
	
	
	
	//
	// Actual class starts here
	//
	
	private int sides;
	private int numFaces;
	private FloatBuffer vertBuff;
	private FloatBuffer colorBuff;
	private FloatBuffer normalBuff;
	
	/**
	 * Constructs a new shape
	 * @param s Sides of the faces - 3 or 4, use Shape.TRIANGLES or Shape.QUADS
	 * @param vertices a 2D array of the vertices of the points
	 * @param colors a 2D array of the color of each vertex
	 * @param faces a 2D array of the faces - each face is the index of the vertex in the vertices array
	 * @param normals a 2D array of the normals of each face
	 */
	public Shape(int s, float[][] vertices, float[][] colors, int[][] faces, float[][] normals){
		sides = s;
		numFaces = faces.length;
		normalBuff = BufferUtil.newFloatBuffer(faces.length*sides*3);
		colorBuff = BufferUtil.newFloatBuffer(faces.length*sides*3);
		vertBuff = BufferUtil.newFloatBuffer(faces.length*sides*3);
		for(int f = 0; f < faces.length; f++){
			for(int i = 0; i < faces[f].length; i++){
				normalBuff.put(normals[f]);
				colorBuff.put(colors[faces[f][i]]);
				vertBuff.put(vertices[faces[f][i]]);
			}
		}
		normalBuff.rewind();
		colorBuff.rewind();
		vertBuff.rewind();
	}
	
	/**
	 * Draw the shape described
	 * @param gl the GL object to draw with
	 */
	public void draw(GL gl){
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
 
        gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertBuff);
        gl.glColorPointer(3, GL.GL_FLOAT, 0, colorBuff);
        gl.glNormalPointer(GL.GL_FLOAT,0, normalBuff);
        
        gl.glDrawArrays(sides == 4 ? GL.GL_QUADS : GL.GL_TRIANGLES, 0, numFaces*sides);
        
        gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
	}
}
