package Controlers;

import DataModel.*;



import java.awt.event.ActionEvent;
import java.util.HashMap;
import View.*;

/**
 * Controller for a View.TestCaseFrame.
 * Takes text inputed in the View.TestCaseFrame and creates a KNN test case for it
 * 
 * @author Gabrielle
 *
 */
public class TestCaseFrameController extends MainController {

	private View view;
	private TestCaseFrame frame;
	
	public TestCaseFrameController(View view, TestCaseFrame testCaseFrame) {
		this.view = view;
		frame = testCaseFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	    String testKey = frame.getTestValue();
        Point newPoint = new Point();
        HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();
        int k =0;

        for(String key : MainController.dataModel.getCellTypes().keySet()){
            if(MainController.dataModel.getCellTypes().get(key).equals("comp")){
                createCompFeature(key, newConfiguredData);
            }else{
                if(!key.equals(testKey)){
                if(key.contains(".")){
                    String keyBrakDown = key.split("\\.")[0];
                    createCompFeature(keyBrakDown, newConfiguredData);
                    createSubFeature(key, frame.getFieldMap().get(key).getText(), (CompositeCell) newConfiguredData.get(keyBrakDown));
                }else{
                    newConfiguredData.put(key, createStanderedFeature(key, frame.getFieldMap().get(key).getText()));
                }}
            }
        }
        newPoint.setHashMaprawValues(newConfiguredData);
        try{
            k = Integer.parseInt(frame.getKTextField().getText());
        }catch (NumberFormatException ex){
            view.sendErrorFrame("Invalid int value was provided");
        }

        view.addTestCaseResult(MainController.dataModel.findkNN(testKey, newPoint, k));
        frame.dispose();
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

}
