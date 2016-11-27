package Algorithms;

import Graph.Graph;
import Graph.Edge;
import Graph.*;


import java.util.ArrayList;

/**
 * Created by Alex, adapted for this project by Mikayla on 11/27/2016.
 *
 */
public class DijkstraSP {
    private static int V; //# vertices
    private String visitedNodes;
    private boolean[] visited;
    private Graph currentState;
    private Edge[][] adjMatrix;
    private Edge[][] newMatrix;
    private ArrayList<Graph> states;

    public DijkstraSP(Graph g, Vertex startV, Vertex destV){
        int start = g.getVertices().indexOf(startV);
        int dest = g.getVertices().indexOf(destV);
        V = g.getVertices().size();

        dijkstra(g, start, dest);

    }

    private void dijkstra(Graph g, int start, int dest){
        visited = new boolean[V];
        int[] dist = new int[V];
        int[] prev = new int[V];

        for(int i = 0; i < dist.length; i++){
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0; //distance from start to start is 0
        prev[start] = -1;
        for(int fromV = 0; fromV < V - 1; fromV++){
            int current = minimumDistance(visited, dist); //find the next vertex to work on. starts with start vertex because we set it to zero.
            visited[current] = true; //mark the current node as visited so we don't keep doing it

            for (int toV = 0; toV < V; toV++) {
                /* 1. check if the toV (vertex) is not visited
                   2. if there is an edge from current to toV (0 in the graph indicates no edge)
                   3. dist[current] is obviously not MAX_VALUE for the one to do, but we want to make this check so that 4) does not overflow
                   4. if the distance to the current vertex plus the weight to the next vertex is better than the existing value to the next vertex
                        then update that distance.
                */
                if (!visited[toV] && g.toAdjacencyMatrix()[current][toV] != null && dist[current] != Integer.MAX_VALUE
                        && dist[current] + g.toAdjacencyMatrix()[current][toV].getWeight() < dist[toV]) {
                    dist[toV] = dist[current] + g.toAdjacencyMatrix()[current][toV].getWeight();
                    prev[toV] = current; //this helps us "remember" the pathway to that vertex
                }
            }
        }
        for (int i : prev){
            System.out.println(i);
        }
        printSolution (dist, dest);
        printAllSolutions (dist, V);
        printPath (prev, 4);


    }

    private static int minimumDistance (boolean[] visited, int[] dist) {
        int minimum = Integer.MAX_VALUE;
        int u = 0;
        for(int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] < minimum) {
                minimum = dist[i];
                u = i;
            }
        }
        return u;
    }

    private static void printAllSolutions (int dist[], int n) {
        System.out.println ("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println (i + "    " + dist[i]);
    }
    private static void printSolution (int dist[], int n) {
        System.out.println ("Vertex   Distance from Source");
        System.out.println (n +  "    " + dist[n]);
    }

    private static void printPath (int prev[], int n) {

        int src = 0; //stupid compiler
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < prev.length; i++)
            if (prev[i] == -1){
                src = i;
                break;
            }
        System.out.println("Path from " + src + " to " + n);
        int i = n;
        while (i != src) { //when i == src, then we're done finding our way back
            path.append(i + "-");
            i = prev[i];
        }
        path.append(src);
        System.out.println (path.reverse().toString());

    }


}
