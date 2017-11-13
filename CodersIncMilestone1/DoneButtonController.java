import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
