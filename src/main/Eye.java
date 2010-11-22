package main;

import javax.media.opengl.GL;

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
    
    public Eye(Vector e, Vector u, Vector v, Vector n){
    	eye = e;
    	right = u;
    	up = v;
    	look = n;
    	orig = new Vector[]{e,u,v,n};
    }
    
    public Eye(Vector e, Vector look){
    	this(e,look,new Vector());
    }
    
    public Eye(Vector e, Vector look, Vector diff){
    	eye = e;
    	up = new Vector(0,1,0);
    	lookAt(look);
    	rotationDif = diff;
    }
    
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
    
    public void setTumblePoint(Vector t){
    	tumblePoint = t;
    	lookAt(tumblePoint);
    	orig = new Vector[]{eye,right,up,look};
    }
    
    public Vector getTumblePoint(){
    	return tumblePoint;
    }
    
    public void trackH(double speed){
    	eye = eye.add(right.scale(speed));
    }
    
    public void trackV(double speed){
    	eye = eye.add(up.scale(speed));
    }
    
    public void dolly(double speed){
    	eye = eye.add(look.scale(speed));
    }
    
    public void roll(double angle){
    	right = right.add(up.scale(-angle)).normalize();
    	up = right.cross(look).normalize();
    }
    
    public void pitch(double angle){
    	look = look.add(up.scale(angle)).normalize();
    	up = right.cross(look).normalize();
    }
    
    public void yaw(double angle){
    	look = look.add(right.scale(angle)).normalize();
    	right = look.cross(up).normalize();
    }

    // like yaw, but always rotates around the y axis
	public void rotateH(double angle) {
		Vector tempUp = new Vector(0,1,0);
		Vector tempRight = tempUp.cross(look);
		look = look.add(tempRight.scale(angle)).normalize();
    	right = look.cross(tempUp).normalize();
	}
    
    public void tumbleH(double angle, boolean relative){
    	Vector look = tumblePoint.minus(rotationDif);
    	double[] sphere = eye.toSpherical(look);
    	sphere[2] = (relative ? sphere[2] : 0) + angle;
    	eye = new Vector(look,sphere);
    	lookAt(tumblePoint);
    }
    
    private static double tumbleMax = .000001;
    private static double tumbleMin = Math.PI-tumbleMax;
    public void tumbleV(double angle, boolean relative){
    	Vector look = tumblePoint.minus(rotationDif);
    	double[] sphere = eye.toSpherical(look);
    	sphere[1] = (relative ? sphere[1] : 0) + angle;
    	if(sphere[1] < tumbleMax)
    		sphere[1] = tumbleMax;
    	if(sphere[1] > tumbleMin)
    		sphere[1] = tumbleMin;
    	eye = new Vector(look,sphere);
    	lookAt(tumblePoint);
    }
    
    public void tumbleV(double angle){
    	tumbleV(angle,true);
    }
    
    public void tumbleH(double angle){
    	tumbleH(angle,true);
    }
    
    public double[] getTumblePosition(){
    	// NOT THE LOOK VECTOR
    	return eye.toSpherical(tumblePoint);
    }
    
    public void lookAt(Vector lookAt){
    	up = new Vector(0,1,0);
    	look = lookAt.minus(eye).normalize();
    	right = look.cross(up).normalize();
    	up = right.cross(look).normalize();
    	tumblePoint = lookAt;
    }
    
    public void reset(){
    	eye = orig[0];
    	right = orig[1];
    	up = orig[2];
    	look = orig[3];
    }
    
    public void reorient(){
    	up = new Vector(0,1,0);
    	right = look.cross(up).normalize();
    	look = up.cross(right).normalize();    	
    }
}
