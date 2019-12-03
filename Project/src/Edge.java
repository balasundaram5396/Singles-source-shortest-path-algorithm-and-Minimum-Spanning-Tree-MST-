
public class Edge implements Comparable<Edge> {

	public final String v1, v2;
	public final int w;

	@Override
	public String toString() {
		return "Edge{" + "v1='" + v1 + '\'' + ", v2='" + v2 + '\''
				+ ", weight=" + w + '}';
	}

	public Edge(String v1, String v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}

	public String fromVertex() {
		return this.v1;
	}

	public String toVertex() {
		return this.v2;
	}

	@Override
	public int compareTo(Edge o) {

		return w < o.w ? -1 : (w > o.w ? 1 : 0);
	}
}