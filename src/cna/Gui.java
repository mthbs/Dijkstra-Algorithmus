package cna;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Gui {

	private JFrame frameCommunicationNetworkAnalysis;
	private JLabel labelInsertGraphmlFile;
	private JTextField textFieldFileToBeInserted;
	private JButton buttonFileSelection;
	
	//Eigene Klasse
	Buttonfileselection fileselection = new Buttonfileselection();
	Startanalysis sa = new Startanalysis();

	//Launch the application.
	 public void mainFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Gui window = new Gui();
					initialize();
					frameCommunicationNetworkAnalysis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public Gui() {
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() {
		
		//Mainframe
		frameCommunicationNetworkAnalysis = new JFrame();
		frameCommunicationNetworkAnalysis.setTitle("Communication Network Analysis");
		frameCommunicationNetworkAnalysis.setResizable(false);
		frameCommunicationNetworkAnalysis.setBounds(100, 100, 640, 250);
		frameCommunicationNetworkAnalysis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCommunicationNetworkAnalysis.getContentPane().setLayout(null);
		
		//Label - Please insert a Graphml file
		labelInsertGraphmlFile = new JLabel("Please insert a Graphml file.");
		labelInsertGraphmlFile.setHorizontalAlignment(SwingConstants.LEFT);
		labelInsertGraphmlFile.setBounds(50, 50, 170, 20);
		frameCommunicationNetworkAnalysis.getContentPane().add(labelInsertGraphmlFile);
		
		//Text field - File to be inserted
		textFieldFileToBeInserted = new JTextField();
		textFieldFileToBeInserted.setBounds(50, 80, 400, 20);
		frameCommunicationNetworkAnalysis.getContentPane().add(textFieldFileToBeInserted);
		textFieldFileToBeInserted.setColumns(10);
		
		//Button - File Selection
		buttonFileSelection = new JButton("File Selection");
		buttonFileSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Buttonfileselection fileselection = new Buttonfileselection();
				fileselection.bfs();
				//fileselection.fileSelection();
				
				textFieldFileToBeInserted.setText(fileselection.getSelected());
			}
		});
		buttonFileSelection.setBounds(460, 79, 115, 20);
		frameCommunicationNetworkAnalysis.getContentPane().add(buttonFileSelection);
		
		/* Button - Start Analysis*/
		JButton buttonStartAnaylsis = new JButton("Start Analysis");
		buttonStartAnaylsis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sa.initialize(fileselection.getSelected());
				//System.out.println("ARRAY: " + sa.NodesL[sa.NodesL.length-1]);
			}
		});
		buttonStartAnaylsis.setBounds(49, 130, 120, 21);
		frameCommunicationNetworkAnalysis.getContentPane().add(buttonStartAnaylsis);
	}



}
