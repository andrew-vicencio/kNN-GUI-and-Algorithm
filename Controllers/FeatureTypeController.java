package Controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.FeaturePanelSimple;

public class FeatureTypeController extends MainController implements ActionListener {

	private FeaturePanelSimple panel;
	
	public FeatureTypeController(FeaturePanelSimple panel) 
	{
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setMetricsArray();
		
	}

}
