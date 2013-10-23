import java.util.Random;
import java.util.*;
import java.util.HashSet;
public class RandomGraph {
	private int NumNode;
	private int NumEdge;
	private double density;
	
	public RandomGraph(int nodes, double dense)
	{
		NumNode = nodes;
		NumEdge = (int) ( dense/100.0 * nodes*(nodes-1)/2);
		density = dense;
	}
	
	public SimpleGraph Generate(){
		SimpleGraph graph = new SimpleGraph();
		Random rd = new Random(System.currentTimeMillis());
		ArrayList<Node> newNode = new ArrayList<Node>();
		ArrayList<Edge> newEdge = new ArrayList<Edge>();
		int weight;
		
		int edgecount = 0;
		
		for(int i = 0; i <NumNode; i++)
		{
			for(int j = 0; j <i; j++)
			{
				
				if(rd.nextDouble() <= density )
				{	
					weight = rd.nextInt(1000) + 1;
					Edge e = new Edge(new Node(i), new Node(j), weight);
					newEdge.add(e);
					graph.AddUniqueEdge(e);
					//System.out.println("Edge No."+edgecount++);
				}
			}
		}
		
		//add newEdge newNode to graph
		int mysize = 0;
		for(Edge e : newEdge)
		{
			graph.AddNode(e.getNodeA());
			//graph.AddNode(e.getNodeB());
			//newNode.add(e.getNodeA());
			//newNode.add(e.getNodeB());
			//System.out.println("AddEdge No."+mysize++);
		}
		
		//remove duplicate in newNode
	/*	HashSet<Node> uniqueNodeSet = new HashSet<Node>(newNode);
		System.out.println("Unique size "+uniqueNodeSet.size());
		System.out.println("original size "+newNode.size());
		for(Node n : newNode )
		{
			graph.AddNode(n);
		}
		*/
		
		System.out.println("A new random graph(Node = "+NumNode+ ", density = " + density+"%)");
		return graph;
		
		
	}
	
}
