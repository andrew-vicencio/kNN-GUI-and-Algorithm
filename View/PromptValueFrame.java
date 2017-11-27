package View;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.ValuePromptFrameController;

/**
 * A JFrame that prompts the user for values for each feature, using JTextfields
 * @author Gabrielle
 *
 */
public class PromptValueFrame extends PromptFrame {
	
	
	private ValuePromptFrameController controller;
	
	/**
	 * Constructor, which initializes the frame and its layout
	 * @param view
	 */
	public PromptValueFrame(View view)
	{
		super(view, "Enter your data values");
		controller = new ValuePromptFrameController(view, this);
		fillPrompts();		
	}
	
	/**
	 * Fills the frame with the number and types of prompts provided in the fieldMap
	 */
	public void fillPrompts()
	{
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
	
}
