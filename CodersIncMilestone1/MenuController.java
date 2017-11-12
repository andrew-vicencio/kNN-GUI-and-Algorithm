import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class MenuController implements ActionListener {

	private View view;
	/**
	 * Constructor to pass along the View containing the MenuController
	 * @param view
	 */
		public MenuController(View view)
		{
			this.view = view;
		}
	/**
	 * Executes action depending on which menu item was chosen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if(s.equals("New Data Set"))
		{
			view.showNewDataInfo();
			view.enableNewDataSet(false);
			view.enableFeatureCreation(true);
			view.clearContentPanel();
		}
		if(s.equals("Add a Simple Feature"))
		{
			view.addFeaturePanelSimple("", 0);
		}
		if(s.equals("Add a Complex Feature"))
		{
			view.addFeaturePanelComplex("", 0);
		}
		if(s.equals("Add Value"))
		{
			
		}

	}

}
