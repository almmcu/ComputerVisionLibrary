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

        String inputImagePath = "\\pictures\\f.bmp";String outputImageName = "f1d.bmp";String outputImageName3D = "f3d.bmp";
        //String inputImagePath = "\\pictures\\sleeping-snoopy.bmp"; String outputImageName = "sleeping-snoopy1d.bmp";String outputImageName3D = "sleeping-snoopy3d.bmp";
        //String inputImagePath = "\\pictures\\landscape.bmp"; String outputImageName = "landscape1d.bmp";String outputImageName3D = "landscape3d.bmp";
        //String inputImagePath = "\\pictures\\Tulips.bmp"; String outputImageName = "Tulips1d.bmp";String outputImageName3D = "Tulips3d.bmp";


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


/*
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
*/
