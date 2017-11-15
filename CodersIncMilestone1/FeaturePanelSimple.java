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


/**
 * A JPanel allowing a user to create a simple feature
 * @author Gabrielle
 *
 */
public class FeaturePanelSimple extends JPanel{
	private JPanel innerPanel;
	private View view;
	private JLabel key, value, superName;
	private JComboBox<String> featureClass;
	private JTextField  featureName;
	private ArrayList<String> types;
	private JButton addFeature;
	private FeaturePanelSimpleController fpController;
	private FeaturePanelComplex parent;
	int tab;
    private String keyParentName;

	/**
	 * Default constructor for a FeaturePanelSimple with no parent
	 * @param view - the View which the panel will appear in
	 * @param types - the primitive types the user will choose from when creating a feature
	 * @param superFeatureName - the name of the parent panel. Empty if does not exist
	 * @param tab - the placement of the panel, linked to whether or not it is a subfeature panel
	 */
	public FeaturePanelSimple(View view, ArrayList<String> types, String superFeatureName, int tab)
	{
		super();
		setLayout(new BorderLayout());
		this.setTypes(types);
		String[] typesArray = types.toArray(new String[0]);
		featureClass = new JComboBox<String>(typesArray);
		value = new JLabel("Feature type: ");
		key = new JLabel("Feature name: ");
		superName = new JLabel(superFeatureName);
		featureName = new JTextField(15);
		addFeature = new JButton("Add");
		innerPanel = new JPanel();
		this.view = view;
		
		add(innerPanel, BorderLayout.CENTER);
		add(superName, BorderLayout.NORTH);
		innerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		innerPanel.add(key);
		innerPanel.add(featureName);
		innerPanel.add(value);
		innerPanel.add(featureClass);
		innerPanel.add(addFeature);
		
		fpController = new FeaturePanelSimpleController(this);
		addFeature.addActionListener(fpController);
		setMaximumSize(new Dimension(800, 80));
		setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
		
		this.tab = tab;
		
		setAlignmentX((float) (1 - (0.1 * tab)));	
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
	 * Returns the list of primitive types the user can choose from 
	 * @return ArrayList <String>
	 */
	public ArrayList<String> getTypes() {
		return types;
	}

	/**
	 * Sets the primitive types the user can choose from
	 * @param types ArrayList <String>
	 */
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	
	/**
	 * Get the FeaturePanelComplex object which is the parent of this panel
	 * @return FeaturePanelComplex
	 */
	public FeaturePanelComplex getParentComplex() {
		return parent;
	}

	/**
	 * Set the FeaturePanelComplex object which is the parent of this panel
	 * @param FeaturePanelComplex
	 */
	public void setParentComplex(FeaturePanelComplex parentComplex) {
		this.parent = parentComplex;
	}


	
    public void setParentComplexKey(String key){
        keyParentName = key;
    }
	
public String getKeyParentName(){
        return keyParentName;
}
	


}
