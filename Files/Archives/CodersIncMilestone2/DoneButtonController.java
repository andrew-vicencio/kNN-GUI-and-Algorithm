import java.awt.event.ActionEvent;
/**
 * Controller for the JButton (Done), when a user is inputting feature types.
 * The number of features is checked first.
 * @author Gabrielle
 *
 */
public class DoneButtonController extends MainController {

	private View view;
	/**
	 * Constructor to pass along the View containing the MenuController
	 * @param view
	 */
	public DoneButtonController(View view) 
	{
		this.view = view;
	}

	/**
	 * Executes action depending on if data inputed is valid or not
	 */
	public void actionPerformed(ActionEvent e) {
		

		if(dataModel.cellTypesLessThanTwo())
		{
			view.sendErrorFrame("Please enter at least two features");
		}
		else
		{
			view.clearContentPanel();
			view.setUpFeatures();
			view.enableDataInput(true);
		}
		
	}

}
