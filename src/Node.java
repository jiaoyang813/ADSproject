
public class Node {
	private int Id;
	private boolean visited = false;
	
	//constructor
	public Node(){}
	public Node(int ID){ this.Id = ID;}
	
	public boolean IsVisited(){ return visited; }
	
	public void visit(){visited = true;}
	
	public void unvisit(){visited = false;}
	//public int compareTo(Node node){ return }
	
	
}
