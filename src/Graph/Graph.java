package Graph;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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
    private List<Vertex> nodes;

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

    public Vertex addVertex(double x, double y, double radius) {
        Vertex toAdd = new Vertex(x, y, radius);
        this.nodes.add(toAdd);
        return toAdd;
    }

    public boolean addEdge(T from, T to, boolean... bidirectional) {
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

    public Vertex getVertex (T value) {
        for (Vertex n : nodes) {
            if (n.getValue().equals(value)) {
                return n;
            }
        }
        return null;
    }

    public Vertex getVertex (int id) {
        for (Vertex n : nodes) {
            if(n.getId().equals(String.valueOf(id))){
                return n;
            }
        }
        return null;
    }

    public boolean removeVertex(T value) {
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

    public Edge getEdge (int id) {
        for (Edge e : edges) {
            if (e.getId().equals(String.valueOf(id))){
                return e;
            }
        }
        return null;
    }

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

    public Edge getEdge (Vertex from, Vertex to){
        for (Edge e: edges) {
            if(e.getTo().equals(to) && e.getFrom().equals(from)) {
                return e;
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
        for (Edge e : edges) {
            returnString.append(e.toString() + '\n');
        }
        return returnString.toString();
    }

    /******************************** INNER CLASS EDGE ******************************/
    private class Edge extends Line {


        private int weight;
        private Vertex from;
        private Vertex to;


        public Edge(int weight, Graph.Vertex from, Graph.Vertex to) {
            super ();
            this.weight = weight;
            this.from = from;
            this.to = to;
            super.setId(String.valueOf(edgeID+=1));
        }
        public Edge(Graph.Vertex from, Graph.Vertex to) {
            this.from = from;
            this.to = to;
            super.setId(String.valueOf(edgeID+=1));
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


        public void setId(int id) {
            super.setId(String.valueOf(edgeID+=1));
        }

        @Override
        public String toString() {
            return "{ id: " + super.getId() + " , from : " + this.from.value + " , to : " + this.to.value  + " }";
        }

    }
    /******************************** INNER CLASS VERTEX ******************************/
    private class Vertex extends Circle {
        private T value;
        private List<Edge> edges;


        public Vertex(T value) {
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

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }


        public void setId(int id) {
            super.setId(String.valueOf(id));
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void put(Edge edge){
            this.edges.add(edge);
        }

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

        /*
         * Equality is based on the T value attribute of the vertices. Change it as needed.
         */
        public boolean equals(Vertex other) {
            if (this.value == other.value)
                return true;
            return false;
        }
    }
}
