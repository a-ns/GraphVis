package Graph;

import javafx.scene.shape.Circle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 11/15/2016.
 */
public class Vertex extends Circle {
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
                "}\nxVal: " + this.getCenterX() + "\nyVal: " + this.getCenterY();
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