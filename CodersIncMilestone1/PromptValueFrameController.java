import java.awt.event.ActionEvent;
public class PromptValueFrameController extends MainController {

	private PromptValueFrame pvf;
	private View view;
	
	/**
	 * Constructor for PromptValueController. Passes along the PromptValueFrame and View associated with it
	 * @param view: View
	 * @param pvf: PromptValueFrame
	 */
	public PromptValueFrameController(View view, PromptValueFrame pvf)
	{
		this.pvf = pvf;
		this.view = view;
	}
	
	/**
	 * Adds the data that was inputted in the PromptValueFrame to a Point, which is added to Dimensional Frame
	 */
	public void actionPerformed(ActionEvent e) {
		

			//TODO BB : pvf.getFieldMap() is a Hash Map of the feature name and their corresponding JTextField.
			// You need iterate through it, get the text from the JTextField, and add to a point 
			// in Dimensional space
			pvf.dispose();
			if(dataModel.getNumberOfPoints() > 0)
			{
				view.enableTesting(true);
			}

	}

}
