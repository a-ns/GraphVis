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
	private ColorMatrix currentCM;
	private ArrayList<ColorMatrix> states;
	private  boolean found = false;
	
	public DepthFirstSearch(Graph g, Vertex startNode, Vertex targetNode){
		states = new ArrayList<ColorMatrix>();
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
		currentCM = cm;
		depthFirstSearch(adjMatrix, startNode, targetNode);
		states.add(backToBlack());
	}
	public String getVisited(){
		return this.visitedNodes;
	}

	/*
    return the states in the visualization represented by an arraylist of colormatrix objects
     */
	public ArrayList<ColorMatrix> getStates(){
		return this.states;
	}

	/*
	Perform a depth first traversal, updating the states with visualization info each step of the way.
	Can be altered to do an actual search for targetNode.
	 */
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
				//visualize this by adding new colormatrix to the states list
				//color the the edge between currentNodeIndex and i, and node i
				ColorMatrix newCM = updateCM(this.currentCM, currentNodeIndex, i);
				states.add(newCM);
				this.currentCM = newCM;
				depthFirstSearch(adjMatrix, currentState.getVertices().get(i), targetNode);
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
	
}

