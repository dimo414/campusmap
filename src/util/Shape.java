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
 * <p>Either use one of the existing static objects (see list below) or construct your own shape object for more unique shapes.</p>
 * 
 * <p>A shape (currently) consists of a set of quads and triangles, described by a list of vertices,
 * faces, normals, and colors.  Construct a new Shape and pass it this data, then call draw whenever
 * the shape is needed.  Shapes can be reused, therefore only one instance of a given shape should
 * ever be constructed.</p>
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
	
	/**
	 * A unit cube with standard normals drawn from the origin (v0: 0,0,0) to (v7: 1,1,-1)
	 */
	public static final Shape Cube;
	
	/**
	 * A unit triangular prism with standard normals drawn with the right
	 * angle running along (0,0,0) to (0,0,-1)
	 */
	public static final Shape TrianglePrism;
	
	/**
	 * A unit pyramid with standard normals drawn with a corner at (0,0,0)
	 * and the tip at (.5,1,-.5)
	 */
	public static final Shape Pyramid;
	
	static {
		// CUBE
		//   v6------v7
		//  /|      /|
		// v2------v3|
		// | |     | |
		// | v4----|-v5
		// |/      |/
		// v0------v1
		
		float[][] CUBE_VERT = {{0,0,0}, {1,0,0},	{0,1,0}, {1,1,0},
			{0,0,-1}, {1,0,-1}, {0,1,-1}, {1,1,-1}}; 
		float[][] CUBE_COL = {{.7f,.7f,.7f}, {.7f,.7f,.7f},	{.7f,.7f,.7f}, {.7f,.7f,.7f},
			{.7f,.7f,.7f}, {.7f,.7f,.7f}};
		int[][] CUBE_FACE = {{0,1,3,2}, {1,5,7,3}, {5,4,6,7}, {4,0,2,6}, {2,3,7,6}, {0,1,5,4}};
		float[][] CUBE_NORM = {{0,0,1}, {1,0,0}, {0,0,-1}, {-1,0,0}, {0,1,0}, {0,-1,0}};
		
		Cube = new Shape(QUADS, CUBE_VERT, CUBE_FACE, CUBE_NORM);
		Cube.setQuadColor(CUBE_COL);
		
		// TRIANGULAR PRISM
		float[][] TRIAG_VERT = {{0,0,0}, {1,0,0},{0,1,0}, {0,0,-1}, {1,0,-1}, {0,1,-1}}; 
		int[][] TRIAG_QUAD_FACE = {{0,1,4,3},{1,4,5,2},{3,0,2,5}};
		float[][] TRIAG_QUAD_NORM = {{0,-1,0},new Vector(1,1,0).normalize().toFArray(),{-1,0,0}};
		float[][] TRIAG_QUAD_COL = {{.7f,.7f,.7f}, {.7f,.7f,.7f}, {.7f,.7f,.7f}};
		int[][] TRIAG_TRIAG_FACE = {{0,1,2},{3,4,5}};
		float[][] TRIAG_TRIAG_NORM = {{0,0,1},{0,0,-1}};
		float[][] TRIAG_TRIAG_COL = {{.7f,.7f,.7f}, {.7f,.7f,.7f}};
		
		TrianglePrism = new Shape(QUADS, TRIAG_VERT, TRIAG_QUAD_FACE, TRIAG_QUAD_NORM);
		TrianglePrism.setTriangles(TRIAG_VERT, TRIAG_TRIAG_FACE, TRIAG_TRIAG_NORM);
		TrianglePrism.setQuadColor(TRIAG_QUAD_COL);
		TrianglePrism.setTriangleColor(TRIAG_TRIAG_COL);
		
		// PYRAMID
		float[][] PYRAMID_VERT = {{0,0,0},{1,0,0},{1,0,-1},{0,0,-1},{.5f,1,-.5f}};
		int[][] PYRAMID_QUAD_FACE = {{0,1,2,3}};
		float[][] PYRAMID_QUAD_NORM = {{0,-1,0}};
		float[][] PYRAMID_QUAD_COL = {{.7f,.7f,.7f}};
		int[][] PYRAMID_TRIAG_FACE = {{0,1,4},{1,2,4},{2,3,4},{3,0,4}};
		float[][] PYRAMID_TRIAG_NORM = {
				new Vector(1,0,0).cross(new Vector(-.5,1,-.5)).toFArray(),
				new Vector(0,0,-1).cross(new Vector(-.5,1,.5)).toFArray(),
				new Vector(-1,0,0).cross(new Vector(-.5,1,.5)).toFArray(),
				new Vector(0,0,1).cross(new Vector(.5,1,-.5)).toFArray(),
				{0,1,0},{0,1,0},{0,1,0}};
		float[][] PYRAMID_TRIAG_COL = {{.7f,.7f,.7f},{.7f,.7f,.7f},{.7f,.7f,.7f},{.7f,.7f,.7f}};
		
		Pyramid = new Shape(QUADS, PYRAMID_VERT, PYRAMID_QUAD_FACE, PYRAMID_QUAD_NORM);
		Pyramid.setTriangles(PYRAMID_VERT, PYRAMID_TRIAG_FACE, PYRAMID_TRIAG_NORM);
		Pyramid.setQuadColor(PYRAMID_QUAD_COL);
		Pyramid.setTriangleColor(PYRAMID_TRIAG_COL);
	}
	
	
	
	
	//
	// Actual class starts here
	//
	
	private float[][] q_vertices;
	private float[][] q_colors;
	private int[][] q_faces;
	private float[][] q_normals;
	private int q_numFaces;
	private FloatBuffer q_vertBuff;
	private FloatBuffer q_colorBuff;
	private FloatBuffer q_normalBuff;
	
	private float[][] t_vertices;
	private float[][] t_colors;
	private int[][] t_faces;
	private float[][] t_normals;
	private int t_numFaces;
	private FloatBuffer t_vertBuff;
	private FloatBuffer t_colorBuff;
	private FloatBuffer t_normalBuff;
	
	private boolean buffersGen = false;
	
	/**
	 * Constructs a new shape with no components
	 */
	public Shape(){
	}
	
	/**
	 * Constructs a new shape
	 * @param s Sides of the faces - 3 or 4, use Shape.TRIANGLES or Shape.QUADS
	 * @param vert a 2D array of the vertices of the points
	 * @param col a 2D array of the color of each vertex
	 * @param fac a 2D array of the faces - each face is the index of the vertex in the vertices array
	 * @param norm a 2D array of the normals of each face
	 */
	public Shape(int s, float[][] vert, int[][] fac, float[][] norm){
		if(s == QUADS){
			setQuads(vert,fac,norm);
		} else if(s == TRIANGLE){
			setTriangles(vert,fac,norm);
		}
	}
	
	public void setQuads(float[][] vert, int[][] fac, float[][] norm){
		q_vertices = vert;
		q_faces = fac;
		q_normals = norm;
		q_numFaces = fac.length;
		buffersGen = false;
	}
	
	public void setTriangles(float[][] vert, int[][] fac, float[][] norm){
		t_vertices = vert;
		t_faces = fac;
		t_normals = norm;
		t_numFaces = fac.length;
		buffersGen = false;
	}
	
	/**
	 * Sets the color of the object
	 * @param cols
	 */
	public void setQuadColor(float[][] cols){
		q_colors = cols;
		buffersGen = false;
	}
	
	public void setTriangleColor(float[][] cols){
		t_colors = cols;
		buffersGen = false;
	}
	
	/**
	 * Generates the buffers for the shape.  Called automatically by draw when needed.
	 */
	public void genBuffers(){
		if(q_faces != null){
			q_normalBuff = BufferUtil.newFloatBuffer(q_faces.length*QUADS*3);
			q_colorBuff = BufferUtil.newFloatBuffer(q_faces.length*QUADS*3);
			q_vertBuff = BufferUtil.newFloatBuffer(q_faces.length*QUADS*3);
			for(int f = 0; f < q_faces.length; f++){
				for(int i = 0; i < q_faces[f].length; i++){
					q_normalBuff.put(q_normals[f]);
					q_colorBuff.put(q_colors[f]);
					q_vertBuff.put(q_vertices[q_faces[f][i]]);
				}
			}
			q_normalBuff.rewind();
			q_colorBuff.rewind();
			q_vertBuff.rewind();
		}

		if(t_faces != null){
			t_normalBuff = BufferUtil.newFloatBuffer(t_faces.length*TRIANGLE*3);
			t_colorBuff = BufferUtil.newFloatBuffer(t_faces.length*TRIANGLE*3);
			t_vertBuff = BufferUtil.newFloatBuffer(t_faces.length*TRIANGLE*3);
			for(int f = 0; f < t_faces.length; f++){
				for(int i = 0; i < t_faces[f].length; i++){
					t_normalBuff.put(t_normals[f]);
					t_colorBuff.put(t_colors[f]);
					t_vertBuff.put(t_vertices[t_faces[f][i]]);
				}
			}
			t_normalBuff.rewind();
			t_colorBuff.rewind();
			t_vertBuff.rewind();
		}
		
		buffersGen = true;
	}
	
	/**
	 * Draw the shape described
	 * @param gl the GL object to draw with
	 */
	public void draw(GL gl){
		if(!buffersGen)
			genBuffers();
		
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
 
        gl.glVertexPointer(3, GL.GL_FLOAT, 0, q_vertBuff);
        gl.glColorPointer(3, GL.GL_FLOAT, 0, q_colorBuff);
        gl.glNormalPointer(GL.GL_FLOAT,0, q_normalBuff);
        

		if(q_faces != null){
	        gl.glVertexPointer(3, GL.GL_FLOAT, 0, q_vertBuff);
	        gl.glColorPointer(3, GL.GL_FLOAT, 0, q_colorBuff);
	        gl.glNormalPointer(GL.GL_FLOAT,0, q_normalBuff);
	        
			gl.glDrawArrays(GL.GL_QUADS, 0, q_numFaces*QUADS);
		}
		if(t_faces != null){
	        gl.glVertexPointer(3, GL.GL_FLOAT, 0, t_vertBuff);
	        gl.glColorPointer(3, GL.GL_FLOAT, 0, t_colorBuff);
	        gl.glNormalPointer(GL.GL_FLOAT,0, t_normalBuff);
	        
			gl.glDrawArrays(GL.GL_TRIANGLES, 0, t_numFaces*TRIANGLE);
		}
        
        gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
	}
}
