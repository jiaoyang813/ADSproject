
public class RandomGraph {
	private int NumNode;
	private int NumEdge;
	
	public RandomGraph(int nodes, double dense)
	{
		NumNode = nodes;
		NumEdge = (int) ( dense * nodes*(nodes-1)/2);
	}
	
	public SimpleGraph Generate(){
		SimpleGraph graph = new SimpleGraph();
		int weight;
		int IndexNodeA;
		int IndexNodeB;
		// add node to graph
		for(int i = 0; i < NumNode; i++)
		{
			Node node = new Node(i);
			graph.AddNode(node);
		}
		
		//add edge to graph
		int edgecount = 0;
		while(edgecount < NumEdge)
		{
			weight = (int )(Math.random() * 1000 + 1);
			IndexNodeA = (int )(Math.random() * NumNode);
			IndexNodeB = (int )(Math.random() * NumNode);
			while(IndexNodeB == IndexNodeA)
			{
				IndexNodeA = (int )(Math.random() * NumNode);
			}
			Edge e = new Edge(graph.getNodes().get(IndexNodeA), 
								graph.getNodes().get(IndexNodeB), (double)weight);
			boolean IsAdded = graph.AddEdge(e);
			if(IsAdded)
				edgecount++;

		}
		
		System.out.println("A new random graph");
		return graph;
		
		
	}
	
}
