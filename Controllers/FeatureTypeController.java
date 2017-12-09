package Controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.FeaturePanelSimple;
/**
 * Action listener for the featureType JComboBox in a FeaturePanelSimple. It triggers a change in the 
 * distanceMetric JComboBox in the same panel, whenever the featureType box is changed
 * @author Gabrielle
 *
 */
public class FeatureTypeController extends MainController implements ActionListener {

	private FeaturePanelSimple panel;
	
	public FeatureTypeController(FeaturePanelSimple panel) 
	{
		this.panel = panel;
	}

	/**
	 * Change was made in the JComboBox. This method alerts the view and changes the distanceMetric JComboBox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setMetricsArray();
		
	}

}
