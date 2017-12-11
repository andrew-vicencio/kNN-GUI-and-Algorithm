package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Controllers.FeaturePanelComplexController;
import Controllers.FeaturePanelController;
/**
 * Abstract class for FeaturePanelSimple and FeaturePanelComplex
 * @author Gabrielle and Ben
 * @version Milestone 4
 */
public abstract class FeaturePanel extends JPanel {
	
	protected JPanel innerPanel;
	protected View view;
	protected JLabel name, superName;
	protected JTextField  featureName;
	protected JButton add;
	protected FeaturePanelController controller;
	protected int tab;
	protected FeaturePanelComplex parent;
	protected String keyParentName;
	private ArrayList<String> types;
	
	/**
	 * Sets up the basic view of the panel
	 * @param view
	 * @param types
	 * @param superFeatureName
	 * @param tab
	 */
	public FeaturePanel(View view, ArrayList<String> types, String superFeatureName, int tab)
	{
		super();
        innerPanel = new JPanel();
		setLayout(new BorderLayout());

		name = new JLabel("Feature name: ");
		superName = new JLabel(superFeatureName);
		featureName = new JTextField(15);
		add = new JButton("Add this feature");


        innerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        innerPanel.add(name);
        innerPanel.add(featureName);

		this.view = view;
		this.tab = tab;
		this.setTypes(types);
		
		add(innerPanel, BorderLayout.CENTER);
		add(superName, BorderLayout.NORTH);
		
		setAlignmentX((float) (1 - (0.1 * tab)));
		setMaximumSize(new Dimension(800, 80));
		setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));

	}
	
	
	/**
	 * Returns the View.View object containing the FeaturePanel
	 * @return View.View
	 */
	public View getView()
	{
		return view;
	}
	
	/**
	 * Returns the tabbing of the panel, ie. how many subfeatures deep we are
	 * @return int
	 */
	public int getTab() {
		return tab;
	}
	
	/**
	 * Returns the text the user has inputed
	 * @return String
	 */
	public String getKey() {
        System.out.println("test");
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
	 * Get the View.FeaturePanelComplex object which is the parent of this panel
	 * @return View.FeaturePanelComplex
	 */
	public FeaturePanelComplex getParentComplex() {
		return parent;
	}

	/**
	 * Set the View.FeaturePanelComplex object which is the parent of this panel
	 * @param parentComplex
	 */
	public void setParentComplex(FeaturePanelComplex parentComplex) {
		this.parent = parentComplex;
	}
	
	/**
	 * Sets the name of the parent complex feature
	 * @param key
	 */
    public void setParentComplexKey(String key){
        keyParentName = key;
    }
    
    /**
     * Returns the name of the parent complex feature
     * @return
     */
    public String getParentComplexKey(){
        return keyParentName;
    }
	/**
	 * Sets the primitive types the user can choose from
	 * @param types ArrayList
	 */
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}


	/**
	 * Returns the list of primitive types the user can choose from 
	 * @return ArrayList
	 */
	public ArrayList<String> getTypes() {
		return types;
	}

    public abstract void disable();

}
