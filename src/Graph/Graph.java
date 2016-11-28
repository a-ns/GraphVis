package Graph;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Graph {
    private final static Logger LOGGER = Logger.getLogger(Graph.class.getName());
    protected static int edgeID = 0;
    protected static int vertexID = 0;
    private boolean isDirected = false;
    private boolean isWeighted = false;
    private List<Edge> edges;
    private List<Text> edgeLabels;
    private List<Vertex> nodes;
    private List<Text> nodeLabels;

    /**
     * Instantiates a new Graph.
     */
    public Graph () {
        this.edges = new LinkedList<>();
        this.nodes = new LinkedList<>();
        this.edgeLabels = new LinkedList<>();
        this.nodeLabels = new LinkedList<>();
    }

    public List<Edge> getEdges () {
        return this.edges;
    }

    public List<Text> getEdgeLabels () {
        return this.edgeLabels;
    }

    public List<Vertex> getVertices () {
        return this.nodes;
    }

    public List<Text> getVertexLabels () {
        return this.nodeLabels;
    }
    /**
     * Add vertex boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean addVertex(String value){
        try {
            Vertex toAdd = new Vertex(value);
            this.nodes.add(toAdd);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.setLevel(Level.WARNING);
            LOGGER.warning("Could not create vertex with value : " + value);
            return false;
        }
    }

    public Vertex addVertex(double x, double y, double radius) {
        Vertex toAdd = new Vertex(x, y, radius);
        this.nodes.add(toAdd);
        return toAdd;
    }

    /**
     * Add edge boolean.
     *
     * @param from          the from
     * @param to            the to
     * @param bidirectional the bidirectional
     * @return the boolean
     */
    public Edge addEdge(String from, String to, int weight, boolean... bidirectional) {
        try {
            if (this.hasVertex(from) && this.hasVertex(to)) {
                Edge newEdge = new Edge (weight, getVertex(from), getVertex(to));
                getVertex(from).put(newEdge);
                if (bidirectional.length > 0) {
                    if (bidirectional[0]) {
                        this.addEdge(to , from, weight);
                    }

                }
                this.edges.add(newEdge);
                return newEdge;
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }


    /**
     * Add edge text.
     *
     * @param edge          edge to be labeled
     * @param label         label for the edge
     * @return the Text object
     */
    public Text addEdgeText(Edge edge, String label) {
        Text text = new Text();
        text.setX((edge.getEndX() + edge.getStartX())/2 - (label.length()*3));
        text.setY((edge.getEndY() + edge.getStartY())/2 - 15);
        text.setText(label);
        this.edgeLabels.add(text);

        return text;
    }

    /**
     * Add edge text.
     *
     * @param circ           to
     * @return the boolean
     */
    public Text addVertexText(Vertex circ, String label) {
        Text text = new Text();
        if(circ != null) {
            double xVal = circ.getCenterX();
            double yVal = circ.getCenterY();

            text.setX(xVal - (label.length() * 3));
            text.setY(yVal - 15);
            text.setText(label);
            this.edgeLabels.add(text);
        }
        return text;
    }

    /**
     * Gets vertex.
     *
     * @param value the value
     * @return the vertex
     */
    public Vertex getVertex (String value) {
        for (Vertex n : nodes) {
            if (n.getValue().equals(value)) {
                return n;
            }
        }
        return null;
    }

    /**
     * Gets vertex.
     *
     * @param id the id
     * @return the vertex
     */
    public Vertex getVertex (int id) {
        for (Vertex n : nodes) {
            if(n.getId().equals(String.valueOf(id))){
                return n;
            }
        }
        return null;
    }

    /**
     * Remove vertex boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean removeVertex(String value) {

        Vertex v = this.getVertex(value);
        if (v == null) return false;
        for (Edge e : v.getEdges()) {
            Vertex otherVertex = e.getTo();
            Edge edgeBack = this.getEdge(otherVertex, v);
            if (edgeBack != null) {
                otherVertex.removeEdge(Integer.valueOf(edgeBack.getId()));
                this.edges.remove(edgeBack);
            }
            this.edges.remove(e);
        }
        this.nodes.remove(v);
        return true;
    }

    /**
     * Gets edge.
     *
     * @param id the id
     * @return the edge
     */
    public Edge getEdge (int id) {
        for (Edge e : edges) {
            if (e.getId().equals(String.valueOf(id))){
                return e;
            }
        }
        return null;
    }

    /**
     * Remove edge boolean.
     *
     * @param from the from
     * @param to   the to
     * @return the boolean
     */
    public boolean removeEdge(Vertex from, Vertex to) {
        for (Edge e: this.edges){
            if (e.getFrom().equals(from) && e.getTo().equals(to)) {
                e.getFrom().removeEdge(Integer.valueOf(e.getId()));
                e.getTo().removeEdge(Integer.valueOf(e.getId()));
                this.edges.remove(e);
                return true;
            }
        }

        return false;
    }

    /**
     * Get edge edge.
     *
     * @param from the from
     * @param to   the to
     * @return the edge
     */
    public Edge getEdge (Vertex from, Vertex to){
        for (Edge e: edges) {
            if(e.getTo().equals(to) && e.getFrom().equals(from)) {
                return e;
            }
        }
        return null;
    }


    /**
     * Has vertex boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean hasVertex(String value) {
        return getVertex(value) != null;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Vertex n : nodes) {
            returnString.append(n.toString() + '\n');
        }
        for (Edge e : edges) {
            returnString.append(e.toString() + '\n');
        }
        return returnString.toString();
    }

    /**
     * To adjacency matrix edge [ ] [ ].
     *
     * @return the edge [ ] [ ]
     */
    public Edge[][] toAdjacencyMatrix(){
        int count = this.nodes.size();
        Edge[][] matrix = new Edge[count][count];
        for(int i = 0; i < count; i++) {
            for(int j = 0; j < count; j++){
                matrix[i][j] = this.getEdge(this.nodes.get(i), this.nodes.get(j));
            }
        }
        return matrix;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public void setWeighted(boolean weighted) {
        isWeighted = weighted;
    }
}
