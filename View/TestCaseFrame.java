package View;


import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Controllers.*;
/**
 * A JFrame that lists all features (other than the feature being tested)
 * in a JLabel, with an associated JTextField
 * @author Gabrielle
 * @version Milestone 4
 *
 */
public class TestCaseFrame extends PromptFrame {
	

	private String testValue, distanceMetric, expected[], testChildren[];
	private JLabel kLabel;
	private JTextField kTextField;
	private ValueTestFrameController controller;
	private int minkInt;
	private JButton addTest;
	
	
	
	/**
	 * Displays the View.TestCaseFrame with required JTextFields
	 * @param view: View.View object this JFrame was spawned from
	 * @param testValue: String
	 */
	public TestCaseFrame(View view, String testValue, String[] testChildren, String[] expected, String distanceMetric, int minkInt)
	{
		super(view, "Enter your test case values");
		kLabel = new JLabel("K value: ");
		kTextField = new JTextField(15);
		this.testValue = testValue;
		controller = new ValueTestFrameController( view, this);
		addTest = new JButton("Add Test");
		
		this.minkInt = minkInt;
		this.view = view;
		this.testValue = testValue;
		this.distanceMetric = distanceMetric;
		this.expected = expected;
		this.testChildren = testChildren;
		fillPrompts();
	
	}
			
	/**
	 * Fills the frame with the number and types of prompts provided
	 * in the fieldMap, minus the test case
	 */
	public void fillPrompts()
	{
	
		for(String feature: view.getDataModel().getCellTypes().keySet())
		{
		    if(!feature.contains(testValue) && !fieldMap.containsKey(feature) && !view.getDataModel().isParent(feature))
		    {
		    	name = new JLabel(feature);
		    	type = new JLabel(view.getDataModel().getCellTypes().get(feature));
		    	jt = new JTextField(15);
		    	promptPanel = new JPanel();
		    	promptPanel.add(name);
		    	promptPanel.add(type);
		    	promptPanel.add(jt);
		    	mainPanel.add(promptPanel);
		    	fieldMap.put(feature, jt);
			}
	
		}
		
		done.addActionListener(controller);
		addTest.addActionListener(controller);
		footerPanel.add(kLabel);
		footerPanel.add(kTextField);
		footerPanel.add(done);
		footerPanel.add(addTest);
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

    /**
     * Returns a String representing the distance metric the user has chosen from a predetermined list
     * @return
     */
	public String getDistanceMetric()
	{
		return distanceMetric;
	}
	
	/**
	 * Returns the value of polynomial the user has chosen if they are using the Minkowski metric (from 3 - 6 inclusive). 
	 * Otherwise, it is 0.
	 * @return
	 */
	public int getMinkPolynomial()
	{
		return minkInt;
	}
	
	/**
	 * Provides the expected value the user has inputed
	 * @return
	 */
	public String[] getExpectedValue()
	{
		return expected;
	}
	
	/**
	 * Returns an array with the String names of all values being tested for, not including the parent name (if applicable)
	 * If the value being test is simple, this array is of length 1 and simply contains the name of the feature 
	 * 
	 * @return
	 */
	public String[] getTestChildren()
	{
		return testChildren;
	}
}


