package main;

/** An immutable 3 dimension vector. */
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
  
  // array constructor
  public Vector(double[] a){
	  this(a[0],a[1],a[2]);
  }
  
  // array constructor
  public Vector(float[] a){
	  this(a[0],a[1],a[2]);
  }

public double x() { return x; }
  public double y() { return y; }
  public double z() { return z; }

  public Vector setX(double x) { return new Vector(x,y,z); }
  public Vector setY(double y) { return new Vector(x,y,z); }
  public Vector setZ(double z) { return new Vector(x,y,z); }
  
  public double[] toSpherical(){
	  return toSpherical(new Vector());
  }
  
  public double[] toSpherical(Vector from){
	  Vector s = this.minus(from);
	  return new double[] {
			  Math.sqrt(s.x*s.x + s.z*s.z + s.y*s.y),
			  Math.acos(s.y / Math.sqrt(s.x*s.x + s.z*s.z + s.y*s.y)),
			  Math.atan2(s.z, s.x)
	  };
  }

  public double length() {
    return Math.sqrt(this.dot(this));
  }

  public Vector normalize() {
    double len = length();
    if (len == 0.0) return new Vector(this);
    return scale(1.0f / len);
  }  

  public Vector scale(double val) {
	  return new Vector(x*val,y*val,z*val);
  }

  public Vector add(Vector v) {
    return add(this,v);
  }
  
  public static Vector add(Vector a, Vector b){
	  return new Vector(a.x+b.x, a.y+b.y, a.z+b.z);
  }

  public Vector minus(Vector v) {
	  return minus(this,v);
  }

  public static Vector minus(Vector a, Vector b){
	  return new Vector(a.x-b.x, a.y-b.y, a.z-b.z);
  }

  public double dot(Vector v) {
	  return dot(this,v);
  }
  
  public static double dot(Vector a, Vector b){
	    return a.x * b.x + a.y * b.y + a.z * b.z;
  }

  public Vector cross(Vector v) {
    return cross(this,v);
  }

  public static Vector cross(Vector a, Vector b) {
	  return new Vector(a.y * b.z - a.z * b.y, 
			  			a.z * b.x - a.x * b.z, 
			  			a.x * b.y - a.y * b.x);
  }
  
  public double distance(Vector v){
	  return Math.sqrt( Math.pow(x-v.x,2) + Math.pow(y-v.y,2) + Math.pow(z-v.z,2) ); 
  }
  
  public boolean equals(Vector v){
	  return x==v.x && y==v.y && z==v.z;
  }

  public String toString() {
    return "{" + x + ", " + y + ", " + z + "}";
  }
  
  private static boolean eq(double x, double y){
	  double precision = .0000001;
	  return Math.abs(x-y) < precision;
  }
  public static void main(String[] args){
	  Vector v1 = new Vector(1,1,1);
	  Vector v2 = new Vector(2,2,2);
	  Vector v3 = new Vector(9.13951, -2.93253, 4.13607);
	  Vector v4 = new Vector(3.04894, 8.55975, -5.07081);
	  
	  // ADD AND LENGTH
	  
	  // http://www.wolframalpha.com/input/?i=vector+{1,1,1}+%2B+{2,2,2}
	  Vector sum = v1.add(v2);
	  if(!eq(sum.x,3) || !eq(sum.y,3) || !eq(sum.z,3))
		  throw new RuntimeException("Sum of "+v1+" and "+v2+" did not add up to {3,3,3} but "+sum);
	  if(!eq(sum.length(),5.196152422706631))
		  throw new RuntimeException("Length of "+sum+" is not 5.196152422706631 but "+sum.length());
	  // http://www.wolframalpha.com/input/?i=Vector+{9.13951,+-2.93253,+4.13607}+%2B+{3.04894,+8.55975,+-5.07081}
	  sum = v3.add(v4);
	  if(!eq(sum.x,12.18845) || !eq(sum.y,5.627219999) || !eq(sum.z,-0.93473999))
		  throw new RuntimeException("Sum of "+v3+" and "+v4+" did not add up to {12.18845,5.627219999,-0.93473999} but "+sum);
	  if(!eq(sum.length(),13.45725295885085))
		  throw new RuntimeException("Length of "+sum+" is not 13.45725295885085 but "+sum.length());
	  
	  // NORMALIZE
	  
	  // http://www.wolframalpha.com/input/?i=Vector+{9.13951,+-2.93253,+4.13607}
	  Vector norm = v3.normalize();
	  if(!eq(norm.length(),1))
		  throw new RuntimeException("Length of "+norm+" is not 1 but "+norm.length());
	  if(!eq(norm.x,0.87445433) || !eq(norm.y,-0.28057998) || !eq(norm.z,0.395732850))
		  throw new RuntimeException("Normalized vector "+norm+" is not {0.87445433, -0.28057998, 0.395732850}");
	  
	  // MINUS
	  
	  // http://www.wolframalpha.com/input/?i=vector+{9.13951,+-2.93253,+4.13607}+-+{3.04894,+8.55975,+-5.07081}
	  Vector dif = v3.minus(v4);
	  if(!eq(dif.x,6.09057) || !eq(dif.y,-11.49228) || !eq(dif.z,9.20688))
			  throw new RuntimeException("Difference of "+v3+" and "+v4+" did not add up to {6.09057, -11.49228, 9.20688} but "+dif);
	  if(!eq(dif.length(),15.93531241))
		  throw new RuntimeException("Length of "+dif+" is not 15.93531241 but "+dif.length());
	  
	  // DOT
	  
	  // http://www.wolframalpha.com/input/?i=vector+{1,1,1}+dot+{2,2,2}
	  double dot = v1.dot(v2);
	  if(!eq(dot,6))
		  throw new RuntimeException("Dot product of "+v1+" and "+v2+" is not 6 but "+dot);
	  // http://www.wolframalpha.com/input/?i=vector+{9.13951,+-2.93253,+4.13607}+dot+{3.04894,+8.55975,+-5.07081}
	  dot = v3.dot(v4);
	  if(!eq(dot,-18.20913116))
		  throw new RuntimeException("Dot product of "+v3+" and "+v4+" is not -18.20913116 but "+dot);
	  
	  // CROSS
	  
	  // http://www.wolframalpha.com/input/?i={1,+1,+1}+cross+{2,+2,+2}
	  Vector cross = v1.cross(v2);
	  if(!eq(cross.x,0) || !eq(cross.y,0) || !eq(cross.z,0))
		  throw new RuntimeException("Cross product of "+v1+" and "+v2+" is not {0,0,0} but }"+cross);
	  if(!eq(cross.length(),0))
		  throw new RuntimeException("Length of "+cross+" should be 0, not "+cross.length());
	  // http://www.wolframalpha.com/input/?i={9.13951,+-2.93253,+4.13607}+cross+{3.04894,+8.55975,+-5.07081}
	  cross = v3.cross(v4);
	  if(!eq(cross.x,-20.53342273) || !eq(cross.y,58.95534796) || !eq(cross.z,87.1730287))
		  throw new RuntimeException("Cross product of "+v1+" and "+v2+" is not {-20.53342273,58.95534796,87.1730287} but }"+cross);
	  if(!eq(cross.length(),107.22169296))
		  throw new RuntimeException("Length of "+cross+" should be 107.22169296, not "+cross.length());
	  
	  // DISTANCE
	  if(!eq(v3.minus(v4).length(),v3.distance(v4)))
		  throw new RuntimeException(v3.minus(v4).length()+" is not the same as distance between "+v3.distance(v4));
	  
	  System.out.println("Passed all tests.");
  }
}
