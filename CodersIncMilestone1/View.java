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
	private JFrame errorFrame;
	private JMenuBar menuBar;
	private JMenu create, edit, display, help;
	private JMenuItem newDataSet, newTestCase, addValue, helpDoc;
	private HashMap<String, String> dataType;
	
	 /**
     * Initializes and displays the initial view
     *
     */
	public View()
	{
		mainFrame = new JFrame("CODERS INC");
		errorFrame = new JFrame("Error");
		menuBar = new JMenuBar();
		create = new JMenu("Create");
		edit = new JMenu("Edit");
		display = new JMenu("Display");
		help = new JMenu("Help");
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
		edit.add(addValue);
		help.add(helpDoc);
		
		mainFrame.setSize(500, 500);
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
     * A hashmap containing the name of the feature (String) as keys, and the class of the feature (String) as value
     *
     */
	public HashMap<String, String> createDataSet()
		{
			int numData = 0;
			try{
				JFrame numDataFrame = new JFrame("Enter a number of attributes");
				SpinnerNumberModel snModel = new SpinnerNumberModel(0, 0, 30, 1);
				JSpinner spinner = new JSpinner(snModel);
				JOptionPane.showOptionDialog(null, spinner, 
						"Enter valid number", JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				 numData = (int) snModel.getNumber();
			}
			catch(Exception e)
			{
				JFrame errorFrame = new JFrame();
				JOptionPane.showMessageDialog(errorFrame,
					    "Please input a valid number.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			if (numData == 0)
			{
				
				JOptionPane.showMessageDialog(errorFrame,
					    "Please input a valid number.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			//TO DO: Show this in mainPanel rather than in individual alert boxes
			for(int i = 0; i < numData; i++)
			{
				JFrame featureFrame= new JFrame();
				
				String key = (String)JOptionPane.showInputDialog(
		                featureFrame,
		                "#" + (i+1) + " Enter your feature name: " ,
		                " ", JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                null);
				//TO DO: Give user drop-down list of classes
				String value = (String)JOptionPane.showInputDialog(
		                featureFrame,
		                "#" + (i+1) + " Enter your feature class: ",
		                " ", JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                null);
				dataType.put(key, value);    
			};
			return dataType;
			
		}

}
