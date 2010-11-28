package main;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import buildings.*;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 * The Central class for the project, runs OpenGL and manages all the buildings and other
 * features.
 */
public class CampusPanel implements GLEventListener {

	public ArrayList<Building> buildings = new ArrayList<Building>();
	public GLU glu = new GLU();
	
	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL gl = drawable.getGL();
        gl.glClearColor(.2f, .2f, .5f, 0);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(20.0f, 1.0f, 1.0f, 200.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);

    //    addBuildings();
        
        gl.glEnable(GL.GL_DEPTH_TEST);
	}
	
	public void addBuildings(){
		Putnam putnam = new Putnam();
		SmullinWalton smwa = new SmullinWalton();
		Gatke gatke = new Gatke();
		
		buildings.add(putnam);
		buildings.add(smwa);
		buildings.add(gatke);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL gl = drawable.getGL();;
		
	//	drawBuildings(gl);
	}

	public void drawBuildings(GL gl){
		for(Building a : buildings){
			a.draw(gl);
		}
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		// TODO Auto-generated method stub
		
	}

}
