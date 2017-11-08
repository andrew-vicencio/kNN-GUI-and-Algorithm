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
	private JFrame mainFrame, errorFrame;
	private JPanel mainPanel, contentPanel, footerPanel;
	private JMenuBar menuBar;
	private JMenu create, edit, display, help;
	private JMenuItem newDataSet, newTestCase, addValue, helpDoc;
	private JButton done;
	private HashMap<String, String> features;
	//private FeaturePanel test;
	
	 /**
     * Initializes and displays the initial view
     *
     */
	public View()
	{
		mainFrame = new JFrame("CODERS INC");
		errorFrame = new JFrame("Error");
		mainPanel = new JPanel();
		contentPanel = new JPanel();
		footerPanel = new  JPanel();
		menuBar = new JMenuBar();
		create = new JMenu("Create");
		edit = new JMenu("Edit");
		display = new JMenu("Display");
		help = new JMenu("Help");
		newDataSet = new JMenuItem("New Data Set");
		newTestCase = new JMenuItem("New Test Case");
		addValue = new JMenuItem("Add Value");
		helpDoc = new JMenuItem("View Help Documents");
		done = new JButton("Done");
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);
		footerPanel.add(done);
		menuBar.add(create);
		menuBar.add(edit);
		menuBar.add(display);
		menuBar.add(help);
		create.add(newDataSet);
		create.add(newTestCase);
		edit.add(addValue);
		help.add(helpDoc);
		
		
		mainFrame.setSize(700, 500);

		newTestCase.setEnabled(false);
		edit.setEnabled(false);
		display.setEnabled(false);
		mainFrame.setVisible(true);
		
		features = new HashMap<String, String>();
	}
	/**
	 * A FeaturePanel is added to the content panel, allowing the user to add a new feature
	 */
	public void addFeaturePanel()
	{
		FeaturePanel fp = new FeaturePanel(this);
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	/**
	 * The features from a featurePanel is added to the features list
	 * @param fp : the featurePanel
	 */
	public void addNewFeature(FeaturePanel fp)
	{
		String key = fp.getKey();
		String value = fp.getValue();
		features.put(key, value);
	}
	
	/**
	 * To get the hashmap containing all features added thus far.
	 * @return  HashMap of String key/value pairs
	 */
	public HashMap<String, String> getFeatureMap()
	{
		return features;
	}
	
	/**
	 * Sends an error message to the user
	 * @param message to be displayed in the error frame
	 */
	public void sendErrorFrame(String message)
	{
		errorFrame = new JFrame();
		JOptionPane.showMessageDialog(errorFrame,
			    message,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
}


