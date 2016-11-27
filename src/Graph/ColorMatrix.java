package Graph;

import java.awt.*;

/**
 * Created by Mikayla on 11/27/2016.
 */
public class ColorMatrix {
    /*
    Color matrix will specify what color the edges (and the nodes they're connected to) need to be
    for the graph visualization.
    Needed to make a nice object so we can store it easily in an ArrayList.
    0 = grey, unvisited.
    1 = black, visited.
    2 = highlighted color (red?), meaning it has become part of the result (search path for dfs/bfs/dijkstras,
    part of the MST for kruskals.
    Will have a separate colormatrix for each step in an algorithm for visualization purposes.
     */
    private int[][] colorMatrix;

    public ColorMatrix(int numVertices){
        colorMatrix = new int[numVertices][numVertices];
    }
    /*
    Get the integer matrix holding the values of colormatrix.
    Used for copying data over to make a clone of the matrix.
     */
    public int[][] getColorMatrix(){
        return this.colorMatrix;
    }
    /*
    Set the value of an item in the color matrix
     */
    public void setColorMatrixAt(int i, int j, int value){
        this.colorMatrix[i][j] = value;
    }

}
