package Controllers;

import DataModel.*;
import View.*;


import java.util.HashMap;

/**
 * Abstract class for controllers that deal with the input of data
 * - ValuePromptFrameController
 * - VaslueTestFrameController
 * @author Ben
 *
 */
public abstract class ValueInputController extends MainController {

    protected View view;
    protected PromptFrame frame;

/**
 * Constructor that passes allong the frame that belongs to the controller, and the view
 * @param view
 * @param promt
 */
    public ValueInputController(View view, PromptFrame promt){
        this.view = view;
        this.frame = promt;
    }

    protected Cell createStanderedFeature(String key, String value){
        String type = MainController.dataModel.getCellTypes().get(key);
        if(type.equals("int")){
            try{
                int valueInt;
                if(value != null ){
                    valueInt = Integer.parseInt(value.replaceAll(" ", ""));;
                }else{
                    valueInt = 0;
                }

                CellSimple<Integer> newCell = new CellSimple<Integer>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
                //view.sendErrorFrame("Invalid int value was provided");
            }

        }else if(type.equals("float")){
            try{
                float valueInt;
                if(value != null ){
                    valueInt = Float.parseFloat(value);
                }else{
                    valueInt = 0;
                }

                CellSimple<Float> newCell = new CellSimple<Float>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
            }
        }else{
            CellSimple<String> newCell = new CellSimple<String>(key, value);
            return newCell;
        }
        return null;
    }

    protected void createSubFeature(String key, String value, CellComposite complexCell){
        Cell newSimpleCell = createStanderedFeature(key, value);
        complexCell.addCell(newSimpleCell);

    }

    protected void createCompFeature(String key,  HashMap<String, Cell> newConfiguredData ) {
        if(!newConfiguredData.keySet().contains(key)){
            CellComposite newComplexCell = new CellComposite(key);
            newConfiguredData.put(key, newComplexCell);
        }
    }
}
