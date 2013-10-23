import java.util.*;

public class MSTSimple{
	private SimpleGraph graph;
	private double TotalCost = 0;
	
	public MSTSimple(SimpleGraph g) { graph = g;}
	
	public double getTotalCost(){ return TotalCost; }
	
	public ArrayList<Edge> getMST(){
		//vertices is the nodes of the MST
		ArrayList<Node> vertices = new ArrayList<Node>();
		//path is the edges of the MST
		ArrayList<Edge> path = new ArrayList<Edge>();
		// first add the first node in graph to vertices
		ArrayList<Edge> neighbourEdges  = new ArrayList<Edge>();
		ArrayList<Edge> remainEdges = graph.getEdges();
		vertices.add(graph.getNodes().get(0));
		ArrayList<Edge> buffer  = new ArrayList<Edge>();
		//it may not have MST, take care of it
		while( vertices.size() < graph.getNodes().size() )
		{
			/*neighbourEdges.clear();
			neighbourEdges = graph.getNeighbourEdges(vertices, remainEdges);
			Collections.sort(neighbourEdges, new edgeComparator());
			*/
			Collections.sort(remainEdges,new edgeComparator());
			while(true)
			{
				Edge minEdge = remainEdges.get(0);
				if(vertices.contains(minEdge.getNodeA())&&vertices.contains(minEdge.getNodeB()) )
				{
					//fheap.dequeueMin();
					remainEdges.remove(0);
					//System.out.println("delete existing edge fheap size "+fheap.size());
				}
				else if(!vertices.contains(minEdge.getNodeA())&&!vertices.contains(minEdge.getNodeB()))
				{
					//buffer.add(fheap.dequeueMin().getValue());
					buffer.add(remainEdges.remove(0));
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
			
			remainEdges.addAll(buffer);
			buffer.clear();
			TotalCost += path.get(path.size()-1).getWeight();
			//System.out.println(neighbourEdges.get(0).getWeight());
			// if no neigbour, there is no solution MST
			/*if(neighbourEdges.size() == 0)
			{
				System.out.println("neighbourEdges is empty, MST NOT exist");
				break;
			}
			
			path.add(neighbourEdges.get(0));
			System.out.println("Add Edge " + neighbourEdges.get(0).getNodeA().getNodeID() +
					"-"+ neighbourEdges.get(0).getNodeB().getNodeID()+"; weight: " +
					neighbourEdges.get(0).getWeight() + " Path length: "+ path.size());
			*/
			
			
			//TotalCost += neighbourEdges.get(0).getWeight();
			//add new node to vertices
			// check whether A or B in vertices
			/*boolean nodeAIn = false;
			for(Node node : vertices)
			{
				if(node.getNodeID() == neighbourEdges.get(0).getNodeA().getNodeID() )
				{
					nodeAIn = true;
					//System.out.println("Node "+ neighbourEdges.get(0).getNodeA().getNodeID() +" In vertices");
				}
				if(neighbourEdges.get(0).getNodeA().equals(node))
				{
					nodeAIn = true;
				}
					
			}

			if(nodeAIn == true)
			{
				vertices.add(neighbourEdges.get(0).getNodeB());
				//System.out.println("Add nodeB " + neighbourEdges.get(0).getNodeB().getNodeID());
			}
			else if(nodeAIn == false)
			{
				vertices.add(neighbourEdges.get(0).getNodeA());
				//System.out.println("Add nodeA " + neighbourEdges.get(0).getNodeA().getNodeID());
			}
			
			
			
			remainEdges.remove(neighbourEdges.get(0));*/
			//System.out.println("Remainning Edges: " + remainEdges.size());
		}
		
		/*if(vertices.size() < graph.getNodes().size())
		{
			System.out.println("No Min Spanning Tree");
			path = null;
			return path;
		}
	*/
		return path;
	}
	
	
	
	
	
}

class edgeComparator implements Comparator<Edge>{
	@Override
	public int compare(Edge e1, Edge e2) {
		if(e1.getWeight() < e2.getWeight())
			return -1;
		else if(e1.getWeight() == e2.getWeight())
			return 0;
		else
			return 1;
	}
	
	
}
