package Controlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.*;

/**
 * Controller for a View.FeaturePanelSimple JPanel. It creates a simple feature and passes it to the data model (Dimensional Space)
 * @author Gabrielle and Ben
 *
 */
public class FeaturePanelSimpleController extends MainController {
	//TODO: BB Change to utilize the data model
	FeaturePanelSimple fp;
	
	/**
	 * Constructor to pass the View.FeaturePanelComplex using this controller
	 * @param fp
	 */
	public FeaturePanelSimpleController(FeaturePanelSimple fp)
	{
		this.fp = fp;
	}

	/**
	 * Determines what actions to take depending on the contents of the JTextField
	 * If the box is empty, or a feature already has that name, an error message is given
	 */
	public void actionPerformed(ActionEvent e) {

		String key = fp.getKey();
		String value = fp.getValue();
		//Make sure there is a valid key
		if(key.isEmpty())
		{
			fp.getView().sendErrorFrame("Please enter a valid name");
		}
		else if(MainController.dataModel.getCellTypes().containsKey(key))
		{
			fp.getView().sendErrorFrame("There is already a feature with that particular name");
		}
		else
		{
		    //Disable so that the name can not be changed
		    fp.disable();
			//Check if the simple feature has a parent complex feature if not just add as its own type
			if(fp.getParentComplex() != null)
			{
				MainController.dataModel.setSingleCellType(fp.getKeyParentName() + "." + key, value);
			}
			else
			{
				MainController.dataModel.setSingleCellType(key, value);
			}
		}

	}
}
