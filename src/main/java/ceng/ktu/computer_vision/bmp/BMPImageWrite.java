package ceng.ktu.computer_vision.bmp;

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

    private static final int [] COLOR_LIST = {16777215,0, 16711680, 65280, 255};
    private int width;
    private int heigth;
    private List<Integer> intensityList;
    private int threshold;

    public BMPImageWrite(int width, int heigth, List<Integer> intensityList, int threshold) {
        this.width = width;
        this.heigth = heigth;
        this.intensityList = intensityList;
        this.threshold = threshold;
    }

    public List operate() throws IOException {

        // k means den gelen eşik değerine göre resim yeniden oluşturulacak.
        // resim oluşturulurken COLOR_LIST elemanları kullanılacak.
        BufferedImage bufferedImage = null;
        bufferedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
        try {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                if (intensityList.get(i*width+j) < threshold)
                    bufferedImage.setRGB(i, j, COLOR_LIST[2]);// col = COLOR List elements
                else
                    bufferedImage.setRGB(i, j, COLOR_LIST[3]);// col = COLOR List elements

            }
        }
        }catch (Exception e){
            System.out.println("Exceptin catched " + e.toString());
        }
        File f = new File("f_color.bmp");
        ImageIO.write(bufferedImage, "BMP", f);

        return null;
    }
}
