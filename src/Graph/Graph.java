package Graph;

import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<Vertex> nodes;


    public boolean addVertex(T value){
        try {
            Vertex toAdd = new Vertex(value);
            this.nodes.add(toAdd);
            return true;
        }
        catch (Exception e){
            LOGGER.setLevel(Level.WARNING);
            LOGGER.warning("Could not create vertex with value : " + value);
            return false;
        }
    }

    private class Edge {


        private int weight;
        private Vertex from;
        private Vertex to;
        private int id;


        public Edge(int weight, Graph.Vertex from, Graph.Vertex to, int id) {
            this.weight = weight;
            this.from = from;
            this.to = to;
            this.id = id;
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



    }

    private class Vertex {
        private T value;
        private int id;
        private List<Edge> edges;

        public Vertex(T value) {
            this.value = value;
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

        public void setEdges(List<Edge> edges) {
            this.edges = edges;
        }
    }
}
