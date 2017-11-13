import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PromptValueFrame extends JFrame {
	
	JPanel mainPanel, footerPanel, promptPanel;
	JLabel jl;
	JTextField jt;
	JButton done, addMore;
	HashMap<String, JTextField> fieldMap;
	View view;
	
	public PromptValueFrame(View view)
	{
		super("Enter your data values");
		mainPanel = new JPanel();
		footerPanel = new JPanel();
		done = new JButton("Done");
		addMore = new JButton("Add another value");
		fieldMap = new HashMap<String, JTextField>();
		this.view = view;
		
		setSize(500, 600);
		mainPanel.setLayout(new GridLayout(0, 1));
		setLayout(new BorderLayout());
		
		for(String str: view.getDataModel().getCellTypes().keySet())
		{
			jl = new JLabel(str);
			jt = new JTextField(15);
			promptPanel = new JPanel();
			promptPanel.add(jl);
			promptPanel.add(jt);
			mainPanel.add(promptPanel);
			fieldMap.put(str, jt);
	
		}
		
		footerPanel.add(done);
		footerPanel.add(addMore);
		add(mainPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

}
