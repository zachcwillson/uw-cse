import java.util.*;

// Zach Willson
// CSE 373 - WI17
// Homework 5 - Graphs

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
     * 
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
     * 
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
     * 
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the
     *         graph, return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    @Override
    public int edgeCost(Vertex a, Vertex b) {
        if (!this.graphMap.containsKey(a) || !this.graphMap.containsKey(b)) {
            throw new IllegalArgumentException("Vertices do not exist");
        }
        for (Edge e : this.graphMap.get(a)) {
            if (e.getDestination().equals(b)) { // I fixed this from HW4
                return e.getWeight();
            }
        }
        return -1;
    }

    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
     * algorithm.
     * 
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *         and contains a (first) and b (last) and the cost is the cost of
     *         the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
        if (!this.graphMap.containsKey(a) || !this.graphMap.containsKey(b)) {
            throw new IllegalArgumentException();
        }
        // The below lines are like my table for drawing out the algorithm
        Set<Vertex> found = new HashSet<Vertex>();
        Set<Vertex> notFound = new HashSet<Vertex>();
        Map<Vertex, Integer> travelTime = new HashMap<Vertex, Integer>();
        Map<Vertex, Vertex> last = new HashMap<Vertex, Vertex>();
        int pathLength = 0;
        travelTime.put(a, 0);
        notFound.add(a);
        // Loop through until I know all the distances to nodes
        while (notFound.size() > 0) {
            Vertex node = getMin(notFound, travelTime);
            found.add(node);
            notFound.remove(node);
            findMinTravel(node, travelTime, notFound, last);
        }
        List<Vertex> pathNodes = new ArrayList<Vertex>();
        Vertex temp = b; // We work backwards to get to where we want to come from
        if (last.get(temp) != null) {
            pathNodes.add(temp);
            // I work back form node b to node a to get where I want to go
            pathLength += travelTime.get(temp);
            while (last.get(temp) != null) {
                temp = last.get(temp);
                pathLength += travelTime.get(temp);
                pathNodes.add(temp);
            }
            Collections.reverse(pathNodes); // I first tried using another data structure, but thanks to Bing, there is this.
            return new Path(pathNodes, pathLength);
        }
        return null;
    }

    // Parameters passed: Set<Vertex>, Map<Vertex, Integer>
    // Finds the min path to next vertex for the given set of not found vertex
    // Returns Vertex
    private Vertex getMin(Set<Vertex> notFound, Map<Vertex, Integer> travelTime) {
        Vertex min = null;
        for (Vertex v : notFound) {
            if (min == null) {
                min = v;
            } else {
                if (shortest(v, travelTime) < shortest(min, travelTime)) {
                    min = v;
                }
            }
        }
        return min;
    }

    // Parameters passed: Vertex, Map<Vertex, Integer>
    // If Vertex passed is known, this will return the distance to that vertex
    // Otherwise, returns infinity in integer terms
    private int shortest(Vertex v, Map<Vertex, Integer> travelTime) {
        Integer distance = travelTime.get(v);
        if (distance != null) {
            return distance;
        }
        return Integer.MAX_VALUE;

    }

    // Parameters: Vertex, Map<Vertex, Integer>, Set<Vertex>, Map<Vertex, Vertex>
    // Gets all the adjacent vertices from a given node, then finds the shortest path
    // Adds to chart if new shortest path is small than its current
    private void findMinTravel(Vertex node, Map<Vertex, Integer> travelTime, Set<Vertex> notFound, Map<Vertex, Vertex> last) {
        Collection<Vertex> adj = adjacentVertices(node);
        for (Vertex v : adj) {
            int test = shortest(node, travelTime) + edgeCost(node, v);
            if (shortest(v, travelTime) > test) {
                travelTime.put(v, test);
                last.put(v, node);
                notFound.add(v);
            }
        }
    }
}
