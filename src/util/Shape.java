package util;

import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.HashMap;

import javax.media.opengl.GL;

import buildings.Building;

import com.sun.opengl.util.BufferUtil;

/**
 * <p>Utility class for creating simple shapes like cubes more efficiently (and simply)
 * than immediate mode.  Currently, this uses Vertex Arrays - Vertex Buffer objects should
 * potentially be easy to drop in.  Furthermore, you are currently limited to either triangle
 * or quad based shapes.  This also could potentially be improved on if needed.</p>
 * 
 * <h3>Usage</h3>
 * <p>Either use one of the existing static objects (see list below) or construct your own shape object for more unique shapes.</p>
 * 
 * <p>A shape (currently) consists of a set of quads and triangles, described by a list of vertices,
 * faces, normals, and colors.  Construct a new Shape and pass it this data, then call draw whenever
 * the shape is needed.  Shapes can be reused, therefore only one instance of a given shape should
 * ever be constructed.</p>
 * 
 * <p>The faces array should contain arrays of faces, each face is described by the index of the vertices
 * that make it up.  A length 3 array describes a triangular face, length 4 a quad face, and any greater
 * length describes a polygon (made of a triangle fan - therefore you must also specify a centerpoint for
 * the triangles to radiate from).  Since you can only specify one normal per face, do not use the triangle
 * fan to construct a fan that does not lie in a plane.</p>
 * 
 * <p>You can dynamically change a shape's color using setColor().  Since shapes are likely to be reused,
 * you may want to get the current color and reset it after you are done, otherwise all shapes drawn after
 * will retain the color you set.</p>
 * 
 * <h3>Shapes</h3>
 * <h4>Cube</h4>
 * <p>A unit cube drawn from the origin to 1,1,-1</p>
 * 
 * @author Michael Diamond
 */
public class Shape {    
    /**
     * A unit cube with standard normals drawn from the origin (v0: 0,0,0) to (v7: 1,1,-1)
     */
    public static final Shape Cube;
    
    /**
     * A unit triangular prism with standard normals drawn with the right
     * angle running along (0,0,0) to (0,0,-1)
     */
    public static final Shape RightTriangle;
    
    /**
     * A unit triangular prism with standard normals drawn such that it's base and height are 1
     */
    public static final Shape UnitTriangle;
    
    /**
     * A unit pyramid with standard normals drawn with a corner at (0,0,0)
     * and the tip at (.5,1,-.5)
     */
    public static final Shape Pyramid;
    
    /**
     * A unit octagonal prism with standard normals oriented vertically
     */
    public static final Shape Octagon;

    /**
     * A unit octagonal pyramid with standard normals oriented vertically
     */
    public static final Shape OctaPyramid;
    
    static {
        // CUBE
        //   v6------v7
        //  /|      /|
        // v2------v3|
        // | |     | |
        // | v4----|-v5
        // |/      |/
        // v0------v1
        
        float[][] CUBE_VERT = {{0,0,0}, {1,0,0},    {0,1,0}, {1,1,0},
            {0,0,-1}, {1,0,-1}, {0,1,-1}, {1,1,-1}};
        int[][] CUBE_FACE = {{0,1,3,2}, {1,5,7,3}, {5,4,6,7}, {4,0,2,6}, {2,3,7,6}, {0,1,5,4}};
        float[][] CUBE_NORM = {{0,0,1}, {1,0,0}, {0,0,-1}, {-1,0,0}, {0,1,0}, {0,-1,0}};
        
        Cube = new Shape(CUBE_VERT, CUBE_FACE, CUBE_NORM);
        
        // RIGHT TRIANGULAR PRISM
        float[][] TRIAG_VERT = {{0,0,0}, {1,0,0},{0,1,0}, {0,0,-1}, {1,0,-1}, {0,1,-1}}; 
        int[][] TRIAG_FACE = {{0,1,2},{3,4,5},{0,1,4,3},{1,4,5,2},{3,0,2,5}};
        float[][] TRIAG_NORM = {{0,0,1},{0,0,-1},{0,-1,0},new Vector(1,1,0).normalize().toFArray(),{-1,0,0}};
        
        RightTriangle = new Shape(TRIAG_VERT, TRIAG_FACE, TRIAG_NORM);
        
        // UNIT TRIANGULAR PRISM
        float[][] U_TRIAG_VERT = {{0,0,0},{1,0,0},{.5f,1,0},{0,0,-1},{1,0,-1},{.5f,1,-1}};
        int[][] U_TRIAG_FACE = {{0,1,2},{1,4,5,2},{2,5,3,0},{0,1,4,3},{3,4,5}};
        float[][] U_TRIAG_NORM = {{0,0,1},
                new Vector(0,0,-1).cross(new Vector(-.5,1,0)).toFArray(),
                new Vector(0,0,1).cross(new Vector(.5,1,0)).toFArray(),
                {0,-1,0},
                {0,0,-1}};
        
        UnitTriangle = new Shape(U_TRIAG_VERT,U_TRIAG_FACE,U_TRIAG_NORM);
        
        // PYRAMID
        float[][] PYRAMID_VERT = {{0,0,0},{1,0,0},{1,0,-1},{0,0,-1},{.5f,1,-.5f}};
        int[][] PYRAMID_FACE = {{0,1,4},{1,2,4},{2,3,4},{3,0,4},{0,1,2,3}};
        float[][] PYRAMID_NORM = {
                new Vector(1,0,0).cross(new Vector(-.5,1,-.5)).toFArray(),
                new Vector(0,0,-1).cross(new Vector(-.5,1,.5)).toFArray(),
                new Vector(-1,0,0).cross(new Vector(-.5,1,.5)).toFArray(),
                new Vector(0,0,1).cross(new Vector(.5,1,-.5)).toFArray(),
                {0,-1,0}};
                
        Pyramid = new Shape(PYRAMID_VERT, PYRAMID_FACE, PYRAMID_NORM);
        
        // OCTAGONAL PRISM
        float sq2 = (float)Math.sqrt(2);
        float a = 1/(1+sq2);
        float aSq = a/sq2;
        
        float[][] OCTA_VERT = {{.5f,0,-.5f},{aSq,0,0},{a+aSq,0,0},{1,0,-aSq},{1,0,-a-aSq},{a+aSq,0,-1},{aSq,0,-1},{0,0,-a-aSq},{0,0,-aSq},
                {aSq,1,0},{a+aSq,1,0},{1,1,-aSq},{1,1,-a-aSq},{a+aSq,1,-1},{aSq,1,-1},{0,1,-a-aSq},{0,1,-aSq},{.5f,1,-.5f}};
        int[][] OCTA_FACE = {{0,1,2,3,4,5,6,7,8,1},
                {1,2,10,9},{2,3,11,10},{3,4,12,11},{4,5,13,12},{5,6,14,13},{6,7,15,14},{7,8,16,15},{8,1,9,16},
                {17,9,10,11,12,13,14,15,16,9}
                };
        float[][] OCTA_NORM = {{0,-1,0},
                {0,0,1},{1,0,1},{1,0,0},{1,0,-1},{0,0,-1},{-1,0,-1},{-1,0,0},{-1,0,1},
                {0,1,0}
                };
        
        Octagon = new Shape(OCTA_VERT, OCTA_FACE,OCTA_NORM);
        
        // OCTAGONAL PYRAMID
        float[][] OCTAP_VERT = {{.5f,0,-.5f},
                {aSq,0,0},{a+aSq,0,0},{1,0,-aSq},{1,0,-a-aSq},{a+aSq,0,-1},{aSq,0,-1},{0,0,-a-aSq},{0,0,-aSq},
                {.5f,1,-.5f}};
        int[][] OCTAP_FACE = {{0,1,2,3,4,5,6,7,8,1},
                {1,2,9},{2,3,9},{3,4,9},{4,5,9},{5,6,9},{6,7,9},{7,8,9},{8,1,9}};
        float[][] OCTAP_NORM = {{0,-1,0},
                new Vector(1,0,0).cross(new Vector(0,1,-.5)).toFArray(),
                new Vector(1,0,-1).cross(new Vector(-.5,1,-.5)).toFArray(),
                new Vector(0,0,-1).cross(new Vector(-.5,1,0)).toFArray(),
                new Vector(-1,0,-1).cross(new Vector(-.5,1,.5)).toFArray(),
                new Vector(-1,0,0).cross(new Vector(0,1,.5)).toFArray(),
                new Vector(-1,0,1).cross(new Vector(.5,1,.5)).toFArray(),
                new Vector(0,0,1).cross(new Vector(.5,1,0)).toFArray(),
                new Vector(1,0,1).cross(new Vector(.5,1,-.5)).toFArray(),
                };
        
        OctaPyramid = new Shape(OCTAP_VERT, OCTAP_FACE, OCTAP_NORM);
    }
    
    
    
    
    //
    // Actual class starts here
    //
    
    private float[][] vertices;
    private float[][] colors = {Building.black};
    private int[][] faces;
    private float[][] normals;
    
    private int[] fanLength;
    private int[] fanDist;
    
    private int triangleCount;
    private int quadCount;
    private int fanCount;
    
    private FloatBuffer vertBuff;
    private FloatBuffer colorBuff;
    private FloatBuffer normalBuff;
    
    private HashMap<FAW,FloatBuffer> colorCache = new HashMap<>();
    
    /**
     * Constructs a new shape with no components
     */
    public Shape(){
        this(new float[][]{}, new int[][]{}, new float[][]{});
    }
    
    /**
     * Constructs a new shape
     * @param vert a 2D array of the vertices of the points
     * @param fac a 2D array of the faces - each face is the index of the vertex in the vertices array, either three or four points
     * @param norm a 2D array of the normals of each face
     */
    public Shape(float[][] vert, int[][] fac, float[][] norm){
        vertices = vert;
        faces = fac;
        normals = norm;
        
        if(faces.length != normals.length || (colors.length != 1 && colors.length <= faces.length))
            throw new RuntimeException("Faces, Normals, and Colors all need to be the same length.");
        
        int fCount = 0;
        for(int[] a : faces){
            if(a.length == 3){
                triangleCount+=3;
            }
            if(a.length == 4){
                quadCount+=4;
            }
            if(a.length > 4){
                fCount++;
                fanCount+=a.length;
            }
        }
        
        int total = (triangleCount+quadCount+fanCount)*3;
        
        fanLength = new int[fCount];
        fanDist = new int[fCount];
        
        normalBuff = BufferUtil.newFloatBuffer(total);
        colorBuff = BufferUtil.newFloatBuffer(total);
        vertBuff = BufferUtil.newFloatBuffer(total);
        
        // two separate loops for quads and triangles to put shapes in separate sections of buffer
        for(int f = 0; f < faces.length; f++){
            if(faces[f].length == 3){ // handle triangles first
                for(int i = 0; i < faces[f].length; i++){
                    normalBuff.put(normals[f]);
                    colorBuff.put(colors.length == 1 ? colors[0] : colors[f]);
                    vertBuff.put(vertices[faces[f][i]]);
                }
            }
        }
        for(int f = 0; f < faces.length; f++){
            if(faces[f].length == 4){ // now quads
                for(int i = 0; i < faces[f].length; i++){
                    normalBuff.put(normals[f]);
                    colorBuff.put(colors.length == 1 ? colors[0] : colors[f]);
                    vertBuff.put(vertices[faces[f][i]]);
                }
            }
        }
        int fanIndex = 0;
        for(int f = 0; f < faces.length; f++){
            if(faces[f].length > 4){ // triangle fan
                for(int i = 0; i < faces[f].length; i++){
                    normalBuff.put(normals[f]);
                    colorBuff.put(colors.length == 1 ? colors[0] : colors[f]);
                    vertBuff.put(vertices[faces[f][i]]);
                }
                fanLength[fanIndex] = faces[f].length;
                fanDist[fanIndex] = (fanIndex == 0 ? 0 : faces[f].length + fanDist[fanIndex-1]);
                fanIndex++;
            }
        }
        // potentially other polygons too?
                
        normalBuff.rewind();
        colorBuff.rewind();
        vertBuff.rewind();
        
        colorCache.put(new FAW(colors), colorBuff);
    }
    
    /**
     * <p>Sets the color of the object, should match the number of faces, in order</p>
     * 
     * <p>WARNING: Because of how OpenGL handles colors, the color used by immediate mode
     * is the same as the color used by vertex arrays and therefore this class.  This means
     * immediate mode will be set to the last color in the array that is drawn to the screen.
     * Therefore, if using a combination of this class and immediate mode, you may find that
     * you need to reset the immediate mode color after calling draw here.</p>
     * @param cols the list of colors per face
     */
    public void setColor(float[][] cols){
        colors = cols;

        FloatBuffer tempBuff = colorCache.get(new FAW(colors));
        if(tempBuff != null){
            colorBuff = tempBuff;
            return;
        }
        
        colorBuff = BufferUtil.newFloatBuffer(colorBuff.capacity());
        // two separate loops for quads and triangles to put shapes in separate sections of buffer
        for(int f = 0; f < faces.length; f++){
            if(faces[f].length == 3){ // handle triangles first
                for(int i = 0; i < faces[f].length; i++){
                    colorBuff.put(colors.length == 1 ? colors[0] : colors[f]);
                }
            }
        }
        for(int f = 0; f < faces.length; f++){
            if(faces[f].length == 4){ // now quads
                for(int i = 0; i < faces[f].length; i++){
                    colorBuff.put(colors.length == 1 ? colors[0] : colors[f]);
                }
            }
        }
        for(int f = 0; f < faces.length; f++){
            if(faces[f].length > 4){ // triangle fan
                for(int i = 0; i < faces[f].length; i++){
                    colorBuff.put(colors.length == 1 ? colors[0] : colors[f]);
                }
            }
        }
        colorBuff.rewind();
        
        colorCache.put(new FAW(colors), colorBuff);
    }
    
    /**
     * Set the color of the whole object, should be three indices, the color
     * @param col a color, three 0-1 floats
     */
    public void setColor(float[] col){
        setColor(new float[][]{col});
    }
    
    /**
     * Gets the current color
     * @return the color array of the object currently.
     */
    public float[][] getColor(){
        return colors;
    }
        
    /**
     * Draw the shape described
     * @param gl the GL object to draw with
     */
    public void draw(GL gl){
        if(vertices.length == 0)
            return;
        
        gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
 
        gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertBuff);
        gl.glColorPointer(3, GL.GL_FLOAT, 0, colorBuff);
        gl.glNormalPointer(GL.GL_FLOAT,0, normalBuff);
        
        // drawArrays count is num of points, not indices.
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, triangleCount);
        gl.glDrawArrays(GL.GL_QUADS, triangleCount, quadCount);
        for(int i = 0; i < fanLength.length; i++){
            gl.glDrawArrays(GL.GL_TRIANGLE_FAN, triangleCount+quadCount+fanDist[i], fanLength[i]);
        }
        
        gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
    }
    
    /**
     * Since arrays don't work as hash keys, we need to create a wrapper before
     * dropping them into our color cache
     */
    private final class FAW{
        public float[][] arr;
        public FAW(float[][] a){
            arr = a;
        }
        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof FAW))
                return false;
            FAW other = (FAW)obj;
            return arr == other.arr || Arrays.equals(arr, other.arr);
        }
    }
}
