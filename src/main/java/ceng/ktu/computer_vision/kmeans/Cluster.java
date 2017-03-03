package ceng.ktu.computer_vision.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *<h1>Cluster class</h1>
 *
 *<h>Represent each cluster</h>
 *
 * @author almmcu
 * @version 1.0
 * @since 02.03.2017.
 */
public class Cluster {

    private int clusterCenter;
    private List<Integer> clusterPoints;
    private int id;

    public Cluster(int id ) {
        clusterCenter = 0;
        clusterPoints = new ArrayList<Integer>();
        this.id = id ;
    }

    public int getClusterCenter() {
        return clusterCenter;
    }

    public void setClusterCenter(int clusterCenter) {
        this.clusterCenter = clusterCenter;
    }

    public List<Integer> getClusterPoints() {
        return clusterPoints;
    }

    public void setClusterPoints(List<Integer> clusterPoints) {
        this.clusterPoints = clusterPoints;
    }

    protected void addPointToList(int point){
        this.clusterPoints.add(point);
    }

    protected int calculateCenter(){
        int sum = 0;
        for (int i = 0; i < this.clusterPoints.size(); i++) {
            sum += this.clusterPoints.get(i);
        }
        return  sum / this.clusterPoints.size();
    }

    protected int createRandomPoint(int min, int max) {
        Random r = new Random();
        int point = (int) (min + (max - min) * r.nextDouble());
        return point;
    }
    protected void resetClusterPoints (){
        this.clusterPoints.clear();
    }
}
