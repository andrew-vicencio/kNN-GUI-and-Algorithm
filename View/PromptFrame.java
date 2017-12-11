package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Abstract class for all JFrames that prompt the user for input
 * The classes that implement this class are:
 * - PromptValueFrame
 * - TestCaseFrame
 * @author Gabrielle
 * @version Milestone 4
 *
 */

public abstract class PromptFrame extends JFrame {
	
	protected View view;
	protected JPanel mainPanel, footerPanel, promptPanel;
	protected JLabel name, type;
	protected JTextField jt;
	protected JButton done;
	protected HashMap<String, JTextField> fieldMap;
    protected JScrollPane scrollPane;
	
	/**
	 * Sets the main aspects of the frame, such as the layout, the done button
	 * and the size.
	 * @param view
	 * @param string
	 */
	public PromptFrame(View view, String string) 
	{
		super(string);
		mainPanel = new JPanel();
		footerPanel = new JPanel();
		promptPanel = new JPanel();
		done = new JButton("Done");
		fieldMap = new HashMap<String, JTextField>();
		this.view = view;

		setSize(500, 600);
		mainPanel.setLayout(new GridLayout(0, 1));

		setLayout(new BorderLayout());
	}
	
	/**
	 * Provides a HashMap with the name of the feature (String) as a key, and a 
	 * JTextField as a value.
	 * @return HashMap
	 */
	public HashMap<String, JTextField> getFieldMap()
	{
		return fieldMap;
	}
	
	/**
	 * The PromptFrame fills out the mainPanel according to its needs
	 */
	public abstract void fillPrompts();
}
