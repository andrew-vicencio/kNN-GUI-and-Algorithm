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
public class ValueTestFrameControler extends ValueInputControler {


	
	public ValueTestFrameControler(View view, TestCaseFrame testCaseFrame) {
		super(view, testCaseFrame);
	}
    //Fix the TestCaseFrame Key
	@Override
	public void actionPerformed(ActionEvent e) {
        TestCaseFrame tempCast = (TestCaseFrame)frame;
	    String testKey = tempCast.getTestValue();
        Point newPoint = new Point();
        HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();
        int k =0;
        System.out.println(testKey);
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
            k = Integer.parseInt(tempCast.getKTextField().getText());
        }catch (NumberFormatException ex){
            view.sendErrorFrame("Invalid int value was provided");
        }

        view.addTestCaseResult(MainController.dataModel.findkNN(testKey, newPoint, k));

	}

}
