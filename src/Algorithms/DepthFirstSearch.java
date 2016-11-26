package Algorithms;

import Graph.Edge;
import Graph.*;
import Graph.Vertex;

import java.util.ArrayList;

public class DepthFirstSearch{
	private String visitedNodes;
	private boolean[] visited;
	private Graph currentState;
	private Edge[][] adjMatrix;
	private Edge[][] newMatrix;
	private ArrayList<Graph> states;
	
	public DepthFirstSearch(Graph g, int startID, Vertex startNode){
		visitedNodes = "";
		currentState = g;
		this.adjMatrix = g.toAdjacencyMatrix();
		visited = new boolean[adjMatrix[0].length]; //number of vertices
		depthFirstSearch(adjMatrix, startNode);
		
	}
	private void depthFirstSearch(Edge[][] adjMatrix, Vertex startNode){
		int currentNodeIndex = currentState.getVertices().indexOf(startNode);
		visitedNodes = visitedNodes + startNode.getValue();
		visited[currentNodeIndex] = true;
		for(int i = 0; i < adjMatrix[0].length; i++){
			if(!visited[i] && adjMatrix[currentNodeIndex][i] != null){
				//if node unvisited and there is a path from current node to that node (edge not null)
				//do dfs from that node to the rest of the nodes it's attached to
				depthFirstSearch(adjMatrix, currentState.getVertices().get(i));
			}
		}
	}
	
}

