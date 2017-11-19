package Controlers;

import java.awt.event.ActionEvent;
import View.*;
/**
 * A controller class for a View.FeaturePanelComplex JPanel. It adds a complex feature to the data model (DataModel.DimensionalSpace)
 * @author Gabrielle and Ben
 *
 */

public class FeaturePanelComplexController extends MainController {

	/**
	 * Constructor to pass the View.FeaturePanelComplex using this controller
	 * @param fp
	 */
	FeaturePanelComplex fp;
	public FeaturePanelComplexController(FeaturePanelComplex fp) {
		this.fp = fp;
	}

	/**
	 * Determines what actions to take depending on the contents of the JTextField, and the buttom pressed
	 * If the box is empty, or a feature already has that name, an error message is given
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		String key = fp.getKey();
		
		if(s.equals("Add a simple subfeature"))
		{
			if(key.isEmpty())
			{
				fp.getView().sendErrorFrame("Please enter a valid name");
			}
			else
			{

				FeaturePanelSimple newSimple = new FeaturePanelSimple(fp.getView(), fp.getTypes(), fp.getKey(), fp.getTab() + 1, fp);
				newSimple.setParentComplexKey(key);
				fp.getView().addFeaturePanelSimple(newSimple);
				fp.disableFeatureName();


			}
		}
		else if(s.equals("Add a complex subfeature"))
		{
            fp.getView().sendErrorFrame("Complex subfeatures are not supported at the moment.");
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
				fp.getView().sendErrorFrame("Please enter a valid name");

			}
			else
			{
			    addValueToDataModel(key);
			}
		}

	}

	public void addValueToDataModel(String key){
        if(MainController.dataModel.cellTypeComp(key) < 2){
            fp.getView().sendErrorFrame("Please add at least 2 subfeatures");
        }
        else
        {
            fp.disableAddSimpleButton();
            fp.disable();
            if(fp.getParentComplex() == null)
            {
                if(!MainController.dataModel.getCellTypes().keySet().contains(key)){
                    MainController.dataModel.setSingleCellType(key, "comp");
                }
            }
        }
    }

}
