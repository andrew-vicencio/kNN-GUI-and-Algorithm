import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoneButtonController implements ActionListener {

	private View view;
	
	public DoneButtonController(View view) 
	{
		this.view = view;
	}

	public void actionPerformed(ActionEvent e) {
	
		if(view.getModel().isFeildsLessThan2())
		{
			view.sendErrorFrame("Please enter at least two features");
		}
		else
		{

			view.setUpFeatures();
		}
		
	}

}
