package ceng.ktu.computer_vision.bmp.run;

import ceng.ktu.computer_vision.bmp.BMPHistogram;
import ceng.ktu.computer_vision.bmp.BMPImageOperations;
import ceng.ktu.computer_vision.bmp.BMPImageWrite;
import ceng.ktu.computer_vision.bmp.ReadBMPImage;
import ceng.ktu.computer_vision.kmeans.KMeans;
import ceng.ktu.computer_vision.kmeans.kmeans3d.KMeans3D;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *<h1>Run the BMP Operations</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 09.03.2017.
 */
public class ImageSegmentation {
    /**
     * {absolutePath} gets the absolute path of the project
     */
    private static String absolutePath = new File("").getAbsolutePath();
    private String inputImagePath;

    public ImageSegmentation(String inputImagePath) {
        this.inputImagePath = inputImagePath;
    }

    public void oneDimensionSegmentation (String outputImageName){
        int dimension = 0;

        try {
            // reading image
            // "\\Pictures\\f.bmp"
            BMPImageOperations bmpImageOperations = new ReadBMPImage(
                    absolutePath.concat(inputImagePath)
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

            bmpImageOperations = new BMPImageWrite(ReadBMPImage.imageHeight, ReadBMPImage.imageWidth, pixelList,threshold, outputImageName);
            bmpImageOperations.operate(0);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void threeDimensionImageSegmentation (String outputImageName, int k){
        int dimension = 3;

        try {
            // reading image
            BMPImageOperations bmpImageOperations = new ReadBMPImage(
                    absolutePath.concat(inputImagePath)
            );

            KMeans3D kMeans3D = new KMeans3D(bmpImageOperations.operate(dimension), k);
            kMeans3D.calculate();
            bmpImageOperations = new BMPImageWrite(ReadBMPImage.imageWidth,ReadBMPImage.imageHeight,kMeans3D.getClusters(), outputImageName);
            bmpImageOperations.operate(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


