package main;

/**
 * Contains static helper methods for use throughout the project.
 */
public class Util {
	/**
	 * Takes a world coordinate and converts it to display in OpenGL
	 * @param coord a coordinate (degrees, minutes, seconds)
	 * @return an OpenGL coordinate (x,y,z)
	 */
	public double[] coordToGL(double[] coord){
		return coord;
		
	}
	
	/**
	 * Takes a measurement in feet, and converts it to the appropriate distance in OpenGL
	 * @param feet measurement (in feet)
	 * @return the distance in OpenGL units
	 */
	public double feetToGL(double feet){
		return feet;
	}
}
