package ceng.ktu.computer_vision;

import ceng.ktu.computer_vision.bmp.BMPHistogram;
import ceng.ktu.computer_vision.bmp.BMPImageOperations;
import ceng.ktu.computer_vision.bmp.BMPImageWrite;
import ceng.ktu.computer_vision.bmp.ReadBMPImage;
import ceng.ktu.computer_vision.kmeans.KMeans;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *<h1>BMP image operations</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 01.03.2017.
 */
public class Test {
    /**
     * {absolutePath} gets the absolute path of the project
     */
     private static String absolutePath = new File("").getAbsolutePath();

    public static void main(String[] args) {
        int r = 0;// red component 0...255
        int g = 0;// green component 0...255
        int b = 255;// blue component 0...255
        int col = (r << 16) | (g << 8) | b;
        System.out.println(col);
        // white: 16777215
        // black: 0
        // red: 16711680
        // green: 65280
        // blue: 255
        int dimension = 0;

        try {
            // reading image
            BMPImageOperations bmpImageOperations = new ReadBMPImage(
                    absolutePath.concat("\\Pictures\\f.bmp")
            );
            // image histogram
            List pixelList = bmpImageOperations.operate(dimension);

            bmpImageOperations = new BMPHistogram(
                    pixelList
            );

            // histogram k mean for finding threshold
            KMeans kMeans =  new KMeans(bmpImageOperations.operate(0), 2);
            kMeans.calculate();

            // get threshold
            int index = ( kMeans.getClusterList().get(0).getClusterCenter() +
                    kMeans.getClusterList().get(1).getClusterCenter() ) / 2;
            int threshold = kMeans.getPointsList().get(index);

            // write image

            bmpImageOperations = new BMPImageWrite(512, 512, pixelList,threshold);
            bmpImageOperations.operate(0);

        } catch (IOException e) {
        e.printStackTrace();
    }catch (Exception e) {
        e.printStackTrace();
    }

    }
}
