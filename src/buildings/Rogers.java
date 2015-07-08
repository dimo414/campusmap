package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * <h3>Rogers Music Center</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>SOMETHING E SOMETHING N</strong></p>
 * 
 * @author Brian Forbis
 */
public class Rogers extends Building{
    private double posEast = 7546930.978; //TODO Find position
    private double posNorth = 473005.738; //TODO Find position
    private double posElevation = 0; // TODO Get Elevation of building
    
    private double height = 45;

    @Override
    public void init(GL gl) {
        coordinate = new double[]{posEast,posNorth,posElevation};
        midpoint = new double[]{30,30};
    }
    
    @Override
    public void draw(GL gl) {
        gl.glPushMatrix();
        
        //Start Drawing, SouthWest Positions
        
        Shape.Cube.setColor(Building.brick);
        gl.glPushMatrix();
        gl.glScaled(20, height, 170+3./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(30, 0, 10);
        gl.glScaled(178+6./12, height, 10);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(20, 0, 0);
        gl.glScaled(168+6./12, height, 25);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(20, 0, -(25));
        gl.glScaled(99, height, 116+8./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(20, 0, -(141+8./12));
        gl.glScaled(91, height, 28+7./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(20, 0, -(170+3./12));
        gl.glScaled(79, height, 20);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPopMatrix();
    }
}