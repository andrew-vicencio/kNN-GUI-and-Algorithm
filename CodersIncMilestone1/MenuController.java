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
		
		if(s.equals("Create New Data Set"))
		{
			view.addFeaturePanel();
			view.disableNewDataSet();
		}

	}

}
