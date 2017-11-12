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
	 * Executes action depending on if data inputted is valid or not
	 */
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(view.getList().toString());
	
		if(view.getList().size() < 2)
		{
			view.sendErrorFrame("Please enter at least two features");
		}
		else
		{
			System.out.println(view.getList().toString());
			view.setUpFeatures();
		}
		
	}

}
