package ceng.ktu.computer_vision.bmp;

import ceng.ktu.computer_vision.kmeans.kmeans3d.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *<h1>Read BMP image</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 01.03.2017.
 */
public class ReadBMPImage implements BMPImageOperations{
    private String bmpImagePath;
    private List rgbList;
    private List intensityList;
    private List<Point3D> pixelList;
    public static int imageWidth, imageHeight;
    public ReadBMPImage(String bmpImagePath) {
        this.bmpImagePath = bmpImagePath;
    }

    public List getRgbList() {
        return rgbList;
    }

    public List getIntensityList() {
        return intensityList;
    }

    public List operate(int dimension) throws IOException {
       File myImg = new File(this.bmpImagePath);
       BufferedImage in = ImageIO.read(myImg);
        pixelList =  new ArrayList<Point3D>();
        rgbList =  new ArrayList<Integer>();
        intensityList = new ArrayList<Integer>();
       // BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //int[][] array2D = new int[in.getWidth()][in.getHeight()];
        byte[] pixels;
        pixels = ((DataBufferByte) in.getRaster().getDataBuffer()).getData();

        imageHeight = in.getHeight();
        imageWidth = in.getWidth();
        int red;
        int green;
        int blue;
        int color;
        int intensity;
        for (int xPixel = 0; xPixel < in.getWidth(); xPixel++)
        {
            for (int yPixel = 0; yPixel < in.getHeight(); yPixel++)
            {
                color = in.getRGB(xPixel, yPixel);
                red = (color >> 16 ) & 0x000000FF;
                green = (color >> 8 ) & 0x000000FF;
                blue = (color) & 0x000000FF;
                Point3D point =  new Point3D(red, green, blue);
                point.setX(xPixel);
                point.setY(yPixel);
                pixelList.add(point);

                rgbList.add(red);
                rgbList.add(green);
                rgbList.add(blue);

                //rgb2gray converts RGB values to grayscale values
                // by forming a weighted sum of the R, G, and B components:
                // 0.2989 * R + 0.5870 * G + 0.1140 * B
                intensity = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
                intensityList.add(intensity);
            }
        }

        if (dimension == 3) return pixelList;
        return intensityList;
    }// operate

}
