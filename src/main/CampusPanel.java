package main;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import buildings.Building;
import util.*;

import java.util.ArrayList;

import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;
import javax.media.opengl.glu.GLU;

/**
 * The Central class for the project, runs OpenGL and manages all the buildings and other
 * features.
 */
public class CampusPanel implements GLEventListener {

	Eye eye = new Eye(new Vector(-5.0,5.0,-5.0), new Vector(1.0,0.0,0.0), new Vector(0.0,1.0,0.0), new Vector(3.0,0.0,3.0));
	public ArrayList<Building> buildings = new ArrayList<Building>();
	public GLU glu = new GLU();  // OpenGL Utility Library - used to set camera view
    public GLUT glut = new GLUT();  // OpenGL Utility Toolkit - contains teapot
	int[] names; // the array of texture names;
	int nTex = 1; // the name of chosen texture
	
	@Override
	/**
	 * Defines buildings, textures, etc. at compile time (only need to be called once)
	 * It also sets some default values 
	 */
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL gl = drawable.getGL();
        gl.glClearColor(.2f, .2f, .5f, 0);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(20.0f, 1.0f, 1.0f, 200.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);

    //    addBuildings();
        addTextures(gl);
        
        gl.glEnable(GL.GL_DEPTH_TEST);
	}
	
	/**
	 * Creates and adds buildings to the buildings arraylist
	 */
	public void addBuildings(){
		Putnam putnam = new Putnam();
		SmullinWalton smwa = new SmullinWalton();
		Gatke gatke = new Gatke();
		
		buildings.add(putnam);
		buildings.add(smwa);
		buildings.add(gatke);
	}
	
	/**
	 * Adds textures to openGl
	 */
	public void addTextures(GL gl){
		MyImage image = new MyImage("BluePrints\\CampusMap2.jpg");
		
		names = new int[1];
        gl.glGenTextures(1, names, 0);
        // Assign each of the textures an ID
        bind(names[0], image, gl);

        nTex = names[0]; 
        gl.glEnable(GL.GL_TEXTURE_2D);
	}
	
	/**
	 * Helper method for addTextures.
	 */
	public void bind(int name, MyImage image, GL gl) {
		// set the current texture 
		gl.glBindTexture(GL.GL_TEXTURE_2D, // target
                name);             // texture ID

        // glTexParameter sets the texture state parameters, i.e. how texturing is done, e.g antialiasing, wrapping, etc
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
        
        gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
        
        gl.glTexImage2D(GL.GL_TEXTURE_2D, // target
                0, // level (used for mipmaps)
                GL.GL_RGB, // internalFormat
                image.getHeight(), // image height
                image.getHeight(), // image width
                0, // border 0 or 1
                GL.GL_RGB, // format of texture image data
                GL.GL_UNSIGNED_BYTE, // type of texture image data
                image.convert() // buffer containing image (byte buffer in this case)
                );
	}
	
	@Override
	/**
	 * Displays everything
	 */
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(-5.0, 5.0, -5.0, 3.0, 0.0, 3.0, 0.0, 1.0, 0.0);
		
	//	drawBuildings(gl);
		drawGround(gl);
	}

	/**
	 * Draws buildings 
	 */
	public void drawBuildings(GL gl){
		for(Building a : buildings){
			a.draw(gl);
		}
	}
	
	/**
	 * Draws the ground
	 */
	public void drawGround(GL gl){
		gl.glPushMatrix();
		Cube cube = new Cube();
		gl.glBindTexture(GL.GL_TEXTURE_2D, names[0]);
		cube.draw(gl);
		gl.glPopMatrix();
	}
	
	@Override
	/**
	 * Allows you to change the size of the screen
	 */
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
	/**
	 * No idea what this is for
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		// TODO Auto-generated method stub
		
	}

}
