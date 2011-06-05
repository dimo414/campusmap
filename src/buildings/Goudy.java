package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Arts</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>7546339.868E 473053.088N</strong></p>

 * 
 * @author Brian Forbis
 */
public class Goudy extends Building{
	// FIXME Goudy is currently being positioned from the SW corner of the dumpster area
	// but the plotted point is from the SW corner of the building proper.
	private double posEast = 7546339.868; //TODO Confirm point
	private double posNorth = 473053.088; //TODO Confirm point
	private double posElevation = 0; // TODO Get Elevation of building
	private double height = 9+9./12;
	private double rheight = 19;
	/*
	Goudy Commons
	Main Hall south outcrop:	19'  F9-F16-F17
	Main Hall south base of roof:	24'4" -F8
	Main Hall south top of roof:	33'6" -F8 Center
	Roof standard height:			19' -F1->F7, F10->F15
	Roof penthouse:					~25' -F7
	Smokestack penthouse:			~30'
	Wall standard height:			9'9" -F1->F7, F10->F15 
	Dumpster wall:					8' F1-F2 
	 */

	@Override
	public void init(GL gl) {
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{170, 55};	
	}
	
	@Override
	public void draw(GL gl) {
		// TODO lots of little errors with the roofs
		// TODO add the penthouse
		gl.glPushMatrix();
		
		//Start Drawing, SouthWest Positions
		Shape.Cube.setColor(Building.brick);
		Shape.Pyramid.setColor(Building.tileRoof);
		Shape.UnitTriangle.setColor(Building.brickRoofTriangle);
		
		gl.glPushMatrix(); //Wall1
		gl.glScaled(Util.f(39,4), 8, 1);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall2
		gl.glScaled(1, 8, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall3
		gl.glTranslated(0, 0, -(3+15));
		gl.glScaled(1, 8, Util.f(40-3-15, 4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall4
		gl.glTranslated(0, 0, -Util.f(40, 4));
		gl.glScaled(6, 8, 1);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall5
		gl.glTranslated(6, 0, -Util.f(40, 4));
		gl.glScaled(1, 8, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall6
		gl.glTranslated(6, 0, -Util.f(43, 4));
		gl.glScaled(Util.f(33, 4), 8, 1);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//END NEW WALLS HERE=============================================================================
		gl.glPushMatrix(); //F3
		gl.glTranslated(Util.f(39, 4), 0, 0);
		gl.glScaled(Util.f(49, 4), height, 60);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3 Roof1
		gl.glTranslated(Util.f(39, 4), height, 0);
		gl.glRotated(90, 0, 1, 0);
		gl.glScaled(30, rheight-height, -Util.f(199,3+3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3 Roof2
		gl.glTranslated(Util.f(39, 4), height, -(30));
		gl.glRotated(90, 0, 1, 0);
		gl.glScaled(30, rheight-height, -Util.f(86, 3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();		
		
		gl.glPushMatrix(); //F3 Roof Fill In
		gl.glTranslated(Util.f(54, 4), height, -(15));
		gl.glScaled(Util.f(73, 3./8), rheight-height, 30);
		Shape.Cube.setColor(Building.tileRoof);
		Shape.Cube.draw(gl);
		Shape.Cube.setColor(Building.brick);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3 Roof Fill In2
		gl.glTranslated(Util.f(39, 4), height, -(15));
		gl.glScaled(30, rheight-height, 30);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F4
		gl.glTranslated(Util.f(88, 8), 0, 0);
		gl.glScaled(Util.f(47, 8+3./8), height, Util.f(61, 8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F5
		gl.glTranslated(Util.f(113, 4+3./8), 0, -Util.f(61, 8));
		gl.glScaled(23, height, Util.f(9, 4+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F6
		gl.glTranslated(Util.f(116, 4+3./8), 0, -Util.f(71, 3./8));
		gl.glScaled(20, height, Util.f(23, 11));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F6 Roof 
		gl.glTranslated(Util.f(116, 4+3./8), height, -Util.f(71, 3./8));
		gl.glScaled(20, rheight-height, Util.f(23, 11));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();

		gl.glPushMatrix(); //F6 Roof2
		gl.glTranslated(Util.f(116, 4+3./8), height, -(45));
		gl.glScaled(20, rheight-height, Util.f(37, 11.5+3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();

		Shape.Cube.setColor(Building.tileRoof);
		gl.glPushMatrix(); //F6 Roof3 
		gl.glTranslated(Util.f(126, 4+3./8), height, -(15));
		gl.glScaled(Util.f(112, 3), rheight-height, Util.f(67, 11.5+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		Shape.Cube.setColor(Building.brick);
		
		gl.glPushMatrix(); //F7
		gl.glTranslated(Util.f(136, 4+3./8), 0, 0);
		gl.glScaled(Util.f(102, 3), height, Util.f(97, 11+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F7 North Roof
		gl.glTranslated(Util.f(126, 4+3./8), height, -Util.f(93,23+3./8));
		gl.glRotated(-90, 0, 1, 0);
		gl.glScaled(Util.f(23,11), rheight-height, Util.f(112,3));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F8
		gl.glTranslated(Util.f(238, 7+3./8), 0, 3);
		gl.glScaled(Util.f(37, 11+5./8), Util.f(24,4), Util.f(103, 11+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F8 Roof
		gl.glTranslated(Util.f(238, 7+3./8), Util.f(24,4), 3);
		gl.glScaled(Util.f(37, 11+5./8), Util.f(9,2), Util.f(103, 11+3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F9
		gl.glTranslated(Util.f(276, 7), 0, -Util.f(27, 9));
		gl.glScaled(3, 19, Util.f(42, 5+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		
		gl.glPushMatrix(); //Northerly Extrusions -F10-F11-F12-F13
		gl.glTranslated(Util.f(144, 3./8), 0, -Util.f(97, 11+3./8));
		for(int i = 0; i < 4; i++){
		gl.glPushMatrix();
		gl.glTranslated(i*Util.f(24, 11+6./8), 0, 0);
		gl.glScaled(Util.f(15, 11+3./8), height, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Northerly Extrusion Roofs -F10-F11-F12-F13
		gl.glTranslated(Util.f(144, 3./8), 0, -Util.f(100, 11+3./8));
		for(int i = 0; i < 4; i++){
		gl.glPushMatrix();
		gl.glTranslated(i*Util.f(24, 11+6./8), height, 0);
		gl.glScaled(Util.f(15, 11+3./8), rheight-height, -15);
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F14
		gl.glTranslated(Util.f(178, 8), 0, 3);
		gl.glScaled(Util.f(18, 7.5), height, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F15
		gl.glTranslated(Util.f(209, 11.5), 0, 3);
		gl.glScaled(Util.f(18, 7.5), height, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F14 Roof
		gl.glTranslated(Util.f(178, 8), height, 8);
		gl.glScaled(Util.f(18, 7.5), rheight-height, 20);
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F15 Roof
		gl.glTranslated(Util.f(209, 11.5), height, 8);
		gl.glScaled(Util.f(18, 7.5), rheight-height, 20);
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		//Pillars
		gl.glPushMatrix();
		gl.glTranslated(Util.f(178,8), 0, 8);
		for(int i=0; i<2; i++){
			gl.glTranslated((i*(18+7.5/12+12+8./12)), 0, 0);
			for(int j=0; j<2; j++){
				gl.glPushMatrix();
				gl.glTranslated((j*(18+7.5/12 - 1.5)), 0, 0);
				gl.glScaled(Util.f(1,6), height, Util.f(1,6));
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
			}
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F16
		gl.glTranslated(Util.f(243, 11+3./8), 0, 6);
		gl.glScaled(Util.f(27, 3+5./8), 19, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F17
		gl.glTranslated(Util.f(243, 11+3./8), 0, -Util.f(100, 11+3./8));
		gl.glScaled(Util.f(27, 3+5./8), 19, 3);
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}
}