package main;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.media.opengl.GLCanvas;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.opengl.util.FPSAnimator;

/**
 * <p>The primary class, run this to run the application.</p>
 * <h4>Features:</h4>
 * <ul>
 * 	<li>Full screen mode.  Removes decoration and maintains awareness of original position.</li>
 * </ul>
 */
public class CampusFrame extends JFrame implements ActionListener, KeyListener {
	private CampusPanel campus;
	private GLCanvas canvas;
	private FPSAnimator animator;

	private boolean fullScreen;
	private Rectangle windowedBounds = null;

	private JPanel main;
	private JPanel bottomControls;
	private JButton fullScreenButton;

	/**
	 * Constructs the CampusFrame, populating it with the contents of CampusPanel and other components.
	 */
	public CampusFrame(boolean full){
		super("Willamette University");
		campus = new CampusPanel();
        canvas = new GLCanvas();
                
		initComponents(full);
	}

	private void initComponents(boolean full) {
		addWindowListener(new WindowAdapter() {
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
		
		addKeyListener(this);

		main = new JPanel(new BorderLayout());
		main.addKeyListener(this);
		main.addKeyListener(campus);
		
		canvas.setFocusable(true);
		canvas.requestFocusInWindow();
        canvas.addGLEventListener(campus);
        canvas.addKeyListener(this);
        canvas.addKeyListener(campus);
        canvas.addMouseListener(campus);
        canvas.addMouseMotionListener(campus);
        main.add(canvas, BorderLayout.CENTER);
        int fps = 20;
        animator = new FPSAnimator(canvas, fps);
        
        bottomControls = new JPanel();
        bottomControls.addKeyListener(this);
        bottomControls.addKeyListener(campus);
        main.add(bottomControls,BorderLayout.SOUTH);
        
        fullScreenButton = new JButton("Full Screen");
        fullScreenButton.addActionListener(this);
        fullScreenButton.addKeyListener(this);
        fullScreenButton.addKeyListener(campus);
        bottomControls.add(fullScreenButton);
        
        setSize(700,500);
        setContentPane(main);
        setLocationRelativeTo(null);
        windowedBounds = getBounds();
        fullScreen(full);
        setVisible(true);
        animator.start();
	}

	private void fullScreen(boolean fs) {
		GraphicsDevice device = getGraphicsConfiguration().getDevice();
		if(fullScreen == fs || !device.isFullScreenSupported())
			return; // nothing to do
		
		if(!fullScreen)
			windowedBounds = getBounds();
		// setting fullScreen at the beginning means further calls to do the same thing will do nothing
		// but repeated calls to switch back and forth would potentially cause race conditions.
		// Probably not a problem, but worth being aware of. 
		fullScreen = fs;
		
		// http://stackoverflow.com/questions/875132/how-to-call-setundecorated-after-a-frame-is-made-visible
		dispose();
        setUndecorated(fullScreen);
        setResizable(!fullScreen);
        setBounds(fullScreen ? getGraphicsConfiguration().getBounds() : windowedBounds);
		device.setFullScreenWindow(fullScreen ? this : null);

		fullScreenButton.setText(fullScreen ? "Exit Full Screen" : "Full Screen");
		
		validate();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if(src == fullScreenButton){
			fullScreen(!fullScreen);
		}
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		int code = evt.getKeyCode();
		if(code == KeyEvent.VK_ESCAPE){
			fullScreen(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// Nothing to do
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// Nothing to do
	}
	
	/**
	 * The program's primary main method.  Can take parameter "fullscreen" to start program
	 * in fullscreen mode.
	 * @param args the set of parameters which modify how the program runs.
	 */
	public static void main(String[] args){
		// necessary to do full screen (hardware accelerated) with JOGL
		// http://stackoverflow.com/questions/4245060/full-screen-swing-with-jogl/424736
		System.setProperty("sun.java2d.noddraw", "true");
		
		ArrayList<String> argsLs = new ArrayList<String>(Arrays.asList(args));
		
		boolean full = argsLs.contains("fullscreen");
		
		new CampusFrame(full);
	}
}
