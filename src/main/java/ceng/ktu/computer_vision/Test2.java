package ceng.ktu.computer_vision;

import ceng.ktu.computer_vision.bmp.BMPImageOperations;
import ceng.ktu.computer_vision.bmp.BMPImageWrite;
import ceng.ktu.computer_vision.bmp.ReadBMPImage;
import ceng.ktu.computer_vision.kmeans.kmeans3d.KMeans3D;

import java.io.File;

/**
 *<h1>Test operations</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 08.03.2017.
 */
public class Test2 {
    private static String absolutePath = new File("").getAbsolutePath();

    public static void main(String[] args) {

        int dimension = 3;

        try {
            // reading image
            BMPImageOperations bmpImageOperations = new ReadBMPImage(
                    absolutePath.concat("\\Pictures\\f.bmp")
            );

            KMeans3D kMeans3D = new KMeans3D(bmpImageOperations.operate(dimension), 3);
            kMeans3D.calculate();
            bmpImageOperations = new BMPImageWrite(512,512,kMeans3D.getClusters());
            bmpImageOperations.operate(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
