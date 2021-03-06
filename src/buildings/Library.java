package buildings;

import javax.media.opengl.GL;

import util.Shape;
import util.Util;

/**
 * <h3>Mark O. Hatfield Library</h3>
 * 
 * <h4>Identifying Location - Southwest Corner</h4>
 * <p><strong>7546826.465E 472747.03N</strong></p>
 * 
 * <h4>Approximate Footprint</h4>
 * <p>187' on the west and north sides, at a right angle.
 * 155' on the east and south sides, meeting at an obtuse angle.
 * 
 * 
 * @author Michael Diamond
 */
public class Library extends Building {
    private double posEast = 7546826.465;
    private double posNorth = 472747.03;
    private double posElevation = 0; // TODO Get Elevation of building
    
    @Override
    public void init(GL gl) {
        coordinate = new double[]{posEast,posNorth,posElevation};
        midpoint = new double[]{Util.f(186,1)/2, Util.f(186,1)/2};
    }

    @Override
    public void draw(GL gl) {
        gl.glPushMatrix();
        
        Shape.Cube.setColor(Building.brick);
        // main building
        gl.glPushMatrix();
        gl.glScaled(Util.f(186,1), 40, Util.f(186,1));
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        // north outcrop - this position has not been measured yet - numbers are guesses
        gl.glPushMatrix();
        gl.glTranslated(60,0,-Util.f(186,1));
        gl.glRotated(45, 0, 1, 0);
        gl.glScaled(20, 40, 20);
        gl.glTranslated(-.5, 0, .5);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPopMatrix();
    }

}