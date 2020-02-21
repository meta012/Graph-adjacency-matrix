/*
    Problem: find cheapest route from one country to the other.
    Solution: graph adjacency matrix implementation using Dijkstra's algorithm.
 */
public class Graph {
    private int vertices;
    private double[][] matrix;
    private String[] airportNames;
    private int numEdges;
    private int[] parents;
    private static int noParent = -1;

    public Graph (int vertices) {
        this.vertices = vertices;
        this.matrix = new double[vertices][vertices];
        this.airportNames = new String[vertices];
        this.numEdges = 0;
        this.parents = new int[vertices];
    }
    // returns the number of vertices
    public int nodeCount() {
        return this.vertices;
    }
    // returns the number of current edges
    public int edgeCount() {
        return this.numEdges;
    }
    // get price value of an edge
    public double getPrice (int source, int destination) {
        return this.matrix[source][destination];
    }
    // removes edge from the graph
    public void removeEdge (int source, int destination) {
        matrix[source][destination] = 0;
        numEdges--;
    }
    //---------------------------------------------------
    // set the value of node with index
    public void setValue (int v, String name) {
        if (this.airportNames[v] != null) {
            throw new IndexOutOfBoundsException("This index is taken");
        }
        this.airportNames[v] = name;
    }
    // get the value of node with index
    public String getValue (int v) {

        return this.airportNames[v];
    }
    // add a new edge between source and destination nodes
    public void addEdge (int source, int destination, double price) {
        if (price == 0) {
            throw new IndexOutOfBoundsException("Price can't be zero");
        }
        matrix[source][destination] = price;  // directed graph
        numEdges++;
    }
    // Dijkstra algorithm to find the shortest possible path
    public void dijkstraAlgorithm (int sourceVertex, int destinationVertex) {
        int noParent = -1;
        boolean[] added = new boolean[vertices];
        double[] distances = new double[vertices];
        int[] parents = new int[vertices];

        // initialization
        for (int i = 0; i < nodeCount(); i++) {
            distances[i] = Integer.MAX_VALUE;
            added[i] = false;
        }
        // can't fly to the same country
        distances[sourceVertex] = 0;
        parents[sourceVertex] = noParent;

        // create shortest path tree
        for (int i = 1; i < nodeCount(); i++) {
            int nearestVertex = -1;
            double shortestDistance = Integer.MAX_VALUE;

            for (int j = 0; j < nodeCount(); j++) {     //iterate through all the adjacent vertices of above vertex and update the keys
                if (!added[j] && (distances[j] < shortestDistance)) { //check if this vertex 'j' already in added and if distances[j]!=Infinity
                    nearestVertex = j;
                    shortestDistance = distances[j];
                }
            }
            added[nearestVertex] = true;
            for (int k = 0; k < nodeCount(); k++) {
                double edgeDistance = matrix[nearestVertex][k];
                if ((edgeDistance > 0) && ((shortestDistance + edgeDistance) < distances[k])) {
                    parents[k] = nearestVertex;
                    distances[k] = shortestDistance + edgeDistance;
                }
            }
        }
        //print shortest path tree
        printSolutionDestination(sourceVertex, distances, parents, destinationVertex);
    }

    private void printSolutionDestination(int sourceVertex, double[] distances, int[] parents, int destinationVertex) {
        System.out.println("\nPigiausias skrydis " + getValue(sourceVertex) + " -> " + getValue(destinationVertex) +
                ((distances[destinationVertex] == Integer.MAX_VALUE) ? " neegzistuoja" : " kainuoja: " + distances[destinationVertex] + " eurus"));
        if (distances[destinationVertex] == Integer.MAX_VALUE) {
            System.out.println("Marsrutas neegzistuoja");
        }
        else {
            System.out.print("Marsrutas:");
            printPath(destinationVertex, parents);
        }

    }
    private void printSolution(int sourceVertex, double[] distances, int[] parents) {
        for (int i = 0; i < nodeCount(); i++) {
            System.out.println("\nPigiausias skrydis " + getValue(sourceVertex) + " -> " + getValue(i) +
                    ((distances[i] == Integer.MAX_VALUE) ? " neegzistuoja" : " kainuoja: " + distances[i] + " eurus"));
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Marsrutas neegzistuoja");
            }
            else {
                System.out.print("Marsrutas:");
                printPath(i, parents);
            }
        }
    }
    private void printPath (int i, int[] parents) {
        if (i == noParent) {
            return;
        }
        printPath(parents[i], parents);
        if (i == 0) {
            System.out.print(getValue(i)+" "+getPrice(i,i));
        }
        else {
            System.out.print(((i - 1) == noParent ? " " : " -> ") + getValue(i)+ " "+getPrice(k, i));
        }
        this.k = i;
    }
    public void printMatrix() {
        System.out.println("\n");
        for (int row = 0; row < this.airportNames.length; row++) {
            System.out.print("\t"+this.airportNames[row]+"\t");
        }
        System.out.println("\n");
        for (int row = 0; row < this.matrix.length; row++) {
            System.out.print(this.airportNames[row]+" ");
            for (int col = 0; col < this.matrix[row].length; col++) {
                System.out.printf("%7.1f",this.matrix[row][col]);
            }
            System.out.println();
        }
    }
}