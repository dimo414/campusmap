package landscape;

import javax.media.opengl.GL;

import util.Shape;

/**
 * <p>This class represents the ground of Willamette University.  Initially, it will be an elevation-accurate version
 * of the landscape, and if time is permitting will go on to include other details such as paths, trees, memorials,
 * and other features of campus which are not buildings.</p>
 * 
 * <p>This class should, at the very least, do the following:</p>
 * <ol>
 * <li>Load a set of 3D coordinates representing easting, northing, and elevation - from a file, for instance</li>
 * <li>With the easting and northing values, construct a 2D triangulation using a Delaunay Triangulation algorithm</li>
 * <li>Construct a triangular lattice of the ground and its elevation using util.Shape and the generated triangulation</li>
 * </ol>
 *  
 * @author Michael Diamond
 */
public class Ground {        
        private Shape shape = new Shape();

        /**
         * Constructs a representation of the ground
         */
        public Ground() {
        	shape = new Triangulator("landscape/elevations.txt").getShape();
        	shape.setColor(new float[]{.1f,.8f,.1f});
        }

        /**
         * draw the ground.
         * @param gl openGL object
         */
        public void draw(GL gl) {
            shape.draw(gl);
        }

}
