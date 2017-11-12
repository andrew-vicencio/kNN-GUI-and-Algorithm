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
	private LinkedHashMap<String, String> features;
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
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
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
		
		features = new LinkedHashMap<String, String>();
		featureTypes = new ArrayList<String>();
		featureTypes.add("Integer");
		featureTypes.add("Float");
		featureTypes.add("String");
		
		newDataSet.addActionListener(new MenuController(this));
		newTestCase.addActionListener(new MenuController(this));
		addValue.addActionListener(new MenuController(this));
		helpDoc.addActionListener(new MenuController(this));
		
		

		//Added close application operation when window closes
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * A FeaturePanel is added to the content panel, allowing the user to add a new feature
	 */
	public void addFeaturePanelSimple()
	{
		FeaturePanelSimple fp = new FeaturePanelSimple(this, featureTypes);
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
	}
	
	public void addFeaturePanelComplex() {
		FeaturePanelSimple fp = new FeaturePanelSimple(this, featureTypes);
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
		
	}
	/**
	 * The features from a featurePanel is added to the features list
	 */
	public void addNewFeature(String key, String value)
	{
		features.put(key, value);
	}



	public void createListeners(){
	    //newDataSet, newTestCase, addValue, helpDoc;

        newDataSet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
             //TO DO: JOption pane that alerts user data set is created, and how to add new features
            }
        });
        
        
        newTestCase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


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
	/**
	 * Sets up the main panel with the keys user has provided as JLabels in the 
	 * headerPanel
	 */
	public void setUpFeatures(Set<String> keys)
	{
		contentPanel.removeAll();
		footerPanel.removeAll();
		headerPanel.setLayout(new GridLayout(1, features.size()));
	
		for (String key : keys){
			JLabel jl = new JLabel("    " + key);
			jl.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
			headerPanel.add(jl);
	    }
		headerPanel.revalidate();
		headerPanel.repaint();

		
	}

    /**
	 * Method to add a new feature type when the "Add a new Feature Type" is chosen in the combo box
	 * The JTextfield value becomes the name of the feature type
	 */

	/**
	 * Get the Done JButton in the footer panel
	 * @return
	 */
	public JButton getDoneButton()
	{
		return done;
	}
	
	public HashMap<String, String> getList()
	{
		return features;
	}
	
	public void enableNewDataSet(boolean b) {
		newDataSet.setEnabled(b);
		
	}
	public void enableFeatureCreation(boolean b) {
		edit.setEnabled(b);
		simpleFeature.setEnabled(b);
		complexFeature.setEnabled(b);
		addValue.setEnabled(!b);
		
	}
	



}




