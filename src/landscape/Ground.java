package landscape;

import javax.media.opengl.GL;

public class Ground {
//    double vertices[][] = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0},
//            {0, 1, 1}, {1, 0, 0}, {1, 0, 1},
//            {1, 1, 0}, {1, 1, 1}};
        int faces[][] = {{0, 1, 3, 2}, {2, 3, 7, 6}, {6, 7, 5, 4}, {4, 5, 1, 0}, {1, 5, 7, 3}, {0, 2, 6, 4}};
        double normals[][] = {{-1, 0, 0}, {0, 1, 0}, {1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
        double scale = 1;
        double textures[][] = {{scale, 0}, {scale, scale}, {0, scale}, {0, 0}};
        double position[] = {0, 0, 0};

        /** Creates a new instance of Cube */
        public Ground() {
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
            scale = s; //
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
