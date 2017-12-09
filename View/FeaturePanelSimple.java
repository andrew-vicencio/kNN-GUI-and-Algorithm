package View;

import View.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controllers.*;

/**
 * A JPanel allowing a user to create a simple feature
 * @author Gabrielle
 *
 */
public class FeaturePanelSimple extends FeaturePanel{

	//Things specialized for this particular input
	private JLabel  featureTypeLabel, distanceMetricLabel;
	private JComboBox<String> featureType;
	private JComboBox<String> numberMetrics, stringMetrics;

	/**
	 * Default constructor for a View.FeaturePanelSimple with no parent
	 * @param view - the View.View which the panel will appear in
	 * @param types - the primitive types the user will choose from when creating a feature
	 * @param superFeatureName - the name of the parent panel. Empty if does not exist
	 * @param tab - the placement of the panel, linked to whether or not it is a subfeature panel
	 */
	public FeaturePanelSimple(View view, ArrayList<String> types, String superFeatureName, int tab)
	{
		super(view, types, superFeatureName, tab);
		String[] typesArray = types.toArray(new String[0]);
		featureType = new JComboBox<String>(typesArray);
		numberMetrics = new JComboBox<String>(view.getDataModel().getNumberMetrics());  //Two metrics arrays are created, one is 
		stringMetrics = new JComboBox<String>(view.getDataModel().getStringMetrics());  //set visible, the other not, depending on the 
		featureTypeLabel = new JLabel("Feature type: ");								//value of featureType
		distanceMetricLabel = new JLabel("Distance metric: ");
		innerPanel.add(featureTypeLabel);
		innerPanel.add(featureType);
		innerPanel.add(distanceMetricLabel);
		innerPanel.add(numberMetrics);
		innerPanel.add(stringMetrics);
		innerPanel.add(add);
		setMetricsArray();
		controller = new FeaturePanelSimpleController(this);
		add.addActionListener(controller);
		featureType.addActionListener(new FeatureTypeController(this));
		
	}
	

	/**
	 * Calls the default constructor, and initialized the parentComplex variable
	 * 
	 * @param view
	 * @param types
	 * @param superFeatureName
	 * @param tab
	 * @param fp
	 */
	public FeaturePanelSimple(View view, ArrayList<String> types, String superFeatureName, int tab, FeaturePanelComplex fp)
	{
		this(view, types, superFeatureName, tab);
		if(fp != null)
		{
			parent = fp;
		}
	}
	
	
	/**
	 * Returns the value/feature class denoted by the user's choice the JComboBox featureClass
	 * @return String
	 */
	public String getFeatureType()
	{
		String s="";
		try
		{
			s = (String) featureType.getSelectedItem();
		}
		catch(Exception e)
		{
			view.sendErrorFrame("Not a valid feature type");
		}
		return s;
	}
	
	/**
	 * Returns the distance metric denoted by the user's choice the JComboBox distanceMetric
	 * @return String
	 */
	public String getDistanceMetric()
	{
		String s="";
		try
		{
			if(featureType.getSelectedItem().equals("String"))
			{
				s = (String) stringMetrics.getSelectedItem();
			}
			else
			{
				s = (String) numberMetrics.getSelectedItem();
			}
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
		featureType.setEnabled(false);
		numberMetrics.setEnabled(false);
		stringMetrics.setEnabled(false);
		add.setEnabled(false);
	}
	
	/**
	 * Set the appropriate metric array visible/non-visible, depending on the selected
	 * value of the featureType JCombobox
	 */
	public void setMetricsArray() 
	{
		if(getFeatureType().equals("int") || getFeatureType().equals("float"))
		{
			numberMetrics.setVisible(true);
			stringMetrics.setVisible(false);
		}
		else
		{
			numberMetrics.setVisible(false);
			stringMetrics.setVisible(true);
		}
	}
}


