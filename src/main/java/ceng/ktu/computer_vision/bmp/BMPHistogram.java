package ceng.ktu.computer_vision.bmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *<h1>Histogram of BMP image</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 02.03.2017.
 */
public class BMPHistogram implements BMPImageOperations{

    private Integer[] histogramList = new Integer[256];
    private List<Integer> intensityList;

    public BMPHistogram(List intensityList) {
        this.intensityList = intensityList;
    }

    public Integer[] getHistogramList() {
        return histogramList;
    }

    public List operate(int dimension) throws IOException {

        for (int i = 0; i < this.histogramList.length; i++) {
            this.histogramList[i] = 0;
        }

        List<Integer> histogramList = new ArrayList<Integer>();
        for (int i = 0; i < this.intensityList.size(); i++) {
            this.histogramList[this.intensityList.get(i)] ++;
        }
        for (int i = 0; i < this.histogramList.length; i++) {
          if(this.histogramList[i] == 0)continue;
            histogramList.add(i);
        }
    return histogramList;
    }

}
