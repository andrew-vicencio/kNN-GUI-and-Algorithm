import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoneButtonController extends MainController {

	private View view;
	
	public DoneButtonController(View view) 
	{
		this.view = view;
	}

	public void actionPerformed(ActionEvent e) {
	
		if(view.getFeatureMap().size() <= 2)
		{
			view.sendErrorFrame("Please enter at least two features");
		}
		else
		{

			view.setUpFeatures(this.getDataModel().getFeilds().keySet());
		}
		
	}

}
