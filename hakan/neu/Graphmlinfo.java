import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class Graphmlinfo{
	public Document d;
	public DocumentBuilder dB;
	public DocumentBuilderFactory dBF;
	public Element element;
	public File graphmlFile;
	public Node node;
	public NodeList nodeListEdge;
	public NodeList nodeListNode;
	public Path path;
	public String[] EdgeIdArray;
	public String[] VertexIdArray;
	public int arrayCounter(String Array[]){
		int counter = 0;
		for(int i = 0; i <Array.length; i++){
				counter++;
		}
		return counter;
	}
	public void info(Node N, NodeList NL, Element E, String nARRAY[]){
		for(int vi = 0; vi < NL.getLength(); vi++){
			N = NL.item(vi);
			if(N.getNodeType() == Node.ELEMENT_NODE){
				E = (Element) N;
				nARRAY[vi] = E.getElementsByTagName("data").item(0).getTextContent();	 
				if(vi%5 == 0){
					System.out.print("\n");
				}	
				System.out.print(String.format(" %-2s",nARRAY[vi]));
			}
		}
	}
    public void graphmlReader(String graphmlFile){
		try{
			path = Paths.get(graphmlFile);
			System.out.println(path.getParent());
			String pathtostring=path.toString();
			dBF = DocumentBuilderFactory.newInstance();
			dB = dBF.newDocumentBuilder();
			d = dB.parse(pathtostring);
			nodeListNode = d.getElementsByTagName("node");
			nodeListEdge = d.getElementsByTagName("edge");
			VertexIdArray = new String[nodeListNode.getLength()];
			EdgeIdArray = new String[nodeListEdge.getLength()];
			System.out.print("THE VERTEX NAMES OR IDs:");
			info(node,nodeListNode,element,VertexIdArray);
			System.out.print("\nNUMBER OF NODES: "+arrayCounter(VertexIdArray));
			System.out.print("\n\nTHE EDGE NAMES OR IDs:");
			info(node,nodeListEdge,element,EdgeIdArray);
			System.out.print("\nNUMBER OF EDGES: "+arrayCounter(EdgeIdArray));
			} catch (ParserConfigurationException PCE){
				// TODO Auto-generated catch block
				PCE.printStackTrace();
			} catch (SAXException SAX){
				System.out.println("PLEASE CHECK THE GRAPHML FILE FOR ERRORS.");
			} catch(FileNotFoundException FNF){
				System.out.println("FILE NOT FOUND.");
			} catch (IOException IOE){
				IOE.printStackTrace();
			}
	}
}