import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JTextField;
public class PromptValueFrameController extends MainController {

	private PromptValueFrame pvf;
	private View view;
	
	public PromptValueFrameController(View view, PromptValueFrame pvf)
	{
		this.pvf = pvf;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		

			//TODO BB : pvf.getFieldMap() is a Hash Map of the feature name and their corresponding JTextField.
			// You need iterate through it, get the text from the JTextField, and add to a point 
			// in Dimensional space
			pvf.dispose();

	}

}
