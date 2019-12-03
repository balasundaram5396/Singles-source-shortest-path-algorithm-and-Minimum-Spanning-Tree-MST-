
import java.util.*;

public class KruskalsAlgorithm {

	private static Map<String, String> PARENT;
	private static Map<String, Integer> DEPTH;
	private static int w=0;
	public static void initialize(String[] universe) {
		PARENT = new HashMap<String, String>();
		DEPTH = new HashMap<>();
		for (String x : universe) {
			PARENT.put(x, x);
			DEPTH.put(x, 1);
		}
	}

	public static String Find(String i) {
		String parent = PARENT.get(i);
		if (parent == i)
			return i;
		else
			return Find(parent);
	}

	public static void Union(String a, String b) {
		String parent1, parent2;
		while ((parent1 = PARENT.get(a)) != a) {
			a = parent1;
		}
		while ((parent2 = PARENT.get(b)) != b) {
			b = parent2;
		}

		int depthFirst = DEPTH.get(a), depthSecond = DEPTH.get(b);
		if (depthFirst > depthSecond) {
			PARENT.put(b, a);
			updateDepthUp(b);
		} else if (depthSecond > depthFirst) {
			PARENT.put(a, b);
			updateDepthUp(a);
		} else {
			PARENT.put(b, a);
			updateDepthUp(b);
		}
	}

	public static void updateDepthUp(String cur) {
		int tempDepth = DEPTH.get(cur);
		String curParent = PARENT.get(cur);
		int parentsDepth = DEPTH.get(curParent);
		if (!(tempDepth < parentsDepth || curParent == cur)) {
			DEPTH.put(curParent, tempDepth + 1);
			updateDepthUp(curParent);
		}
	}

	public ArrayList<Edge> KruskalAlgo(String[] vertices, Edge[] edges) {
		ArrayList<Edge> min = new ArrayList<>();
		initialize(vertices);
		Arrays.sort(edges);
		for (Edge e : edges) {

			if (Find(e.v1) != Find(e.v2)) {
				min.add(e);
				w=w+e.w;
				Union(e.v1, e.v2);
			}
		}
		// Display the minimum spanning tree
		System.out.println("minimum spanning tree has the edges: " + min);
		System.out.println("Cost of the MST is "+w);
		return min;
	}
}