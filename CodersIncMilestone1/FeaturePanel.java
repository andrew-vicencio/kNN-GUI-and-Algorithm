
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


public class FeaturePanel extends JPanel {
	
	JPanel innerPanel;
	View view;
	JLabel key;
	JLabel value;
	JComboBox<String> featureClass;
	JTextField  featureName;
	ArrayList<String> types;
	JButton addFeature;
	FeaturePanelController fpController;

	
	public FeaturePanel(View view, ArrayList<String> types)
	{
		super();
		setLayout(new GridLayout(1, 0));
		this.types = types;
		String[] typesArray = types.toArray(new String[0]);
		featureClass = new JComboBox<String>(typesArray);
		value = new JLabel("Feature type: ");
		key = new JLabel("Feature name: ");
		featureName = new JTextField(15);
		addFeature = new JButton("Add");
		innerPanel = new JPanel();
		this.view = view;
		
		add(innerPanel);
		innerPanel.setLayout(new FlowLayout());
		innerPanel.add(key);
		innerPanel.add(featureName);
		innerPanel.add(value);
		innerPanel.add(featureClass);
		innerPanel.add(addFeature);
		
		fpController = new FeaturePanelController(this);
		addFeature.addActionListener(fpController);
		
		setMaximumSize(new Dimension(1000, 70));
		setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
			
	}
	/**
	 * Adds a feature panel within a feature panel.
	 * @param key
	 */
	
	public void addFeaturePanel(String key)
	{
		
		setMaximumSize(new Dimension(getWidth() - 100, getHeight() + 70));
		FeaturePanel newFP = new FeaturePanel(view, types);
		add(newFP);
		revalidate();
		repaint();
	}
	/**
	 * Returns the key/feature name inputed by the user in the JTextField featureName
	 * @return String
	 */
	
	public String getKey()
	{
		String s="";
		try
		{
			s = featureName.getText();
		}
		catch(Exception e)
		{
			view.sendErrorFrame("Not a valid feature name");
		}
		return s;
	}
	/**
	 * Returns the value/feature class denoted by the user's choice the JComboBox featureClass
	 * @return String
	 */
	public String getValue()
	{
		String s="";
		try
		{
			s = (String) featureClass.getSelectedItem();
		}
		catch(Exception e)
		{
			view.sendErrorFrame("Not a valid feature type");
		}
		return s;
	}
	/**
	 * Disables the JTextField and JComBobox
	 */
	public void disable()
	{
		featureName.setEnabled(false);
		featureClass.setEnabled(false);
		addFeature.setEnabled(false);
	}
	
	/**
	 * Returns the View object containing the FeaturePanel
	 * @return View
	 */
	public View getView()
	{
		return view;
	}
	
	/**
	 * Refreshed the JCombobox to add the newest key addition, and sets the selected item to that key
	 * @param key: String
	 */
	public void refreshComboBox(String key) {
		featureClass.addItem(key);
		featureClass.setSelectedItem(key);	
	}
	
	

}
