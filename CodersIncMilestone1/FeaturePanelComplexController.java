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
				fp.getView().addFeaturePanelSimple(newSimple);
				fp.disable();

			}
		}
		else if(s.equals("Add a complex subfeature"))
		{
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
				FeaturePanelComplex newComplex = new FeaturePanelComplex(fp.getView(), fp.getTypes(), fp.getKey(), fp.getTab() + 1, fp);
				fp.getView().addFeaturePanelComplex(newComplex);
				fp.disable();		

			}
		}
		else if(s.equals("Add this feature"))
		{
			if(key.isEmpty())
			{
				fp.getView().sendErrorFrame("Please enter a valid name");
			}
			else if(fp.getSubFeaturePanels().size() < 2)
			{
				fp.getView().sendErrorFrame("Please enter at least two sub-features");
			}
			else
			{
				fp.disable();
				fp.getView().addNewFeature(key, fp.getSubFeaturePanels());
			}
		}

	}

}
