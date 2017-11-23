package Controlers;

import View.FeaturePanel;
import View.FeaturePanelSimple;

public abstract class FeaturePanelController extends MainController{
    protected FeaturePanel currentPanel;
    public FeaturePanelController(FeaturePanel fp)
    {
        this.currentPanel = fp;
    }

}
