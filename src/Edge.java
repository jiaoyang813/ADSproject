
public class Edge {
	private Node A, B;
	private double weight;
	
	public Edge(Node a, Node b)
	{
		this(a, b, Double.POSITIVE_INFINITY);
	}
	public Edge(Node a, Node b, double EdgeWeight)
	{
		A = a;
		B = b;
		this.weight = EdgeWeight;
	}
	public double getWeight(){	return this.weight;}
	
}
