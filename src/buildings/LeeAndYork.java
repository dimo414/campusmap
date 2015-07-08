package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Lee and York Halls
 * 
 * Identifying Location - Southeast Corner
 * 7546282.605E 472812.244N
 * 
 * @author Jose Alvarado
 */
public class LeeAndYork extends Building{

    //1 - top left cube of Lee, 2 - top right cube, 3 - bottom left, 4 - bottom cube, 5 - bottom right cube
        
        private double posEast = 7546282.605;
        private double posNorth = 472812.244;
        private double posElevation = 0; // TODO Get Elevation of building

        @Override
        public void init(GL gl) {
            coordinate = new double[]{posEast,posNorth,posElevation};
            midpoint = new double[]{205/2,238/2};
        }
                
        @Override
        public void draw(GL gl) {
            gl.glPushMatrix();
            //TODO When building proper model, consider making the southwest corner the canonical point
            Shape.Cube.setColor(Building.brick);
            // 1st Cube
            gl.glPushMatrix();
            gl.glScaled(-29, 50, 109);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            // 2nd Cube
            gl.glPushMatrix();
            gl.glTranslated(-29,0,0);
            gl.glScaled(-20, 50, 63-36);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            // 3rd Cube
            gl.glPushMatrix();
            gl.glTranslated(-49,0,0);
            gl.glScaled(-49, 50, 63);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            // 4th Cube
            gl.glPushMatrix();
            gl.glTranslated(-(49+11),0,-63);
            gl.glScaled(-60, 50, 126);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            // 5th Cube
            gl.glPushMatrix();
            gl.glTranslated(27,0,-(109+23));
            gl.glScaled(-87, 50, 29);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            gl.glPopMatrix();
        }
}