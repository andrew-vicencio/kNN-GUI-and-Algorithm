package Controllers;

import View.FeaturePanel;
import View.FeaturePanelSimple;

/**
 * Abstract class for FeaturePanelSimpleController and FeaturePanelComplexController.
 * @author Ben
 *
 */
public abstract class FeaturePanelController extends MainController{
    protected FeaturePanel currentPanel;
    public FeaturePanelController(FeaturePanel fp)
    {
        this.currentPanel = fp;
    }

}
