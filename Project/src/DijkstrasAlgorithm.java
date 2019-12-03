import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DijkstrasAlgorithm {
	static HashSet<String> verticesSet = new HashSet<>();
	static List<Edge> listOfEdges = new ArrayList<>();
	static String graphType, noOfEdges, noOfVertices;
	private static Edge[] e = null;
	private static boolean undirected = false;
	static String startNode = null;
	private static int k = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter path of the text file to be given as Input: ");
		String url = sc.nextLine();
		System.out.println("Enter:\n 1. Dijkstra's Algorithm \n 2. Kruskal's Algorithm");
		int a = sc1.nextInt();
		br = new BufferedReader(new FileReader(url));

		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String line1 = line;
			noOfVertices = line1.substring(0,2).trim();
			e = new Edge[Integer.parseInt(noOfVertices)];
			noOfEdges = line1.substring(3, 5).trim();
			graphType = line1.substring(6, 7).trim();
			System.out.println("Number of vertices: " + noOfVertices);
			System.out.println("number of edges: " + noOfEdges);
			System.out.println("Type of graph: " + graphType);

			if (graphType.toUpperCase().equals("U")) {
				undirected = true;
			} else {
				undirected = false;
			}

			int num = Integer.parseInt(noOfEdges);

			e = new Edge[num];

			while (num != 0) {
				line = br.readLine();
				createNode(line);
				num--;
			}

			if (a == 1) {

				if ((line = (br.readLine())) != null) {
					startNode = line.trim().substring(0, 1);
				} else {
					System.out.println("Enter start node: ");
					startNode = sc2.nextLine();
					startNode.toLowerCase().trim();

				}

				System.out.println("Line 1 " + line1);

				Graph g = new Graph(e, undirected);
				g.dijkstra(startNode);
				g.printAllPaths();
			} else if (a == 2) {
				if (undirected) {
					KruskalsAlgorithm kruskal = new KruskalsAlgorithm();
					String x[] = verticesSet.toArray(new String[verticesSet.size()]);

					kruskal.KruskalAlgo(x, e);

				} else {
					System.out.println("Cannot find Minimum Spanning Tree for the directed graph");
				}
			}

		}

		catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	private static void createNode(String temp) {
		String li = temp.trim();
		String s1 = li.substring(0, 1).trim();
		String s2 = li.substring(2, 3).trim();
		String s3 = li.substring(4, li.length()).trim();

		verticesSet.add(s1);
		verticesSet.add(s2);
		int s = Integer.valueOf(s3);

		e[k] = new Edge(s1, s2, s);

		System.out.println(e[k]);
		k++;
	}

}