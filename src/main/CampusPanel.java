package main;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import buildings.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;
import javax.media.opengl.glu.GLU;

import util.Eye;
import util.MyImage;
import util.Vector;

/**
 * The Central class for the project, runs OpenGL and manages all the buildings and other
 * features.
 */
public class CampusPanel implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private GLU glu = new GLU();  // OpenGL Utility Library - used to set camera view
    private GLUT glut = new GLUT();  // OpenGL Utility Toolkit - contains teapot

	//glu.gluLookAt(-5.0, 5.0, -5.0, 3.0, 0.0, 3.0, 0.0, 1.0, 0.0);
	private Eye eye = new Eye(new Vector(-5.0,5.0,-5.0), new Vector(3.0, 0.0, 3.0));
	private HashSet<Integer> keys = new HashSet<Integer>();

	private double moveSpeed = 2;
    private double turnAngle = .01;
	private int lastX;
	private int lastY;
	private boolean turn = false;
	
	private  ArrayList<Building> buildings = new ArrayList<Building>();
	
	private int[] names; // the array of texture names;
	private int nTex = 1; // the name of chosen texture
	
	@Override
	/**
	 * Defines buildings, textures, etc. at compile time (is called once)
	 * It also sets some default values
	 */
	public void init(GLAutoDrawable drawable) {		
		GL gl = drawable.getGL();
        gl.glClearColor(.2f, .2f, .5f, 0);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL.GL_MODELVIEW);

    //    addBuildings();
        addTextures(gl);
        
        gl.glEnable(GL.GL_DEPTH_TEST);
	}
	
	/**
	 * Creates and adds buildings to the buildings arraylist
	 */
	public void addBuildings(){
		buildings.add(new Putnam());
		buildings.add(new SmullinWalton());
		buildings.add(new Gatke());
		buildings.add(new Library());
	}
	
	/**
	 * Adds textures to openGl
	 */
	public void addTextures(GL gl){
		// TODO this should use ImageTexture, not MyImage - ImageTexture improves on how MyImage works and works inside Jars
		MyImage image = new MyImage("src/textures/CampusMap.jpg");
		
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
	 * Runs once per frame, generates the image to display
	 */
	public void display(GLAutoDrawable drawable) {
		handleControls();
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        eye.positionCamera(gl);
		
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
	 * What to do when the display is reshaped (resized)
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL gl = drawable.getGL();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60., (double) width / height, 0.1, 3000.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	@Override
	/**
	 * What to do when the display changes.  We do not use this method at all.
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
		// Nothing to do
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		keys.add(evt.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		keys.remove(evt.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// Nothing to do!
	}
	
	private void handleControls(){
    	if(keys.contains((int)'W')){ // forward
    		eye.dolly(moveSpeed);
    	}
    	if(keys.contains((int)'A')){ // left
    		eye.trackH(-moveSpeed);
    	}
    	if(keys.contains((int)'S')){ // backwards
    		eye.dolly(-moveSpeed);
    	}
    	if(keys.contains((int)'D')){ // right
    		eye.trackH(moveSpeed);
    	}
    }

	@Override
	public void mousePressed(MouseEvent evt) {
		lastX = evt.getX();
		lastY = evt.getY();
		turn = evt.getButton() == MouseEvent.BUTTON1;
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
	}

	@Override
	public void mouseDragged(MouseEvent evt) {
		if(turn){
			eye.rotateH(-(evt.getX()-lastX)*turnAngle);
			eye.pitch((evt.getY()-lastY)*turnAngle);
		}
		lastX = evt.getX();
		lastY = evt.getY();
	}

	@Override
	public void mouseMoved(MouseEvent evt) {
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
	}

	@Override
	public void mouseExited(MouseEvent evt) {
	}
}
