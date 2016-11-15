package Graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 11/1/2016.
 */
public class GraphTest {

    @Test
    public void testAddVertex() throws Exception {
        Graph graph = new Graph();
        assertNotEquals(null, graph);
    }

    @Test
    public void testGetVertex() throws Exception {
        Graph graph = new Graph();
        graph.addVertex("5");
        assertNotEquals(null, graph.getVertex("5"));
    }

    @Test
    public void testGetVertex1() throws Exception {

    }



    @Test
    public void testRemoveVertex() throws Exception {
        Graph graph = new Graph();
        graph.addVertex("5");
        graph.addVertex("10");
        graph.addEdge("5", "10");
        graph.removeVertex("5");
        assertEquals(null, graph.getVertex("5"));

    }

    @Test
    public void testGetEdge() throws Exception {
        Graph graph = new Graph();
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addEdge("5", "6");
        assertNotEquals(null, graph.getEdge(graph.getVertex("5"), graph.getVertex("6")));
    }

    @Test
    public void testHasVertex() throws Exception {
        Graph graph = new Graph();
        graph.addVertex("5");
        assertNotEquals(null, graph.getVertex("5"));
    }
}