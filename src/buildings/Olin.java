package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * Olin Hall
 * 
 * Identifying Location - Southwest Corner
 * 7546446.576E 473514.619N
 * 
 * @author Jose Alvarado
 */
public class Olin extends Building{

    //1 - left cube of Olin, 2 - right cube of Olin
        
        private double posEast = 7546446.576;
        private double posNorth = 473514.619;
        private double posElevation = 0; // TODO Get Elevation of building

        @Override
        public void init(GL gl) {
            coordinate = new double[]{posEast,posNorth,posElevation};
            midpoint = new double[]{205/2,238/2};
        }
        
        @Override
        public void draw(GL gl) {
            gl.glPushMatrix();
            
            Shape.Cube.setColor(Building.brick);
            // 1st Cube
            gl.glPushMatrix();
            gl.glScaled(53+20, 50, 33);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            // 2nd Cube
            gl.glPushMatrix();
            gl.glTranslated(-13,0,-33);
            gl.glScaled(81, 50, 122);
            Shape.Cube.draw(gl);
            gl.glPopMatrix();
            
            gl.glPopMatrix();
        }
}