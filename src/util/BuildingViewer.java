package util;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


import buildings.Building;
import buildings.Library;

import com.sun.opengl.util.FPSAnimator;

/**
 * A very simple utility class for viewing buildings on their own, in order to look at them in more detail.
 * Additional features like dolly can be added as needed, but for now this simply draws the building and rotates it.
 * 
 * @author Michael Diamond
 */
public class BuildingViewer implements GLEventListener {
    
    private GLU glu = new GLU(); // OpenGL Utility Library - used to set camera view
    
    private Eye eye = new Eye(new Vector(0.0, 150.0, 300.0), new Vector(0,4,0));
    private Building bld = new Library();
    
    private double lAngle = 60;
    private double rAngle = 0;
    
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        // Enable VSync
        gl.setSwapInterval(1);
        gl.glClearColor(.5f, .5f, .5f, 0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        
        gl.glEnable(GL.GL_COLOR_MATERIAL);  // uses glColor to set the color      
        gl.glEnable(GL.GL_LIGHT0);  
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_NORMALIZE);
        
        bld.init(gl); 
        bld.drawAtOrigin(true);
    }

   	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        
        glu.gluPerspective(60., (double) width / height, 0.1, 30000.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
    }

    public void display(GLAutoDrawable drawable) {    	
        GL gl = drawable.getGL();
        
      	gl.glEnable(GL.GL_LIGHT0);  
        gl.glEnable(GL.GL_LIGHTING);
        
        // lightwork
        float[] lightPosition = {1000.0f*(float)Math.sin(lAngle), 1000.0f*(float)Math.abs(Math.cos(lAngle)), 5.0f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition,0);
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        eye.positionCamera(gl);

        // DRAW
        gl.glPushMatrix();
        rAngle += .5;
    	gl.glRotated(rAngle, 0, 1, 0);
        
	    bld.draw(gl);
	    
	    gl.glPopMatrix();
	    // END DRAWING
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    //
    // MAIN
    //

    /**
     * Main Method, draws a building
     * @param args no args
     */
    public static void main(String[] args) {
        Frame frame = new Frame("Lighting");
        GLCanvas canvas = new GLCanvas();

        BuildingViewer v = new BuildingViewer();
        canvas.addGLEventListener(v);
        frame.add(canvas);
        frame.setSize(640, 480);
        int fps = 20;
        final FPSAnimator animator = new FPSAnimator(canvas, fps);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }
}
