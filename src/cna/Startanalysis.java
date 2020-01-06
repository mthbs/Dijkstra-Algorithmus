package cna;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
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
import java.lang.*;

public class Startanalysis {

	private JFrame frame;
	private JFrame frameCommunicationNetworkAnalysis;
	private JTextField textFieldGraphmlFile;
	private JFrame frameStartAnalysis;
	private JTextField textFieldSelectedPath;
	private NodeList nl;
	public String[] NodesL;
	public String[] EdgesL;
	public int[][] Combo;
	public int value = 0;
	
	//Buttonfileselection bfs= new Buttonfileselection(); 



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
	
		
		
				/*
				frameStartAnalysis = new JFrame();
				frameStartAnalysis.setTitle("Start Analysis");
				frameStartAnalysis.setResizable(false);
				frameStartAnalysis.setBounds(100, 100, 640, 250);
				frameStartAnalysis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frameStartAnalysis.getContentPane().setLayout(null);
				frameStartAnalysis.setVisible(true);
				
				textFieldSelectedPath = new JTextField();
				textFieldSelectedPath.setBounds(49, 80, 400, 19);
				frameStartAnalysis.getContentPane().add(textFieldSelectedPath);
				textFieldSelectedPath.setColumns(10);
				*/
				
				try {
				//File graphmlFile= new File(textFieldGraphmlFile.getText(x);
				
				//System.out.println(graphmlFile);
				
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder dB = dBF.newDocumentBuilder();
				Document d = dB.parse(x);
				
				
				//String[] array;
				
				
				nl = d.getElementsByTagName("node");
				NodeList nl1 = d.getElementsByTagName("edge");
				NodesL = new String[nl.getLength()];
				EdgesL = new String[nl1.getLength()];
				Combo  = new int[nl.getLength()][nl.getLength()];
				
				
				for(int i = 0; i < nl.getLength(); i++)
				{
					Node n =nl.item(i);
					
					//if(i== nl.getLength()-1) { 
					
						if(n.getNodeType() == Node.ELEMENT_NODE)
						{
							Element lmnt = (Element) n;
							NodesL[i] = lmnt.getAttribute("id");
							//System.out.println("ID: " + lmnt.getAttribute("id"));
							//System.out.println("Data: " + lmnt.getElementsByTagName("data").item(0).getTextContent());
							
							//System.out.println();
						}
					//}
				}
				
				
				for (int i = 0; i < nl.getLength();i++)
				{
					Node n =nl.item(i);
					Node n1 = nl1.item(i);
					
					if(n.getNodeType() == Node.ELEMENT_NODE)
					{
						Element lmnt1 = (Element) n1;
//						System.out.println("ID Source: " + lmnt1.getAttribute("source"));
//						System.out.println("ID Target: " + lmnt1.getAttribute("target"));
//						EdgesL[i1] = lmnt1.getElementsByTagName("data").item(0).getTextContent();
//						System.out.println("Data Edge ID: " + lmnt1.getElementsByTagName("data").item(0).getTextContent());
//						System.out.println("Data Edge Weight: " + lmnt1.getElementsByTagName("data").item(1).getTextContent());
//                       
						for(int j = 0; j < nl.getLength();j++) 
						{
							String src = null;
							String tgt = null; //Source ,Target Node
							
							src  = NodesL[i];
						    tgt  = NodesL[j];

						    
						    if(src == tgt) 
						    {
						    	Combo[i][j] = 0;
						    	
						    }
						    else 
						    {
						    	value = ed(x,src,tgt);
						    	Combo[i][j] = value;
						    	
						    	
						    	
						    	
						    }
						}
						
					}
				}
				
				
				
				
    			for (int i = 0; i < nl.getLength();i++) //Printing the adjacency matrix
			  {	
   				
		         	for(int j = 0; j < nl.getLength();j++) {
		         		if(j == nl.getLength()-1) {
						System.out.println(Combo[i][j]);
						}
		         		else 
		         		{
		         			System.out.print(Combo[i][j]);
		         		}
					}
				}
				
				
				
				
				
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
				
//				for(int i = 0; i<NodesL.length;i++)
//				{
//					System.out.println("Nodes ID:" + NodesL[i]);
//				}
//				
//				
//				for(int i = 0; i<EdgesL.length;i++) {
//					
//					System.out.println("Edges ID:" + EdgesL[i]);
//				}
				
				
				
				
				
				
				
			}
	/*public String [] NodesLoutput()
	{
		array = new String[nl.getLength()];

		for(int i = 0; i<array.length-1;i++)
		{
			System.out.println("array :" + array[i]);
		}
		
		return array;
	}*/
	
		
	public int ed(String x, String x1 , String x2)

{  
		
		try {
			
	    String y1,y2,svalue;
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
        DocumentBuilder dB = dBF.newDocumentBuilder();
        Document d = dB.parse(x);
        
		NodeList nl1 = d.getElementsByTagName("edge"); 
		//Getting the Information from Nodes beginning with "Edge"
		
	for(int i1 = 0; i1 < nl1.getLength();i1++) 
	{
		
        Node n1 = nl1.item(i1);
	    Element lmnt1 = (Element) n1;
	    svalue = lmnt1.getElementsByTagName("data").item(1).getTextContent();
	    value = Integer.parseInt(svalue);
	    //Getting the weight of an edge between two nodes as String
	    
	    y1 = lmnt1.getAttribute("source");//The Source and the target Node as String
	    y2 = lmnt1.getAttribute("target");
	    
//	    System.out.println("Value is " + x1+x2);
//	    System.out.println("Compared to " + y1+y2);
	    
	    
	   
	    
		
	    if(x1.equals(y1) || x1.equals(y2)) 
	    {
	    	
	    	if(x2.equals(y1) || x2.equals(y2)) 
	    	{
	    		
	    	
	    	break;
	    	
	    	
	    	}
	    	else 
	    	{
	    		value = 0;
	    	}
	    	
	    	
	    }
	    
	    else 
	    {
	    	value = 0;
	    }
	    
	    
	    
	} 
	
		}
		
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
}}


//?_?