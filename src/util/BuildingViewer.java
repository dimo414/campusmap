package util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import buildings.Building;

import com.sun.opengl.util.FPSAnimator;

/**
 * A very simple utility class for viewing buildings on their own, in order to look at them in more detail.
 * Additional features like dolly can be added as needed, but for now this simply draws the building and rotates it.
 * 
 * @author Michael Diamond
 */
public class BuildingViewer implements GLEventListener, ActionListener {
    
    private GLU glu = new GLU(); // OpenGL Utility Library - used to set camera view
    
    private Eye eye = new Eye(new Vector(0.0, 150.0, 300.0), new Vector(0,4,0));
    private HashMap<String, Building> buildings = new HashMap<String,Building>();
    private Building bld = null;
    
    private double lAngle = 60;
    private double rAngle = 0;
    
    private JFrame frame;
    private JPanel controls;
    private JComboBox choices;
    
    private BuildingViewer(JFrame f, JPanel c){
    	frame = f;
    	controls = c;
    }
   
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
        
        // The following block of code loads all children of Building in the buildings package into a hash map        
        // WARNING this will not work in a Jar, since it's not using getResource()
        File buildingDir = new File("bin/buildings");
        for(String file : buildingDir.list()){
        	file = file.replaceAll("\\.class", "");
        	if(!file.equals("Building")){
        		try {
					Class<?> c = Class.forName("buildings."+file);
					if(Building.class.isAssignableFrom(c)){ // if class is a building
						Building b = (Building) c.newInstance();
						b.init(gl);
						b.drawAtOrigin(true);
						buildings.put(file,b);
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
        
        // Add choices to panel
        String[] buildStrs = buildings.keySet().toArray(new String[]{});
        Arrays.sort(buildStrs);
        if(buildStrs.length > 0) // set default building
        	bld = buildings.get(buildStrs[0]);
        choices = new JComboBox(buildStrs);
        choices.addActionListener(this);
        controls.add(choices);
        frame.validate();
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
        
    	if(bld != null)
    		bld.draw(gl);
	    
	    gl.glPopMatrix();
	    // END DRAWING
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if(src == choices){
			bld = buildings.get(choices.getSelectedItem());
		}
	}
    
    //
    // MAIN
    //

    /**
     * Main Method, draws a building
     * @param args no args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Building Viewer");
        GLCanvas canvas = new GLCanvas();

        JPanel main = new JPanel(new BorderLayout());
        JPanel cont = new JPanel();
        
        BuildingViewer v = new BuildingViewer(frame,cont);
        canvas.addGLEventListener(v);
        
        main.add(canvas,BorderLayout.CENTER);
        main.add(cont,BorderLayout.SOUTH);
        
        frame.add(main);
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
