package ceng.ktu.computer_vision.bmp;

import ceng.ktu.computer_vision.kmeans.kmeans3d.Cluster3D;
import ceng.ktu.computer_vision.kmeans.kmeans3d.Point3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *<h1>Write BMP image to disk</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 03.03.2017.
 */
public class BMPImageWrite implements BMPImageOperations {

    // white: 16777215
    // black: 0
    // red: 16711680
    // green: 65280
    // blue: 255
    private static final int [] COLOR_LIST = {16777215, 16711680, 65280, 255, 65535, 16711935, 16776960, 0, 16777215};
    private int width;
    private int height;
    private List<Integer> intensityList;
    private int threshold;
    private List<Cluster3D> clusters;
    private String outputImagePath;

    public BMPImageWrite(int width, int height, List<Integer> intensityList, int threshold, String outputImagePath) {
        this.width = width;
        this.height = height;
        this.intensityList = intensityList;
        this.threshold = threshold;
        this.outputImagePath = outputImagePath;
    }
    public BMPImageWrite(int width, int heigth, List<Cluster3D> clusters, String outputImagePath) {
        this.width = width;
        this.height = heigth;
        this.clusters = clusters;
        this.outputImagePath = outputImagePath;
    }

    public List operate(int dimension) throws IOException {

        // k means den gelen eşik değerine göre resim yeniden oluşturulacak.
        // resim oluşturulurken COLOR_LIST elemanları kullanılacak.
        BufferedImage bufferedImage = null;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        try {
        if (dimension == 0)
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (intensityList.get(i*width+j) < threshold)
                        bufferedImage.setRGB(i, j, COLOR_LIST[7]);// col = COLOR List elements
                    else
                        bufferedImage.setRGB(i, j, COLOR_LIST[8]);// col = COLOR List elements

                }
            }
        if (dimension == 3)
            for(Cluster3D cluster : clusters) {
                for (Point3D point :
                        cluster.getClusterPoints()) {
                    bufferedImage.setRGB(
                            point.getX(),
                            point.getY(),
                            COLOR_LIST[point.getClusterNumber()]);// col = COLOR List elements
                }
            }
        }catch (Exception e){
            System.out.println("Exceptin catched " + e.toString());
        }
        String fileDir = ".\\pictures\\outputs";
        File f = new File(fileDir, this.outputImagePath);
        ImageIO.write(bufferedImage, "BMP", f);

        return null;
    }
}
