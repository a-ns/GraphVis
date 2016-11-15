package Graph;

import javafx.scene.shape.Line;

/**
 * Created by Alex on 11/15/2016.
 */
public class Edge extends Line {


    private int weight;
    private Vertex from;
    private Vertex to;


    /**
     * Instantiates a new Edge.
     *
     * @param weight the weight
     * @param from   the from
     * @param to     the to
     */
    public Edge(int weight, Vertex from, Vertex to) {
        super ();
        this.weight = weight;
        this.from = from;
        this.to = to;
        super.setId(String.valueOf(Graph.edgeID+=1));
    }

    /**
     * Instantiates a new Edge.
     *
     * @param from the from
     * @param to   the to
     */
    public Edge(Vertex from, Vertex to) {
        this.weight = 0;
        this.from = from;
        this.to = to;
        super.setId(String.valueOf(Graph.edgeID+=1));
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
        super.setId(String.valueOf(Graph.edgeID+=1));
    }

    @Override
    public String toString() {
        return "{ id: " + super.getId() + " , from : " + this.from.getValue() + " , to : " + this.to.getValue()  + " , weight: " + this.weight + "}";
    }

}