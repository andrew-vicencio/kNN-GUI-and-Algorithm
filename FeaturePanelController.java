import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FeaturePanelController implements ActionListener {
	
	FeaturePanel fp;
	
	public FeaturePanelController(FeaturePanel fp)
	{
		this.fp = fp;
	}

	public void actionPerformed(ActionEvent e) {
		
		String key = fp.getKey();
		String value = fp.getValue();
		if(key.isEmpty())
		{
			fp.getView().sendErrorFrame("Please enter a valid name");
		}
		else if(fp.getView().getFeatureMap().containsKey(key))
		{
			fp.getView().sendErrorFrame("There is already a feature with that particular name");
		}
		else
		{
			if(value.equals("Add new feature type"))
			{
				fp.getView().addFeatureType(key);
				fp.refreshComboBox(key);
				fp.disable();
				fp.addFeaturePanel(key);
				if(!fp.getView().fpExists())
				{
					fp.getView().addFeaturePanel();
					fp.getView().setfpExists(true);
				}
			}
			else
			{
				fp.getView().addNewFeature(key, value);
				System.out.println(fp.getView().getList().toString());
				fp.disable();	
				fp.getView().addFeaturePanel();
			}
			
			}
		}

		

}
