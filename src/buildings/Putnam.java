package buildings;

import javax.media.opengl.GL;

import util.Shape;

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
		coordinate = new double[]{posEast,posNorth,posElevation};
		midpoint = new double[]{lengthEW/2, lengthNS/2};
	}

	@Override
	public void draw(GL gl) {//TODO find a better way to position the building at the origin or not
		gl.glPushMatrix();
		
		gl.glTranslated(balconyw, 0, -(balconyw));
		//Pillars
		Shape.Cube.setColor(Building.concrete);
		for(int i = 0; i < 8; i++){//NS Pillars
			for(int j = 0; j < 5; j++){
			gl.glPushMatrix();
			gl.glTranslated((i*(23+11./12)-.5), 0, (j==0 ? balconyw +.3 : j==1 ? .7 : j==2 ? -lengthNS : j==3 ? -(lengthNS+balconyw-.4):-117-8./12));
			gl.glScaled(1, j!=4 ? floor1+floor2+floor3 : floor1, 1);
			Shape.Cube.draw(gl);
			gl.glPopMatrix();
			}
		}

		for(int i = 0; i < 5; i++){//EW Pillars
			for(int j = 0; j < 2; j++){
				gl.glPushMatrix();
				gl.glTranslated(j==0 ? -balconyw +.3: lengthEW+balconyw-.3, 0, -(i * lengthNS/4));
				gl.glScaled(1, floor1+floor2+floor3, 1);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
			}
		}
		
		for(int i = 1; i < 4; i++){ //EW Pillars
			for(int j = 0; j < 2; j++){
				gl.glPushMatrix();
				gl.glTranslated(j==0 ? -.3: lengthEW+.3, 0, -(i * lengthNS/4));
				gl.glScaled(1, floor1+floor2+floor3, 1);
				Shape.Cube.draw(gl);
				gl.glPopMatrix();
			}
		}
//		Shape.Cube.setColor(Building.greenGlass);
//		for(int i = 0; i < 2; i++){
//			gl.glPushMatrix();
//			gl.glTranslated(i==0 ? -.3: lengthEW+.3, 0, -(lengthNS-(117+8./12-lengthNS)/2));
//			gl.glScaled(1, floor1, 1);
//			Shape.Cube.draw(gl);
//			gl.glPopMatrix();
//		}
		Shape.Cube.setColor(Building.brick);
		
		//First Floor
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(lengthEW, floor1, lengthNS);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //left square
		gl.glTranslated(0, 0, -(lengthNS));
		gl.glScaled(47+9./12, floor1, 22+1./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //right square
		gl.glTranslated(71+10./12, 0, -(95+7./12));
		gl.glScaled(95+9./12, floor1, 22+1./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //staircase
		gl.glTranslated(167+7./12, 0, -(72));
		gl.glScaled(12, floor1, 35+8./12);
		// TODO make this a right triangle, or otherwise more like a staircase
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//Second Floor
		gl.glTranslated(0, floor1, 0);
		
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(lengthEW, floor2, lengthNS);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//Balconies
		Shape.Cube.setColor(Building.concrete);
		gl.glPushMatrix(); //Balcony 3 - S
		gl.glTranslated(-(balconyw), 0, balconyw);
		gl.glScaled(193+5./12, balconyh, balconyw);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 1 - NW
		gl.glTranslated(-(1), 0, -(lengthNS));
		gl.glScaled(50, balconyh, 23+2./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 2 - W
		gl.glTranslated(-(balconyw), 0, 0);
		gl.glScaled(balconyw, balconyh, 108+6./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 4 - E
		gl.glTranslated(lengthEW, 0, 0);
		gl.glScaled(balconyw, balconyh, 108+6./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 5 - NE
		gl.glTranslated(71, 0, -(lengthNS));
		gl.glScaled(98, balconyh, 23+2./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix(); //Balcony 6 - N mid
		gl.glTranslated(49, 0, -(lengthNS));
		gl.glScaled(22, balconyh, balconyw);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//3rd Floor
		Shape.Cube.setColor(Building.brick);
		gl.glTranslated(0, floor2, 0);
		
		gl.glPushMatrix();//mainbuilding
		gl.glScaled(lengthEW, floor3, lengthNS);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		//Roof
		gl.glPushMatrix();
		Shape.Cube.setColor(Building.concrete);
		gl.glTranslated(-(roofOverhang), floor3, roofOverhang);
		gl.glScaled(194+4./12, roofh, 122+4./12);
		
		Shape.Cube.draw(gl);
		gl.glPopMatrix();
		
		gl.glPopMatrix();
	}

}