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
	private double posEast = 7546339.868; //TODO Confirm point
	private double posNorth = 473053.088; //TODO Confirm point
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
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
	public void draw(GL gl) {
		gl.glPushMatrix();
		
		// Universal positioning
		if(!drawOrigin){
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
			gl.glRotated(buildingRotation, 0, 1, 0);
		}
		else
			// this is the appx centerpoint of the building
			gl.glTranslated(-Util.feetToGL(170), 0, Util.feetToGL(55));
		// End universal positioning
		
		//Start Drawing, SouthWest Positions
		Shape.Cube.setColor(Building.brick);
		Shape.Pyramid.setColor(Building.tileRoof);
		Shape.Pyramid.setColor(Building.tileRoof);
		
		gl.glPushMatrix(); //Wall1
		gl.glScaled(Util.feetToGL(39,4), Util.feetToGL(8), Util.feetToGL(1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall2
		gl.glScaled(Util.feetToGL(1), Util.feetToGL(8), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall3
		gl.glTranslated(0, 0, -Util.feetToGL(3+15));
		gl.glScaled(Util.feetToGL(1), Util.feetToGL(8), Util.feetToGL(40-3-15, 4));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall4
		gl.glTranslated(0, 0, -Util.feetToGL(40, 4));
		gl.glScaled(Util.feetToGL(6), Util.feetToGL(8), Util.feetToGL(1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall5
		gl.glTranslated(Util.feetToGL(6), 0, -Util.feetToGL(40, 4));
		gl.glScaled(Util.feetToGL(1), Util.feetToGL(8), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Wall6
		gl.glTranslated(Util.feetToGL(6), 0, -Util.feetToGL(43, 4));
		gl.glScaled(Util.feetToGL(33, 4), Util.feetToGL(8), Util.feetToGL(1));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//END NEW WALLS HERE=============================================================================
		gl.glPushMatrix(); //F3
		gl.glTranslated(Util.feetToGL(39, 4), 0, 0);
		gl.glScaled(Util.feetToGL(49, 4), Util.feetToGL(height), Util.feetToGL(60));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3 Roof1
		gl.glTranslated(Util.feetToGL(39, 4), Util.feetToGL(height), 0);
		gl.glRotated(90, 0, 1, 0);
		gl.glScaled(Util.feetToGL(30), Util.feetToGL(rheight-height), -Util.feetToGL(199,3+3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3 Roof2
		gl.glTranslated(Util.feetToGL(39, 4), Util.feetToGL(height), -Util.feetToGL(30));
		gl.glRotated(90, 0, 1, 0);
		gl.glScaled(Util.feetToGL(30), Util.feetToGL(rheight-height), -Util.feetToGL(86, 3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();		
		
		gl.glPushMatrix(); //F3 Roof Fill In
		gl.glTranslated(Util.feetToGL(54, 4), Util.feetToGL(rheight), -Util.feetToGL(45));
		gl.glRotated(90, 0, 1, 0);
		gl.glRotated(180, 0, 0, 1);
		gl.glScaled(Util.feetToGL(30), Util.feetToGL(rheight-height), -Util.feetToGL(73, 3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F3 Roof Fill In2
		gl.glTranslated(Util.feetToGL(39, 4), Util.feetToGL(height), -Util.feetToGL(15));
		gl.glScaled(Util.feetToGL(30), Util.feetToGL(rheight-height), Util.feetToGL(30));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F4
		gl.glTranslated(Util.feetToGL(88, 8), 0, 0);
		gl.glScaled(Util.feetToGL(47, 8+3./8), Util.feetToGL(height), Util.feetToGL(61, 8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F5
		gl.glTranslated(Util.feetToGL(113, 4+3./8), 0, -Util.feetToGL(61, 8));
		gl.glScaled(Util.feetToGL(23), Util.feetToGL(height), Util.feetToGL(9, 4+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F6
		gl.glTranslated(Util.feetToGL(116, 4+3./8), 0, -Util.feetToGL(71, 3./8));
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(height), Util.feetToGL(23, 11));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F6 Roof 
		gl.glTranslated(Util.feetToGL(116, 4+3./8), Util.feetToGL(height), -Util.feetToGL(71, 3./8));
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(rheight-height), Util.feetToGL(23, 11));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();

		gl.glPushMatrix(); //F6 Roof2
		gl.glTranslated(Util.feetToGL(116, 4+3./8), Util.feetToGL(height), -Util.feetToGL(45));
		gl.glScaled(Util.feetToGL(20), Util.feetToGL(rheight-height), Util.feetToGL(37, 11.5+3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();

		Shape.Cube.setColor(Building.tileRoof);
		gl.glPushMatrix(); //F6 Roof3 
		gl.glTranslated(Util.feetToGL(126, 4+3./8), Util.feetToGL(height), -Util.feetToGL(15));
		gl.glScaled(Util.feetToGL(112, 3), Util.feetToGL(rheight-height), Util.feetToGL(67, 11.5+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		Shape.Cube.setColor(Building.brick);
		
		gl.glPushMatrix(); //F7
		gl.glTranslated(Util.feetToGL(136, 4+3./8), 0, 0);
		gl.glScaled(Util.feetToGL(102, 3), Util.feetToGL(height), Util.feetToGL(97, 11+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F7 North Roof
		gl.glTranslated(Util.feetToGL(126, 4+3./8), height, -Util.feetToGL(93,23+3./8));
		gl.glRotated(-90, 0, 1, 0);
		gl.glScaled(Util.feetToGL(23,11), Util.feetToGL(rheight-height), Util.feetToGL(112,3));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F8
		gl.glTranslated(Util.feetToGL(238, 7+3./8), 0, Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(37, 11+5./8), Util.feetToGL(24,4), Util.feetToGL(103, 11+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F8 Roof
		gl.glTranslated(Util.feetToGL(238, 7+3./8), Util.feetToGL(24,4), Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(37, 11+5./8), Util.feetToGL(9,2), Util.feetToGL(103, 11+3./8));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F9
		gl.glTranslated(Util.feetToGL(276, 7), 0, -Util.feetToGL(27, 9));
		gl.glScaled(Util.feetToGL(3), Util.feetToGL(19), Util.feetToGL(42, 5+3./8));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		
		gl.glPushMatrix(); //Northerly Extrusions -F10-F11-F12-F13
		gl.glTranslated(Util.feetToGL(144, 3./8), 0, -Util.feetToGL(97, 11+3./8));
		for(int i = 0; i < 4; i++){
		gl.glPushMatrix();
		gl.glTranslated(i*Util.feetToGL(24, 11+6./8), 0, 0);
		gl.glScaled(Util.feetToGL(15, 11+3./8), Util.feetToGL(height), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Northerly Extrusion Roofs -F10-F11-F12-F13
		gl.glTranslated(Util.feetToGL(144, 3./8), 0, -Util.feetToGL(100, 11+3./8));
		for(int i = 0; i < 4; i++){
		gl.glPushMatrix();
		gl.glTranslated(i*Util.feetToGL(24, 11+6./8), Util.feetToGL(height), 0);
		gl.glScaled(Util.feetToGL(15, 11+3./8), Util.feetToGL(rheight-height), Util.feetToGL(-15));
		Shape.UnitTriangle.draw(gl);
		gl.glPopMatrix();
		}
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F14
		gl.glTranslated(Util.feetToGL(178, 8), 0, Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(18, 7.5), Util.feetToGL(height), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F15
		gl.glTranslated(Util.feetToGL(209, 11.5), 0, Util.feetToGL(3));
		gl.glScaled(Util.feetToGL(18, 7.5), Util.feetToGL(height), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F14 Roof
		gl.glTranslated(Util.feetToGL(178, 8), Util.feetToGL(height), Util.feetToGL(8));
		gl.glScaled(Util.feetToGL(18, 7.5), Util.feetToGL(rheight-height), Util.feetToGL(20));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F15 Roof
		gl.glTranslated(Util.feetToGL(209, 11.5), Util.feetToGL(height), Util.feetToGL(8));
		gl.glScaled(Util.feetToGL(18, 7.5), Util.feetToGL(rheight-height), Util.feetToGL(20));
		Shape.Pyramid.draw(gl);
		gl.glPopMatrix();
		
		Shape.Cube.setColor(Building.white);//Pillars
		gl.glPushMatrix();
		gl.glTranslated(Util.feetToGL(178,8), 0, Util.feetToGL(8));
		for(int i=0; i<2; i++){
			gl.glTranslated(Util.feetToGL(i*(18+7.5/12+12+8./12)), 0, 0);
			for(int j=0; j<2; j++){
				gl.glPushMatrix();
				gl.glTranslated(Util.feetToGL(j*(18+7.5/12 - 1.5)), 0, 0);
				gl.glScaled(Util.feetToGL(1,6), Util.feetToGL(height), Util.feetToGL(1,6));
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
			}
		}
		gl.glPopMatrix();
		Shape.Cube.setColor(Building.brick);
		
		gl.glPushMatrix(); //F16
		gl.glTranslated(Util.feetToGL(243, 11+3./8), 0, Util.feetToGL(6));
		gl.glScaled(Util.feetToGL(27, 3+5./8), Util.feetToGL(19), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //F17
		gl.glTranslated(Util.feetToGL(243, 11+3./8), 0, -Util.feetToGL(100, 11+3./8));
		gl.glScaled(Util.feetToGL(27, 3+5./8), Util.feetToGL(19), Util.feetToGL(3));
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);		
	}

}
