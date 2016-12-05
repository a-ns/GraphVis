package Algorithms;

import Graph.Vertex;
import Graph.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Mikayla on 11/26/2016.
 *
 */
public class BreadthFirstSearch {
    private Queue<Vertex> queue;
    private String visitedNodes;
    public Graph currentState;
    private boolean[] visited;
    private ColorMatrix currentCM;
    private ArrayList<ColorMatrix> states;

    public BreadthFirstSearch(Graph g, Vertex startNode, Vertex targetNode){
        queue = new LinkedList<Vertex>();
        this.states = new ArrayList<ColorMatrix>();
        this.currentState = g;
        visited = new boolean[g.getVertices().size()];
        ColorMatrix cm = new ColorMatrix(g.getVertices().size());
        //initially all set to grey
        //set first node to red
        int index = g.getVertices().indexOf(startNode);
        cm.setColorMatrixAt(index, index, 2);
        states.add(cm);
        currentCM = cm;
        bfs(g.toAdjacencyMatrix(), startNode, targetNode);
        states.add(backToBlack());
    }
    /*
	Perform a breadth first traversal, updating the states with visualization info each step of the way.
	Can be altered to do an actual search for targetNode.
	 */
    private void bfs(Edge[][] adjMatrix, Vertex startNode, Vertex targetNode){
        int currentNodeIndex = currentState.getVertices().indexOf(startNode);
        visitedNodes = visitedNodes + startNode.getValue();
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
                    ColorMatrix newCM = updateCM(this.currentCM, indexOfCurrentVert, i);
                    states.add(newCM);
                    this.currentCM = newCM;
                }
            }
        }
    }

    /*
	Update the colormatrix to highlight the newly traversed edge and node.
	Return a new colormatrix with the new values.
	To be added to the states list back in depthFirstSearch method.
	 */
    private ColorMatrix updateCM(ColorMatrix cm, int currentNodeIndex, int i) {
        ColorMatrix newCM = new ColorMatrix(cm.getNumVertices());
        int[][] oldMatrix = cm.getColorMatrix();
		/*
		copy the old cm into newCM before updating
		 */
        for(int j = 0; j < cm.getNumVertices(); j++){
            for(int k = 0; k < cm.getNumVertices(); k++){
                if(oldMatrix[j][k] == 2){
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
        newCM.setColorMatrixAt(i, i, 2); //set node to highlighted
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

    public String getVisitedNodes() {
        return visitedNodes;
    }

}
