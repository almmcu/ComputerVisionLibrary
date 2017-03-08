package ceng.ktu.computer_vision.kmeans.kmeans3d;

import java.util.ArrayList;
import java.util.List;

/**
 *<h1>Cluster class for 3 Coordinates</h1>
 *
 *<h>Represent each cluster</h>
 *
 * @author almmcu
 * @version 1.0
 * @since 08.03.2017.
 */
public class Cluster3D {

    private Point3D clusterCenter;
    private List<Point3D> clusterPoints;
    private int id;

    public Cluster3D(int id) {
        this.id = id;
        this.clusterPoints =  new ArrayList<Point3D>();
        this.clusterCenter = Point3D.getRandomPoint(0,255);
    }

    public Point3D getClusterCenter() {
        return clusterCenter;
    }

    public List<Point3D> getClusterPoints() {
        return clusterPoints;
    }

    public void setClusterCenter(Point3D clusterCenter) {
        this.clusterCenter = clusterCenter;
    }

    protected void addPointToList (Point3D point){
        this.clusterPoints.add(point);
    }
    protected void calculateClusterCenter (){
        int sumR = 0, sumG = 0, sumB = 0;
        int size = this.clusterPoints.size();
        for (int i = 0; i < size; i++){
            sumR += this.clusterPoints.get(i).getR();
            sumG += this.clusterPoints.get(i).getG();
            sumB += this.clusterPoints.get(i).getB();
        }
        this.clusterCenter = new Point3D(sumR / size, sumG / size, sumB / size);
    }
    protected void resetClusterPoints (){
        this.clusterPoints.clear();
    }

}
