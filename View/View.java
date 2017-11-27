package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import Controllers.*;
import DataModel.DimensionalSpace;
import DataModel.Point;

/**
 * View.View class for the GUI. This class is responsible for initiating the UI frame, and updating it as required
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
	private ButtonMenuController menuController;
    private DimensionalSpace dataModel;
    private JScrollPane scrollPane;
    private int pointCount;

	
	 /**
     * Initializes and displays the initial view
     *
     */
	public View()
	{
		//Instantiation of View.View elements / Data Model
		mainFrame = new JFrame("CODERS INC");
		errorFrame = new JFrame("Error");
		mainPanel = new JPanel();
		headerPanel = new JPanel();
		contentPanel = new JPanel();
		footerPanel = new  JPanel();
		scrollPane = new JScrollPane(contentPanel);
		menuBar = new JMenuBar();
		create = new JMenu("Create");
		edit = new JMenu("Edit");
		display = new JMenu("Display");
		help = new JMenu("Help");
		newDataSet = new JMenuItem("New Data Set");
		newTestCase = new JMenuItem("New Test Case");
		addValue = new JMenuItem("Add Value");
		helpDoc = new JMenuItem("View.View Help Documents");
		simpleFeature = new JMenuItem("Add a Simple Feature");
		complexFeature = new JMenuItem("Add a Complex Feature");
		done = new JButton("Done");
		menuController = new ButtonMenuController(this);
        dataModel = menuController.getDataModel();
        pointCount = 0;
        dataModel.setView(this);

        //Placement and sizing of View.View elements
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
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
		done.addActionListener(new ButtonAddFeaturesController(this));
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
		featureTypes.add("int");
		featureTypes.add("float");
		featureTypes.add("String");
		
		//Added close application operation when window closes
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set keyboard shortcuts
		simpleFeature.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		complexFeature.setAccelerator(KeyStroke.getKeyStroke('D', CTRL_DOWN_MASK));
		addValue.setAccelerator(KeyStroke.getKeyStroke('A', CTRL_DOWN_MASK));
		newTestCase.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
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
	 * @param fp: the View.FeaturePanelSimple object
	 */
	public void addFeaturePanelSimple(FeaturePanelSimple fp)
	{
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();

	}
	
	/**
	 * Adds an already created panel for a complex feature to the contentPanel
	 * @param fp: the View.FeaturePanelSimple object
	 */
	public void addFeaturePanelComplex(FeaturePanelComplex fp)
	{
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();

	}
	
	/**
	 * The features from a featurePanel is added to the features list
	 */
	public void addNewFeature(String key, Object value)
	{
		features.put(key, value);
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
		contentPanel.removeAll();
		footerPanel.removeAll();
		headerPanel.setLayout(new GridLayout(1, features.size() + 1));
		headerPanel.setPreferredSize(new Dimension(1000, 40));
		JLabel countLabel = new JLabel("Count");
		countLabel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
		headerPanel.add(countLabel);
		for(String str: dataModel.getCellTypes().keySet())
		{
		    if(!dataModel.getCellTypes().get(str).equals("comp"))
			{
				JLabel jl = new JLabel("    " + str);
				jl.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
				headerPanel.add(jl);
			}	
	    }
		headerPanel.revalidate();
		headerPanel.repaint();
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
	 * Returns the data model (DataModel.DimensionalSpace) the view is using
	 * @return DataModel.DimensionalSpace
	 */
	public DimensionalSpace getDataModel()
	{
		return dataModel;
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
		footerPanel.add(done);
		done.setVisible(true);
	}
	/**
	 * Enables the ability to input data with the addValue menu item
	 * @param b : boolean
	 */
	public void enableDataInput(boolean b) {
		edit.setEnabled(b);
		addValue.setEnabled(b);
		simpleFeature.setEnabled(!b);
		complexFeature.setEnabled(!b);		
	}
	
	/**
	 * Enables the user to choose to input a test case
	 * @param b boolean
	 */
	public void enableTesting(boolean b) {
		newTestCase.setEnabled(b);

	}
	
	/**
	 * Displays program information to the contentPanel
	 */
	public void displayInfo()
	{
		JLabel info = new JLabel("CODERS INC by Benjamin Bichel, Darren Holden, Gabrielle Hubert, Andrew Vicencio");
		contentPanel.add(info);
		info.setVisible(true);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	
	/**
	 * Clears all contents of the contentPanel
	 */
	public void clearContentPanel() {
		contentPanel.removeAll();
	}
	
	
	/**
	 * Creates a new View.PromptValueFrame to gather data from user
	 */
	public void promptValue() 
	{
		new PromptValueFrame(this);
	}
	
	/**
	 * Prompts the user for a test case, by asking for which value they want to test for, and 
	 * then providing the rest of the values
	 */
	public void promptTestCase()
	{
		 String[] metricsArray = new String[dataModel.getDistanceMetrics().size()];
		 dataModel.getDistanceMetrics().toArray(metricsArray);
		 Set<String> optionsSet = dataModel.getCellTypes().keySet();
		 ArrayList<String> optionsArrayList = new ArrayList<String>();
		 for(String s: optionsSet)
		 {
			 if(!s.contains("."))
			 {
				 optionsArrayList.add(s);
			 }
		 }
		 String[] optionsArray = new String[optionsArrayList.size()];
		 optionsArrayList.toArray(optionsArray);
		 JFrame chooseValueFrame = new JFrame("New Test Case");
		 String testValue = (String) JOptionPane.showInputDialog(chooseValueFrame, "Choose a value to test",
		 "Feature", JOptionPane.QUESTION_MESSAGE, null, optionsArray, optionsArray[0]);
		 String distanceMetric = (String) JOptionPane.showInputDialog(chooseValueFrame, "Choose a distance metric",
				 "Feature", JOptionPane.QUESTION_MESSAGE, null, metricsArray, metricsArray[0]);
		 
		 int minkInt = 0;
		 
		 if(distanceMetric.equals("Minkowski"))
		 {
			 String minkArray[] = {"3", "4", "5", "6"};
			 String minkString = (String) JOptionPane.showInputDialog(chooseValueFrame, "Choose a polynomial value",
					 "Feature", JOptionPane.QUESTION_MESSAGE, null, minkArray, minkArray[0]);
			 minkInt = Integer.parseInt(minkString);
		 }
		 
		 TestCaseFrame testFrame = new TestCaseFrame(this, testValue, distanceMetric, minkInt);
	}
	
	/**
	 * Adds the Point x to the contentPanel
	 * @param x
	 */
	public void updateDisplay(Point x) {
		
		JPanel point = new JPanel();
		String values[] = x.toString().split(",");
		point.setLayout(new GridLayout(1, values.length + 1));
		pointCount++;
		JLabel countLabel = new JLabel(Integer.toString(pointCount));
		countLabel.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
		point.add(countLabel);
		for(String value: values)
		{
			String[] substrings = value.split(":");
			JLabel jl = new JLabel(substrings[1]);
			jl.setHorizontalTextPosition(SwingConstants.LEFT);
			jl.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
			point.add(jl);
		}
		point.setAlignmentY(JPanel.TOP_ALIGNMENT);
		point.setAlignmentX(0);
		point.setMaximumSize(new Dimension(1000, 25));
		point.setBackground(new Color(255, 255, 255));
		
		contentPanel.add(point);
		contentPanel.revalidate();
		contentPanel.repaint();
		
	}
	
	/**
	 * Adds the result of a test case, in String s, to the footerPanel
	 * @param s
	 */
	public void addTestCaseResult(String s)
	{
		JLabel label = new JLabel(s);
		footerPanel.add(label);
		footerPanel.revalidate();
		footerPanel.repaint();
	}
	
}





