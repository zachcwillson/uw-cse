import java.io.*;
import java.util.*;

public class MyClient {

    // Assumes my_vertices.txt and my_edges.txt are in the src folder
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Starting Tests...");
        testMethod();
        System.out.println("Ending Tests...");
        Scanner scanVertex = new Scanner(new File("src\\vertex.txt"));
        Scanner scanEdge = new Scanner(new File("src\\edge.txt"));
        System.out.println();
        extraCredit(scanVertex, scanEdge);
    }
    
    public static void extraCredit(Scanner scanVertex, Scanner scanEdge) throws FileNotFoundException {
        System.out.println("Extra Credit:");
        System.out.println("My Extra credit is a graph of flights that I have taken");
        System.out.println("The Edges represent flights, and the weights represent \nthe distance between destinations.");
        // Get input file for nodes
        // Add to Collection
        Collection<Vertex> v = new HashSet<Vertex>();
        while (scanVertex.hasNextLine()) {
            v.add(new Vertex(scanVertex.nextLine()));
        }
        // Get input file for Edges
        // add to Collection
        Collection<Edge> e = new HashSet<Edge>();
        while (scanEdge.hasNextLine()) {
            Vertex from = new Vertex(scanEdge.next());
            Vertex to = new Vertex(scanEdge.next());
            int weight = scanEdge.nextInt();
            e.add(new Edge(from, to, weight));
        }
        Graph g = new MyGraph(v, e);
        int x = g.edgeCost(new Vertex("SEA"), new Vertex("LAX"));
        System.out.println("Flight distance between SEA and LAX: " + x + " Km");
        int lax = g.edgeCost(new Vertex("LAX"), new Vertex("YYZ"));
        int yyz = g.edgeCost(new Vertex("YYZ"), new Vertex("TLV"));
        int sea = g.edgeCost(new Vertex("SEA"), new Vertex("LAX"));
        int total = lax + yyz + sea;
        System.out.println("Flight distance from my trip to Israel (one-way): " + total + " Km");
        System.out.println("Airports: " + g.vertices());
    }

    // Test to make sure Graph works properly
    public static void testMethod() {
        Collection<Vertex> nodes = new HashSet<Vertex>();
        Collection<Edge> edges = new HashSet<Edge>();
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Edge ab = new Edge(a, b, 5);
        Edge cb = new Edge(c, b, 10);
        Edge bd = new Edge(b, d, 4);
        Edge ad = new Edge(a, d, 8);
        Edge ab2 = new Edge(a, b, 10);
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        edges.add(ab);
        edges.add(cb);
        edges.add(ab2); // -> check to make sure Exception was thrown
        edges.add(bd);
        edges.add(ad);
        Graph test;
        try {
            test = new MyGraph(nodes, edges);
        } catch (Exception e) {
            edges.remove(ab2);
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Passed Exception Test");
            test = new MyGraph(nodes, edges);
        }
        boolean test1 = (5 == test.edgeCost(a, b));
        boolean test2 = (10 == test.edgeCost(c, b));
        nodes.clear();
        boolean test3 = (5 == test.edgeCost(a, d));
        if(test1 && test2 && test3) {
            System.out.println("Passed EdgeCost Test");
        }
        System.out.println("AdjVert Test: " + test.adjacentVertices(a));
        edges.clear();
        System.out.println("Edges Test: " + test.edges());
        System.out.println("Verteces Test: " + test.vertices());
    }

}