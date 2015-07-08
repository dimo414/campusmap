package buildings;

import javax.media.opengl.GL;

import util.Shape;

/**
 * <h3>Smullin / Walton Hall</h3>
 * 
 * <h4>Identifying Location - SW Corner</h4>
 * <p><strong>7547138.978E 473205.738N</strong></p>

 * 
 * @author Brian Forbis
 */
public class SmullinWalton extends Building{
    private double posEast = 7547138.978;
    private double posNorth = 473205.738;
    private double posElevation = 0; // TODO Get Elevation of building
    
    private double height = 20;

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
        gl.glPushMatrix(); //South extrusion
        gl.glTranslated(37+3./12, 0, 8+9./12);
        gl.glScaled(65+1./12, height, 8+9./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Walton Hall
        gl.glScaled(139+7./12, height, 36+7./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Middle Section
        gl.glTranslated(29+11./12, 0, -(36+7./12));
        gl.glScaled(59+4./12, height, 62+2./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Bike Shed
        gl.glTranslated(89+3./12, 0, -(84+7./12));
        gl.glScaled(21+2./12, height, 14+2./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //Smullin Hall
        gl.glTranslated(-(9+8./12), 0, -(98+9./12));
        gl.glScaled(166, height, 35+6./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix(); //North Extrusion
        gl.glTranslated(26+2./12, 0, -(134+3./12));
        gl.glScaled(94+4./12, height, 18+6./12);
        Shape.Cube.draw(gl);
        gl.glPopMatrix();
        
        gl.glPopMatrix();
    }

}