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

public class PromptValueFrameController extends MainController {

	private PromptValueFrame pvf;
	private View view;

	/**
	 * Constructor for PromptValueController. Passes along the View.PromptValueFrame and View.View associated with it
	 * @param view: View.View
	 * @param pvf: View.PromptValueFrame
	 */
	public PromptValueFrameController(View view, PromptValueFrame pvf)
	{
		this.pvf = pvf;
		this.view = view;
	}
	
	/**
	 * Adds the data that was inputed in the View.PromptValueFrame to a DataModel.Point, which is added to Dimensional Frame
	 */
	public void actionPerformed(ActionEvent e) {
		
            Point newPoint = new Point();
            HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();

			pvf.dispose();

            for(String key : MainController.dataModel.getCellTypes().keySet()){
                if(MainController.dataModel.getCellTypes().get(key).equals("comp")){
                    createCompFeature(key, newConfiguredData);
                }else{
                    if(key.contains(".")){
                        String keyBrakDown = key.split("\\.")[0];
                        createCompFeature(keyBrakDown, newConfiguredData);
                        createSubFeature(key, pvf.getFieldMap().get(key).getText(), (CompositeCell) newConfiguredData.get(keyBrakDown));
                    }else{
                       newConfiguredData.put(key, createStanderedFeature(key, pvf.getFieldMap().get(key).getText()));
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

	private Cell createStanderedFeature(String key, String value){
        String type = MainController.dataModel.getCellTypes().get(key);

        if(type.equals("int")){
            try{
                int valueInt = Integer.parseInt(value.replaceAll(" ", ""));
                SimpleCell<Integer> newCell = new SimpleCell<Integer>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
                view.sendErrorFrame("Invalid int value was provided");
            }

        }else if(type.equals("float")){
           try{
               float valueInt = Float.parseFloat(value);
               SimpleCell<Float> newCell = new SimpleCell<Float>(key, valueInt);
               return newCell;
           }catch (NumberFormatException ex){
               view.sendErrorFrame("Invalid float value was provided");
           }
        }else{
            SimpleCell<String> newCell = new SimpleCell<String>(key, value);
            return newCell;
        }
        return null;
    }

    private void createSubFeature(String key, String value, CompositeCell complexCell){
        Cell newSimpleCell = createStanderedFeature(key, value);
        complexCell.addCell(newSimpleCell);

    }

    private void createCompFeature(String key,  HashMap<String, Cell> newConfiguredData ) {
        if(!newConfiguredData.keySet().contains(key)){
            CompositeCell newComplexCell = new CompositeCell(key);
            newConfiguredData.put(key, newComplexCell);
        }
    }

    //this gets called if problem with casting needs to stop the action one aka exit that area
    private void errorCasting(){

    }

}
