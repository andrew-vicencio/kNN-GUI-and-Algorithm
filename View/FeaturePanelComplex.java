package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controllers.*;
/**
 * A JPanel allowing a user to create a complex feature
 * @author Gabrielle
 *
 */
public class FeaturePanelComplex extends FeaturePanel
{

	private LinkedHashMap<String, Object> subFeaturePanels;
	private JButton addSimple;


	/**
	 * Default constructor for a View.FeaturePanelComplex with no parent
	 * @param view - the View.View which the panel will appear in
	 * @param types - the primitive types the user will choose from when creating a feature
	 * @param superFeatureName - the name of the parent panel. Empty if does not exist
	 * @param tab - the placement of the panel, linked to whether or not it is a subfeature panel
	 */
	public FeaturePanelComplex(View view, ArrayList<String> types, String superFeatureName, int tab) {
		
		super(view, types, superFeatureName, tab);
		
		addSimple = new JButton("Add a simple subfeature");
		subFeaturePanels = new LinkedHashMap<String, Object>();

		innerPanel.add(addSimple);
		innerPanel.add(add);
		
		controller = new FeaturePanelComplexController(this);
		addSimple.addActionListener(controller);
		add.addActionListener(controller);
	
	}		

	/**
	 * Disables the add button, preventing the panel from being modified
	 */
	public void disable()
	{
        add.setEnabled(false);
	}

	/**
	 * Disables the FeaturenName from being modified
	 */
	public void disableFeatureName(){
	    featureName.setEnabled(false);
    }

	/**
	 * Disables the ability of adding new simpel subfeatures
	 */
    public void disableAddSimpleButton(){
	    addSimple.setEnabled(false);
    }
    
	/**
	 * Returns the list of panels which the panel is the parent of
	 * @return LinkedHashMap<String, Object>
	 */
	public LinkedHashMap<String, Object> getSubFeaturePanels()
	{
		return subFeaturePanels;
	}
	
	/**
	 * Adds a subfeature to the current feature
	 * @param name: String, name of the feature
	 * @param fp: Object, what the feature is. If simple, a String. If complex, another LinkedHashMap of panels
	 */
	public void addSubFeaturePanel(String name, Object fp)
	{
		subFeaturePanels.put(name, fp);
	}	
}
