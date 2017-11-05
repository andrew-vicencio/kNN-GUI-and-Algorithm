import javax.swing.*;

public class View {
	JFrame mainFrame;
	JMenuBar menuBar;
	JMenu create, edit, display, help;
	JMenuItem newDataSet, newTestCase, addValue, helpDoc;
	JPanel mainPanel;
	
	public View()
	{
		mainFrame = new JFrame("CODERS INC");
		menuBar = new JMenuBar();
		create = new JMenu("Create");
		edit = new JMenu("Edit");
		display = new JMenu("Display");
		help = new JMenu("Help");
		mainPanel = new JPanel();
		newDataSet = new JMenuItem("New Data Set");
		newTestCase = new JMenuItem("New Test Case");
		addValue = new JMenuItem("Add Value");
		helpDoc = new JMenuItem("View Help Documents");
		
		mainFrame.setJMenuBar(menuBar);
		menuBar.add(create);
		menuBar.add(edit);
		menuBar.add(display);
		menuBar.add(help);
		create.add(newDataSet);
		create.add(newTestCase);
		edit.add("Add Values");
		help.add("View User Guide");
		
		mainFrame.setSize(500, 500);
		newTestCase.setEnabled(false);
		edit.setEnabled(false);
		display.setEnabled(false);
		mainFrame.setVisible(true);
		
		
		
		
		
		
	}

}
