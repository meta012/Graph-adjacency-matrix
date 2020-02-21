
public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(6);

        graph.setValue(0, "Lietuva");
        graph.setValue(1, "Latvija");
        graph.setValue(2, "Estija");
        graph.setValue(3, "Suomija");
        graph.setValue(4, "Danija");
        graph.setValue(5, "Prancuzija");
        graph.addEdge(0, 1, 100);
        graph.addEdge(0, 3, 350);
        graph.addEdge(0, 5, 327);
        graph.addEdge(1, 2, 114);
        graph.addEdge(1, 3, 212);
        graph.addEdge(2, 4, 46);
        graph.addEdge(3, 4, 255);
        graph.addEdge(4, 5, 66);
        graph.addEdge(4, 1, 98);


        graph.dijkstraAlgorithm(0, 3);
        graph.printMatrix();
    }
}


