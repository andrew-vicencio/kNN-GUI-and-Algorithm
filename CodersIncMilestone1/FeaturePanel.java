
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FeaturePanel extends JPanel {
	
	View view;
	JLabel key;
	JLabel value;
	JComboBox<String> featureClass;
	JTextField  featureName;
	String[] types = {"Integer", "Float", "Coordinates", "String", "Make New Feature"};
	JButton add;
	
	public FeaturePanel(View view)
	{
		super(new FlowLayout());
		featureClass = new JComboBox<String>(types);
		value = new JLabel("Attribute type: ");
		key = new JLabel("Attribute name: ");
		featureName = new JTextField(15);
		add = new JButton("Add");
		this.view = view;
		
		
		add(key);
		add(featureName);
		add(value);
		add(featureClass);
		add(add);
		
		//To do: event listeners for add button
			
	}
	
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

}
