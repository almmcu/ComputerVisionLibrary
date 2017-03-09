package ceng.ktu.computer_vision;

import ceng.ktu.computer_vision.bmp.run.ImageSegmentation;

/**
 *<h1>BMP image operations</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 01.03.2017.
 */
public class Test {

    public static void main(String[] args) {
        int r = 255;// red component 0...255
        int g = 255;// green component 0...255
        int b = 0;// blue component 0...255
        int col = (r << 16) | (g << 8) | b;
        System.out.println(col);
        // white: 16777215
        // black: 0
        // red: 16711680
        // green: 65280
        // blue: 255

        String inputImagePath = "\\Pictures\\f.bmp";
        String outputImageName = "output1d.bmp";
        String outputImageName3D = "output3d.bmp";
        int k = 3;
        try {
            ImageSegmentation imageSegmentation = new ImageSegmentation(inputImagePath);
            imageSegmentation.oneDimensionSegmentation(outputImageName);
            imageSegmentation.threeDimensionImageSegmentation(outputImageName3D, k);
        }
        catch (Exception e) {
        e.printStackTrace();
    }

    }
}
