package Controllers;

import View.View;

import java.awt.event.ActionEvent;

import Controllers.MainController;
/**
 * Controller for JMenuItems. It checks what was clicked, and executes the appropriate method in the view
 * @author Gabrielle
 *
 */
public class ButtonMenuController extends MainController {

	private View view;
	/**
	 * Constructor to pass along the View.View containing the Controlers.ButtonMeanuController
	 * @param view
	 */
		public ButtonMenuController(View view)
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
		else if(s.equals("Add a Simple Feature"))
		{
			view.addFeaturePanelSimple("", 0);
		}
		else if(s.equals("Add a Complex Feature"))
		{
			view.addFeaturePanelComplex("", 0);
		}
		else if(s.equals("Add Value"))
		{
			view.promptValue();
		}
		else if(s.equals("New Test Case"))
		{
			view.promptTestCase();
		}
		else if(s.equals("Load Sample Data"))
		{
			dataModel.presetTestData();
			view.enableFeatureCreation(false);
			view.enableDataInput(true);
			view.enableTesting(true);
			view.enableNewDataSet(false);
			view.enableTestData(false);
			view.getDoneButton().setVisible(false);
		}
		else if(s.equals("Import Data")){
		    System.out.println("test");
            //TODO: AV Make the thing load the data from a test case
        }else if(s.equals("Export Data")){
            System.out.println("t1est");
            //TODO: AV Make the thing export to a file for now
        }

	}

}
