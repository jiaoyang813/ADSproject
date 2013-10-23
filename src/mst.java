import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class mst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long ArrayStartTime;
		long HeapStartTime;
		long ArrayEndTime;
		long HeapEndTime;
		int numEdges;
		int numNodes;
		ArrayList<Edge> resultMST;
		//random mode
		if( args[0].equals("-r"))
		{
			//random mode: mst -r n d  n vertices with d% density.
			RandomGraph rdgraph = new RandomGraph(Integer.parseInt(args[1]), Double.parseDouble(args[2]));
			SimpleGraph randomGraph = rdgraph.Generate();
			
			//fibMST random mode
			HeapStartTime = System.currentTimeMillis();
			MSTFib mstfib = new MSTFib(randomGraph);
			printMST(mstfib.getMST());
			System.out.println("MST cost "+mstfib.getTotalCost());
			HeapEndTime = System.currentTimeMillis() - HeapStartTime;
			//simple MST random mode
			ArrayStartTime = System.currentTimeMillis();
			MSTSimple simpleMST = new MSTSimple(randomGraph);
			printMST(simpleMST.getMST());
			System.out.println("MSTSimple cost "+simpleMST.getTotalCost());
			ArrayEndTime = System.currentTimeMillis() - ArrayStartTime;
			System.out.println("Heap time "+ HeapEndTime + "; Array time "+ ArrayEndTime);
		}
		
		else if(args[0].equals("-s"))
		{
			//user input mode: mst -s filename
			SimpleGraph InputGraph = new SimpleGraph();
			try {
				Scanner FileIn = new Scanner(new File(args[1]));
				numNodes = Integer.parseInt(FileIn.next() );
				numEdges = Integer.parseInt(FileIn.next() );
				while(FileIn.hasNextLine())
				{
					Node a = new Node(Integer.parseInt(FileIn.next()) );
					Node b = new Node(Integer.parseInt(FileIn.next()) );
					Edge e = new Edge(a, b, Integer.parseInt(FileIn.next()) );
					InputGraph.AddNode(a);
					InputGraph.AddNode(b);
					InputGraph.AddEdge(e);
				}
				
				FileIn.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			//simple MST input mode
			MSTSimple simpleMST = new MSTSimple(InputGraph);
			resultMST = simpleMST.getMST();
			System.out.println("MSTSimple cost "+simpleMST.getTotalCost());
			outputMST(resultMST);
			
			
			
		}
		
		else if(args[0].equals("-f") )
		{
			//user input mode: mst -f filename
			SimpleGraph InputGraph = new SimpleGraph();
			try {
				Scanner FileIn = new Scanner(new File(args[1]));
				numNodes = Integer.parseInt(FileIn.next() );
				numEdges = Integer.parseInt(FileIn.next() );
				while(FileIn.hasNextLine())
				{
					Node a = new Node(Integer.parseInt(FileIn.next()) );
					Node b = new Node(Integer.parseInt(FileIn.next()) );
					Edge e = new Edge(a, b, Integer.parseInt(FileIn.next()) );
					InputGraph.AddNode(a);
					InputGraph.AddNode(b);
					InputGraph.AddEdge(e);
				}
				
				FileIn.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//fibMST
			// to init the neighbour edge of each node.
			MSTFib mstfib = new MSTFib(InputGraph);
			resultMST = mstfib.getMST();
			System.out.println("MST cost "+mstfib.getTotalCost());
			outputMST(resultMST);	
		}
		else{
			System.out.println("Invalid input mode");
		}
			

	}
	
	public static void outputMST(ArrayList<Edge> mst)
	{
		if(mst != null)
		{
			for(Edge e : mst)
	    	{
	    		System.out.println(e.getNodeA().getNodeID() +" "
	    				+e.getNodeB().getNodeID());
	    	}
			
			
		}
		else 
			System.out.println("No MST exist. Can't print mst");
		
	}
	
	public static void printMST(ArrayList<Edge> mst)
	{
		if(mst != null)
		{
			for(Edge e : mst)
	    	{
	    		System.out.print("Edge: "+ e.getNodeA().getNodeID() +"-"
	    				+e.getNodeB().getNodeID()+" Weight: "+e.getWeight() +", ");
	    	}
			
			
		}
		else 
			System.out.println("No MST exist. Can't print mst");
		
	}

}
