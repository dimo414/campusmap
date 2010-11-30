package buildings;


import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import javax.media.opengl.GL;

import com.sun.opengl.util.BufferUtil;

import util.*;

public class KresgePhysical extends Building{
	double longitude;
	double latitude;
	double elevation;
	double [] position = Util.coordToGL(longitude, latitude, elevation);
	static double Kheight = 40;
	static double Pheight = 20;
    public DoubleBuffer vertBuffer;
    public DoubleBuffer colorBuffer;
    public DoubleBuffer normalBuffer;
    public ByteBuffer faceBuffer;
	
	/*Kresge (Starting from NW corner, going counter-clockwise)
	v0 = 0, 0, 0
	v1 = 0, 0, 109
	v2 = 119, 0, 109
	v3 = 119, 0,  58+6./12
	v4 = 130+8./12, 0, 58+6./12
	v5 = 130+8./12, 0, 21+2./12
	v6 = 120, 0, 21+2./12
	v7 = 120, 0, 0
	
	v8 = 0, Kheight, 0
	v9 = 0, Kheight, 109
	v10 = 119, Kheight, 109
	v11 = 119, Kheight,  58+6./12
	v12 = 130+8./12, Kheight, 58+6./12
	v13 = 130+8./12, Kheight, 21+2./12
	v14 = 120, Kheight, 21+2./12
	v15 = 120, Kheight, 0
	*/
	
	/*Physical Plant (GL coord origin at v0. Starting at NW corner, going counter-clockwise)
	v16 = 0, 0, 109
	v17 = 0, 0, 164
	v18 = 83, 0, 164
	v19 = 83, 0, 135
	v20 = 51, 0, 135
	v21 = 51, 0, 109
	
	v22 = 0, Pheight, 109
	v23 = 0, Pheight, 164
	v24 = 83, Pheight, 164
	v25 = 83, Pheight, 135
	v26 = 51, Pheight, 135
	v27 = 51, Pheight, 109
	*/
	
	static final double [] vertices = { 
			//Kresge
			0, Kheight, 0, 0, 0, 0, 0, 0, 109, 0, Kheight, 109, //v8-v0-v1-v9
			0, Kheight, 109, 0, 0, 109, 119, 0, 109, 119, Kheight, 109, //v9-v1-v2-v10
			119, Kheight, 109, 119, 0, 109, 119, 0, 58+6./12, 119, Kheight, 58+6./12, //v10-v2-v3-v11
			119, Kheight,  58+6./12, 119, 0, 109, 130+8./12, 0, 58+6./12, 130+8./12, Kheight, 58+6./12, //v11-v3-v4-v12
			130+8./12, Kheight, 58+6./12, 130+8./12, 0, 58+6./12, 130+8./12, 0, 21+2./12, 130+8./12, Kheight, 21+2./12, //v12-v4-v5-v13
			130+8./12, Kheight, 21+2./12, 130+8./12, 0, 21+2./12, 120, 0, 21+2./12, 120, Kheight, 21+2./12, //v13-v5-v6-v14
			120, Kheight, 21+2./12, 120, 0, 21+2./12, 120, 0, 0, 120, Kheight, 0, //v14-v6-v7-v15
			120, Kheight, 0, 120, 0, 0, 0, 0, 0, 0, Kheight, 0, //v15-v7-v0-v8
			
			//Physical Plant
			0, Pheight, 109, 0, 0, 109, 0, 0, 164, 0, Pheight, 164, //v22-v16-v17-v23
			0, Pheight, 164, 0, 0, 164, 83, 0, 164, 83, Pheight, 164, //v23-v17-v18-v24
			83, Pheight, 164, 83, 0, 164, 83, 0, 135, 83, Pheight, 135, //v24-v18-v19-v25
			83, Pheight, 135, 83, 0, 135, 51, 0, 135, 51, Pheight, 135, //v25-v19-v20-v26
			51, Pheight, 135, 51, 0, 135, 51, 0, 109, 51, Pheight, 109, //v26-v20-v21-v27
			51, Pheight, 109, 51, 0, 109, 0, 0, 109, 0, Pheight, 109//v27-v21-v16-v22
	};
	
	static double [] normals = {
			//Kresge
			-1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
			0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
			1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
			0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
			1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
			0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
			1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
			0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
			//Physical
			-1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
			0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
			1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
			0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
			1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
			0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1
	};
	
	static double [] colors = {
			//Kresge
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			//Physical
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
	};
	
    byte indices[] = {0, 1, 2, 3,
            4, 5, 6, 7,
            8, 9, 10, 11,
            12, 13, 14, 15,
            16, 17, 18, 19,
            20, 21, 22, 23,
            24, 25, 26, 27,
            28, 29, 30, 31,
            
            32, 33, 34, 35,
            36, 37, 38, 39,
            40, 41, 42, 43,
            44, 45, 46, 47,
            48, 49, 50, 51,
            52, 53, 54, 55
        };
    
    public void createBuffers() {
        //note length of vertices, normals, and colors should all be the same!
        try {
        	for(int i = 0; i < vertices.length; i++){
        		vertices[i] = Util.feetToGL(vertices[i]);
        	}
            vertBuffer = BufferUtil.newDoubleBuffer(vertices.length);
            colorBuffer = BufferUtil.newDoubleBuffer(vertices.length);
            normalBuffer = BufferUtil.newDoubleBuffer(vertices.length);
            faceBuffer = BufferUtil.newByteBuffer(indices.length);
            
            vertBuffer.put(vertices);
            colorBuffer.put(colors);
            normalBuffer.put(normals);
            faceBuffer.put(indices);

            vertBuffer.rewind();
            colorBuffer.rewind();
            normalBuffer.rewind();
            faceBuffer.rewind();
        } catch (Exception e) {
            System.out.println("Error creating buffers \n" + e);
        }


    }
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		gl.glTranslated(position[0], position[1], position[2]);
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
 
        gl.glVertexPointer(3, GL.GL_DOUBLE, 0, vertBuffer);
        gl.glColorPointer(3, GL.GL_DOUBLE, 0, colorBuffer);
        gl.glNormalPointer(GL.GL_DOUBLE,0, normalBuffer);
        
        gl.glDrawElements(GL.GL_QUADS, indices.length, GL.GL_UNSIGNED_BYTE, faceBuffer);
        
        gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
        gl.glPopMatrix();
	}

	@Override
	public void init(GL gl) {
		// TODO Auto-generated method stub
		
	}

}
