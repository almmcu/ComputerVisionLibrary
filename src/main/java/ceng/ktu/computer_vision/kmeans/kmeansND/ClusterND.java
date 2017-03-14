package ceng.ktu.computer_vision.kmeans.kmeansND;

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
public class ClusterND {

    private PointND clusterCenter;
    private List<PointND> clusterPoints;
    private int id;

    public ClusterND(int id) {
        this.id = id;
        this.clusterPoints =  new ArrayList<PointND>();
        this.clusterCenter = PointND.getRandomPoint(0,255);
    }

    public PointND getClusterCenter() {
        return clusterCenter;
    }

    public List<PointND> getClusterPoints() {
        return clusterPoints;
    }

    public void setClusterCenter(PointND clusterCenter) {
        this.clusterCenter = clusterCenter;
    }

    protected void addPointToList (PointND point){
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
        this.clusterCenter = new PointND(sumR / size, sumG / size, sumB / size);
    }
    protected void resetClusterPoints (){
        this.clusterPoints.clear();
    }

}
