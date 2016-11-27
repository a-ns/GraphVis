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
		currentState = g;
		this.adjMatrix = g.toAdjacencyMatrix();
		visited = new boolean[adjMatrix[0].length]; //number of vertices
		ColorMatrix cm = new ColorMatrix(g.getVertices().size());
		//initially all set to grey
		//set first node to red
		int index = g.getVertices().indexOf(startNode);
		cm.setColorMatrixAt(index, index, 2);
		states.add(cm);
		depthFirstSearch(adjMatrix, startNode, targetNode, cm);
		
	}
	public ArrayList<ColorMatrix> getStates(){
		return this.states;
	}
	private void depthFirstSearch(Edge[][] adjMatrix, Vertex startNode, Vertex targetNode, ColorMatrix cm){
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
				//visualize this by adding new colormatrix to the states list
				//color the the edge between currentNodeIndex and i, and node i
				ColorMatrix newCM = updateCM(cm, currentNodeIndex, i);
				states.add(newCM);
				depthFirstSearch(adjMatrix, currentState.getVertices().get(i), targetNode, cm);
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
				newCM.setColorMatrixAt(j, k, oldMatrix[j][k]);
			}
		}
		/*
		update with newly colored edge and node
		 */
		newCM.setColorMatrixAt(currentNodeIndex, i, 3); //set edge to highlighted
		newCM.setColorMatrixAt(i, i, 3); //set node to highlighted
		return newCM;
	}

	private void addState(Vertex currentNode){
		//Graph newG = currentState.clone();
		int indexOfCurr = currentState.getVertices().indexOf(currentNode);

	}
	private void addState(Vertex currentNode, Edge currentEdge){

	}
	
}

