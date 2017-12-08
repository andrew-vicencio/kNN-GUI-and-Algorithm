package Controllers;

import DataModel.*;
import View.*;


import java.util.HashMap;

/**
 * Abstract class for controllers that deal with the input of data
 * - ValuePromptFrameController
 * - VaslueTestFrameController
 * @author Ben
 * @version Milestone 4
 *
 */
public abstract class ValueInputController extends MainController {

    protected View view;
    protected PromptFrame frame;

/**
 * Constructor that passes along the frame that belongs to the controller, and the view
 * @param view
 * @param promt
 */
    public ValueInputController(View view, PromptFrame promt){
        this.view = view;
        this.frame = promt;
    }

    /**
     * Parses the value with its specified type to check to see if it can be type cast
     * If not then throw error once
     * If cell can been created then create the cell with specified type then return said cell
     * @param key
     * @param value
     * @return
     */
    protected Cell createStanderedFeature(String key, String value){
        //Get the type requested
        String type = MainController.dataModel.getCellTypes().get(key);

        //Type Casting
        if(type.equals("int")){
            try{
                Integer valueInt;
                if(value != null ){
                    valueInt = Integer.parseInt(value.replaceAll(" ", ""));;
                }else{
                    valueInt = null;
                }

                CellSimple<Integer> newCell = new CellSimple<Integer>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
                //view.sendErrorFrame("Invalid int value was provided");
            }

        }else if(type.equals("float")){
            try{
                Float valueInt;
                if(value != null ){
                    valueInt = Float.parseFloat(value);
                }else{
                    valueInt = null;
                }

                CellSimple<Float> newCell = new CellSimple<Float>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
            }
        }else{
            CellSimple<String> newCell = new CellSimple<String>(key, value);
            return newCell;
        }

        //Failed casting
        return null;
    }

    /**
     * Creates a sub simple cell that attaches to it to its corresponding complex cell
     * @param key
     * @param value
     * @param complexCell
     */
    protected void createSubFeature(String key, String value, CellComposite complexCell){
        Cell newSimpleCell = createStanderedFeature(key, value);
        complexCell.addCell(newSimpleCell);

    }

    /**
     * Checks if the chosen complex has been created if not then create said complex cell
     * @param key
     * @param newConfiguredData
     */
    protected void createCompFeature(String key,  HashMap<String, Cell> newConfiguredData ) {
        if(!newConfiguredData.keySet().contains(key)){
            CellComposite newComplexCell = new CellComposite(key);
            newConfiguredData.put(key, newComplexCell);
        }
    }
}
