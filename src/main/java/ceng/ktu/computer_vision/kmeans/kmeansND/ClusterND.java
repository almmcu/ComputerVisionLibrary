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

   // güncellendi
    protected void calculateClusterCenter (){
        int size = this.clusterPoints.size();
        int [] meanList = new int[PointND.getDimension()];
        PointND clusterCenter = new PointND(PointND.getDimension());
        for (int i = 0; i < size; i++){
            for (int j = 0; j < PointND.getDimension(); j++) {
                meanList[j] += this.clusterPoints.get(i).getPointList()[j];
            }
        }
        for (int i = 0; i < PointND.getDimension(); i++) {
            clusterCenter.getPointList()[i] = meanList[i] / size;
        }
        this.clusterCenter = clusterCenter;
    }
    protected void resetClusterPoints (){
        this.clusterPoints.clear();
    }

}
