package buildings;

import java.util.Calendar;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Ford Hall</h3>
 * 
 * <h4>Identifying Location - Relative to Southwest Corner of Library</h4>
 * <p><strong>7546826.465E" 472747.03N"</strong></p>
 *  
 * @author Michael Diamond
 */
public class ClockTower extends Building {
	// Did some trig given the clocktower's position relative to the library
	// to figure out the easting and northing of the clocktower
	private double posEast = 7546826.465-Util.f(1,6.152);
	private double posNorth = 472747.03+Util.f(102, 1.29);
	private double posElevation = 0; // TODO Get Elevation of building
	
	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{Util.f(11,4)/2,Util.f(11,4)/2};
	}

	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();

		Shape.Cube.setColor(Building.brick);
		// SW Leg
		gl.glPushMatrix();
			gl.glScaled(Util.f(2,3.5), 43, Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.f(0,8),0,-Util.f(0,8));
			gl.glScaled(Util.f(2,3.5), Util.f(8,6), Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// S Face
		gl.glPushMatrix();
			gl.glTranslated(Util.f(0,8),Util.f(8,6),-Util.f(0,8));
			gl.glScaled(10,Util.f(3,6),Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(0,12,0);
			gl.glScaled(Util.f(11,4),3,Util.f(2,11.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.f(2,3.5),15,-Util.f(0,6));
			gl.glScaled(Util.f(6,8.5),26,Util.f(0,1));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// W Face
		gl.glPushMatrix();
			gl.glTranslated(Util.f(0,8),Util.f(8,6),-Util.f(0,8));
			gl.glScaled(Util.f(2,3.5),Util.f(3,6),10);
			Shape.Cube.setColor(Building.brick);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(0,12,0);
			gl.glScaled(Util.f(2,11.5),3,Util.f(11,4));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.f(0,6),15,-Util.f(2,3.5));
			gl.glScaled(Util.f(0,1),26,Util.f(6,8.5));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// NW Leg
		gl.glTranslated(0, 0, -Util.f(9,.5));
		gl.glPushMatrix();
			gl.glScaled(Util.f(2,3.5), 43, Util.f(2,3.5));
			Shape.Cube.setColor(Building.brick);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.f(0,8),0,Util.f(0,8));
			gl.glScaled(Util.f(2,3.5), Util.f(8,6), Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// N Face
		gl.glPushMatrix();
			gl.glTranslated(Util.f(0,8),Util.f(8,6),Util.f(0,8));
			gl.glScaled(10,Util.f(3,6),Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(0,12,Util.f(0,8));
			gl.glScaled(Util.f(11,4),3,Util.f(2,11.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.f(2,3.5),15,-Util.f(1,8.5));
			gl.glScaled(Util.f(6,8.5),26,Util.f(0,1));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// NW Leg
		gl.glTranslated(Util.f(9,.5),0,0);
		gl.glPushMatrix();
			gl.glScaled(Util.f(2,3.5), 43, Util.f(2,3.5));
			Shape.Cube.setColor(Building.brick);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(-Util.f(0,8),0,Util.f(0,8));
			gl.glScaled(Util.f(2,3.5), Util.f(8,6), Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		// SE Leg
		gl.glTranslated(0, 0, Util.f(9,.5));
		gl.glPushMatrix();
			gl.glScaled(Util.f(2,3.5), 43, Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(-Util.f(0,8),0,-Util.f(0,8));
			gl.glScaled(Util.f(2,3.5), Util.f(8,6), Util.f(2,3.5));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		// W Face
		gl.glPushMatrix();
			gl.glTranslated(-Util.f(0,8),Util.f(8,6),-Util.f(0,8));
			gl.glScaled(Util.f(2,3.5),Util.f(3,6),10);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(-Util.f(0,8),12,0);
			gl.glScaled(Util.f(2,11.5),3,Util.f(11,4));
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glTranslated(Util.f(1,8.5),15,-Util.f(2,3.5));
			gl.glScaled(Util.f(0,1),26,Util.f(6,8.5));
			Shape.Cube.setColor(Building.greenGlass);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
	
		gl.glTranslated(-Util.f(9,.5),0,0);
				
		// Top brick - the height of this is a guess
		gl.glPushMatrix();
			gl.glTranslated(0, 41, 0);
			gl.glScaled(Util.f(11,4), 2, Util.f(11,4));
			Shape.Cube.setColor(Building.brick);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();

		// Peak - the height of this is a guess
		gl.glPushMatrix();
			gl.glTranslated(0, 43, 0);
			gl.glScaled(Util.f(11,4), 1, Util.f(11,4));
			Shape.Cube.setColor(Building.oldCopper);
			Shape.Cube.draw(gl);
		gl.glPopMatrix();
			
		gl.glPushMatrix();
			gl.glTranslated(0, 44, 0);
			gl.glScaled(Util.f(11,4), 5, Util.f(11,4));
			Shape.Pyramid.setColor(Building.oldCopper);
			Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		// clock faces
		
		gl.glPushMatrix();
		gl.glTranslated(Util.f(5,8), 38, -Util.f(11,0));
		gl.glScaled(5, 5, 1);
		clock(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(0,4), 38, -Util.f(5,8));
		gl.glRotated(90, 0, 1, 0);
		gl.glScaled(5, 5, 1);
		clock(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Util.f(5,8), 38, -Util.f(0,4));
		gl.glRotated(180, 0, 1, 0);
		gl.glScaled(5, 5, 1);
		clock(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(11, 38, -Util.f(5,8));
		gl.glRotated(270, 0, 1, 0);
		gl.glScaled(5, 5, 1);
		clock(gl);
		gl.glPopMatrix();
		
//		gl.glPushMatrix();
//		gl.glTranslated(0, 700, 200);
//		gl.glScaled(500, 500, 1);
//		gl.glRotated(180, 0, 1, 0);
//		clock(gl);
//		gl.glPopMatrix();
				
		gl.glPopMatrix();
	}
	
	/**
	 * Generates a clockface for the clocktower.  This method could be migrated elsewhere
	 * if there is need for clocks anywhere else on campus
	 * @param gl
	 */
	public static void clock(GL gl){
		Calendar time = Calendar.getInstance();
	    double hourAngle = (time.get(Calendar.HOUR)+time.get(Calendar.MINUTE)/60.0)   /12.0*360;
	    double minAngle = (time.get(Calendar.MINUTE)+time.get(Calendar.SECOND)/60.0)  /60.0*360;
	    
	    gl.glPushMatrix();
	    gl.glScaled(1, 1, .1);
	    gl.glRotated(90, 1, 0, 0);
	    gl.glTranslated(-.5, 0, .5);
	    Shape.Octagon.setColor(Building.darkGreenGlass);
	    Shape.Octagon.draw(gl);
	    gl.glPopMatrix();
	    
	    Shape.Cube.setColor(Building.black);
		for(int i = 0; i < 12; i++){
		    gl.glPushMatrix();
			gl.glRotated(i*360/12,0,0,1);
		    gl.glTranslated(0, .35, 0);
		    gl.glScaled((i % 3 == 0 ? .03 : .01), .1, .1);
		    gl.glTranslated(-.5, 0, 0);
		    Shape.Cube.draw(gl);
		    gl.glPopMatrix();
		}
		// minute
		gl.glPushMatrix();
		gl.glRotated(minAngle,0,0,1);
	    gl.glScaled(.025, .5, .1);
	    gl.glTranslated(-.5, -.2, 0);
	    Shape.Cube.draw(gl);
	    gl.glPopMatrix();
		// hour
		gl.glPushMatrix();
		gl.glRotated(hourAngle,0,0,1);
	    gl.glScaled(.04, .3, .1);
	    gl.glTranslated(-.5, -.2, 0);
	    Shape.Cube.draw(gl);
	    gl.glPopMatrix();
	}

}