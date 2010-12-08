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
	
	// these colors are all loose approximations, change them if you see fit
	/** Brick color */
	public static final float[] brick = {0.698039f, 0.133333f, 0.133333f};
	/** White paint color */
	public static final float[] white = {1f,1f,1f};
	/** Green glass color */
	public static final float[] greenGlass = {0x47/255f,0xC2/255f,0x9C/255f};
	/** Old copper color */
	public static final float[] oldCopper = {0x07/255f,0x32/255f,0x18/255f};
	/** Tile roof color - many of the buildings have different colored roofs, but this is a good start at least */
	public static final float[] tileRoof = {0x69/255f,0x69/255f,0x69/255f};
	
	protected boolean drawOrigin = false;
	
	/**
	 * If this method is passed true, the building will be drawn centered on the origin, for viewing of the individual building.
	 * Otherwise (passed false, or never called) the building will be drawn where it is supposed to be, relative to the campus.
	 * @param draw indicates if building should be drawn at origin (true) or in it's correct spatial position (false)
	 */
	public void drawAtOrigin(boolean draw){
		drawOrigin = draw;
	}
	
	/**
	 * Get's called at GL init time, work to properly construct the building should be done in this method, rather
	 * than in a constructor.
	 * @param gl the OpenGL object
	 */
	public abstract void init(GL gl);
	/**
	 * Called every time the object is to be drawn.  Draws the building in the correct location.
	 * @param gl the OpenGL object
	 */
	public abstract void draw(GL gl);
}
