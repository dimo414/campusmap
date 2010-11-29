package util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

/**
 * This class has been deprecated in favor of ImageTexture.  Do not use it.  It will be deleted shortly.
 */
@Deprecated
public class MyImage {

    private BufferedImage image;
    private String filename = "";

    /**
     * Creates a new instance of MyImage
     * @param name name of image file.
     */
    public MyImage(String name) {
        filename = name;
        loadImage(filename);
    }

    /** Wrap an already exisitng BufferedImage into a MyImage object.
     * @param im the BufferedImage to be wrapped.
     */
    public MyImage(BufferedImage im) {
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
     * @param fileStr name of image file.
     */
    public void loadImage(String fileStr) {
        File file = new File(fileStr);
        try {
            image = ImageIO.read(file.toURL());
            System.out.println("Loading image " + fileStr + ".");
        } catch (Exception e) {
            System.out.println(e + "\nError reading " + fileStr);
        }
        if (image == null) {
            System.out.println("null image!!");
        }
    }

    /**
     * Convert BufferedImage to a ByteBuffer.
     * @return returns the image as a ByteBuffer.
     */
    public ByteBuffer convert() {
        int h = image.getHeight();
        int w = image.getWidth();
        System.out.println("Image height = " + h + "  width = " + w);
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
}
