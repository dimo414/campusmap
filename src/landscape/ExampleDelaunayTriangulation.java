package landscape;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.Animator;

/**
 * Implementation of a fast randomized incremental 2D Delaunay triangulation
 * algorithm with a run-time complexity of O(n*log(n)) written in java.
 * It enables you to triangulate even very large point sets in almost no time.
 * 
 * Copyright (C) 2010 Johannes Diemke. All rights reserved.
 * 
 * TODO: Implement exact arithmetic for ultimate robustness. 
 * 
 * @author trigger
 */
/*
 * Taken from http://www.informatik.uni-oldenburg.de/~trigger/ Dec. 2010
 * Website releases code for personal and educational use.
 */
public class ExampleDelaunayTriangulation implements GLEventListener {
	
	DelaunayTriangulator delaunayTriangulator;

	public static void main(String[] args) {
		Frame frame = new Frame("Delaunay Triangulation");
		
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(new ExampleDelaunayTriangulation());

		frame.add(canvas);
		frame.setSize(700, 480);

		final Animator animator = new Animator(canvas);

		frame.addWindowListener(new WindowAdapter() {
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
		frame.setVisible(true);
		animator.start();
	}

	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();

		gl.glDisable(GL.GL_CULL_FACE);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.setSwapInterval(1);
		
		ArrayList<Vector2D> pointSet = loadPointSet("src/landscape/examplepoints.conf");
		
		delaunayTriangulator = new DelaunayTriangulator(pointSet);
	
		// delaunay is in general faster if the points of the point set are
		// added in a random order. this is a custom permutation for
		// tracking down bugs.
		int[] permutation = new int[]{
                56,6,57,4,85,93,55,67,84,104,107,83,30,38,8,108,29,36,106,95,
                86,100,111,73,25,74,79,31,16,40,32,14,80,81,103,68,51,101,42,
                3,0,49,89,75,21,54,24,47,82,90,44,17,19,105,92,2,99,12,13,65,
                94,87,33,72,97,96,102,78,48,91,50,110,26,88,112,41,18,43,39,
                113,20,60,23,58,62,64,77,10,63,114,66,9,69,22,98,15,59,34,28,
                11,109,1,46,37,61,70,45,5,35,71,7,52,53,27,76
		};
		
		// shuffle with a permutation that leads to an erroneous delaunay
		// triangulation to track down bugs
		delaunayTriangulator.shuffle(permutation);
		
		// measure execution time
		long start = System.currentTimeMillis();	
		
		delaunayTriangulator.compute();
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Delaunay Triangulation generated in " + elapsed + " ms");
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL gl = drawable.getGL();
		GLU glu = new GLU();
		float aspect;

		if (height == 0) {
			height = 1;
		}
		
		aspect = (float) width / (float) height;

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -98.0f);
		
		// draw all triangles
		gl.glColor3f(1.65f, 0.25f, 0.25f);
		gl.glBegin(GL.GL_LINES);
		
		for(int i= 0; i < delaunayTriangulator.getTriangleSet().size(); i++) {
			Triangle triangle = delaunayTriangulator.getTriangleSet().get(i);
			Vector2D a = triangle.a;
			Vector2D b = triangle.b;
			Vector2D c = triangle.c;
			
			gl.glVertex2d(a.x, a.y);
			gl.glVertex2d(b.x, b.y);
			gl.glVertex2d(b.x, b.y);
			gl.glVertex2d(c.x, c.y);
			gl.glVertex2d(c.x, c.y);
			gl.glVertex2d(a.x, a.y);
		}
		
		gl.glEnd();
	
		// draw all points
		gl.glPointSize(5.0f);
		gl.glColor3f(0.2f, 1.2f, 0.25f);
		
		gl.glBegin(GL.GL_POINTS);
		
		for(Vector2D vector : delaunayTriangulator.getPointSet()) {
			gl.glVertex2d(vector.x, vector.y);
		}
		
		gl.glEnd();
	}
	
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}
	
	private ArrayList<Vector2D> loadPointSet(String fileName) {
		
		ArrayList<Vector2D> pointSet = new ArrayList<Vector2D>();
		int index = 0;
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
			
			String line;
		
			while ((line = input.readLine()) != null) {
				Scanner scanner = new Scanner(line);
			    scanner.useDelimiter(" ");
			    
			    if (scanner.hasNext()){
			      String identifier = scanner.next();
			      
			      if(identifier.equals("Ball")) {
			    	  String xpos = scanner.next();
				      String ypos = scanner.next();
			    	  pointSet.add(new Vector2D(Double.parseDouble(xpos), Double.parseDouble(ypos)));
			      }
			    }
			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		
		return pointSet;
	}
}