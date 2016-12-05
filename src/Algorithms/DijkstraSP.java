package Algorithms;

import Graph.Graph;
import Graph.*;


import java.util.ArrayList;

/**
 * Algorithm implemented by alex, adapted for this project by Mikayla on 11/27/2016.
 *
 */
public class DijkstraSP {
    private static int V; //# vertices
    private boolean[] visited;
    private ColorMatrix currentCM;
    private Graph currentState;
    private ArrayList<ColorMatrix> states;
    private String shortestPath = "";

    public DijkstraSP(Graph g, Vertex startV, Vertex destV){
        int start = g.getVertices().indexOf(startV);
        this.currentState = g;
        int dest = g.getVertices().indexOf(destV);
        V = g.getVertices().size();
        states = new ArrayList<ColorMatrix>();
        ColorMatrix cm = new ColorMatrix(g.getVertices().size());
        //initially all set to grey
        //set first node to red
        int index = g.getVertices().indexOf(startV);
        cm.setColorMatrixAt(index, index, 2);
        states.add(cm);
        currentCM = cm;
        dijkstra(g, start, dest);
    }

    /*
    Run Dijkstra's algorithm on the graph, searching for the dest vertex, starting at start vertex.
    update the states arraylist each step through the algorithm.
     */
    private void dijkstra(Graph g, int start, int dest){
        visited = new boolean[V];
        int[] distances = new int[V];
        int[] prev = new int[V];

        for(int i = 0; i < distances.length; i++){
            //visited[i] = false;
            distances[i] = Integer.MAX_VALUE;
        }
        distances[start] = 0; //distance from start to start is 0
        prev[start] = -1;
        for(int fromV = 0; fromV < V - 1; fromV++){
            int currentIndex = minimumDistance(visited, distances); //find the next vertex to work on. starts with start vertex because we set it to zero.
            visited[currentIndex] = true; //mark the current node as visited so we don't keep doing it

            for (int toV = 0; toV < V; toV++) {
                /* 1. check if the toV (vertex) is not visited
                   2. if there is an edge from current to toV (0 in the graph indicates no edge)
                   3. dist[current] is obviously not MAX_VALUE for the one to do, but we want to make this check so that 4) does not overflow
                   4. if the distance to the current vertex plus the weight to the next vertex is better than the existing value to the next vertex
                        then update that distance.
                */
                if (!visited[toV] && g.toAdjacencyMatrix()[currentIndex][toV] != null && distances[currentIndex] != Integer.MAX_VALUE
                        && distances[currentIndex] + g.toAdjacencyMatrix()[currentIndex][toV].getWeight() < distances[toV]) {
                    distances[toV] = distances[currentIndex] + g.toAdjacencyMatrix()[currentIndex][toV].getWeight();
                    prev[toV] = currentIndex; //this helps us "remember" the pathway to that vertex
                    ColorMatrix cm = updateCM(this.currentCM, currentIndex, toV, true);
                    this.currentCM = cm;
                    states.add(cm);
                }
            }
        }
        for (int i : prev){
            System.out.println(i);
        }
        printSolution (distances, dest);
        printAllSolutions (distances, V);
        System.out.println("Printing path");
        this.shortestPath = printPath (prev, dest+1);
        states.add(highlightBestPath());
    }

    /*
    highlight the shortest path created using dijkstra's algorithm. Represent it through the ColorMatrix
     */
    private ColorMatrix highlightBestPath() {
        String[] path = this.shortestPath.split("-");
        //remove current red colorings, make them black
        ColorMatrix cm = backToBlack();
        //highlight start node red
        System.out.println("updating node " + Integer.parseInt(path[path.length-1]));
        cm = updateCM(cm, Integer.parseInt(path[path.length-1]), Integer.parseInt(path[path.length-1]), false);
        for(int i = path.length-1; i > 0; i--){
            //update all other nodes and connecting edges red
            System.out.println("updating node " + Integer.parseInt(path[i]) + " and " + Integer.parseInt(path[i-1]));
            cm = updateCM(cm, Integer.parseInt(path[i]), Integer.parseInt(path[i-1]),false);
        }
        return cm;
    }

    /*
    calculate the index of the minimum distance so far
     */
    private static int minimumDistance (boolean[] visited, int[] dist) {
        int minimum = Integer.MAX_VALUE;
        int indexOfMin = 0;
        for(int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] < minimum) {
                minimum = dist[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }
    /*
    print all of the distances from start node to each node
     */
    private static void printAllSolutions (int dist[], int n) {
        System.out.println ("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println (i + "    " + dist[i]);
    }
    /*
    print the distance required to travel to the destination from source node
     */
    private static void printSolution (int dist[], int n) {
        System.out.println ("Vertex   Distance from Source");
        System.out.println (n +  "    " + dist[n]);
    }

    /*
    print the shortest path found using dijkstra's algorithm
     */
    private static String printPath (int prev[], int n) {

        int src = 0; //stupid compiler
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < prev.length; i++)
            if (prev[i] == -1){
                src = i;
                break;
            }
        System.out.println("Path from " + (src+1) + " to " + n);
        int i = n-1;
        while (i != src) { //when i == src, then we're done finding our way back
            path.append(i + "-");
            System.out.println("i = " + i + " src = " + src + " prev[i] = " + prev[i]);
            i = prev[i];
            System.out.println("new i = " + prev[i]);
        }
        path.append(src);
        System.out.println (path.reverse().toString());
        return path.reverse().toString();
    }

    /*
	Update the colormatrix to highlight the newly traversed edge and node.
	Return a new colormatrix with the new values.
	To be added to the states list back in depthFirstSearch method.
	 */
    private ColorMatrix updateCM(ColorMatrix cm, int currentNodeIndex, int i, boolean goBlack) {
        ColorMatrix newCM = new ColorMatrix(cm.getNumVertices());
        int[][] oldMatrix = cm.getColorMatrix();
		/*
		copy the old cm into newCM before updating
		 */
        for(int j = 0; j < cm.getNumVertices(); j++){
            for(int k = 0; k < cm.getNumVertices(); k++){
                if(oldMatrix[j][k] == 2 && goBlack){
                    newCM.setColorMatrixAt(j, k, 1);
                }
                else newCM.setColorMatrixAt(j, k, oldMatrix[j][k]);
            }
        }
		/*
		update with newly colored edge and node
		 */
        newCM.setColorMatrixAt(currentNodeIndex, i, 2); //set edge to highlighted
        if(!this.currentState.isDirected()){
            //not directed so update edge from i to currentNodeIndex also
            newCM.setColorMatrixAt(i, currentNodeIndex, 2); //set edge to highlighted
        }
        newCM.setColorMatrixAt(i, i, 2); //set new node to highlighted
        return newCM;
    }

    /*
        Turn all of the previously highlighted components back to black.
     */
    private ColorMatrix backToBlack(){
        ColorMatrix newCM = new ColorMatrix(this.currentCM.getNumVertices());
        int[][] oldMatrix = this.currentCM.getColorMatrix();
		/*
		make the last edge and node highlighted go to black for the ending. #badcodingpractices
		 */
        for(int j = 0; j < this.currentCM.getNumVertices(); j++){
            for(int k = 0; k < this.currentCM.getNumVertices(); k++){
                if(oldMatrix[j][k] == 2){
                    newCM.setColorMatrixAt(j, k, 1);
                }
                else newCM.setColorMatrixAt(j, k, oldMatrix[j][k]);
            }
        }
        return newCM;
    }

    /*
    return the states in the visualization represented by an arraylist of colormatrix objects
     */
    public ArrayList<ColorMatrix> getStates() {
        return states;
    }


}
