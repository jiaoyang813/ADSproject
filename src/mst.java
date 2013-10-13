import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class mst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int numEdges;
		int numNodes;
		ArrayList<Edge> resultMST;
		//random mode: mst -r n d  n vertices with d% density
		RandomGraph rdgraph = new RandomGraph(1000, 0.1);
		SimpleGraph randomGraph = rdgraph.Generate();
		//user input mode: mst -s filename
		//user input mode: mst -f filename
		SimpleGraph g = new SimpleGraph();
		try {
			Scanner FileIn = new Scanner(new File("graph.txt"));
			numNodes = Integer.parseInt(FileIn.next() );
			numEdges = Integer.parseInt(FileIn.next() );
			while(FileIn.hasNextLine())
			{
				Node a = new Node(Integer.parseInt(FileIn.next()) );
				Node b = new Node(Integer.parseInt(FileIn.next()) );
				Edge e = new Edge(a, b, Integer.parseInt(FileIn.next()) );
				g.AddNode(a);
				g.AddNode(b);
				g.AddEdge(e);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//g.PrintGraph();
		//user input graph
		//MSTSimple simpleMST = new MSTSimple(g);
		//random graph
		//randomGraph.PrintGraph();
		MSTSimple simpleMST = new MSTSimple(randomGraph);
		resultMST = simpleMST.getMST();
		System.out.println(simpleMST.getTotalCost());
		System.out.println("ResultMST:");
		printMST(resultMST);
		
	}
	
	public static void printMST(ArrayList<Edge> mst)
	{
		if(mst != null)
		{
			for(Edge e : mst)
	    	{
	    		System.out.print("Edge: "+ e.getNodeA().getNodeID() +"->"
	    				+e.getNodeB().getNodeID()+" Weight: "+e.getWeight() +", ");
	    	}
		}
		else 
			System.out.println("No MST exist. Can't print mst");
		
	}

}
