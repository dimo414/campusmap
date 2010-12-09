package buildings;

import javax.media.opengl.GL;


import util.Shape;
import util.Util;

/**
 * <h3>Putnam University Center</h3>
 * 
 * <h4>Identifying Location - Southwest Corner of Balcony</h4>
 * <p><strong>7546493.302-E 472808.16-N</strong></p>
 * 
 * <h4>Approximate Footprint</h4>
 * <p>95'7" on the west and east side.
 * 167'7" on the north and south side.
 * 2nd story balcony has width of 12'11" except 23'2" over bookstore and bistro.
 * Roof overhang is 13'4.5".
 * </p>
 * 
 * @author Brian Forbis
 */
public class Putnam extends Building {
	private double posEast = 7546493.302;
	private double posNorth = 472808.16;
	private double posElevation = 0; // TODO Get Elevation of building
	private double[] glPos;
	
	
	private double balconyw = 12+11./12;
	private double roofOverhang = 13+4.5/12;
	private double lengthEW = 167+7./12;
	private double lengthNS = 95+7./12;
	
	private double floor1 = 13+10./12; // the 1st floor stops at the bottom of the balcony
	private double balconyh = 1;
	private double floor2 = 21.5 /2;
	private double floor3 = 21.5/2;
	private double roofh = 6;
	@Override
	public void init(GL gl) {
		glPos = Util.coordToGL(posEast, posNorth, posElevation);
	}

	@Override
	public void draw(GL gl) {//TODO find a better way to position the building at the origin or not
		gl.glPushMatrix();
		
		// Universal positioning
		if(!drawOrigin){
			gl.glTranslated(glPos[0],glPos[1],glPos[2]);
			gl.glRotated(buildingRotation, 0, 1, 0);
		}
		else
			// this is the appx centerpoint of the building
			gl.glTranslated(-Util.feetToGL(lengthEW/2), 0, Util.feetToGL(lengthNS/2));
		// End universal positioning
		
		gl.glTranslated(Util.feetToGL(balconyw), 0, -Util.feetToGL(balconyw));
		//Pillars
		Shape.Cube.setColor(Building.concrete);
		for(int i = 0; i < 8; i++){//NS Pillars
			for(int j = 0; j < 5; j++){
			gl.glPushMatrix();
			gl.glTranslated(Util.feetToGL(i*(23+11./12)-.5), 0, Util.feetToGL(j==0 ? balconyw +.3 : j==1 ? .7 : j==2 ? -lengthNS : j==3 ? -(lengthNS+balconyw-.4):-117-8./12));
			gl.glScaled(1, Util.feetToGL(j!=4 ? floor1+floor2+floor3 : floor1), 1);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			}
		}

		for(int i = 0; i < 5; i++){//EW Pillars
			for(int j = 0; j < 2; j++){
				gl.glPushMatrix();
				gl.glTranslated(Util.feetToGL(j==0 ? -balconyw +.3: lengthEW+balconyw-.3), 0, -Util.feetToGL(i * lengthNS/4));
				gl.glScaled(1, Util.feetToGL(floor1+floor2+floor3), 1);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
			}
		}
		
		for(int i = 1; i < 4; i++){ //EW Pillars
			for(int j = 0; j < 2; j++){
				gl.glPushMatrix();
				gl.glTranslated(Util.feetToGL(j==0 ? -.3: lengthEW+.3), 0, -Util.feetToGL(i * lengthNS/4));
				gl.glScaled(1, Util.feetToGL(floor1+floor2+floor3), 1);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
			}
		}
//		Shape.Cube.setColor(Building.greenGlass);
//		for(int i = 0; i < 2; i++){
//			gl.glPushMatrix();
//			gl.glTranslated(Util.feetToGL(i==0 ? -.3: lengthEW+.3), 0, -Util.feetToGL(lengthNS-(117+8./12-lengthNS)/2));
//			gl.glScaled(1, Util.feetToGL(floor1), 1);
//			Shape.Cube.draw(gl);
//			gl.glPopMatrix();
//		}
		Shape.Cube.setColor(Building.brick);
		
		//First Floor
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(Util.feetToGL(lengthEW), Util.feetToGL(floor1), Util.feetToGL(lengthNS));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //left square
		gl.glTranslated(0, 0, -Util.feetToGL(lengthNS));
		gl.glScaled(Util.feetToGL(47+9./12), Util.feetToGL(floor1), Util.feetToGL(22+1./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //right square
		gl.glTranslated(Util.feetToGL(71+10./12), 0, -Util.feetToGL(95+7./12));
		gl.glScaled(Util.feetToGL(95+9./12), Util.feetToGL(floor1), Util.feetToGL(22+1./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //staircase
		gl.glTranslated(Util.feetToGL(167+7./12), 0, -Util.feetToGL(72));
		gl.glScaled(Util.feetToGL(12), Util.feetToGL(floor1), Util.feetToGL(35+8./12));
		// TODO make this a right triangle, or otherwise more like a staircase
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//Second Floor
		gl.glTranslated(0, Util.feetToGL(floor1), 0);
		
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(Util.feetToGL(lengthEW), Util.feetToGL(floor2), Util.feetToGL(lengthNS));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//Balconies
		Shape.Cube.setColor(Building.concrete);
		gl.glPushMatrix(); //Balcony 3 - S
		gl.glTranslated(-Util.feetToGL(balconyw), 0, Util.feetToGL(balconyw));
		gl.glScaled(Util.feetToGL(193+5./12), Util.feetToGL(balconyh), Util.feetToGL(balconyw));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 1 - NW
		gl.glTranslated(-Util.feetToGL(1), 0, -Util.feetToGL(lengthNS));
		gl.glScaled(Util.feetToGL(50), Util.feetToGL(balconyh), Util.feetToGL(23+2./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 2 - W
		gl.glTranslated(-Util.feetToGL(balconyw), 0, 0);
		gl.glScaled(Util.feetToGL(balconyw), Util.feetToGL(balconyh), Util.feetToGL(108+6./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 4 - E
		gl.glTranslated(Util.feetToGL(lengthEW), 0, 0);
		gl.glScaled(Util.feetToGL(balconyw), Util.feetToGL(balconyh), Util.feetToGL(108+6./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 5 - NE
		gl.glTranslated(Util.feetToGL(71), 0, -Util.feetToGL(lengthNS));
		gl.glScaled(Util.feetToGL(98), Util.feetToGL(balconyh), Util.feetToGL(23+2./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 6 - N mid
		gl.glTranslated(Util.feetToGL(49), 0, -Util.feetToGL(lengthNS));
		gl.glScaled(Util.feetToGL(22), Util.feetToGL(balconyh), Util.feetToGL(balconyw));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//3rd Floor
		Shape.Cube.setColor(Building.brick);
		gl.glTranslated(0, Util.feetToGL(floor2), 0);
		
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(Util.feetToGL(lengthEW), Util.feetToGL(floor3), Util.feetToGL(lengthNS));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//Roof
		gl.glPushMatrix();
		Shape.Cube.setColor(Building.concrete);
		gl.glTranslated(-Util.feetToGL(roofOverhang), Util.feetToGL(floor3), Util.feetToGL(roofOverhang));
		gl.glScaled(Util.feetToGL(194+4./12), Util.feetToGL(roofh), Util.feetToGL(122+4./12));
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}
