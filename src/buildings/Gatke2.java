package buildings;

import javax.media.opengl.GL;

import util.Util;
import util.Vector;

public class Gatke2 extends Building{

	double length = 45; 
	double width = 98;
	double height= 40;
	
	private double[] glPos = {0,0,0};
	private double posEast = 7547561.591;
	private double posNorth = 473329.669;
	private double posElevation = 0; // TODO Get Elevation of building
	
	VertexBuilding cube = new VertexBuilding();
	
	public void init(GL gl) {
		// TODO Auto-generated method stub
		glPos = Util.coordToGL(posEast, posNorth, posElevation);
		
		cube.setVertices(new Vector(0,height,0), new Vector(length,height,0), new Vector(length,0,0), new Vector(0,0,0),
				new Vector(0,0,width), new Vector(0,height,width), new Vector(length,height,width), new Vector(length,0,width));
		cube.createBuffers();
	}
	
	@SuppressWarnings("static-access")
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
		
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
		
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        gl.glEnableClientState(gl.GL_NORMAL_ARRAY);
 
        gl.glVertexPointer(3, gl.GL_FLOAT, 0, cube.vertBuffer);
        gl.glNormalPointer(gl.GL_FLOAT,0,cube.normalBuffer);
        
        gl.glDrawElements(gl.GL_QUADS, cube.indices.length, gl.GL_UNSIGNED_BYTE, cube.faceBuffer);
        
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_NORMAL_ARRAY);
        
        gl.glFlush();
		
		gl.glPopMatrix();
	}
}
