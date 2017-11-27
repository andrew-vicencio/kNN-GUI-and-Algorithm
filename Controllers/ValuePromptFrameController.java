package Controllers;

import DataModel.*;



import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;

import View.*;
/**
 * A Controller class for a View.PromptValueFrame.
 * Takes data inputed in the View.PromptValueFrame, and creates DataModel.Cell objects and a DataModel.Point object, and adds
 * it to the model (DataModel.DimensionalSpace)
 * @author Gabrielle and Ben
 *
 */

public class ValuePromptFrameController extends ValueInputController {



	/**
	 * Constructor for PromptValueController. Passes along the View.PromptValueFrame and View.View associated with it
	 * @param view: View.View
	 * @param pvf: View.PromptValueFrame
	 */
	public ValuePromptFrameController(View view, PromptValueFrame pvf)
	{
		super(view, pvf);
	}
	
	/**
	 * Adds the data that was inputed in the View.PromptValueFrame to a DataModel.Point, which is added to Dimensional Frame
	 */
	public void actionPerformed(ActionEvent e) {
		
                Point newPoint = new Point();
                LinkedHashMap<String, Cell> newConfiguredData = new LinkedHashMap<String, Cell>();

                frame.dispose();

                if(MainController.dataModel.getNumberOfPoints() > 0)
                {
                    view.enableTesting(true);
                }
                
                
                
                for(String key : MainController.dataModel.getCellTypes().keySet()){
                    if(MainController.dataModel.getCellTypes().get(key).equals("comp")){
                        createCompFeature(key, newConfiguredData);
                    }else{
                        if(key.contains(".")){
                            String keyBrakDown = key.split("\\.")[0];
                            createCompFeature(keyBrakDown, newConfiguredData);
                            createSubFeature(key, frame.getFieldMap().get(key).getText(), (CellComposite) newConfiguredData.get(keyBrakDown));
                        }else{
                           newConfiguredData.put(key, createStanderedFeature(key, frame.getFieldMap().get(key).getText()));
                        }
                    }
                }
                newPoint.setHashMaprawValues(newConfiguredData);
                
                if(!view.isSetUp())
                {
                	view.setUpFeatures(newPoint);
                	view.setSetUp(true);
                }
                MainController.dataModel.addPoint(newPoint);

                
	}





}
