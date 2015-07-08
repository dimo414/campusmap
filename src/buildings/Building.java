package buildings;

import javax.media.opengl.GL;

/**
 * Abstract implementation of a building.  A building should position itself into the correct location and orientation
 * and should use the conversion methods in util.Util in order to translate real world measurements into OpenGL coordinates.
 * 
 * Although blueprints show the buildings oriented facing North, the campus itself faces slightly off of North.
 * Calculations using ArcMap indicate that campus is Appx. 21.2999 degrees clockwise off of North.  Therefore, assuming
 * this is accurate, you should rotate your building by that much -  use buildingRotation to make your job easier.
 */
public abstract class Building {
    /**
     * The standard amount a building developed facing north should be rotated in order to face the correct direction
     * on the map.
     */
    public static final double buildingRotation = -21.2999;
    
    // standard colors
    /** White color */
    public static final float[] white = {1,1,1};
    /** Black color */
    public static final float[] black = {0,0,0};
    
    // these colors are all loose approximations, change them if you see fit
    /** Brick color */
    public static final float[] brick = {0.698039f, 0.133333f, 0.133333f};
    /** Green glass color */
    public static final float[] greenGlass = {0x47/255f,0xC2/255f,0x9C/255f};
    /** Slightly darker green glass color */
    public static final float[] darkGreenGlass = {0x40/255f,0xB8/255f,0x94/255f};
    /** Old copper color */
    public static final float[] oldCopper = {0x07/255f,0x32/255f,0x18/255f};
    /** Tile roof color - many of the buildings have different colored roofs, but this is a good start at least */
    public static final float[] tileRoof = {0x69/255f,0x69/255f,0x69/255f};
    /** Concrete color */
    public static final float[] concrete = {0xC0/255f,0xC0/255f,0xC0/255f};
    
    // composite colors, for objects with common color combinations
    /** Composite color for right and unit triangles, brick on triangular faces and bottom quad, tile on top two quad faces */
    public static final float[][] brickRoofTriangle = {brick,tileRoof,tileRoof,brick,brick};
    
    
    
    /**
     * The real world coordinate of the building (usually the SW corner)
     * Building should be drawn such that the point at the origin has this coordinate.
     */
    protected double[] coordinate = {0,0,0};
    
    /**
     * The midpoint of the building relative to the canonical coordinate.  There is no vertical component.
     */
    protected double[] midpoint = {0,0};
    
    /**
     * Gets the real world coordinate of the canonical point of the building.
     * @return an array containing the easting, northing, and elevation of the point.
     */
    public double[] getCoord(){
        return coordinate;
    }
    
    /**
     * Gets the midpoint of the building, relative to the canonical point
     * @return an array containing the easting and northing from the canonical point.
     */
    public double[] getMidpoint(){
        return midpoint;
    }
    
    /**
     * Get's called at GL init time, work to properly construct the building should be done in this method, rather
     * than in a constructor, to ensure all work is done in one place.
     * @param gl the OpenGL object
     */
    public abstract void init(GL gl);
    /**
     * Called every time the object is to be drawn.  Draws the building in the correct location.
     * @param gl the OpenGL object
     */
    public abstract void draw(GL gl);
}
