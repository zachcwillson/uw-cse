import java.util.*;

// Zach Willson
// CSE 373 - WI17
// Homework 4 - Graphs

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {

    // Collection to hold edges for fast return for method edges()
    // Map that goes from Vertex to a List of Edges to represent which nodes are
    // connected
    private Map<Vertex, Set<Edge>> graphMap;

    /**
     * Creates a MyGraph object with the given collection of vertices and the
     * given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
        if (v == null || e == null) {
            throw new IllegalArgumentException("Parameters are null");
        }
        this.graphMap = new HashMap<Vertex, Set<Edge>>();
        Set<Edge> connections = new HashSet<Edge>();
        for (Edge ec : e) {
            for (Edge ee : connections) {
                if (ec.getSource().equals(ee.getSource()) && ec.getDestination().equals(ee.getDestination())) {
                    if (ec.getWeight() != ee.getWeight()) {
                        throw new IllegalArgumentException(
                                "Two Edges cannot have the same nodes but different weights");
                    }
                }
            }
            connections.add(new Edge(ec.getSource(), ec.getDestination(), ec.getWeight()));
        }
        for (Vertex node : v) {
            if (!this.graphMap.containsKey(node)) {
                Set<Edge> lis = new HashSet<Edge>();
                for (Edge ed : e) {
                    if (ed.getWeight() < 0) {
                        throw new IllegalArgumentException("Edge length cannot be negative");
                    } else if (!v.contains(ed.getSource()) || !v.contains(ed.getDestination())) {
                        throw new IllegalArgumentException("Edges cannot connect to vertexes that do not exist");
                    }
                    if (ed.getSource().equals(node)) {
                        lis.add(new Edge(ed.getSource(), ed.getDestination(), ed.getWeight()));
                    }
                }
                this.graphMap.put(new Vertex(node.getLabel()), lis);
            }
        }
    }

    // Return the collection of vertices of this graph
    // @return the vertices as a collection (which is anything iterable)
    @Override
    public Collection<Vertex> vertices() {
        Collection<Vertex> returnd = new HashSet<Vertex>();
        for (Vertex v : this.graphMap.keySet()) {
            returnd.add(new Vertex(v.getLabel()));
        }
        return returnd;
    }

    // Return the collection of edges of this graph
    // @return the edges as a collection (which is anything iterable)
    @Override
    public Collection<Edge> edges() {
        Collection<Edge> returnd = new HashSet<Edge>();
        for (Set<Edge> eSet : this.graphMap.values()) {
            for (Edge e : eSet) {
                returnd.add(new Edge(e.getSource(), e.getDestination(), e.getWeight()));
            }
        }
        return returnd;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v. i.e., the
     * set of all vertices w where edges v -> w exist in the graph. Return an
     * empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    @Override
    public Collection<Vertex> adjacentVertices(Vertex v) {
        if (!this.graphMap.containsKey(v)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        Collection<Vertex> returnd = new HashSet<Vertex>();
        for (Edge e : this.graphMap.get(v)) {
            returnd.add(new Vertex(e.getDestination().getLabel()));
        }
        return returnd;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
     * graph. Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    @Override
    public int edgeCost(Vertex a, Vertex b) {
        if (!this.graphMap.containsKey(a) || !this.graphMap.containsKey(b)) {
            throw new IllegalArgumentException("Vertices do not exist");
        }
        for (Edge e : this.graphMap.get(a)) {
            if (e.getSource().equals(a)) {
                return e.getWeight();
            }
        }
        return -1;
    }
}
