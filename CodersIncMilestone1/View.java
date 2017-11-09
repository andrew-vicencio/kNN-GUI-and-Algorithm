import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	private JMenuItem newDataSet, newTestCase, addValue, helpDoc;
	private JButton done;
	private LinkedHashMap<String, String> features;
	private ArrayList<String> featureTypes;
	private boolean fpexists = false;

	
	 /**
     * Initializes and displays the initial view
     *
     */
	public View()
	{
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
		done = new JButton("Done");
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
		edit.add(addValue);
		help.add(helpDoc);
		
		
		mainFrame.setSize(1000, 700);

		newTestCase.setEnabled(false);
		edit.setEnabled(false);
		display.setEnabled(false);
		mainFrame.setVisible(true);
		done.setVisible(false);
		
		done.addActionListener(new DoneButtonController(this));
		
		features = new LinkedHashMap<String, String>();
		featureTypes = new ArrayList<String>();
		featureTypes.add("Integer");
		featureTypes.add("Float");
		featureTypes.add("Coordinates");
		featureTypes.add("String");
		featureTypes.add("Add new feature type");
	}
	/**
	 * A FeaturePanel is added to the content panel, allowing the user to add a new feature
	 * @param i: integer denoting the level of subfeatures deep this feature is. Will start with 0.
	 */
	public void addFeaturePanel()
	{
		FeaturePanel fp = new FeaturePanel(this, featureTypes);
		contentPanel.add(fp);
		contentPanel.revalidate();
		contentPanel.repaint();
		footerPanel.add(done);
		done.setVisible(true);
	}
	/**
	 * The features from a featurePanel is added to the features list
	 * @param fp : the featurePanel
	 */
	public void addNewFeature(String key, String value)
	{
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
	/**
	 * Sets up the main panel with the keys user has provided as JLabels in the 
	 * headerPanel
	 */
	public void setUpFeatures()
	{
		contentPanel.removeAll();
		footerPanel.removeAll();
		headerPanel.setLayout(new GridLayout(1, features.size()));
	
		for (String key : features.keySet()){
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
	 * @param String: name of new feature type
	 */
	public void addFeatureType(String s)
	{
		featureTypes.add(featureTypes.size()-1, s);
	}
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
	public boolean fpExists() {

		return fpexists;
	}
	public void setfpExists(boolean b) {
		fpexists = true;
		
	}
	
}




