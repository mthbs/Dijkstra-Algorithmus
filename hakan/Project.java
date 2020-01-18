import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class Project{
	public int counterfornodes;
	public int counterforedges;
	public String NumberOfNodes;
	public String NumberOfEdges;
	public String[] NodeVertexNamesIdArray;
	public String[] EdgeNamesIdArray;
	public int[][]WeightArray;
	public int value = 0;
	public String getNumberOfNodes(){
		return NumberOfNodes;
	}
	public String getNumberOfEdges(){
		return NumberOfEdges;
	}
    public void graphmlAnalysis(String inputFileX) {	
			try{
				File graphmlFile= new File("C:\\Users\\hakan\\OneDrive\\Desktop\\"+inputFileX);
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder dB = dBF.newDocumentBuilder();
				Document d = dB.parse(graphmlFile);
				NodeList nodecollection = d.getElementsByTagName("node");
				NodeList edgecollection = d.getElementsByTagName("edge");
				NodeVertexNamesIdArray = new String[nodecollection.getLength()];
				EdgeNamesIdArray = new String[edgecollection.getLength()];
				WeightArray = new int[nodecollection.getLength()][nodecollection.getLength()];
				System.out.print("THE VERTEX NAMES OR IDs:");
				for(int countnodes = 0; countnodes < nodecollection.getLength(); countnodes++){
					Node singelnode = nodecollection.item(countnodes);
						if(singelnode.getNodeType() == Node.ELEMENT_NODE){
							Element elementnode = (Element) singelnode;
							NodeVertexNamesIdArray[countnodes] = elementnode.getElementsByTagName("data").item(0).getTextContent();
							if(countnodes%5 == 0){
								System.out.print("\n");
							}System.out.print(String.format(" %-2s",NodeVertexNamesIdArray[countnodes]));
							if(NodeVertexNamesIdArray[countnodes] == NodeVertexNamesIdArray[countnodes]){
								counterfornodes++;
							}
						}
				}
				NumberOfNodes = String.valueOf(counterfornodes);
				System.out.println("\nNUMBER OF NODES: "+counterfornodes+"\n");
				System.out.print("THE EDGE NAME OR IDs:");
				for(int countedges = 0; countedges < edgecollection.getLength(); countedges++){
					Node singeledge = edgecollection.item(countedges);
						if(singeledge.getNodeType() == Node.ELEMENT_NODE){
							Element elementedge = (Element) singeledge;
							EdgeNamesIdArray[countedges] = elementedge.getElementsByTagName("data").item(0).getTextContent();
							if(countedges%5 == 0){
								System.out.print("\n");
							}System.out.print(String.format(" %-2s",EdgeNamesIdArray[countedges]));
							if(EdgeNamesIdArray[countedges] == EdgeNamesIdArray[countedges]){	
								counterforedges++;
							}
						}
				}
				NumberOfEdges = String.valueOf(counterforedges);
				System.out.println("\nNUMBER OF EDGES: "+counterforedges);
				for(int countnodes = 0; countnodes < nodecollection.getLength(); countnodes++){
					Node singelnode = nodecollection.item(countnodes);
						if(singelnode.getNodeType() == Node.ELEMENT_NODE){
							Element elementnode = (Element) singelnode;
							NodeVertexNamesIdArray[countnodes] = elementnode.getAttribute("id");
						}
				}
				System.out.print(String.format("%-4s", "\nADJACENCY MATRIX\n\n"+"n = Node\n"+"w = Weight\n\n"+String.format("%-6s","w")));
				for(int countnodes = 0; countnodes < nodecollection.getLength(); countnodes++) {
					System.out.print(String.format("%-4s", NodeVertexNamesIdArray[countnodes]));
				}System.out.println("\n");
				for(int countnodessource = 0; countnodessource < nodecollection.getLength(); countnodessource++) {
					Node singelnode = nodecollection.item(countnodessource);
					System.out.print(String.format("%-6s", NodeVertexNamesIdArray[countnodessource]));
					if(singelnode.getNodeType() == Node.ELEMENT_NODE){
						for(int countnodestarget = 0; countnodestarget < nodecollection.getLength();countnodestarget++){
							String sourcenode = NodeVertexNamesIdArray[countnodessource];
							String targetnode = NodeVertexNamesIdArray[countnodestarget];
							if(sourcenode == targetnode){
								WeightArray[countnodessource][countnodestarget] = 0;
							}else{
								value = ed(inputFileX,sourcenode,targetnode);
								WeightArray[countnodessource][countnodestarget]= value;
							}if(countnodestarget == nodecollection.getLength()-1){
								System.out.println(WeightArray[countnodessource][countnodestarget]+" ");
							}else{
								System.out.print(String.format("%-4s",WeightArray[countnodessource][countnodestarget]));
							}
						}
					}
				}
				System.out.println(NodeVertexNamesIdArray[5]);
			} catch (ParserConfigurationException parser) {
					// TODO Auto-generated catch block
					parser.printStackTrace();
			} catch (SAXException sax) {
					// TODO Auto-generated catch block
					sax.printStackTrace();
			} catch (FileNotFoundException fe){
					System.out.println("File not found!"+fe);
			} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("Fehler im code!"+e1);
					//System.out.println("Bitte ");
			}
	}

	public int ed(String x, String x1 , String x2) {  
		
		try {
			String y1,y2,svalue;
			File graphmlX= new File("C:\\Users\\hakan\\OneDrive\\Desktop\\"+x);
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document d = dB.parse(graphmlX);
        
			NodeList nl1 = d.getElementsByTagName("edge"); 
			//Getting the Information from Nodes beginning with "Edge"
		
			for(int i1 = 0; i1 < nl1.getLength();i1++) {
				Node n1 = nl1.item(i1);
				Element lmnt1 = (Element) n1;
				svalue = lmnt1.getElementsByTagName("data").item(1).getTextContent();
				value = Integer.parseInt(svalue);
				//Getting the weight of an edge between two nodes as String
	    
				//The Source and the target Node as String
				y1 = lmnt1.getAttribute("source");
				y2 = lmnt1.getAttribute("target");

				if(x1.equals(y1) || x1.equals(y2)) {
					if(x2.equals(y1) || x2.equals(y2)) {
						break;
					}else 
						value = 0;
				}else 
					value = 0;
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
		return value;		
	}
	
}


