package Controlers;

import DataModel.*;



import java.awt.event.ActionEvent;
import java.util.HashMap;
import View.*;
/**
 * A Controller class for a View.PromptValueFrame.
 * Takes data inputed in the View.PromptValueFrame, and creates DataModel.Cell objects and a DataModel.Point object, and adds
 * it to the model (DataModel.DimensionalSpace)
 * @author Gabrielle and Ben
 *
 */

public class ValuePromtFrameControler extends ValueInputControler {



	/**
	 * Constructor for PromptValueController. Passes along the View.PromptValueFrame and View.View associated with it
	 * @param view: View.View
	 * @param pvf: View.PromptValueFrame
	 */
	public ValuePromtFrameControler(View view, PromptValueFrame pvf)
	{
		super(view, pvf);
	}
	
	/**
	 * Adds the data that was inputed in the View.PromptValueFrame to a DataModel.Point, which is added to Dimensional Frame
	 */
	public void actionPerformed(ActionEvent e) {
		
                Point newPoint = new Point();
                HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();

                frame.dispose();

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
                System.out.println(newPoint.toString());
                MainController.dataModel.addPoint(newPoint);


                if(MainController.dataModel.getNumberOfPoints() > 0)
                {
                    view.enableTesting(true);
                }
	}





}
