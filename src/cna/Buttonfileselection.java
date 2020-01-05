package cna;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Buttonfileselection {

	private JFrame frame;
	public String selected;

	//Launch the application.
	//public void fileSelection() {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Buttonfileselection window = new Buttonfileselection();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	//Create the application.
	public void bfs() {
		initialize();
	}
	
	public String getSelected() {
		return selected;
	}
	/*public String getSelected()
	{
		JFileChooser fC = new JFileChooser();
		File gml = fC.getSelectedFile();
		String s = gml.getAbsolutePath();
		
		return s;
	}*/

	//Initialize the contents of the frame.
	private void initialize() {
		
		JFileChooser fileChooser = new JFileChooser();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fileChooser.setDialogTitle("File Selection");
		
		FileFilter filter = new FileNameExtensionFilter("GRAPHML", "graphml");
		fileChooser.setFileFilter(filter);
		//fileChooser.setAcceptAllFileFilterUsed(false);
		
		int result = fileChooser.showDialog(null, "Select");
		File gml = fileChooser.getSelectedFile();
		
		if(result == JFileChooser.APPROVE_OPTION)
		{
			if(!gml.exists()){
				JOptionPane.showMessageDialog(null, "This file does not exist.");
			}
			else if(!gml.getName().contains(".graphml")){
				JOptionPane.showMessageDialog(null, "This is not a graphml file.");
			}
			else
				selected = gml.getAbsolutePath();
	    		System.out.println("Path: " + selected);
		}
		//else if (result == JFileChooser)
		else
		{
	    	System.out.println("Exit...");

		}
		/*
		switch (result) {
	    case JFileChooser.APPROVE_OPTION:
	    	if(!gml.exists()){
				JOptionPane.showMessageDialog(null, "This file does not exist.");
			}
			else if(!gml.getName().contains(".graphml")){
				JOptionPane.showMessageDialog(null, "This is not a graphml file.");
			}
			else
				selected = gml.getAbsolutePath();
	    		System.out.println("Path: " + selected);
	      break;
	   case JFileChooser.CANCEL_OPTION:
	    	System.out.println("Exit");
	    	break;
		}
		*/
		
	}
}
