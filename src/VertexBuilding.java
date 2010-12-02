

import com.sun.opengl.util.BufferUtil;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import util.Vector;

public class VertexBuilding {

// cube ///////////////////////////////////////////////////////////////////////
//  v6----- v5
// /|      /|
//v1------v0|
//| |     | |
//| |v7---|-|v4
//|/      |/
//v2------v3
	
  Vector v0 = new Vector(0,0,0), v1 = new Vector(0,0,0), v2 = new Vector(0,0,0), v3 = new Vector(0,0,0), 
  				v4 = new Vector(0,0,0), v5 = new Vector(0,0,0), v6 = new Vector(0,0,0), v7 = new Vector(0,0,0);
	
//vertex coords array
  float vertices[] = {(float) v0.x(), (float) v0.y(), (float) v0.z(),(float) v1.x(), (float) v1.y(), (float) v1.z(), (float) v2.x(), (float) v2.y(), (float) v2.z(), (float) v3.x(), (float) v3.y(), (float) v3.z(), // v0-v1-v2-v3
		  (float) v0.x(), (float) v0.y(), (float) v0.z(),(float) v3.x(), (float) v3.y(), (float) v3.z(), (float) v4.x(), (float) v4.y(), (float) v4.z(), (float) v5.x(), (float) v5.y(), (float) v5.z(), // v0-v3-v4-v5
		  (float) v0.x(), (float) v0.y(), (float) v0.z(),(float) v5.x(), (float) v5.y(), (float) v5.z(), (float) v6.x(), (float) v6.y(), (float) v6.z(), (float) v1.x(), (float) v1.y(), (float) v1.z(), // v0-v5-v6-v1
		  (float) v1.x(), (float) v1.y(), (float) v1.z(),(float) v6.x(), (float) v6.y(), (float) v6.z(), (float) v7.x(), (float) v7.y(), (float) v7.z(), (float) v2.x(), (float) v2.y(), (float) v2.z(), // v1-v6-v7-v2
		  (float) v7.x(), (float) v7.y(), (float) v7.z(),(float) v4.x(), (float) v4.y(), (float) v4.z(), (float) v3.x(), (float) v3.y(), (float) v3.z(), (float) v2.x(), (float) v2.y(), (float) v2.z(), // v7-v4-v3-v2
		  (float) v4.x(), (float) v4.y(), (float) v4.z(),(float) v7.x(), (float) v7.y(), (float) v7.z(), (float) v6.x(), (float) v6.y(), (float) v6.z(), (float) v5.x(), (float) v5.y(), (float) v5.z()};   // v4-v7-v6-v5
	
//vertex coords array
//  float vertices[] = {1, 1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1, // v0-v1-v2-v3
//      1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, -1, // v0-v3-v4-v5
//      1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1, 1, // v0-v5-v6-v1
//      -1, 1, 1, -1, 1, -1, -1, -1, -1, -1, -1, 1, // v1-v6-v7-v2
//      -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, 1, // v7-v4-v3-v2
//      1, -1, -1, -1, -1, -1, -1, 1, -1, 1, 1, -1};   // v4-v7-v6-v5
  
//normal array
  float normals[] = {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, // v0-v1-v2-v3
      1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, // v0-v3-v4-v5
      0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, // v0-v5-v6-v1
      -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, // v1-v6-v7-v2
      0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, // v7-v4-v3-v2
      0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1};        // v4-v7-v6-v5
  
//index array of vertex array for glDrawElements()
//Notice the indices are listed straight from beginning to end as exactly
//same order of vertex array without hopping, because of different normals at
//a shared vertex. For this case, glDrawArrays() and glDrawElements() have no
//difference.
  byte indices[] = {0, 1, 2, 3,
      4, 5, 6, 7,
      8, 9, 10, 11,
      12, 13, 14, 15,
      16, 17, 18, 19,
      20, 21, 22, 23
  };
  
  float scale = 1;
  public FloatBuffer vertBuffer;
  public FloatBuffer normalBuffer;
  public ByteBuffer faceBuffer;

  public VertexBuilding() {
  }

  public VertexBuilding(float scale) {
      this.scale = scale;
  }

  public void setVertices(Vector a, Vector b, Vector c, Vector d, Vector e, Vector f, Vector g, Vector h){
	  v0 = a; v1 = b; v2 = c; v3 = d; v4 = e; v5 = f; v6 = g; v7 = h;
  }
  
  // This is needed because we are using JOGL
  public void createBuffers() {
      //note length of vertices, normals, and colors should all be the same!
      try {
          vertBuffer = BufferUtil.newFloatBuffer(vertices.length);
          normalBuffer = BufferUtil.newFloatBuffer(vertices.length);
          faceBuffer = BufferUtil.newByteBuffer(indices.length);

          vertBuffer.put(vertices);
          normalBuffer.put(normals);
          faceBuffer.put(indices);

          vertBuffer.rewind();
          normalBuffer.rewind();
          faceBuffer.rewind();
      } catch (Exception e) {
          System.out.println("Error creating buffers \n" + e);
      }
  }
	
}
