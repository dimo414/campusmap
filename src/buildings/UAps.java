package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * University Apartments
 * @author Michael Diamond
 * @author Jose Alvarado
 */
public class UAps extends Building{

		private double posEast = 7545738.017;
		private double posNorth = 472840.221;
		private double posElevation = 0; // TODO Get Elevation of building
		
		private double crossWidth = Util.f(32,6);
		private double crossLength = Util.f(93,8);
		private double crossDepth = Util.f(11,8);
		private double stairWidth = Util.f(10,6);
		private double stairDepth = Util.f(4,2);
		private double roofBase = 52;
		private double roofShort = 65;
		private double roofTop = 72;

		// main body dimensions, a cross at 45 degrees from outside cross
		// width (visible side) = sqrt(2*(length/2-width/2-depth))
		// length (through building) = sqrt(2*(length/2+width/2-depth))
		private double mainWidth = Math.sqrt(2*Math.pow(crossLength/2-crossWidth/2-crossDepth,2));
		private double mainLength = Math.sqrt(2*Math.pow(crossLength/2+crossWidth/2-crossDepth,2));

		@Override
		public void init(GL gl) {
			coordinate = new double[]{posEast,posNorth,posElevation};
			midpoint = new double[]{184.0/2,177.0/2};
		}
		
		@Override
		public void draw(GL gl) {
			gl.glPushMatrix();
			
			Shape.Cube.setColor(Building.brick);
			Shape.UnitTriangle.setColor(Building.brickRoofTriangle);
			Shape.Pyramid.setColor(Building.tileRoof);
			// N/S Cross
			gl.glPushMatrix();
			gl.glScaled(crossWidth,roofBase,crossLength);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			gl.glPushMatrix();
			gl.glTranslated(0,roofBase,0);
			gl.glScaled(crossWidth,roofTop-roofBase,crossLength);
			Shape.UnitTriangle.draw(gl);
			gl.glPopMatrix();
			// E/W Cross
			gl.glPushMatrix();
			gl.glTranslated(-(crossLength/2-crossWidth/2),0,-(crossLength/2-crossWidth/2));
			gl.glScaled(crossLength,roofBase,crossWidth);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			gl.glPushMatrix();
			gl.glTranslated(-(crossLength/2-crossWidth/2),roofBase,-(crossLength/2-crossWidth/2));
			gl.glScaled(-crossLength,roofTop-roofBase,crossWidth);
			gl.glRotated(90, 0, 1, 0);
			Shape.UnitTriangle.draw(gl);
			gl.glPopMatrix();

			// body
			gl.glPushMatrix();
				gl.glTranslated(0, 0, -crossDepth);
				gl.glRotated(45, 0, 1, 0);
				// SW -> NE walls
				gl.glPushMatrix();
				gl.glScaled(mainLength, roofBase, mainWidth);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glTranslated(0,roofBase,0);
				gl.glScaled(mainLength,(roofShort-roofBase)/2,mainWidth);
				Shape.UnitTriangle.draw(gl);
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glTranslated(-stairDepth,roofBase,-(mainWidth/2-stairWidth/2));
				gl.glScaled(-mainLength-stairDepth*2,roofShort-roofBase,stairWidth);
				gl.glRotated(90, 0, 1, 0);
				Shape.UnitTriangle.draw(gl);
				gl.glPopMatrix();
				// SE -> NW walls
				gl.glPushMatrix();
				gl.glTranslated(mainLength/2-mainWidth/2,0,mainLength/2-mainWidth/2);
				gl.glScaled(mainWidth, roofBase, mainLength);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glTranslated(mainLength/2-mainWidth/2,roofBase,mainLength/2-mainWidth/2);
				gl.glScaled(-mainWidth,(roofShort-roofBase)/2,mainLength);
				gl.glRotated(90, 0, 1, 0);
				Shape.UnitTriangle.draw(gl);
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glTranslated(mainLength/2-stairWidth/2,roofBase,mainLength/2-mainWidth/2);
				gl.glScaled(stairWidth,roofShort-roofBase,mainLength);
				Shape.UnitTriangle.draw(gl);
				gl.glPopMatrix();
				// stairwell
				gl.glPushMatrix();
				gl.glTranslated(-stairDepth,0,-(mainWidth/2-stairWidth/2));
				gl.glScaled(mainLength+stairDepth*2, roofBase, stairWidth);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();

			gl.glPopMatrix();
			
			gl.glPopMatrix();
		}
	
}