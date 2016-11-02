package Graph;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex on 11/1/2016.
 */
public class Graph<T> {
    private final static Logger LOGGER = Logger.getLogger(Graph.class.getName());
    private static int edgeID = 0;
    private static int vertexID = 0;

    private List<Edge> edges;
    private LinkedList<Vertex> nodes;

    public Graph () {
        this.edges = new LinkedList<>();
        this.nodes = new LinkedList<>();
    }

    public boolean addVertex(T value){
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

    public boolean addEdge(T from, T to) {
        try {
            if (this.hasVertex(from) && this.hasVertex(to)) {
                Edge newEdge = new Edge (getVertex(from), getVertex(to));
                getVertex(from).put(newEdge);
                getVertex(to).put(newEdge);
                this.edges.add(newEdge);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Vertex getVertex (T value) {
        for (Vertex n : nodes) {
            if (n.getValue().equals(value)) {
                return n;
            }
        }
        return null;
    }

    public boolean hasVertex(T value) {
        return getVertex(value) != null;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Vertex n : nodes) {
            returnString.append(n.toString() + '\n');
        }
        return returnString.toString();
    }

    /******************************** INNER CLASS EDGE ******************************/
    private class Edge {


        private int weight;
        private Vertex from;
        private Vertex to;
        private int id;


        public Edge(int weight, Graph.Vertex from, Graph.Vertex to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
            this.id = edgeID +=1;
        }
        public Edge(Graph.Vertex from, Graph.Vertex to) {
            this.from = from;
            this.to = to;
            this.id = edgeID += 1;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Vertex getFrom() {
            return from;
        }

        public void setFrom(Vertex from) {
            this.from = from;
        }

        public Vertex getTo() {
            return to;
        }

        public void setTo(Vertex to) {
            this.to = to;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "{ id: " + this.id + " , to : " + this.to.value + " , from : " + this.from.value  + " }";
        }

    }
    /******************************** INNER CLASS VERTEX ******************************/
    private class Vertex {
        private T value;
        private int id;
        private List<Edge> edges;

        public Vertex(T value) {
            this.value = value;
            this.id = Graph.vertexID += 1;
            this.edges = new LinkedList<>();
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void put(Edge edge){
            this.edges.add(edge);
        }
        @Override
        public String toString(){
            return "VERTEX ==>\n" + "{ value : " + this.value + ", ID: " + this.id + "," + '\n' +
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
    }
}
