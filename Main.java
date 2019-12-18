package gui_cna;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;



public class Main {

	private JFrame frameCommunicationNetworkAnalysis;
	private JFrame frameStartAnalysis;
	private JTextField textFieldGraphmlFile;
	private JButton buttonFileSelection;
	private JTextField textFieldSelectedPath;
	//private DocumentBuilderFactory dBF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frameCommunicationNetworkAnalysis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/* First frame */
		frameCommunicationNetworkAnalysis = new JFrame();
		frameCommunicationNetworkAnalysis.setTitle("Communication Network Analysis");
		frameCommunicationNetworkAnalysis.setResizable(false);
		frameCommunicationNetworkAnalysis.setBounds(100, 100, 640, 250);
		frameCommunicationNetworkAnalysis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCommunicationNetworkAnalysis.getContentPane().setLayout(null);
		
		/* Label - Please insert a GRAPHML file */
		JLabel labelInsertGraphmlFile = new JLabel("Please insert a GRAPHML file.");
		labelInsertGraphmlFile.setHorizontalAlignment(SwingConstants.LEFT);
		labelInsertGraphmlFile.setBounds(49, 55, 170, 20);
		frameCommunicationNetworkAnalysis.getContentPane().add(labelInsertGraphmlFile);
		
		/* Text field - Insert the GRAPHML file here */
		textFieldGraphmlFile = new JTextField();
		textFieldGraphmlFile.setBounds(49, 80, 400, 19);
		frameCommunicationNetworkAnalysis.getContentPane().add(textFieldGraphmlFile);
		textFieldGraphmlFile.setColumns(10);
		
		/* Button - Start Analysis*/
		JButton buttonStartAnaylsis = new JButton("Start Analysis");
		buttonStartAnaylsis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
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
				
				
				try {
				File graphmlFile= new File(textFieldGraphmlFile.getText());
				
				System.out.println(graphmlFile);
				
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder dB = dBF.newDocumentBuilder();
				Document d = dB.parse(graphmlFile);
				
				NodeList nl = d.getElementsByTagName("node");
				NodeList nl1 = d.getElementsByTagName("edge");
				
				for(int i = 0; i < nl.getLength(); i++)
				{
					Node n =nl.item(i);
					
					if(i== nl.getLength()-1) { 
					
						if(n.getNodeType() == Node.ELEMENT_NODE)
						{
							Element lmnt = (Element) n;
							System.out.println("ID: " + lmnt.getAttribute("id"));
							System.out.println("Data: " + lmnt.getElementsByTagName("data").item(0).getTextContent());
							
							//System.out.println();
						}
					}
				}
				
				for (int i1 = 0; i1 < nl1.getLength();i1++)
				{
					Node n1 = nl1.item(i1);
					
					if(n1.getNodeType() == Node.ELEMENT_NODE)
					{
						Element lmnt1 = (Element) n1;
						System.out.println("ID: " + lmnt1.getAttribute("source"));
						System.out.println("ID: " + lmnt1.getAttribute("target"));
						System.out.println("Data: " + lmnt1.getElementsByTagName("key").item(0).getTextContent());
						
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
				
				
				
				
				
				
				
				textFieldSelectedPath.setText(textFieldGraphmlFile.getText());
				
				//System.out.println(xmlFile);
				
				
				
			}
		});
		buttonStartAnaylsis.setBounds(49, 130, 120, 21);
		frameCommunicationNetworkAnalysis.getContentPane().add(buttonStartAnaylsis);
		
		/* Button - File Selection */
		buttonFileSelection = new JButton("File Selection");
		buttonFileSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* JFileChooser opens a new Frame, where you can select, open or save files  */
				JFileChooser fileChooser = new JFileChooser();
				
			
				fileChooser.setDialogTitle("File Selection");
				
				/* setAcceptAllFileFilterUsed disable us to select all files */
				/* We force the user to use/select only graphml files, thats why there is no need for an error handling try/catch  */
				//fileChooser.setAcceptAllFileFilterUsed(false);
				
				/* FileFilter allows you to define a specific file extension */
				/* FileNameExtensionFilter helps you to insert a wished file extension */
				FileFilter filter = new FileNameExtensionFilter("GRAPHML", "graphml");
				fileChooser.setFileFilter(filter);
				
				
				
				/* Opens a frame with title and a button File Selection */
				/* Gets the current directory of a file */
				/* Outputs the current directory in the text field */
				int result = fileChooser.showDialog(null, "Select");
				File gml = fileChooser.getSelectedFile();
				
				
				/* Switch-case for clean coding */
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					
					
					if(!gml.exists())
					{
						JOptionPane.showMessageDialog(null, "This file does not exists.", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
					}
					else if(!gml.getName().contains(".graphml") )
					{
						JOptionPane.showMessageDialog(null, "This is not a graphml file.", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
					
					}
					else 
						textFieldGraphmlFile.setText(gml.getAbsolutePath());	
				}
				
				
				
				/* Switch-Case for testing, if buttons are working */
				/*switch (result) {
			    case JFileChooser.APPROVE_OPTION:
			      System.out.println("File Selection was clicked");
			      break;
			    case JFileChooser.CANCEL_OPTION:
			      System.out.println("Cancel or X was clicked");
			      break;
			    case JFileChooser.ERROR_OPTION:
			      System.out.println("Error");
			      break;
			    }*/
				
				/*File graphml = fileChooser.getSelectedFile();
				String graphmlfile = graphml.getAbsolutePath();
				textFieldGraphmlFile.setText(graphmlfile);*/
					
			}
		});
		
		buttonFileSelection.setBounds(459, 79, 115, 21);
		frameCommunicationNetworkAnalysis.getContentPane().add(buttonFileSelection);

	}
}
