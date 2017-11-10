import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FeaturePanelController extends MainController{
	
	FeaturePanel fp;
	
	public FeaturePanelController(FeaturePanel fp)
	{
		this.fp = fp;
	}

	public void actionPerformed(ActionEvent e) {
        //TODO: BB mabye make it so that after add is hit it continously creates another feature then when done is hit
        //TODO Just make done deal with empty feature aka dont count the one empty feature one that would be easy for User experience
		String key = fp.getKey();
		String value = fp.getValue();
		
		if(key.isEmpty())
		{
			fp.getView().sendErrorFrame("Please enter a valid name");
		}
		else if(this.getDataModel().getFeilds().containsKey(key))
		{
			fp.getView().sendErrorFrame("There is already a feature with that particular name");
		}
		else
		{
			if(value.equals("Add new feature type"))
			{
                this.getDataModel().addField(key, value);
				fp.refreshComboBox(key);
				fp.disable();
				//fp.getView().addSubFeaturePanel();
			}
			else
			{
                this.getDataModel().addField(key, value);
				fp.disable();	
				fp.getView().addFeaturePanel();

			}
			
			}
		}

		

}
