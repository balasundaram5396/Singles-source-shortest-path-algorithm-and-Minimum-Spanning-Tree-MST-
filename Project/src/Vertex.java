
import java.util.HashMap;
import java.util.Map;

public class Vertex implements Comparable<Vertex> {
	Vertex prev = null;
	public int val = Integer.MAX_VALUE;
	public final String node;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();



	public int compareTo(Vertex temp) {
		if (val == temp.val)
			return node.compareTo(temp.node);

		return Integer.compare(val, temp.val);
	}

	public void print() {
		if (this == this.prev) {
			System.out.printf("%s", this.node);
		} else if (this.prev == null) {
			System.out.printf("%s(cannot reach)", this.node);
		} else {
			this.prev.print();
			System.out.printf(" -> %s(%d)", this.node, this.val);
		}
	}

	public Vertex(String node) {
		this.node = node;
	}

	@Override
	public String toString()

	{
		return "(" + node + ", " + val + ")";
	}
}