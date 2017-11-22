package View;


import java.awt.BorderLayout;
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
public class TestCaseFrame extends PromptFrame {
	

	private String testValue;
	private JLabel kLabel;
	private JTextField kTextField;
	private TestCaseFrameController controller;
	
	
	/**
	 * Displays the View.TestCaseFrame with required JTextFields
	 * @param view: View.View object this JFrame was spawned from
	 * @param testValue: String
	 */
	public TestCaseFrame(View view, String testValue)
	{
		super(view, "Enter your test case values");
		kLabel = new JLabel("K value to test");
		kTextField = new JTextField(15);
		this.testValue = testValue;
		controller = new TestCaseFrameController( view, this);
		
		
		this.view = view;
		this.testValue = testValue;
	}
			
	/**
	 * Fills the frame with the number and types of prompts provided
	 * in the fieldMap, minus the test case
	 */
	public void fillPrompts()
	{
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
	 * Provides the JTextField where the user inputed the K value they require
	 * @return
	 */
	public JTextField getKTextField()
	{
		return kTextField;
	}
	
	/**
	 * Provides the value we are testing for as a String
	 * @return
	 */
    public String getTestValue() {
        return testValue;
    }

    /**
     * Sets the value we are testing for as a String
     * @param testValue
     */
    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
}
