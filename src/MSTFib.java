import java.util.*;

public class MSTFib{
	private SimpleGraph graph;
	private double TotalCost = 0;
	
	public MSTFib(SimpleGraph g) { graph = g;}
	
	public double getTotalCost(){ return TotalCost; }
	
	public ArrayList<Edge> getMST(){
		//vertices is the nodes of the MST
		ArrayList<Node> vertices = new ArrayList<Node>();
		//path is the edges of the MST
		ArrayList<Edge> path = new ArrayList<Edge>();
		// first add the first node in graph to vertices
		//ArrayList<Edge> neighbourEdges  = new ArrayList<Edge>();
		//ArrayList<Edge> remainEdges = graph.getEdges();
		vertices.add(graph.getNodes().get(0));
		//it may not have MST, take care of it
		FibonacciHeap<Edge> fheap = new FibonacciHeap<Edge>();
		
		for(Edge e : graph.getEdges())
		{
			//fheap.enqueue(e, e.getWeight());
			fheap.insert(e, e.getWeight());
			//System.out.println("Add edge "+ e.getWeight());
		}
		
		//System.out.println("fheap size "+fheap.size());
		ArrayList<Edge> buffer = new ArrayList<Edge>();
		while( vertices.size() < graph.getNodes().size() )
		{
			while(true)
			{
				//Edge minEdge = fheap.min().getValue();
				Edge minEdge = fheap.min().getData();
				if(vertices.contains(minEdge.getNodeA())&&vertices.contains(minEdge.getNodeB()) )
				{
					//fheap.dequeueMin();
					fheap.deleteMin();
					//System.out.println("delete existing edge fheap size "+fheap.size());
				}
				else if(!vertices.contains(minEdge.getNodeA())&&!vertices.contains(minEdge.getNodeB()))
				{
					//buffer.add(fheap.dequeueMin().getValue());
					buffer.add(fheap.deleteMin().getData());
					//System.out.println("Add to buffer "+ buffer.size());
				}
				else if(vertices.contains(minEdge.getNodeA())&&!vertices.contains(minEdge.getNodeB()))
				{
					vertices.add(minEdge.getNodeB());
					path.add(minEdge);
					//System.out.println("Add edge No."+ path.size());
					break;
				}
				else if(!vertices.contains(minEdge.getNodeA())&&vertices.contains(minEdge.getNodeB()))
				{
					vertices.add(minEdge.getNodeA());
					path.add(minEdge);
					//System.out.println("Add edge No."+ path.size());
					break;
					
				}
				else
				{
					System.out.println("Something bad happen");
					break;
				}
				
			}
			
			for(Edge e : buffer)
			{
				//fheap.enqueue(e, e.getWeight());
				fheap.insert(e, e.getWeight());
				//System.out.println("Add back "+ e.getWeight());
				
			}
			
			buffer.clear();
			TotalCost += path.get(path.size()-1).getWeight();
		
		}
		
		/*if(vertices.size() < graph.getNodes().size())
		{
			System.out.println("No Min Spanning Tree");
			path = null;
			return path;
		}*/
	
		return path;
	}
		
	
}


