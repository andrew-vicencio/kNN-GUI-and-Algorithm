import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FeaturePanelSimpleController extends MainController{
	
	FeaturePanelSimple fp;
	
	/**
	 * Constructor to pass the FeaturePanelComplex using this controller
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
		
		if(key.isEmpty())
		{
			fp.getView().sendErrorFrame("Please enter a valid name");
		}
		else if(fp.getView().getList().containsKey(key))
		{
			fp.getView().sendErrorFrame("There is already a feature with that particular name");
		}
		else
		{
			fp.disable();
			if(fp.getParentComplex() != null)
			{
				fp.getParentComplex().addSubFeaturePanel(key, value);
			}
			else
			{
				fp.getView().addNewFeature(key, value);
			}
		}

	}
}
