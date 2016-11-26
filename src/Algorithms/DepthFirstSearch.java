package Algorithms;

import Graph.Edge;
import Graph.Vertex;

public class DepthFirstSearch{
	private String visitedNodes;
	private boolean[] visited;
	
	public DepthFirstSearch(Edge[][] adjMatrix, int startID, Vertex startNode){
		visitedNodes = "";
		visited = new boolean[adjMatrix[0].length]; //number of vertices
		depthFirstSearch(adjMatrix, startID, startNode);
		
	}
	
	public String depthFirstSearch(Edge[][] adjMatrix, int startID, Vertex startNode){
		visitedNodes = visitedNodes + startNode.getValue();
		visited[startID] = true;
		for(int i = 0; i < adjMatrix[0].length; i++){
			if(!visited[i] && adjMatrix[startID][i] != 0){
				depthFirstSearch(adjMatrix, )
			}
		}
	}
	
}

