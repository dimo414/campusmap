package util;

/**
 * Contains static helper methods for use throughout the project.
 */
public class Util {
	/**
	 * <p>
	 * Takes a world coordinate (longitude, latitude, and elevation) and
	 * converts it to display in OpenGL. This method is the definitive
	 * conversion of real world positions to the internal positioning of objects
	 * in this application.
	 * </p>
	 * 
	 * <h4>Implementation notes</h4>
	 * <ul>
	 * <li>All buildings should be built from 150ft in vertical elevation. A
	 * building at 163ft should therefore be 13 feet off "the ground".</li>
	 * </ul>
	 * 
	 * <p>
	 * See: http://en.wikipedia.org/wiki/World_Geodetic_System,
	 * http://en.wikipedia.org/wiki/Longitude and
	 * http://en.wikipedia.org/wiki/Latitude
	 * </p>
	 * 
	 * @param latitude
	 *            The latitude of the point, in degrees (and fractions thereof)
	 * @param longitude
	 *            The longitude of the point, in degrees (and fractions thereof)
	 * @param elevation
	 *            The elevation of the point, from sea level
	 * @return an OpenGL coordinate (x,y,z)
	 */

	static double minX = Double.MIN_VALUE;
	static double maxX = Double.MAX_VALUE;
	static double minY = Double.MIN_VALUE;
	static double maxY = Double.MAX_VALUE;

	/**
	 * Takes coordinates of four corners of campus and 
	 * sets minimum and maximum x and y coordinates for scaling
	 * 
	 */
	public void setMinMax(double[][] vertices) {
		for (int i = 0; i < 4; i++) {
				if (vertices[i][0] < minX)
					minX = vertices[i][0];
				if (vertices[i][0] > maxX)
					maxX = vertices[i][0];
				if (vertices[i][1] < minY)
					minY = vertices[i][1];
				if (vertices[i][1] > maxY)
					maxY = vertices[i][1];
		}
	}

	/**
	 * Uses min and max to scale components between 0 and 1
	 * @return
	 */
	public static double[] scale(double[] component) {
		component[0] = (component[0] - minX) / (maxX - minX);
		component[1] = (component[1] - minY) / (maxY - minY);
		return component;
	}

	/**
	 * Sets coordinates on a scale of 0 to 1
	 * @param latitude
	 * @param longitude
	 * @param elevation
	 * @return
	 */
	public static double[] coordToGL(double longitude, double latitude,
			double elevation) {
		double[] coordinates = {longitude, latitude};
		coordinates = scale(coordinates);
		return new double[] { coordinates[0], coordinates[1], elevation };

	}

	/**
	 * Takes an angle in degrees, minutes, and seconds, and converts it to just
	 * degrees (and fractions thereof).
	 * "For example, the Eiffel Tower has a latitude of 48° 51' 29" N, where 48°
	 * refers to the number of degrees, 51' refers to the number of minutes, and
	 * 29" refers to the number of seconds. Alternatively, latitude may be
	 * measured entirely in degrees, e.g. 48.8583° N." From
	 * http://en.wikipedia.org/wiki/Latitude.
	 * 
	 * @param deg
	 *            the degrees from zero
	 * @param min
	 *            the minutes from that degree
	 * @param sec
	 *            the seconds from that minute
	 * @return the angle as a single double, see example above
	 */
	public static double minSecToDegrees(int deg, int min, double sec) {
		return 0;
	}

	/**
	 * Takes a measurement in feet, and converts it to the appropriate distance
	 * in OpenGL
	 * 
	 * @param feet
	 *            measurement (in feet)
	 * @return the distance in OpenGL units
	 */
	public static double feetToGL(double feet) {
		return feet;
	}

	/**
	 * Helper method to turn feet and inches into a double
	 * 
	 * @param feet
	 *            number of feet
	 * @param inches
	 * @return a double of feet and inches
	 */
	public static double f(int feet, double inches) {
		return feet + inches / 12.0;
	}
}
