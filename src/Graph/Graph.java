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
            if(n.getId() == id){
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
                otherVertex.removeEdge(edgeBack.getId());
                this.edges.remove(edgeBack);
            }
            this.edges.remove(e);
        }
        this.nodes.remove(v);
        return true;



        /*
        Iterator<Vertex> vertexIterator = nodes.iterator();
        while (vertexIterator.hasNext()) {
            Vertex n = vertexIterator.next();
            if(n.getValue().equals(value)) {

                Iterator<Edge> iterator = n.getEdges().iterator();

                while(iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                }

                Iterator<Vertex> it = this.nodes.iterator();
                while(it.hasNext()) {
                    Vertex v = it.next();
                    iterator = v.getEdges().iterator();
                    while (iterator.hasNext()) {
                        Edge e = iterator.next();
                        if (e.getFrom().getValue().equals(value) || e.getTo().getValue().equals(value)) {
                            iterator.remove();
                        }

                    }
                }
                vertexIterator.remove();
                return true;
            }

        }
        return false;
        */
    }

    public Edge getEdge (int id) {
        for (Edge e : edges) {
            if (e.getId() == id){
                return e;
            }
        }
        return null;
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
            return "{ id: " + this.id + " , from : " + this.from.value + " , to : " + this.to.value  + " }";
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

        public boolean removeEdge(int id) {
            Iterator<Edge> edgeIterator = this.edges.iterator();
            while(edgeIterator.hasNext()) {
                Edge e = edgeIterator.next();
                if (e.getId() == id) {
                    edgeIterator.remove();
                    return true;
                }
            }
            return false;
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
