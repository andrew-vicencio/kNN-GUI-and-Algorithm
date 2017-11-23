package Controlers;

import java.awt.event.ActionEvent;
import View.*;
/**
 * A controller class for a View.FeaturePanelComplex JPanel. It adds a complex feature to the data model (DataModel.DimensionalSpace)
 * @author Gabrielle and Ben
 *
 */

public class FeaturePanelComplexController extends FeaturePanelController{

	/**
	 * Constructor to pass the View.FeaturePanelComplex using this controller
	 * @param fp
	 */

	public FeaturePanelComplexController(FeaturePanel fp) {
		super(fp);
	}

	/**
	 * Determines what actions to take depending on the contents of the JTextField, and the buttom pressed
	 * If the box is empty, or a feature already has that name, an error message is given
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		String key = currentPanel.getKey();
		FeaturePanelComplex tempCast = (FeaturePanelComplex) currentPanel;
		if(s.equals("Add a simple subfeature"))
		{
			if(key.isEmpty())
			{
                currentPanel.getView().sendErrorFrame("Please enter a valid name");
			}
			else
			{

				FeaturePanelSimple newSimple = new FeaturePanelSimple(currentPanel.getView(), currentPanel.getTypes(), currentPanel.getKey(), currentPanel.getTab() + 1,tempCast);
				newSimple.setParentComplexKey(key);
                currentPanel.getView().addFeaturePanelSimple(newSimple);
                tempCast.disableFeatureName();


			}
		}
		else if(s.equals("Add a complex subfeature"))
		{
            currentPanel.getView().sendErrorFrame("Complex subfeatures are not supported at the moment.");
		    /* CODE FOR A LATER IMPLEMENTATION
			if(key.isEmpty())
			{
				fp.getView().sendErrorFrame("Please enter a valid name");
			}
			else if(fp.getTab() > 0)
			{
				fp.getView().sendErrorFrame("We currently do not support a second layer of subfeatures");
			}
			else
			{
				View.FeaturePanelComplex newComplex = new View.FeaturePanelComplex(fp.getView(), fp.getTypes(), fp.getKey(), fp.getTab() + 1, fp);
				newComplex.setParentComplexKey(key);
				fp.getView().addFeaturePanelComplex(newComplex);
				fp.disable();		

			}*/
		}
		else if(s.equals("Add this feature"))
		{
		    //Disables add Simple button

			if(key.isEmpty())
			{
                currentPanel.getView().sendErrorFrame("Please enter a valid name");

			}
			else
			{
			    addValueToDataModel(key, tempCast);
			}
		}

	}

	public void addValueToDataModel(String key, FeaturePanelComplex tempCast){

        if(MainController.dataModel.cellTypeComp(key) < 2){
            tempCast.getView().sendErrorFrame("Please add at least 2 subfeatures");
        }
        else
        {
            tempCast.disableAddSimpleButton();
            tempCast.disable();
            if(currentPanel.getParentComplex() == null)
            {
                if(!MainController.dataModel.getCellTypes().keySet().contains(key)){
                    MainController.dataModel.setSingleCellType(key, "comp");
                }
            }
        }
    }

}
