package Algorithms;

import Graph.Vertex;
import Graph.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Mikayla on 11/26/2016.
 */
public class BreadthFirstSearch {
    private Queue<Vertex> queue;
    private String visitedNodes;
    public Graph currentState;
    private boolean[] visited;
    private ArrayList<Graph> states;

    public BreadthFirstSearch(Graph g, int startID, Vertex startNode){
        queue = new LinkedList<Vertex>();
        this.currentState = g;
        visited = new boolean[g.getVertices().size()];
        bfs(g.toAdjacencyMatrix(), startNode);
    }
    private void bfs(Edge[][] adjMatrix, Vertex startNode){
        int currentNodeIndex = currentState.getVertices().indexOf(startNode);
        //visitedNodes = visitedNodes + startNode.getValue();
        visited[currentNodeIndex] = true;
        queue.add(startNode);
        while(!queue.isEmpty()){
            //while there are still vertices in the queue, keep going
            Vertex currentVert = queue.remove();
            int indexOfCurrentVert = currentState.getVertices().indexOf(currentVert);
            String currentVal = currentVert.getValue(); //get head of queue
            this.visitedNodes = visitedNodes + currentVal;
            for(int i = 0; i < adjMatrix[0].length; ++i){ //go thru all vertices
                if(adjMatrix[indexOfCurrentVert][i] != null && !visited[i]){
                    //an edge exists that is unvisited. add him to queue
                    Vertex newCurrent = currentState.getVertices().get(i);
                    queue.add(newCurrent);
                    //mark him as visited
                    visited[i] = true;
                    this.visitedNodes = visitedNodes + " " + newCurrent.getValue();
                }
            }


        }
    }
}
