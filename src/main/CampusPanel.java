package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import util.Eye;
import util.ImageTexture;
import util.Util;
import util.Vector;
import buildings.Building;

import com.sun.opengl.util.GLUT;

/**
 * The Central class for the project, runs OpenGL and manages all the buildings and other
 * features.
 */
public class CampusPanel implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private GLU glu = new GLU();  // OpenGL Utility Library - used to set camera view
    private GLUT glut = new GLUT();  // OpenGL Utility Toolkit - contains teapot

	//glu.gluLookAt(-5.0, 5.0, -5.0, 3.0, 0.0, 3.0, 0.0, 1.0, 0.0);
	private Eye eye;
	private HashSet<Integer> keys = new HashSet<Integer>();

	private double moveSpeed = 10;
    private double turnAngle = .01;
	private int lastX;
	private int lastY;
	private boolean turn = false;
	private boolean track = false;
	
	private double lAngle = 60;
	
	private  ArrayList<Building> buildings = new ArrayList<Building>();
	
	private int[] names; // the array of texture names;
	private int nTex = 1; // the name of chosen texture
	
	//
	// OPENGL METHODS
	//
	
	@Override
	/**
	 * Defines buildings, textures, etc. at compile time (is called once)
	 * It also sets some default values
	 */
	public void init(GLAutoDrawable drawable) {
		// Setup Eye
		// TODO this could be better positioned, but is perfectly good for right now
		Vector pos = new Vector(Util.coordToGL((Util.CAMPUS_SE[0]+Util.CAMPUS_SW[0])/2, (Util.CAMPUS_SE[1]+Util.CAMPUS_SW[1])/2, 1000));
		Vector lookAt = new Vector(Util.coordToGL((Util.CAMPUS_NE[0]+Util.CAMPUS_NW[0])/2, (Util.CAMPUS_NE[1]+Util.CAMPUS_NW[1])/2, 0));
		eye = new Eye(pos, lookAt);
		eye.pitch(-.5);
		
		GL gl = drawable.getGL();
        gl.glClearColor(.2f, .2f, .5f, 0);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        
        gl.glEnable(GL.GL_COLOR_MATERIAL);  // uses glColor to set the color      
        gl.glEnable(GL.GL_LIGHT0);  
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_NORMALIZE);

        addBuildings(gl);
        addTextures(gl);
        
        gl.glEnable(GL.GL_DEPTH_TEST);
	}
	
	@Override
	/**
	 * Runs once per frame, generates the image to display
	 */
	public void display(GLAutoDrawable drawable) {
		handleControls();
        GL gl = drawable.getGL();
        
        // lightwork
        float[] lightPosition = {(float)(Util.feetToGL(1000)*Math.sin(lAngle)), (float)(Util.feetToGL(1000)*Math.abs(Math.cos(lAngle))), (float)Util.feetToGL(500), 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition,0);
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        eye.positionCamera(gl);
		
		drawBuildings(gl);
		drawGround(gl);
	}
	
	@Override
	/**
	 * Handles what happens when the display is reshaped (resized)
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL gl = drawable.getGL();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
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
	
	//
	// HELPER METHODS
	//
	
	/**
	 * Creates and adds buildings to the buildings arraylist
	 */
	public void addBuildings(GL gl){
		// The following block of code loads all children of Building in the buildings package into a hash map        
        // FIXME this will not work in a Jar, since it's not using getResource()
        File buildingDir = new File("bin/buildings");
        for(String file : buildingDir.list()){
        	file = file.replaceAll("\\.class", "");
        	if(!file.equals("Building")){
        		try {
					Class<?> c = Class.forName("buildings."+file);
					if(Building.class.isAssignableFrom(c)){ // if class is a building
						Building b = (Building) c.newInstance();
						b.init(gl);
						buildings.add(b);
					}
				} catch (ClassNotFoundException e) {
					// Do nothing - if it's not a Class, we don't care about it.
				} catch (InstantiationException e) {
					// Do nothing - if it failed to instantiate, we don't care about it.
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// Do nothing - if we weren't supposed to access the Class, we don't care about it.
				}
        	}
        }
	}
	
	/**
	 * Adds textures to openGl
	 */
	public void addTextures(GL gl){
		ImageTexture image = new ImageTexture("textures/CampusMap.jpg");
		
		names = new int[1];
        gl.glGenTextures(1, names, 0);
        // Assign each of the textures an ID
        image.bind(names[0], gl);

        nTex = names[0]; 
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
        gl.glEnable(GL.GL_TEXTURE_2D);
        
		gl.glBindTexture(GL.GL_TEXTURE_2D, names[0]);
		
        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2d(1,0);
        gl.glVertex3dv(Util.coordToGL(Util.CAMPUS_SW[0],Util.CAMPUS_SW[1],0),0);

        gl.glTexCoord2d(0,0);
        gl.glVertex3dv(Util.coordToGL(Util.CAMPUS_SE[0],Util.CAMPUS_SE[1],0),0);

        gl.glTexCoord2d(0,1);
        gl.glVertex3dv(Util.coordToGL(Util.CAMPUS_NE[0],Util.CAMPUS_NE[1],0),0);

        gl.glTexCoord2d(1,1);
        gl.glVertex3dv(Util.coordToGL(Util.CAMPUS_NW[0],Util.CAMPUS_NW[1],0),0);
        
        gl.glEnd();

        gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glPopMatrix();
	}
	
	//
	// LISTENER METHODS
	//

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
		track = evt.getButton() == MouseEvent.BUTTON3;
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
		if(track){
			eye.trackH(-(evt.getX()-lastX)*moveSpeed);
			eye.trackV((evt.getY()-lastY)*moveSpeed);
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
