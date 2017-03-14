package ceng.ktu.computer_vision.kmeans.kmeansND;

import java.util.ArrayList;
import java.util.List;

/**
 *<h1>Three dimension K-Means</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 08.03.2017.
 */
public class KMeansND {
    private int k;
    private List<PointND> points;
    private List<ClusterND> clusters;

    public KMeansND(List<PointND> points, int k) {
        this.k = k;
        this.clusters = new ArrayList<ClusterND>();
        this.points = points;
    }

    public List<ClusterND> getClusters() {
        return clusters;
    }

    //The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        init();
        // Add in new data, one at a time, recalculating centroids with each new one.
        while(!finish) {
            //Clear cluster state
            clearClusters();

            List<PointND> lastClusterCenters = getClusterCenters();

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateClusterCenters();

            iteration++;

            List<PointND> currentClusterCenters = getClusterCenters();

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastClusterCenters.size(); i++) {
                distance += PointND.getDistance(lastClusterCenters.get(i),currentClusterCenters.get(i));
            }
            System.out.println("************************");
            System.out.println("Döngü: " + iteration);
            System.out.println("Küme Merkezleri Uzaklıkları: " + distance);

            if(distance == 0) {
                finish = true;
            }
        }
    }

    // Initial step.
    // Create k number of cluster
    // Add them to list
    private void init(){
        for (int i = 0; i < k; i++) {
            ClusterND cluster = new ClusterND(i);
            clusters.add(cluster);
        }
    }

    private void clearClusters() {
        for(ClusterND cluster : clusters) {
            cluster.resetClusterPoints();
        }
    }

    private List<PointND> getClusterCenters() {
        List<PointND> clusterCenters = new ArrayList(k);
        for(ClusterND cluster : this.clusters) {
            clusterCenters.add(cluster.getClusterCenter());
        }
        return clusterCenters;
    }

    private void calculateClusterCenters() {
        for(ClusterND cluster : this.clusters) {
            cluster.calculateClusterCenter();
        }
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int clusterId = 0;
        double distance = 0.0;

        for(PointND point : points) {
            min = max;
            for(int i = 0; i < k; i++) {
                ClusterND cluster = clusters.get(i);
                distance = PointND.getDistance(point, cluster.getClusterCenter());
                if(distance < min){
                    min = distance;
                    clusterId = i;
                }
            }
            point.setClusterNumber(clusterId);
            clusters.get(clusterId).addPointToList(point);
        }
    }

}
