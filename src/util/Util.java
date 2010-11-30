package util;

/**
 * <p>Contains static helper methods for use throughout the project.  Notably, this class defines
 * how real world coordinates are converted to points to be drawn in OpenGL.  No other classes
 * should be aware of, nor concerned with, the underlying OpenGL coordinates, everything should be
 * specified in terms of real world coordinates and converted by these methods to the internal measurements.</p>
 *  * 
 * <p>The State Plane Coordinate System is a useful and common way to describe
 * locations at a scale approximately the size of a state.  It uses plane coordinates,
 * as opposed to the spherical latitude and longitude coordinates, which greatly simplify
 * calculations.  We are using the State Plane Coordinate System to describe positions in space.</p>
 * 
 * <p>Willamette University is covered by Zone 3601 - Oregon North.
 * Our coordinates are in international feet.</p>
 * 
 * <p>See: http://en.wikipedia.org/wiki/State_Plane_Coordinate_System</p>
 * <p>Useful Resource: http://www.earthpoint.us/StatePlane.aspx</p>
 */
public class Util {
	
	/** The location of the northwest corner of the main Willamette campus. */
	public static final double[] CAMPUS_NW = new double[]{7546523.888, 473824.754};
	/** The location of the northeast corner of the main Willamette campus. */
	public static final double[] CAMPUS_NE = new double[]{7547756.519, 473339.332};
	/** The location of the southeast corner of the main Willamette campus. */
	public static final double[] CAMPUS_SE = new double[]{7547272.347, 472011.012};
	/** The location of the southwest corner of the main Willamette campus. */
	public static final double[] CAMPUS_SW = new double[]{7546005.950, 472500.560};
	
	/**
	 * <p>
	 * Takes a State Plane coordinate point (easting, northing, and elevation) and
	 * converts it to display in OpenGL. This method is the definitive
	 * conversion of real world positions to the internal positioning of objects
	 * in this application.
	 * </p>
	 * 
	 * @param easting
	 *            The number of feet east of the Oregon North origin
	 * @param northing
	 *            The number of feet north of the Oregon North origin
	 * @param elevation
	 *            The elevation of the point, from sea level
	 * @return an OpenGL coordinate (x,y,z)
	 */
	public static double[] coordToGL(double easting, double northing, double elevation) {
		// The southwest corner of campus will be considered the origin for now
		// If the application is developed properly, changing the origin should be trivial
		return new double[]{easting-CAMPUS_SW[0],elevation,-(northing-CAMPUS_SW[1])};
	}

	/**
	 * Takes a measurement in feet, and converts it to the appropriate distance
	 * in OpenGL.  This method corresponds to coordToGL() - cordToGL(x,y,z)[0]+feetToGL(f) is exactly equivalent to cordToGL(x+f,y,z)[0].
	 * 
	 * @param feet
	 *            measurement (in feet)
	 * @return the distance in OpenGL units
	 */
	public static double feetToGL(double feet) {
		return feet;
	}

	/**
	 * Takes a measurement in feet and inches, and converts it to the appropriate distance
	 * in OpenGL.  This method corresponds to coordToGL() - cordToGL(x,y,z)[0]+feetToGL(f) is exactly equivalent to cordToGL(x+f,y,z)[0].
	 * 
	 * @param feet measurement (in feet).
	 * @param inches remainder (in inches) 
	 * @return the distance in OpenGL units
	 */
	public static double feetToGL(int feet, double inches) {
		return feetToGL(f(feet,inches));
	}

	/**
	 * Helper method to turn feet and inches into a double
	 * 
	 * @param feet number of feet
	 * @param inches number of inches
	 * @return a double of feet and inches
	 */
	public static double f(int feet, double inches) {
		return feet + inches / 12.0;
	}
}
