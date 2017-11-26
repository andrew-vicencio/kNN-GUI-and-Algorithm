package View;

import View.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
	private JLabel  featureTypeLabel;
	private JComboBox<String> featureType;

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
		String[] metricsArray = {"Example1", "Example2", "Example3"};
		featureType = new JComboBox<String>(typesArray);
		featureTypeLabel = new JLabel("Feature type: ");

		innerPanel.add(featureTypeLabel);
		innerPanel.add(featureType);
        innerPanel.add(add);
		controller = new FeaturePanelSimpleController(this);
		add.addActionListener(controller);
		
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
	public String getValue()
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
	 * Disables the JTextField and JComBobox
	 */
	public void disable()
    {
		featureName.setEnabled(false);
		featureType.setEnabled(false);
		add.setEnabled(false);
	}
}


