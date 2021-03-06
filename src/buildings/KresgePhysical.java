package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * <h3>Kresge Playhouse / Physical Plant</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p>This is a guess - a better reference point would be the corner of Rogers.  
 * <strong>7547046.11E 472980.9N</strong></p>
 * 
 * @author Brian Forbis
 */
public class KresgePhysical extends Building{
    private double posEast = 7547046.11;
    private double posNorth = 472980.9;
    private double posElevation = 0; // TODO Get Elevation of building
    
    private double pheight = 16;
    private double kheight = 29;

    @Override
    public void init(GL gl) {
        coordinate = new double[]{posEast,posNorth,posElevation};
        midpoint = new double[]{50,90};     
    }

    @Override
    public void draw(GL gl) {
        gl.glPushMatrix();
        
        //Start Drawing, SouthWest Positions
        
        Shape.Cube.setColor(Building.brick);
        //Physical Plant
        gl.glPushMatrix(); //MainBuilding
        gl.glScaled(51, pheight, 55);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //East Extrusion (staircase)
        gl.glTranslated(51, 0, 0);
        gl.glScaled(32, pheight, 29);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glTranslated(0, 0, -(55));
        //Kresge Playhouse
        
        gl.glPushMatrix(); //Southern Portion
        gl.glScaled(119, kheight, 50+6./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Middle Portion (w/ Staircase)
        gl.glTranslated(0, 0, -(50+6./12));
        gl.glScaled(130+8./12, kheight, 37+4./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Northern Portion
        gl.glTranslated(0, 0, -(87+10./12));
        gl.glScaled(120, kheight, 21+2./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPopMatrix();
    }
}