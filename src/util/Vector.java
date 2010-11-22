package util;

/**
 * An immutable representation of a 3D vector.
 * @author Michael Diamond
 */
public class Vector {
  private final double x;
  private final double y;
  private final double z;

  /** default vector */
  public Vector() { x = y = z = 0; }

  /** copy constructor */
  public Vector(Vector v) {
	  this.x = v.x;
	  this.y = v.y;
	  this.z = v.z;
  }

  /** standard constructor */
  public Vector(double x, double y, double z) {
	  this.x = x;
	  this.y = y;
	  this.z = z;
  }
  
  /** spherical vector constructor (r, theta, phi) */
  // double[] {r, theta, phi}
  public Vector(Vector from, double[] sphere){
	  double tx = sphere[0] * Math.sin(sphere[1]) * Math.cos(sphere[2]);
	  double ty = sphere[0] * Math.cos(sphere[1]);
	  double tz = sphere[0] * Math.sin(sphere[1]) * Math.sin(sphere[2]);
	  Vector temp = new Vector(tx,ty,tz);
	  Vector v = temp.add(from);
	  x = v.x;
	  y = v.y;
	  z = v.z;
  }
  
  /** array constructor */
  public Vector(double[] a){
	  this(a[0],a[1],a[2]);
  }

  /** array constructor */
  public Vector(float[] a){
	  this(a[0],a[1],a[2]);
  }

  /** Get the x component of the vector */
  public double x() { return x; }
  /** Get the y component of the vector */
  public double y() { return y; }
  /** Get the z component of the vector */
  public double z() { return z; }

  /** Set the x component */
  public Vector setX(double x) { return new Vector(x,y,z); }
  /** Set the y component */
  public Vector setY(double y) { return new Vector(x,y,z); }
  /** Set the z component */
  public Vector setZ(double z) { return new Vector(x,y,z); }
  
  /** Returns the spherical coordinates of the vector, with {0,0,0}
   * as the center of the sphere. */
  public double[] toSpherical(){
	  return toSpherical(new Vector());
  }
  
  /** Returns the spherical coordinates of the vector, with from
   * as the center of the sphere. */
  public double[] toSpherical(Vector from){
	  Vector s = this.minus(from);
	  return new double[] {
			  Math.sqrt(s.x*s.x + s.z*s.z + s.y*s.y),
			  Math.acos(s.y / Math.sqrt(s.x*s.x + s.z*s.z + s.y*s.y)),
			  Math.atan2(s.z, s.x)
	  };
  }

  /** Returns the length of the vector */
  public double length() {
    return Math.sqrt(this.dot(this));
  }
  
  /** Normalizes the vector to a length of 1 */
  public Vector normalize() {
    double len = length();
    if (len == 0.0) return new Vector(this);
    return scale(1.0f / len);
  }  

  /** Scales the vector by val */
  public Vector scale(double val) {
	  return new Vector(x*val,y*val,z*val);
  }

  /** Adds v to this vector */
  public Vector add(Vector v) {
	  return new Vector(x+v.x, y+v.y, z+v.z);
  }

  /** Subtracts v from this vector */
  public Vector minus(Vector v) {
	  return new Vector(x-v.x, y-v.y, z-v.z);
  }

  /** Returns the dot product of these vectors */
  public double dot(Vector v) {
	    return x * v.x + y * v.y + z * v.z;
  }

  /** Returns the cross product of these vectors */
  public Vector cross(Vector v) {
	  return new Vector(y * v.z - z * v.y, 
	  			z * v.x - x * v.z, 
	  			x * v.y - y * v.x);
  }
  
  /** Returns the distance between these two vectors */
  public double distance(Vector v){
	  return Math.sqrt( Math.pow(x-v.x,2) + Math.pow(y-v.y,2) + Math.pow(z-v.z,2) ); 
  }
  
  /** Indicates these two vectors are exactly equivalent */
  public boolean equals(Vector v){
	  return x == v.x && y == v.y && z == v.z;
  }
  
  /** Indicates these two vectors are approximately equivalent, given a precision p */
  public boolean equals(Vector v, double p){
	  return eq(x,v.x,p) && eq(y,v.y,p) && eq(z,v.z,p);
  }

  public String toString() {
    return "{" + x + ", " + y + ", " + z + "}";
  }
  
  private static boolean eq(double x, double y, double precision){
	  return Math.abs(x-y) < precision;
  }
}
