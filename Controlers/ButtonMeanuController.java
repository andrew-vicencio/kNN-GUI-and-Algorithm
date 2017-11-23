package Controlers;

import Controlers.MainController;
import View.View;

import java.awt.event.ActionEvent;
/**
 * Controller for JMenuItems. It checks what was clicked, and executes the appropriate method in the view
 * @author Gabrielle
 *
 */
public class ButtonMeanuController extends MainController {

	private View view;
	/**
	 * Constructor to pass along the View.View containing the Controlers.ButtonMeanuController
	 * @param view
	 */
		public ButtonMeanuController(View view)
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
			view.promptValue();
		}
		if(s.equals("New Test Case"))
		{
			view.promptTestCase();
		}

	}

}
