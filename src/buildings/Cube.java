package buildings;

import javax.media.opengl.GL;

/**
 * Create a cube geometry with center at (.5,.5,.5) and 
 * length of side = 1.
 */
public class Cube {

	int height = 336, width = 442;
    double vertices[][] = {{0, 0, 0}, {0, 0, height }, {0, .1, 0},
            {0, .1, height }, {width , 0, 0}, {width , 0, height },
            {width , .1, 0}, {width , .1, height }};
    int faces[][] = {{0, 1, 3, 2}, {2, 3, 7, 6}, {6, 7, 5, 4}, {4, 5, 1, 0}, {1, 5, 7, 3}, {0, 2, 6, 4}};
    double normals[][] = {{-1, 0, 0}, {0, 1, 0}, {1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    double scale = 1;
    double textures[][] = {{scale, 0}, {scale, scale}, {0, scale}, {0, 0}};
    double position[] = {0, 0, 0};

    /** Creates a new instance of Cube */
    public Cube() {
    }

    /**
     * set the position of center of cube.
     * @param x x coordinate of new position
     * @param y y coordinate of new position
     * @param z z coordinate of new position
     */
    public void setPosition(double x, double y, double z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    /** Set scaling for texture.
     *
     * @param s scale factor
     */
    public void setScale(double s) {
        scale = s;
        double newTextures[][] = {{scale, 0}, {scale, scale}, {0, scale}, {0, 0}};
        textures = newTextures;
    }

    /**
     * draw the cube.
     * @param gl openGL object
     */
    public void draw(GL gl) {
        gl.glPushMatrix();
        gl.glTranslated(position[0], position[1], position[2]);
        gl.glTranslatef(-.5f, -.5f, -.5f);
        gl.glBegin(GL.GL_QUADS);
        for (int i = 0; i < faces.length; i++) {
            for (int j = 0; j < 4; j++) {
                gl.glNormal3dv(normals[i], 0);
                gl.glTexCoord2dv(textures[j], 0);
                gl.glVertex3dv(vertices[faces[i][j]], 0);
            }
        }
        gl.glEnd();
        gl.glPopMatrix();
    }
}
