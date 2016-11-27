package Algorithms;

import Graph.Edge;
import Graph.*;
import Graph.Vertex;

import java.awt.*;
import java.util.ArrayList;

public class DepthFirstSearch{
	private String visitedNodes;
	private boolean[] visited;
	private Graph currentState;
	private Edge[][] adjMatrix;
	private Edge[][] newMatrix;
	private ArrayList<ColorMatrix> states;
	private  boolean found = false;
	
	public DepthFirstSearch(Graph g, Vertex startNode, Vertex targetNode){
		visitedNodes = "";
		currentState = g; //make it a clone instead


		this.adjMatrix = g.toAdjacencyMatrix();
		visited = new boolean[adjMatrix[0].length]; //number of vertices
		ColorMatrix cm = new ColorMatrix(g.getVertices().size());
		depthFirstSearch(adjMatrix, startNode, targetNode);
		
	}
	private void depthFirstSearch(Edge[][] adjMatrix, Vertex startNode, Vertex targetNode){
		int currentNodeIndex = currentState.getVertices().indexOf(startNode);
		visitedNodes = visitedNodes + " " + startNode.getValue();
		visited[currentNodeIndex] = true;
		//stop when we reached the target node.
		/*
		if(startNode.equals(targetNode)){
			found = true;
			return;
		}
		//will add in later if time permits
		*/
		for(int i = 0; i < adjMatrix[0].length; i++){
			if(!visited[i] && adjMatrix[currentNodeIndex][i] != null && !found){
				//if node unvisited and there is a path from current node to that node (edge not null)
				//do dfs from that node to the rest of the nodes it's attached to
				//addState();
				depthFirstSearch(adjMatrix, currentState.getVertices().get(i), targetNode);
			}
		}
	}

	private void addState(Vertex currentNode){
		//Graph newG = currentState.clone();
		int indexOfCurr = currentState.getVertices().indexOf(currentNode);

	}
	private void addState(Vertex currentNode, Edge currentEdge){

	}
	
}

