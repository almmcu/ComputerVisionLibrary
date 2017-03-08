package ceng.ktu.computer_vision.kmeans.kmeans3d;

import java.util.Random;

/**
 *<h1>Model for RGB Pixel Value</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 08.03.2017.
 */

public class Point3D {

    // RGB - Pixel Values
    private int r;
    private int g;
    private int b;
    private int clusterNumber;
    // Coordinates of Pixel
    private int x;
    private int y;

    public Point3D(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
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

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    //Calculates the distance between two points.
    protected static double getDistance(Point3D point, Point3D centroid) {
        return Math.sqrt(
                        Math.pow((centroid.getR() - point.getR()), 2) +
                        Math.pow((centroid.getG() - point.getG()), 2) +
                        Math.pow((centroid.getB() - point.getB()), 2)
                 );
    }

    protected static Point3D getRandomPoint(int min, int max) {
        Random r = new Random();
        int pointR = (int) (min + (max - min) * r.nextDouble());
        int pointG = (int) (min + (max - min) * r.nextDouble());
        int pointB = (int) (min + (max - min) * r.nextDouble());

        return new Point3D(pointR, pointG,  pointB);
    }

}
