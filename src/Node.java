
public class Node {
	private int Id;
	private boolean visited = false;
	
	//constructor
	public Node(){}
	public Node(int ID){ this.Id = ID;}
	
	public boolean IsVisited(){ return visited; }
	
	public int getNodeID() {return Id;}
	public void visit(){visited = true;}
	
	public void unvisit(){visited = false;}
	//public int compareTo(Node node){ return }
	
	@Override
	public boolean equals(Object o) {
		
		if(o != null && o instanceof Node)
		{
			return this.getNodeID() == ((Node) o).getNodeID();
		}
		
		return false;
	}
	
}
