package Controllers;

import DataModel.*;



import java.awt.event.ActionEvent;
import java.util.HashMap;
import View.*;

/**
 * Controller for a View.TestCaseFrame.
 * Takes text inputed in the View.TestCaseFrame and creates a KNN test case for it
 * 
 * @author Gabrielle and Ben
 *
 */
public class ValueTestFrameController extends ValueInputController {


	
	public ValueTestFrameController(View view, TestCaseFrame testCaseFrame) {
		super(view, testCaseFrame);
	}
    //Fix the TestCaseFrame Key
	@Override
	public void actionPerformed(ActionEvent e) {
        TestCaseFrame tempCast = (TestCaseFrame)frame;
        String distanceMetric = tempCast.getDistanceMetric();
	    String testKey = tempCast.getTestValue();
        Point newPoint = new Point();
        HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();
        int k =0;
        int mink = 0;
        System.out.println(testKey);
        for(String key : MainController.dataModel.getCellTypes().keySet()){
        	System.out.println(key);
            if(MainController.dataModel.getCellTypes().get(key).equals("comp")){
                createCompFeature(key, newConfiguredData);
            }else{
                if(!key.equals(testKey)){
                	if(key.contains(".")){
                		String keyBrakDown = key.split("\\.")[0];
                		createCompFeature(keyBrakDown, newConfiguredData);
                    	createSubFeature(key, frame.getFieldMap().get(key).getText(), (CellComposite) newConfiguredData.get(keyBrakDown));
                	}else{
                		try
                		{
                			newConfiguredData.put(key, createStanderedFeature(key, frame.getFieldMap().get(key).getText()));
                		}
                		catch(Exception z)
                		{}
                }}
            }
        }
        newPoint.setHashMaprawValues(newConfiguredData);
        try{
            k = Integer.parseInt(tempCast.getKTextField().getText());
        }catch (NumberFormatException ex){
            view.sendErrorFrame("Invalid K value was provided");
        }
        if(distanceMetric.equals("Minkowski"))
        {
        	try{
            		mink = Integer.parseInt(tempCast.getMinkTextField().getText());
            	}
        	catch (NumberFormatException ex){
        		view.sendErrorFrame("Invalid polynomial value was provided");
        	}
		}
        view.addTestCaseResult(MainController.dataModel.findkNN(testKey, newPoint, k, distanceMetric, tempCast.getMinkPolynomial()));
        tempCast.dispose();
	}

}
