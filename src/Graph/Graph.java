package Graph;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex on 11/1/2016.
 */
public class Graph {
    private final static Logger LOGGER = Logger.getLogger(Graph.class.getName());
    private static int edgeID = 0;
    private static int vertexID = 0;

    private List<Edge> edges;
    private List<Vertex> nodes;

    /**
     * Instantiates a new Graph.
     */
    public Graph () {
        this.edges = new LinkedList<>();
        this.nodes = new LinkedList<>();
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

    public boolean addEdge(String from, String to, boolean... bidirectional) {
    /**
     * Add edge boolean.
     *
     * @param from          the from
     * @param to            the to
     * @param bidirectional the bidirectional
     * @return the boolean
     */
    public boolean addEdge(String from, String to, boolean... bidirectional) {
        try {
            if (this.hasVertex(from) && this.hasVertex(to)) {
                Edge newEdge = new Edge (getVertex(from), getVertex(to));
                getVertex(from).put(newEdge);
                if (bidirectional.length > 0) {
                    if (bidirectional[0]) {
                        this.addEdge(to , from);
                    }

                }
                this.edges.add(newEdge);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
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

    /******************************** INNER CLASS EDGE ******************************/
    private class Edge extends Line {


        private int weight;
        private Vertex from;
        private Vertex to;
        private int id;


        /**
         * Instantiates a new Edge.
         *
         * @param weight the weight
         * @param from   the from
         * @param to     the to
         */
        public Edge(int weight, Graph.Vertex from, Graph.Vertex to) {
            super ();
            this.weight = weight;
            this.from = from;
            this.to = to;
            super.setId(String.valueOf(edgeID+=1));
        }

        /**
         * Instantiates a new Edge.
         *
         * @param from the from
         * @param to   the to
         */
        public Edge(Graph.Vertex from, Graph.Vertex to) {
            this.weight = 0;
            this.from = from;
            this.to = to;
            super.setId(String.valueOf(edgeID+=1));
        }

        /**
         * Gets weight.
         *
         * @return weight
         */
        public int getWeight() {
            return weight;
        }

        /**
         * Sets weight.
         *
         * @param weight the weight
         */
        public void setWeight(int weight) {
            this.weight = weight;
        }

        /**
         * Gets from.
         *
         * @return from
         */
        public Vertex getFrom() {
            return from;
        }

        /**
         * Sets from.
         *
         * @param from the from
         */
        public void setFrom(Vertex from) {
            this.from = from;
        }

        /**
         * Gets to.
         *
         * @return to
         */
        public Vertex getTo() {
            return to;
        }

        /**
         * Sets to.
         *
         * @param to the to
         */
        public void setTo(Vertex to) {
            this.to = to;
        }


        /**
         * Sets id.
         *
         * @param id the id
         */
        public void setId(int id) {
            super.setId(String.valueOf(edgeID+=1));
        }

        @Override
        public String toString() {
            return "{ id: " + super.getId() + " , from : " + this.from.value + " , to : " + this.to.value  + " , weight: " + this.weight + "}";
        }

    }
    /******************************** INNER CLASS VERTEX ******************************/
    private class Vertex extends Circle {
        private String value;
        private List<Edge> edges;

        /**
         * Instantiates a new Vertex.
         *
         * @param value the value
         */
        public Vertex(String value) {
            super();
            this.value = value;
            super.setId(String.valueOf(Graph.vertexID+=1));
            this.edges = new LinkedList<>();
        }

        public Vertex(double x, double y, double radius){
            super(x, y, radius);
            super.setId(String.valueOf(Graph.vertexID+=1));
            this.edges = new LinkedList<>();
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets value.
         *
         * @param value the value
         */
        public void setValue(String value) {
            this.value = value;
        }



        /**
         * Sets id.
         *
         * @param id the id
         */
        public void setId(int id) {
            super.setId(String.valueOf(id));
        }

        /**
         * Gets edges.
         *
         * @return the edges
         */
        public List<Edge> getEdges() {
            return edges;
        }

        /**
         * Put.
         *
         * @param edge the edge
         */
        public void put(Edge edge){
            this.edges.add(edge);
        }

        /**
         * Remove edge boolean.
         *
         * @param id the id
         * @return the boolean
         */
        public boolean removeEdge(int id) {
            Iterator<Edge> edgeIterator = this.edges.iterator();
            while(edgeIterator.hasNext()) {
                Edge e = edgeIterator.next();
                if (e.getId().equals(String.valueOf(id))) {
                    edgeIterator.remove();
                    return true;
                }
            }
            return false;
        }
        @Override
        public String toString(){
            return "VERTEX ==>\n" + "{ value : " + this.value + ", ID: " + super.getId() + "," + '\n' +
                    "\t\t edges : {" + this.printEdges() +
                    "}";
        }
        private String printEdges () {
            if (this.edges.isEmpty()) {
                return "";
            }
            StringBuilder returnString = new StringBuilder();
            for (Edge e : this.edges) {
                returnString.append("  " + e.toString() + "\n\t\t\t\t  ");
            }
            returnString.append("}\n");
            return returnString.toString();
        }

        /**
         * Equals boolean.
         *
         * @param other the other
         * @return the boolean
         */
/*
         * Equality is based on the T value attribute of the vertices. Change it as needed.
         */
        public boolean equals(Vertex other) {
            if (this.value.equals( other.value))
                return true;
            return false;
        }
    }
}
