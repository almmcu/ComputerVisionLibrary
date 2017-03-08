package ceng.ktu.computer_vision.kmeans.kmeans3d;

import java.util.ArrayList;
import java.util.List;

/**
 *<h1>Three dimension K-Means</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 08.03.2017.
 */
public class KMeans3D {
    private int k;
    private List<Point3D> points;
    private List<Cluster3D> clusters;

    public KMeans3D(List<Point3D> points, int k) {
        this.k = k;
        this.clusters = new ArrayList<Cluster3D>();
        this.points = points;
    }

    public List<Cluster3D> getClusters() {
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

            List<Point3D> lastClusterCenters = getClusterCenters();

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateClusterCenters();

            iteration++;

            List<Point3D> currentClusterCenters = getClusterCenters();

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastClusterCenters.size(); i++) {
                distance += Point3D.getDistance(lastClusterCenters.get(i),currentClusterCenters.get(i));
            }
            System.out.println("************************");
            System.out.println("Iteration: " + iteration);
            System.out.println("Cluster Center distances distances: " + distance);

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
            Cluster3D cluster = new Cluster3D(i);
            clusters.add(cluster);
        }
    }

    private void clearClusters() {
        for(Cluster3D cluster : clusters) {
            cluster.resetClusterPoints();
        }
    }

    private List<Point3D> getClusterCenters() {
        List<Point3D> clusterCenters = new ArrayList(k);
        for(Cluster3D cluster : this.clusters) {
            clusterCenters.add(cluster.getClusterCenter());
        }
        return clusterCenters;
    }

    private void calculateClusterCenters() {
        for(Cluster3D cluster : this.clusters) {
            cluster.calculateClusterCenter();
        }
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int clusterId = 0;
        double distance = 0.0;

        for(Point3D point : points) {
            min = max;
            for(int i = 0; i < k; i++) {
                Cluster3D cluster = clusters.get(i);
                distance = Point3D.getDistance(point, cluster.getClusterCenter());
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
