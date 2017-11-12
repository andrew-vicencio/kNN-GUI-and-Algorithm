import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

	private View view;
		public MenuController(View view)
		{
			this.view = view;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if(s.equals("New Data Set"))
		{
			view.enableNewDataSet(false);
			view.enableFeatureCreation(true);
		}
		if(s.equals("Add a Simple Feature"))
		{
			view.addFeaturePanelSimple();
		}
		if(s.equals("Add a Complex Feature"))
		{
			view.addFeaturePanelComplex();
		}

	}

}
