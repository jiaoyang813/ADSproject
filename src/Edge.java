
public class Edge {
	private Node A, B;
	private int weight;
	
	public Edge(Node a, Node b)
	{
		this(a, b, Integer.MAX_VALUE);
	}
	public Edge(Node a, Node b, int EdgeWeight)
	{
		A = a;
		B = b;
		this.weight = EdgeWeight;
	}
	public int getWeight(){	return this.weight;}
	
	public Node getNodeA(){return A;}
	public Node getNodeB(){return B;}
	@Override
	public boolean equals(Object o){
		if( o != null && o instanceof Edge)
		{
			return ( this.A.getNodeID() == ((Edge)o).getNodeA().getNodeID() &&
					this.B.getNodeID() == ((Edge)o).getNodeB().getNodeID() ) ||
					( this.B.getNodeID() == ((Edge)o).getNodeA().getNodeID() &&
					this.A.getNodeID() == ((Edge)o).getNodeB().getNodeID() );
		}
		
		return false;
	}
	
}
