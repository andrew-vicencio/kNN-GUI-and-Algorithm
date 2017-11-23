package Controlers;

import DataModel.*;
import View.*;


import java.util.HashMap;

public abstract class ValueInputControler extends MainController {

    protected View view;
    protected PromptFrame frame;

    public ValueInputControler(View view, PromptFrame promt){
        this.view = view;
        this.frame = promt;
    }

    protected Cell createStanderedFeature(String key, String value){
        String type = MainController.dataModel.getCellTypes().get(key);

        if(type.equals("int")){
            try{
                int valueInt = Integer.parseInt(value.replaceAll(" ", ""));
                CellSimple<Integer> newCell = new CellSimple<Integer>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
                view.sendErrorFrame("Invalid int value was provided");
            }

        }else if(type.equals("float")){
            try{
                float valueInt = Float.parseFloat(value);
                CellSimple<Float> newCell = new CellSimple<Float>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
                view.sendErrorFrame("Invalid float value was provided");
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
