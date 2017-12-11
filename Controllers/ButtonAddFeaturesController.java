package Controllers;

import View.View;

import java.awt.event.ActionEvent;

import Controllers.MainController;
/**
 * Controller for the JButton (Done), when a user is inputting feature types.
 * The number of features is checked first.
 * @author Gabrielle
 * @version Milestone 4
 *
 */
public class ButtonAddFeaturesController extends MainController {

	private View view;
	/**
	 * Constructor to pass along the View.View containing the Controlers.ButtonMeanuController
	 * @param view
	 */
	public ButtonAddFeaturesController(View view)
	{
		this.view = view;
	}

	/**
	 * Executes action depending on if data inputed is valid or not
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		

		if(dataModel.cellTypesLessThanTwo())
		{
			view.sendErrorFrame("Please enter at least two features");
		}
		else
		{
			view.clearContentPanel();
			view.enableDataInput(true);
		}
		
	}

}
