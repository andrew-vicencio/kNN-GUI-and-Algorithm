package View;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controlers.*;
/**
 * A JFrame that lists all features (other than the feature being tested)
 * in a JLabel, with an associated JTextField
 * @author Gabrielle
 *
 */
public class TestCaseFrame extends JFrame {
	
	private View view;
	private String testValue;
	private JPanel mainPanel, footerPanel, promptPanel;
	private JLabel name, type, kLabel;
	private JTextField jt, kTextField;
	private JButton done;
	private TestCaseFrameController controller;
	private HashMap<String, JTextField> fieldMap;
	
	
	/**
	 * Displays the View.TestCaseFrame with required JTextFields
	 * @param view: View.View object this JFrame was spawned from
	 * @param testValue: String
	 */
	public TestCaseFrame(View view, String testValue)
	{
		super("Enter your test case values");
		mainPanel = new JPanel();
		footerPanel = new JPanel();
		done = new JButton("Done");
		fieldMap = new HashMap<String, JTextField>();
		kLabel = new JLabel("K value to test");
		kTextField = new JTextField(15);
		this.view = view;
		this.testValue = testValue;
		controller = new TestCaseFrameController( view, this);
		
		setSize(500, 600);
		mainPanel.setLayout(new GridLayout(0, 1));
		setLayout(new BorderLayout());
		
		this.view = view;
		this.testValue = testValue;
		
		for(String str: view.getDataModel().getCellTypes().keySet())
		{

		    if(!str.contains(testValue) && !view.getDataModel().getCellTypes().get(str).equals("comp"))
		    {
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
		footerPanel.add(kLabel);
		footerPanel.add(kTextField);
		footerPanel.add(done);
		add(mainPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	/**
	 * Provides a HashMap with the name of the feature (String) as a key, and a 
	 * JTextField as a value
	 * @return HashMap<String, JTextField>
	 */
	
	public HashMap<String, JTextField> getFieldMap()
	{
		return fieldMap;
	}
	
	/**
	 * Provides the JTextField where the user inputed the K value they require
	 * @return
	 */
	public JTextField getKTextField()
	{
		return kTextField;
	}

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
}
