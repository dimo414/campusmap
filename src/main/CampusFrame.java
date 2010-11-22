package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.opengl.util.FPSAnimator;

/**
 * The primary class, run this to run the application.
 */
public class CampusFrame extends JFrame implements ActionListener {
	private CampusPanel campus;
	private GLCanvas canvas;
	private FPSAnimator animator;
	private JPanel main;
	private JPanel bottomControls;
	private JButton fullScreenButton;

	public CampusFrame(){
		super("Willamette University");
		campus = new CampusPanel();
        canvas = new GLCanvas();
        
		initComponents();
	}

	private void initComponents() {
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

		main = new JPanel(new BorderLayout());
		
		canvas.setFocusable(true);
		canvas.requestFocusInWindow();
        canvas.addGLEventListener(campus);
        main.add(canvas, BorderLayout.CENTER);
        int fps = 20;
        animator = new FPSAnimator(canvas, fps);
        
        bottomControls = new JPanel();
        main.add(bottomControls,BorderLayout.SOUTH);
        
        fullScreenButton = new JButton("Full Screen");
        fullScreenButton.addActionListener(this);
        bottomControls.add(fullScreenButton);
        
        setSize(700,500);
        setContentPane(main);
        setLocationRelativeTo(null);
        setVisible(true);
        animator.start();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if(src == fullScreenButton){
			// TODO implement full screen
		}
	}
	
	public static void main(String[] args){
		new CampusFrame();
	}
}
