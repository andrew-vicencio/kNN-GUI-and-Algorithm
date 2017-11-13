import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * A JFrame that prompts the user for values for each feature, using JTextfields
 * @author Gabrielle
 *
 */
public class PromptValueFrame extends JFrame {
	
	JPanel mainPanel, footerPanel, promptPanel;
	JLabel name, type;
	JTextField jt;
	JButton done;
	HashMap<String, JTextField> fieldMap;
	View view;
	PromptValueFrameController controller;
	
	/**
	 * Constructor, which initializes the frame and its layout
	 * @param view
	 */
	public PromptValueFrame(View view)
	{
		super("Enter your data values");
		mainPanel = new JPanel();
		footerPanel = new JPanel();
		done = new JButton("Done");
		fieldMap = new HashMap<String, JTextField>();
		this.view = view;
		controller = new PromptValueFrameController(view, this);
		
		setSize(500, 600);
		mainPanel.setLayout(new GridLayout(0, 1));
		setLayout(new BorderLayout());

		for(String str: view.getDataModel().getCellTypes().keySet())
		{
		    if(!view.getDataModel().getCellTypes().get(str).equals("comp")){
		    	name = new JLabel(str);
		    	type = new JLabel(view.getDataModel().getCellTypes().get(str));
		    	jt = new JTextField(15);
		    	promptPanel = new JPanel();
		    	promptPanel.add(name);
		    	promptPanel.add(type);
		    	promptPanel.add(jt);
		    	mainPanel.add(promptPanel);
		    	fieldMap.put(str, jt);
		    }
	
		}
		
		done.addActionListener(controller);
		footerPanel.add(done);
		add(mainPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	/**
	 * Provides a HashMap with the name of the feature (String) as a key, and a 
	 * JTextField as a value.
	 * @return HashMap<String, JTextField>
	 */
	public HashMap<String, JTextField> getFieldMap()
	{
		return fieldMap;
	}

}
