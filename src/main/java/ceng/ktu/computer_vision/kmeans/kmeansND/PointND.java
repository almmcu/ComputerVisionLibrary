package ceng.ktu.computer_vision.kmeans.kmeansND;

import java.util.Random;

/**
 *<h1>Model for RGB Pixel Value</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 08.03.2017.
 */

public class PointND {


    private static int dimension;
    private int[] pointList;
    private int clusterNumber;
    // Coordinates of Pixel
    private int x;
    private int y;

    public PointND(int dimension) {
        this.dimension = dimension;
        this.pointList = new int[dimension];
    }

    public int[] getPointList() {
        return pointList;
    }

    public static int getDimension() {
        return dimension;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    //Calculates the distance between two points.
    protected static double getDistance(PointND point, PointND centroid) {
        double sum = 0;
        for (int i = 0; i < dimension; i++) {
           sum +=  Math.pow( centroid.getPointList()[i] - point.getPointList()[i], 2);
        }
        return Math.sqrt(
                        sum
                 );
    }

    protected static PointND getRandomPoint(int min, int max) {
        Random r = new Random();
        PointND pointND = new PointND(dimension);
        for (int i = 0; i < dimension; i++) {
            pointND.getPointList()[i] = (int) (min + (max - min) * r.nextDouble());
        }


        return pointND;
    }

}
