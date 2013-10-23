import java.util.ArrayList;;
public class SimpleGraph {
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	
	
	public boolean AddNode(Node node){
		if(!nodes.contains(node))
		{	
			nodes.add(node);
			return true;
		}
		else 
		{
			//System.out.println("Duplicate node");
			return false;
		}
	}
	
	public boolean AddUniqueNode(Node node){
		
			nodes.add(node);
			return true;
	}
	
	public boolean AddEdge(Edge e)
	{
		if(!edges.contains(e))
		{
			edges.add(e);
			return true;
		}
		else 
			return false;
	}
	
	public boolean AddUniqueEdge(Edge e)
	{
		
		edges.add(e);
		return true;
	}
	
	public ArrayList<Node> getNodes(){ return nodes;}
	
	public ArrayList<Edge> getEdges(){ return edges;}
	
	public boolean RemoveNode(Node node){	
		return	nodes.remove(node);
	}
	
	public boolean RemoveEdge(Edge e){
		return edges.remove(e);
	}
	
	//get neighbour edges of current vertices
		public ArrayList<Edge> getNeighbourEdges(ArrayList<Node> vertices, ArrayList<Edge> remainEdges)
		{
			ArrayList<Edge> neighbourEdges = new ArrayList<Edge>();

				for(Edge e : remainEdges)
				{ 
					if( !vertices.contains(e.getNodeA()) && vertices.contains(e.getNodeB()))
					{
						neighbourEdges.add(e);
					}
					else if( vertices.contains(e.getNodeA()) && !vertices.contains(e.getNodeB()))
					{
						neighbourEdges.add(e);
					}
				}
			
			return neighbourEdges;
		}
	
	/*	public void getEachNodeNeighbour()
		{
			for(Node n: nodes)
			{
				for(Edge e: edges)
				{
					if(e.getNodeA().equals(n))
						n.neighbourEdges.add(e);
					if(e.getNodeB().equals(n))
						n.neighbourEdges.add(e);
					
				}
			}
		}*/
		
    public void PrintGraph(){
    	for(Node n : nodes)
    	{
    		System.out.print("Node: "+n.getNodeID()+", ");

    	}
    	System.out.println();
    	for(Edge e : edges)
    	{
    		System.out.print("Edge: "+ e.getNodeA().getNodeID() +"->"
    				+e.getNodeB().getNodeID()+" Weight: "+e.getWeight() +", ");
    		
    	}
    	System.out.println();
    	
  	
    }

}

