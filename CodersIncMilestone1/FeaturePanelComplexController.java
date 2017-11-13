import sun.applet.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FeaturePanelComplexController extends MainController {

	/**
	 * Constructor to pass the FeaturePanelComplex using this controller
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
				fp.disable();

			}
		}
		else if(s.equals("Add a complex subfeature"))
		{
            fp.getView().sendErrorFrame("Sorry Complex values are not allowed in other complex values");
		    /* CODE FOR A LATER IMPLEMENTATION
			if(key.isEmpty())
			{
				fp.getView().sendErrorFrame("Please enter a valid name");
			}
			else
			{
				FeaturePanelComplex newComplex = new FeaturePanelComplex(fp.getView(), fp.getTypes(), fp.getKey(), fp.getTab() + 1, fp);
				newComplex.setParentComplexKey(key);
				fp.getView().addFeaturePanelComplex(newComplex);
				fp.disable();		

			}*/
		}
		else if(s.equals("Add this feature"))
		{
			if(key.isEmpty())
			{
				fp.getView().sendErrorFrame("Please enter a valid name");

			}
			else
			{
				fp.disable();
                if(fp.getParentComplex() == null)
                {
                    dataModel.setSingleCellType(key, "Comp");

                }
                else
                {
                    //CODE FOR A LATER IMPLEMENTATION
                    key = fp.getParentComplex().getParentComplexKey() + "." + key ;

                    dataModel.setSingleCellType(key, "Comp");

                }


			}
		}

	}

}
