package util;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

public class ImageTexture {
	private BufferedImage image;
    private String filename = "";

    /**
     * Creates a new instance of MyImage
     * @param name name of image file.
     */
    public ImageTexture(String name) {
        filename = name;
        loadImage(filename);
    }

    /** Wrap an already existing BufferedImage into a MyImage object.
     * @param im the BufferedImage to be wrapped.
     */
    public ImageTexture(BufferedImage im) {
        image = im;
    }

    /** Return the BufferedImage.
     * @return the BufferedImage.
     */
    public BufferedImage getImage() {
        return image;
    }

    /** Return the image height.
     * @return the image height.
     */
    public int getHeight() {
        return image.getHeight();
    }

    /** Return the image width.
     * @return the image width.
     */
    public int getWidth() {
        return image.getWidth();
    }

    /** Return the image file name.
     * @return the image file name.
     */
    public String getFileName() {
        return filename;
    }

    /** Read in image and store as a BufferedImage.
     * @param filename name of image file.
     */
    public void loadImage(String filename) {
        try {
        	getClass().getClassLoader();
        //	File file = new File("src/"+filename);
        //	image = ImageIO.read(file);
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));
        //    System.out.println("Loading image " + filename + ".");
        } catch (Exception e) {
            System.err.println(e + "\nError reading " + filename);
        }
        if (image == null) {
        //    System.out.println("null image!!");
        }
    }

    /**
     * Convert BufferedImage to a ByteBuffer.
     * @return returns the image as a ByteBuffer.
     */
    public ByteBuffer convert() {
        int h = image.getHeight();
        int w = image.getWidth();
    //    System.out.println("Image height = " + h + "  width = " + w);
        byte pix[] = new byte[h * w * 3];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int color = image.getRGB(w - j - 1, h - i - 1);
                int index = 3 * (i * w + j);
                pix[index] = ((byte) ((color >> 16) & 0xFF));      // red
                pix[index + 1] = ((byte) ((color >> 8) & 0xFF));      // green
                pix[index + 2] = ((byte) ((color) & 0xFF));           // blue
            }
        }
        return ByteBuffer.wrap(pix);
    }
    
    /** Bind an image into opengl and set the texture parameters for the given texture
     * @param name the name of image (assigned by opengl)
     * @param gl opengl object.
     */
    public void bind(int name, GL gl) {
        // set the current texture 
        gl.glBindTexture(GL.GL_TEXTURE_2D, // target
                name);             // texture ID

        // glTexParameter sets the texture state parameters, i.e. how texturing is done, e.g antialiasing, wrapping, etc
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);// try GL.GL_CLAMP to see what happens
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);

        // glTexEnvi – determines how texture interacts with lighting.
        //   Defines the action of texture when applied on per-fragment basis:
        //   The following allows you to mix the texture with the material color or other color:
        //     GLfloat c[4] = { 1.0f,0.0f,0.0f,0.0f};
        //     glTexEnvfv(GL_TEXTURE_ENV, GL_TEXTURE_ENV_COLOR,c);
        //     glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE,GL_BLEND);
        //   where we have the following choices. Here
        //              Cs is the texture color,
        //              Cc is the color in c[4]
        //              Cs is the material color.
        //    GL_BLEND  C = Cf * (1-Cs) + Cc * Cs
        //    GL_DECAL, GL_REPLACE  C = Cs
        //    GL_MODULATE  C = Cf * Cs
        //    GL_ADD   C = Cf + Cs
        gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);

        // If you want to have opengl automatically generate texture coordinates, you can
        // use these commands:
//                int choice = GL.GL_SPHERE_MAP; // Options: GL_EYE_LINEAR, GL_SPHERE_MAP, GL_OBJECT_LINEAR
//                gl.glTexGeni(GL.GL_S, GL.GL_TEXTURE_GEN_MODE, choice);
//                gl.glTexGeni(GL.GL_T, GL.GL_TEXTURE_GEN_MODE, choice);
//                gl.glEnable(GL.GL_TEXTURE_GEN_S);
//                gl.glEnable(GL.GL_TEXTURE_GEN_T);


        // Load texture into texture memory:
        gl.glTexImage2D(GL.GL_TEXTURE_2D, // target
                0, // level (used for mipmaps)
                GL.GL_RGB, // internalFormat
                image.getHeight(), // image height
                image.getHeight(), // image width
                0, // border 0 or 1
                GL.GL_RGB, // format of texture image data
                GL.GL_UNSIGNED_BYTE, // type of texture image data
                convert() // buffer containing image (byte buffer in this case)
                );
    }
}
