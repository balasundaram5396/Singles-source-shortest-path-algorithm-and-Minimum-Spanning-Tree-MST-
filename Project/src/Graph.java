
import java.util.*;

class Graph {
	private final Map<String, Vertex> graph;

	public Graph(Edge[] edges, boolean undirected) {
		graph = new HashMap<>(edges.length);

		// putting the vertices in the hash map "graph"
		for (Edge edge : edges) {
			if (!graph.containsKey(edge.v1))
				graph.put(edge.v1, new Vertex(edge.v1));
			if (!graph.containsKey(edge.v2))
				graph.put(edge.v2, new Vertex(edge.v2));
		}

		for (Edge e : edges) {
			// directed graph
			graph.get(e.v1).neighbours.put(graph.get(e.v2), e.w);
			if (undirected) {
				graph.get(e.v2).neighbours.put(graph.get(e.v1), e.w);

			}
		}
	}

	// Dijkstra to run on starting vertex
	public void dijkstra(String start) {
		if (!graph.containsKey(start)) {
			System.err.printf("Graph does not have start vertex \"%s\"\n",
					start);
			return;
		}
		final Vertex source = graph.get(start);

		// Priority Queue to store vertices
		PriorityQueue<Vertex> q1 = new PriorityQueue<>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.prev = (v == source) ? source : null;
			v.val = (v == source) ? 0 : Integer.MAX_VALUE; // setting to
															// infinite if it is
															// not source
			q1.add(v);

		}

		implementDijkstra(q1); // implementing Dijkstra algorithm on the priority

	}

	// / Implementation of dijkstra's algorithm using a queue
	private void implementDijkstra(final PriorityQueue<Vertex> q) {
		Vertex u, v;
		// Checking till queue is not empty
		while (!q.isEmpty()) {
			u = q.poll(); // vertex with shortest cost will be polled out of the
							// queue, for first time it will be start
			if (u.val == Integer.MAX_VALUE) {
				break; // we can ignore u (and any other remaining vertices)
						// since they are unreachable
			}

			// checking cost of each neighboring vertices
			for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
				v = a.getKey();

				final int tempDist = u.val + a.getValue();
				if (tempDist < v.val) { // if shortest path found to
											// neighboring vertex
					q.remove(v); // remove v
					v.val = tempDist;
					v.prev = u;
					q.add(v); // add new v
				}
			}
		}
	}

	// prints a path from the vertex (source) to the end vertex
	// This method was used for testing purpose only
	public void printPath(String last) {
		if (!graph.containsKey(last)) {
			System.err
					.printf("Graph doesn't contain end vertex \"%s\"\n", last);
			return;
		}

		graph.get(last).print();
		System.out.println();
	}

	// prints all the path from the source vertex to other vertices
	public void printAllPaths() {
		for (Vertex v : graph.values()) {
			v.print();
			System.out.println();
		}
	}

}