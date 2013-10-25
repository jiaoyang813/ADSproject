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
			System.out.println("Random Mode");
			//random mode: mst -r n d  n vertices with d% density.
			RandomGraph rdgraph = new RandomGraph(Integer.parseInt(args[1]), Double.parseDouble(args[2]));
			SimpleGraph randomGraph = rdgraph.Generate();
			
			//fibMST random mode
			System.out.println("Genrating MST(f-heap scheme)...");
			
			MSTFib mstfib = new MSTFib(randomGraph);
			HeapStartTime = System.currentTimeMillis();
			resultMST = mstfib.getMST();
			HeapEndTime = System.currentTimeMillis() - HeapStartTime;
			//printMST(resultMST);
			System.out.println("MST(f-heap scheme cost) "+mstfib.getTotalCost());
			
			//System.out.println("f-heap scheme time: "+ HeapEndTime+"ms");
			
			//simple MST random mode
			System.out.println("Genrating MST(simple scheme)...");
			
			MSTSimple simpleMST = new MSTSimple(randomGraph);
			ArrayStartTime = System.currentTimeMillis();
			resultMST = simpleMST.getMST();
			ArrayEndTime = System.currentTimeMillis() - ArrayStartTime;
			//printMST(resultMST);
			System.out.println("MST(Simple Scheme) cost "+simpleMST.getTotalCost());
			System.out.println("f-heap scheme running time: "+ HeapEndTime+"ms");
			System.out.println("simple scheme running time: "+ ArrayEndTime+"ms");
		
		}
		
		else if(args[0].equals("-s"))
		{
			//user input mode: mst -s filename
			System.out.println("User Input Mode(simple scheme)");
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
			System.out.println(simpleMST.getTotalCost());
			outputMST(resultMST);

		}
		
		else if(args[0].equals("-f") )
		{
			//user input mode: mst -f filename
			System.out.println("User Input Mode(f-heap scheme)");
			SimpleGraph InputGraph = new SimpleGraph();
			try {
				// use scanner to read file from disk
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
			System.out.println(mstfib.getTotalCost());
			outputMST(resultMST);	
		}
		else{
			System.out.println("Invalid input mode");
		}
			

	}
	
	//standard output stream to print out the MST
	public static void outputMST(ArrayList<Edge> mst)
	{
		if(mst != null)
		{
			for(Edge e : mst)
	    	{
				System.out.println(e.getNodeA().getNodeID() +" "
	    				+e.getNodeB().getNodeID() +" "+e.getWeight());
	    	}
				
		}
		else 
			System.out.println("No MST exist. Can't print mst");
		
	}
	
	//print mst
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
		
		System.out.print("");
		
	}

}
