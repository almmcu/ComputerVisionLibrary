package ceng.ktu.computer_vision.kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 *<h1>One dimension K-Means</h1>
 *
 * @author almmcu
 * @version 1.0
 * @since 02.03.2017.
 */
public class KMeans {

    private List<Integer> pointsList;
    private int k;
    private List<Cluster> clusterList;
    private boolean isFinished;

    public KMeans(List<Integer> pointsList, int k) {
        this.pointsList = pointsList;
        this.k = k;
        clusterList = new ArrayList<Cluster>();
        isFinished = false;
    }

    public List<Cluster> getClusterList() {
        return clusterList;
    }

    public List<Integer> getPointsList() {
        return pointsList;
    }

    public void calculate (){
        init();
        for (;;) {
            calculateClusters();
            compareClusterCenters();
            if (this.isFinished) break;
            for (int i = 0; i < k; i++) {
                this.clusterList.get(i).resetClusterPoints();
            }
        }// infinite loop
        // noktalar kümelere atandı. küme sayısı = k
        // küme merkezleri hesaplanacak ve eksileri ile karşılaştırılacak.
        // her seferinde eğer eski küme merkezleri yenilerine eşit değilse
        // clusterda olan pointleri sıfırla.

    }// calculate

    protected void init (){
        for (int i = 0; i < k; i++) {
            Cluster cluster = new Cluster(i);
            cluster.setClusterCenter(cluster.createRandomPoint(0,255));
            clusterList.add(cluster);
        }
    }



    protected void calculateClusters (){

        // Listedeki hangi nokta hangi cluster a ait
        // hesaplanıyor...
        Integer[] distanceList = new Integer[this.k];
        for (int i = 0; i < this.pointsList.size(); i++) {
            for (int j = 0; j < this.k; j++) {
                distanceList[j] = Math.abs(
                        this.pointsList.get(i) - this.clusterList.get(j).getClusterCenter()
                );
            } // for j

            // nokta en yakın olan kümeye ekleniyor
            this.clusterList.get(
                    getMinIndex(distanceList)
            ).addPointToList(
                    this.pointsList.get(i)
            );
        } //for i
    }
    protected int getMinIndex (Integer[] distanceList){
        int minIndex = 0;
        int min = distanceList[0];
        for (int i = 0; i < distanceList.length; i++) {
            if (min > distanceList[i]) minIndex = i;
        }
        return minIndex;
    }
    protected void compareClusterCenters (){
        int newClusterCenter = 0;
        for (int i = 0; i < k; i++) {
            newClusterCenter = this.clusterList.get(i).calculateCenter();
            if (this.clusterList.get(i).getClusterCenter() == newClusterCenter)
                this.isFinished = true;
            else
            {
                this.clusterList.get(i).setClusterCenter(newClusterCenter);
                isFinished = false;

            }
        }
    } // compareClusterCenters
}
