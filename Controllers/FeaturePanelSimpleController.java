package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.*;

/**
 * Controller for a View.FeaturePanelSimple JPanel. It creates a simple feature and passes it to the data model (Dimensional Space)
 * @author Gabrielle and Ben
 *
 */
public class FeaturePanelSimpleController extends FeaturePanelController {


	
	/**
	 * Constructor to pass the View.FeaturePanelComplex using this controller
	 * @param fp
	 */
	public FeaturePanelSimpleController(FeaturePanelSimple fp)
	{
		super(fp);
	}

	/**
	 * Determines what actions to take depending on the contents of the JTextField
	 * If the box is empty, or a feature already has that name, an error message is given
     * @param e
	 */
	public void actionPerformed(ActionEvent e) {

		String key = currentPanel.getKey();
		String value = ((FeaturePanelSimple)currentPanel).getFeatureType();

		//Make sure there is a valid key
		if(key.isEmpty())
		{
            currentPanel.getView().sendErrorFrame("Please enter a valid name");
		}
		else if(MainController.dataModel.getCellTypes().containsKey(key))
		{
            currentPanel.getView().sendErrorFrame("There is already a feature with that particular name");
		}
		else
		{
		    //Disable so that the name can not be changed
            currentPanel.disable();
			//Check if the simple feature has a parent complex feature if not just add as its own type
			if(currentPanel.getParentComplex() != null)
			{
				MainController.dataModel.setSingleCellType(currentPanel.getParentComplexKey() + "." + key, value);
				MainController.dataModel.addDistanceMetric(currentPanel.getParentComplexKey() + "." + key, ((FeaturePanelSimple) currentPanel).getDistanceMetric());
			}
			else
			{
				MainController.dataModel.setSingleCellType(key, value);
				MainController.dataModel.addDistanceMetric(key, ((FeaturePanelSimple) currentPanel).getDistanceMetric());
			}
		}

	}
}
