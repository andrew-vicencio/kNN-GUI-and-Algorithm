import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * View class for the GUI. This class is responsible for initiating the UI frame, and updating it as required
 *
 * @version Milestone 2
 * @author Gabrielle
 */


public class View {

	private JFrame mainFrame, errorFrame;
	private JPanel mainPanel, headerPanel, contentPanel, footerPanel;
	private JMenuBar menuBar;
	private JMenu create, edit, display, help;
	private JMenuItem newDataSet, newTestCase, simpleFeature, complexFeature, addValue, helpDoc;
	private JButton done;
	private LinkedHashMap<String, Object> features;
	private ArrayList<String> featureTypes;
	private MenuController menuController;


	
	 /**
     * Initializes and displays the initial view
     *
     */
	public View()
	{
		//Instantiation of View elements
		mainFrame = new JFrame("CODERS INC");
		errorFrame = new JFrame("Error");
		mainPanel = new JPanel();
		headerPanel = new JPanel();
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
		simpleFeature = new JMenuItem("Add a Simple Feature");
		complexFeature = new JMenuItem("Add a Complex Feature");
		done = new JButton("Done");
		menuController = new MenuController(this);
		
		//Placement and sizing of View elements
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(footerPanel, BorderLayout.SOUTH);

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		menuBar.add(create);
		menuBar.add(edit);
		menuBar.add(display);
		menuBar.add(help);
		create.add(newDataSet);
		create.add(newTestCase);
		edit.add(complexFeature);
		edit.add(simpleFeature);
		edit.add(addValue);
		help.add(helpDoc);
		mainFrame.setSize(1000, 700);

		//Initial disabling of menu items
		newTestCase.setEnabled(false);
		edit.setEnabled(false);
		display.setEnabled(false);
		mainFrame.setVisible(true);
		done.setVisible(false);
		complexFeature.setEnabled(false);
		simpleFeature.setEnabled(false);
		
		//Action Listeners
		createListeners();
		done.addActionListener(new DoneButtonController(this));
		create.addActionListener(menuController);
		simpleFeature.addActionListener(menuController);
		complexFeature.addActionListener(menuController);
		newDataSet.addActionListener(menuController);
		newTestCase.addActionListener(menuController);
		addValue.addActionListener(menuController);
		helpDoc.addActionListener(menuController);
		
		//Set up list of primitive types the user can choose from
		features = new LinkedHashMap<String, Object>();
		featureTypes = new ArrayList<String>();
		featureTypes.add("Integer");
		featureTypes.add("Float");
		featureTypes.add("String");
		
		//Added close application operation when window closes
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initialize main panel
		displayInfo();
		
	}
	/**
	 * Creates a panel for the user to add a Simple Feature, then adds it to the contentPanel
	 * @param superFeatureName: String. If this is a subFeature, its parent's feature will be included as a label. If not, leave an empty string
	 * @param tab: If this is a subFeature, the panel will be tabbed over for readability, so increment from parent's tab value. Otherwise, leave 0
	 */
	public void addFeaturePanelSimple(String superFeatureName, int tab)
	{
		FeaturePanelSimple fp = new FeaturePanelSimple(this, featureTypes, superFeatureName, tab);
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
	}
	
	/**
	 * Creates a panel for the user to add a Complex Feature, then adds it to the contentPanel
	 * @param superFeatureName: String. If this is a subFeature, its parent's feature will be included as a label. If not, leave an empty string
	 * @param tab: If this is a subFeature, the panel will be tabbed over for readability, so increment from parent's tab value. Otherwise, leave 0
	 */
	public void addFeaturePanelComplex(String superFeatureName, int tab) 
	{
		FeaturePanelComplex fp = new FeaturePanelComplex(this, featureTypes, superFeatureName, tab);
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
		
	}
	
	/**
	 * Adds an already created panel for a simple feature to the contentPanel
	 * @param fp: the FeaturePanelSimple object
	 */
	public void addFeaturePanelSimple(FeaturePanelSimple fp)
	{
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
	}
	
	/**
	 * Adds an already created panel for a complex feature to the contentPanel
	 * @param fp: the FeaturePanelSimple object
	 */
	public void addFeaturePanelComplex(FeaturePanelComplex fp)
	{
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
	}
	/**
	 * The features from a featurePanel is added to the features list
	 */
	public void addNewFeature(String key, Object value)
	{
		features.put(key, value);
	}


	/**
	 * Create actionListeners for elements that do not require a full controller class
	 */
	public void createListeners(){
	    //newDataSet, newTestCase, addValue, helpDoc;


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
	
	/**
	 * Sets up the main panel with the keys user has provided as JLabels in the 
	 * headerPanel
	 */
	public void setUpFeatures()
	{
		//contentPanel.removeAll();
		//footerPanel.removeAll();
		//headerPanel.setLayout(new GridLayout(1, features.size()));
	
		//for (String key : keys){
		//	JLabel jl = new JLabel("    " + key);
		//	jl.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
		//	headerPanel.add(jl);
	    //}
		//headerPanel.revalidate();
		//headerPanel.repaint();

		
	}

	/**
	 * Get the Done JButton in the footer panel
	 * @return
	 */
	public JButton getDoneButton()
	{
		return done;
	}
	
	/**
	 * Get the list of feature names that have been added. The key will refer to the name of the feature, and the value will be the type
	 * In the case of a simple feature, the type will simply be a string
	 * In the case of a complex feature, the type will be another HashMap of features
	 * @return
	 */
	public HashMap<String, Object> getList()
	{
		return features;
	}
	
	/**
	 * Enables the newDataSet menu item
	 * @param b : boolean
	 */
	public void enableNewDataSet(boolean b) {
		newDataSet.setEnabled(b);
		
	}
	
	/**
	 * Enables the menu items allowing users to add features
	 * @param b : boolean
	 */
	public void enableFeatureCreation(boolean b) {
		edit.setEnabled(b);
		simpleFeature.setEnabled(b);
		complexFeature.setEnabled(b);
		addValue.setEnabled(!b);
		
	}
	
	/**
	 * Displays program information to the contentPanel
	 * 
	 */
	public void displayInfo()
	{
		JLabel info = new JLabel("CODERS INC by Benjamin Bichel, Darren Holden, Gabrielle Hubert, Andrew Vicencio");
		contentPanel.add(info);
		info.setVisible(true);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	public void clearContentPanel() {
		contentPanel.removeAll();

		
	}
	public void showNewDataInfo() {
		JLabel info = new JLabel("To add a simple feature, click Edit -> Add Simple Feature. To add a "
				+ "composite/complex feature, click Edit -> Add Complex Feature."
				+ " Always click 'ADD' when you are finished inputting a feature ");
		contentPanel.add(info);
		info.setVisible(true);
		contentPanel.revalidate();
		contentPanel.repaint();
		
	}
	


}




