import java.io.*;
import java.util.*;

public class Homework4 {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Starting Tests...");
        testMethod();
        Scanner scanVertex = new Scanner(new File("src\\my_vertices.txt"));
        Scanner scanEdge = new Scanner(new File("src\\my_edges.txt"));
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
        // edges.add(ab2); -> check to make sure Exception was thrown
        edges.add(bd);
        edges.add(ad);
        Graph test = new MyGraph(nodes, edges);
        System.out.println(test.edgeCost(a, b));
        System.out.println(test.edgeCost(c, b));
        nodes.clear();
        System.out.println(test.edgeCost(a, d));
        System.out.println("AdjVert Test: " + test.adjacentVertices(a));
        edges.clear();
        System.out.println(test.edges());
        System.out.println(test.vertices());
    }

}