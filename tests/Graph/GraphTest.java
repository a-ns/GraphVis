package Graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 11/1/2016.
 */
public class GraphTest {

    @Test
    public void testAddVertex() throws Exception {
        Graph<Integer> graph = new Graph<Integer>();
        assertNotEquals(null, graph);
    }

    @Test
    public void testGetVertex() throws Exception {
        Graph<Integer> graph = new Graph<Integer>();
        graph.addVertex(new Integer(5));
        assertNotEquals(null, graph.getVertex(new Integer(5)));
    }

    @Test
    public void testGetVertex1() throws Exception {

    }

    @Test
    public void testRemoveVertex() throws Exception {
        Graph<Integer> graph = new Graph<Integer>();
        graph.addVertex(new Integer(5));
        graph.removeVertex(new Integer(5));
        assertEquals(null, graph.getVertex(new Integer(5)));
    }

    @Test
    public void testRemoveVertex1() throws Exception {
        Graph<Integer> graph = new Graph<Integer>();
        graph.addVertex(new Integer(5));
        graph.removeVertex(new Integer(5));
        assertEquals(null, graph.getVertex(new Integer(5)));
    }

    @Test
    public void testGetEdge() throws Exception {
        Graph<Integer> graph = new Graph<Integer>();
        graph.addVertex(new Integer(5));
        graph.addVertex(new Integer(6));
        graph.addEdge(5, 6);
        assertNotEquals(null, graph.getEdge(1));
    }

    @Test
    public void testHasVertex() throws Exception {
        Graph<Integer> graph = new Graph<Integer>();
        graph.addVertex(new Integer(5));
        assertNotEquals(null, graph.getVertex(new Integer(5)));
    }
}