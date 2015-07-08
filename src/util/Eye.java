package util;

import javax.media.opengl.GL;

/**
 * A self contained camera controller, maintains awareness of camera position and orientation,
 * and allows for detailed control of movement.
 * @author Michael Diamond
 */
public class Eye {
    // Position
    private Vector eye;
    // U
    private Vector right;
    // V
    private Vector up;
    // N
    private Vector look;
    
    private Vector[] orig;
    
    private Vector tumblePoint = new Vector(0,10,0);
    private Vector rotationDif = new Vector(0,15,0);
    
    /** Constructs an eye with a given position and UVN vectors.
     * @param e the position of the eye
     * @param u the U vector - right vector
     * @param v the V vector - up vector
     * @param n the N vector - the look vector
     */
    public Eye(Vector e, Vector u, Vector v, Vector n){
        eye = e;
        right = u;
        up = v;
        look = n;
        orig = new Vector[]{e,u,v,n};
    }
    
    /**
     * Constructs an eye with a given position and look vector - orientation is always up
     * @param e position of eye
     * @param look direction to look
     */
    public Eye(Vector e, Vector look){
        this(e,look,new Vector());
    }
    
    /**
     * Constructs an eye with a given position and look vector - orientation is always up.
     * Specify a vector away from the look point to tumble from - makes tumbling slightly prettier.
     * @param e position of eye
     * @param look direction to look
     * @param diff a point relative to look to tumble around 
     */
    public Eye(Vector e, Vector look, Vector diff){
        eye = e;
        up = new Vector(0,1,0);
        lookAt(look);
        rotationDif = diff;
    }
    
    /**
     * instead of using gl.gluLookAt() this will position the camera and orient it correctly.
     * @param gl a GL object
     */
    // From Brian Mock's myLookAt()
    public void positionCamera(GL gl){
        double[] mat;

        // Remember, this is transposed...
        mat = new double[] {
            right.x(), up.x(), -look.x(), 0,
            right.y(), up.y(), -look.y(), 0,
            right.z(), up.z(), -look.z(), 0,
            0,     0,      0,     1
        };

        gl.glMultMatrixd(mat, 0);
        gl.glTranslated(
            -eye.x(),
            -eye.y(),
            -eye.z()
        );
    }
    
    /**
     * Sets the point around which to tumble
     * @param t a vector indicating the point to tumble
     */
    public void setTumblePoint(Vector t){
        tumblePoint = t;
        lookAt(tumblePoint);
        orig = new Vector[]{eye,right,up,look};
    }
    
    /**
     * Returns the point being tumbled around
     * @return a vector indicating the point being tumbled around
     */
    public Vector getTumblePoint(){
        return tumblePoint;
    }
    
    /**
     * Returns the spherical coordinates of the eye, relative to the tumble point
     * @return an array of the spherical coordinates {r, theta, phi}
     */
    public double[] getTumblePosition(){
        // NOT THE LOOK VECTOR
        return eye.toSpherical(tumblePoint);
    }
    
    /**
     * Tracks along the horizontal by a given speed
     * @param speed the amount to track
     */
    public void trackH(double speed){
        eye = eye.add(right.scale(speed));
    }
    
    /**
     * Tracks along the vertical by a given speed
     * @param speed the amount to track
     */
    public void trackV(double speed){
        Vector temp = eye.add(up.scale(speed));
        if(temp.y() > 1){
            eye = temp;
        }
    }
    
    /**
     * Dolly (zoom) in and out
     * @param speed the amount to dolly
     */
    public void dolly(double speed){
        Vector temp = eye.add(look.scale(speed));
        if(temp.y() > 1){
            eye = eye.add(look.scale(speed));
        }
    }
    
    /**
     * Roll - turn around the look vector
     * @param angle the angle (0-1) to turn
     */
    public void roll(double angle){
        right = right.add(up.scale(-angle)).normalize();
        up = right.cross(look).normalize();
    }
    
    /**
     * Pitch - turn around the right vector
     * @param angle the angle (0-1) to turn
     */
    public void pitch(double angle){
        look = look.add(up.scale(angle)).normalize();
        up = right.cross(look).normalize();
    }
    
    /**
     * Yaw - turn around the up vector
     * @param angle the angle (0-1) to turn
     */
    public void yaw(double angle){
        look = look.add(right.scale(angle)).normalize();
        right = look.cross(up).normalize();
    }
    
    /**
     * Rotate in the vertical, works like pitch, but stops before reaching straight up
     * or straight down.  This prevents poor behavior when using with rotateH.
     * @param angle the angle (0-1) to turn
     */
    public void rotateV(double angle){
        // need to determine if the rotation would put us too close to vertical orientation
        // we use a fixed angle to ensure a minimum distance from vertical
        double testAngle = Math.copySign(.25, angle);
        Vector testLook = look.add(up.scale(testAngle)).normalize();
        Vector tempLook = look.add(up.scale(angle)).normalize();
        if(look.distance(tempLook) > look.distance(testLook))
            testLook = tempLook;
        // test if x or z switch signs - that is, it tries to move past vertical
        if(look.x() > 0 != testLook.x() > 0 || look.z() > 0 != testLook.z() > 0){
            return;
        }
        look = tempLook;
        up = right.cross(look).normalize();
    }

    /**
     * Rotate in the horizontal, works like yaw, but turns around the y axis
     * @param angle the angle (0-1) to turn
     */
    // like yaw, but always rotates around the y axis
    public void rotateH(double angle) {
        Vector tempUp = new Vector(0,1,0);
        look = look.add(right.scale(angle)).normalize();
        right = look.cross(tempUp).normalize();
        up = right.cross(look).normalize();
    }
    
    /**
     * Tumble - Rotates around the tumble point in a large sphere in the horizontal.
     * Reorients the camera to looking at the tumble point if necessary. 
     * @param angle the angle (in degrees) to rotate, or if relative is false, the actual angle to position at
     * @param relative if true, rotate by angle, if false the angle (in degrees) to be positioned at
     */
    public void tumbleH(double angle, boolean relative){
        Vector lookAt = tumblePoint.minus(rotationDif);
        double[] sphere = eye.toSpherical(lookAt);
        sphere[2] = (relative ? sphere[2] : 0) + angle;
        eye = new Vector(lookAt,sphere);
        lookAt(tumblePoint);
    }
    
    
    /**
     * Tumble - Rotates around the tumble point in a large sphere in the vertical.
     * Reorients the camera to looking at the tumble point if necessary. 
     * @param angle the angle (in degrees) to rotate, or if relative is false, the actual angle to position at
     * @param relative if true, rotate by angle, if false the angle (in degrees) to be positioned at
     */
    public void tumbleV(double angle, boolean relative){
        Vector lookAt = tumblePoint.minus(rotationDif);
        double[] sphere = eye.toSpherical(lookAt);
        sphere[1] = (relative ? sphere[1] : 0) + angle;
        if(sphere[1] < tumbleMax)
            sphere[1] = tumbleMax;
        if(sphere[1] > tumbleMin)
            sphere[1] = tumbleMin;
        eye = new Vector(lookAt,sphere);
        lookAt(tumblePoint);
    }
    private static double tumbleMax = .000001;
    private static double tumbleMin = Math.PI-tumbleMax;
    
    /**
     * Tumbles around the tumble point vertically
     * @param angle the angle (in degrees) to tumble by
     */
    public void tumbleV(double angle){
        tumbleV(angle,true);
    }

    /**
     * Tumbles around the tumble point horizontally
     * @param angle the angle (in degrees) to tumble by
     */
    public void tumbleH(double angle){
        tumbleH(angle,true);
    }
    
    /**
     * Tells the camera to look at a given point
     * @param lookAt the point vector to look at
     */
    public void lookAt(Vector lookAt){
        up = new Vector(0,1,0);
        look = lookAt.minus(eye).normalize();
        right = look.cross(up).normalize();
        up = right.cross(look).normalize();
        tumblePoint = lookAt;
    }
    
    /**
     * Resets the eye to it's original position and orientation
     */
    public void reset(){
        eye = orig[0];
        right = orig[1];
        up = orig[2];
        look = orig[3];
    }
    
    /**
     * Reorients the eye so the up vector is the y axis
     */
    public void reorient(){
        up = new Vector(0,1,0);
        right = look.cross(up).normalize();
        look = up.cross(right).normalize();     
    }
}
