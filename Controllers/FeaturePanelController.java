package Controllers;

import View.FeaturePanel;
import View.FeaturePanelSimple;

/**
 * Abstract class for FeaturePanelSimpleController and FeaturePanelComplexController.
 * @author Ben
 * @version Milestone 4
 *
 */
public abstract class FeaturePanelController extends MainController{
    protected FeaturePanel currentPanel;
    
    
    /**
     * @param fp
     */
    public FeaturePanelController(FeaturePanel fp)
    {
        this.currentPanel = fp;
    }

}
