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
/**
 * A JPanel allowing a user to create a complex feature
 * @author Gabrielle
 *
 */
public class FeaturePanelComplex extends JPanel
{
	private JPanel innerPanel;
	private View view;
	private JLabel name, superName;
	private JTextField  featureName;
	private JButton addSimple, add;
	private FeaturePanelComplexController fpController;
	private LinkedHashMap<String, Object> subFeaturePanels;
	private ArrayList<String> types;
	private int tab;
	private FeaturePanelComplex parent;
	private String keyParentName;
	

	/**
	 * Default constructor for a FeaturePanelComplex with no parent
	 * @param view - the View which the panel will appear in
	 * @param types - the primitive types the user will choose from when creating a feature
	 * @param superFeatureName - the name of the parent panel. Empty if does not exist
	 * @param tab - the placement of the panel, linked to whether or not it is a subfeature panel
	 */
	public FeaturePanelComplex(View view, ArrayList<String> types, String superFeatureName, int tab) {
		super();
		setLayout(new BorderLayout());
		name = new JLabel("Feature name: ");
		superName = new JLabel(superFeatureName);
		featureName = new JTextField(15);
		addSimple = new JButton("Add a simple subfeature");
		add = new JButton("Add this feature");
		innerPanel = new JPanel();
		subFeaturePanels = new LinkedHashMap<String, Object>();
		this.view = view;
		this.tab = tab;
		this.types = types;
		
		add(innerPanel, BorderLayout.CENTER);
		add(superName, BorderLayout.NORTH);
		innerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		innerPanel.add(name);
		innerPanel.add(featureName);
		innerPanel.add(addSimple);
		innerPanel.add(add);
		
		fpController = new FeaturePanelComplexController(this);
		addSimple.addActionListener(fpController);
		add.addActionListener(fpController);
		setMaximumSize(new Dimension(800, 80));
		setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(6, 6, 6, 6)));
		
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
	public FeaturePanelComplex(View view, ArrayList<String> types, String superFeatureName, int tab, FeaturePanelComplex fp)
	{
		this(view, types, superFeatureName, tab);
		if(fp != null)
		{
			parent = fp;
		}
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
	 * Returns the key/feature name inputed by the user in the JTextField featureName
	 * @return String
	 */
	public String getName()
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
	 * Disables the panel from being modified
	 */
	public void disable()
	{
        add.setEnabled(false);
	}

	public void disableFeatureName(){
	    featureName.setEnabled(false);
    }

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
	
	/**
	 * Return the  list of primitive types the user may choose from
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getTypes() {
		return types;
	}

	/**
	 * Set the list of primitive types the user may choose from
	 * @param types ArrayList<String>
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

    public String getParentComplexKey(){
        return keyParentName;
    }
}
