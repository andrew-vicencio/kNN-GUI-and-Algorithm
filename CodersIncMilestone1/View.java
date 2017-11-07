import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
/**
 * View class for the GUI. This class is responsible for initiating the UI frame, and updating it as required
 *
 * @version Milestone 2
 * @author Gabrielle
 */


public class View {
	private JFrame mainFrame;
	private JMenuBar menuBar;
	private JMenu create, edit, display, help;
	private JMenuItem newDataSet, newTestCase, addValue, helpDoc;
	private JPanel mainPanel;
	private HashMap<String, String> dataType;
	
	 /**
     * Initializes and displays the initial view
     *
     */
	public View()
	{
		mainFrame = new JFrame("CODERS INC");
		menuBar = new JMenuBar();
		create = new JMenu("Create");
		edit = new JMenu("Edit");
		display = new JMenu("Display");
		help = new JMenu("Help");
		mainPanel = new JPanel();
		newDataSet = new JMenuItem("New Data Set");
		newTestCase = new JMenuItem("New Test Case");
		addValue = new JMenuItem("Add Value");
		helpDoc = new JMenuItem("View Help Documents");
		
		
		mainFrame.setJMenuBar(menuBar);
		menuBar.add(create);
		menuBar.add(edit);
		menuBar.add(display);
		menuBar.add(help);
		create.add(newDataSet);
		create.add(newTestCase);
		edit.add("Add Values");
		help.add("View User Guide");
		
		mainFrame.setSize(500, 500);
		mainPanel.setLayout(new BoxLayout(mainPanel, 0));
		newTestCase.setEnabled(false);
		edit.setEnabled(false);
		display.setEnabled(false);
		mainFrame.setVisible(true);
		
		dataType = new HashMap<String, String>();
		
	}
	 /**
     * Allows user to initialize a data set. 
     * First, the user is prompted for the number of features required.
     * Next, the user provides the name and class for each feature.
     *
     */
	public HashMap<JTextField, JTextField> createDataSet()
		{
			JFrame numDataFrame = new JFrame();
			// TO DO: error handling for non-int types OR restrict user from entering non-numerical data
			int numData = Integer.parseInt((String)JOptionPane.showInputDialog(
	                numDataFrame,
	                "Enter the number of data features required: ",
	                " ", JOptionPane.QUESTION_MESSAGE,
	                null,
	                null,
	                null));
			
			//TO DO: Show this in mainPanel rather than in individual alert boxes
			for(int i = 0; i < numData; i++)
			{
				JFrame featureFrame= new JFrame();
				String key = (String)JOptionPane.showInputDialog(
		                featureFrame,
		                "Enter your feature name: ",
		                " ", JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                null);
				//TO DO: Give user drop-down list of classes
				String value = (String)JOptionPane.showInputDialog(
		                featureFrame,
		                "Enter your feature class: ",
		                " ", JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                null);
				dataType.put(key, value);
		        
			};
			return null;
			
		}

}
