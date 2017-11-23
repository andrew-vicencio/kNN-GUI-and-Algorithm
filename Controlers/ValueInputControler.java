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

    protected void createSubFeature(String key, String value, CompositeCell complexCell){
        Cell newSimpleCell = createStanderedFeature(key, value);
        complexCell.addCell(newSimpleCell);

    }

    protected void createCompFeature(String key,  HashMap<String, Cell> newConfiguredData ) {
        if(!newConfiguredData.keySet().contains(key)){
            CompositeCell newComplexCell = new CompositeCell(key);
            newConfiguredData.put(key, newComplexCell);
        }
    }
}
