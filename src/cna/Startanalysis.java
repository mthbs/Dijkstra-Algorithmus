package cna;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Startanalysis {


	private JFrame frame;
	private JFrame frameCommunicationNetworkAnalysis;
	private JTextField textFieldGraphmlFile;
	private JFrame frameStartAnalysis;
	private JTextField textFieldSelectedPath;
	private NodeList NodesList;
	public int[][] AdjacenceMatrix;
	public int[] DConnect; // All Nodes that are isolated
	public int value = 0;
	public String[] NodesL; //The NodesList as String with values like "n0,n1...."
	public int[] NodesLI; //The NodesLst as Integer with values like "0,1,2,...."
	public boolean hi = true;



	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startanalysis window = new Startanalysis();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	/*public Startanalysis() {
		initialize();
	}
*/
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(String x) {
		
				
				try {
				
				
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder dB = dBF.newDocumentBuilder();
				Document d = dB.parse(x);

				
				NodesList = d.getElementsByTagName("node");
				NodeList EdgesList = d.getElementsByTagName("edge");
				String convert = null;
				NodesL = new String[NodesList.getLength()];
				NodesLI = new int[NodesList.getLength()];
				AdjacenceMatrix  = new int[NodesList.getLength()][NodesList.getLength()];
				
				
				for(int i = 0; i < NodesList.getLength(); i++)
				{
					Node Nodes =NodesList.item(i);
					
					
					
						if(Nodes.getNodeType() == Node.ELEMENT_NODE)
						{
							Element lmnt = (Element) Nodes;
							NodesL[i] = lmnt.getAttribute("id");
							convert = lmnt.getElementsByTagName("data").item(0).getTextContent();
							NodesLI[i] = Integer.parseInt(convert);
						}
					
				}
				
				AdjacenceMatrix(x);
//				
//				
	
				
				} catch (ParserConfigurationException parser) {
					// TODO Auto-generated catch block
					parser.printStackTrace();
				} catch (SAXException sax) {
					// TODO Auto-generated catch block
					sax.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	
	}


	public void AdjacenceMatrix(String x) 
	{
		for (int i = 0; i < NodesList.getLength();i++)
		{
			Node Nodes = NodesList.item(i);
			
			if(Nodes.getNodeType() == Node.ELEMENT_NODE)
			{
				
				for(int j = 0; j < NodesList.getLength();j++) 
				{
					String src = null;
					String tgt = null; //Source ,Target Node
					
					src  = NodesL[i];
				    tgt  = NodesL[j];

				    
				    if(src == tgt) 
				    {
				    	AdjacenceMatrix[i][j] = 0;
				    	
				    }
				    else 
				    {
				    	value = Edge_Weight(x,src,tgt);
				    	AdjacenceMatrix[i][j] = value; 	
				    	
				    }
				}
				
			}
		}
		
		printAdjacenceMatrix();
		connectivity();
		
		
		Scanner scanner;
		
		scanner = new Scanner(System.in);
		System.out.println("Startnode: ");
		int startnode = scanner.nextInt();
		System.out.println("Targetnode: ");
		int targetnode = scanner.nextInt();
		
		Dijkstra algo = new Dijkstra(NodesL);
		algo.algorithm(AdjacenceMatrix, startnode, targetnode);
		scanner.close();
	}
	public int Edge_Weight(String x, String x1 , String x2)

{  
		
		try {
			
	    String y1,y2,svalue;
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
        DocumentBuilder dB = dBF.newDocumentBuilder();
        Document d = dB.parse(x);
        
		NodeList EdgesList = d.getElementsByTagName("edge"); 
		//Getting the Information from Nodes beginning with "Edge"
		
	for(int i = 0; i < EdgesList.getLength();i++) 
	{
		
        Node e = EdgesList.item(i);
	    Element lmnt1 = (Element) e;
	    svalue = lmnt1.getElementsByTagName("data").item(1).getTextContent();
	    value = Integer.parseInt(svalue);
	    //Getting the weight of an edge between two nodes as String
	    
	    y1 = lmnt1.getAttribute("source");//The Source and the target Node as String for a comparison
	    y2 = lmnt1.getAttribute("target");
	    
        
	    
	    if(x1.equals(y1) || x1.equals(y2)) 
	    {
	    	
	    	if(x2.equals(y1) || x2.equals(y2)) 
	    	{
	    	break;
	    	}
	    	else 
	    	{
	    		value = 0;
	    	}}
	    else 
	    {
	    	value = 0;
	    }}}
		catch (ParserConfigurationException parser) {
			// TODO Auto-generated catch block
			parser.printStackTrace();
		} catch (SAXException sax) {
			// TODO Auto-generated catch block
			sax.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
      return value;		
}
	


public void printAdjacenceMatrix() 
{
	for (int i = 0; i < NodesList.getLength();i++) //Printing the adjacency matrix
	  {
		for(int j = 0; j < NodesList.getLength();j++) {
       		if(j == NodesList.getLength()-1) {
				System.out.println(AdjacenceMatrix[i][j]);
				}
       		else 
       		{
       			System.out.print(AdjacenceMatrix[i][j]);
       		}
			}
		}

}


public void connectivity() 
{
	
	int mat[][] = 
		    { { 0, 0, 0, 0, 0 }, 
            { 0, 0, 0, 0, 0 }, 
            { 0, 1, 1, 1, 1 }, 
            { 1, 1, 1, 1, 1 }, 
            { 1, 1, 1, 1, 1 } };
	
	boolean isConnect = true;
    Integer [] DConnect = new Integer[4]; 
    int count = 0;
	
	for(int i = 0; i < 4; i++) 
	{
		for(int j = 0; j < 4; j++) 
		{
//			if(AdjacenceMatrix[i][j] == 0) 
//			{
			if(mat[i][j] == 0) {
				isConnect = false;
				
				if(j == 4-1) 
				{
					DConnect[count] = i;
					count++;
				}}
			isConnect = true;
			}}
	
	
//	if(isConnect == false) 
//	{
		System.out.println("FALSCH, die falschen Stellen sind :");
		for(int m = 0; DConnect[m] != null; m++) 
		{
			
			System.out.println(DConnect[m]);
//		}
//	}
//	else 
//	{
//		System.out.println("RICHTIG");
//		
//	}
}}}