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


	/**
	 * Constructor to pass along the testCaseFrame this controller is responsible for, and the View it belongs to
	 * @param view
	 * @param testCaseFrame
	 */
	public ValueTestFrameController(View view, TestCaseFrame testCaseFrame) {
		super(view, testCaseFrame);
	}
	
	/**
	 * Calls the KNN function in the data model to pass along data that the user inputed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        TestCaseFrame tempCast = (TestCaseFrame)frame;
        String distanceMetric = tempCast.getDistanceMetric();
	    String testKey = tempCast.getTestValue();
        Point newPoint = new Point();
        HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();
        int k =0;
        int mink = 0;

        for(String key : MainController.dataModel.getCellTypes().keySet()){

            if(!key.contains(testKey)){
            if(MainController.dataModel.getCellTypes().get(key).equals("comp")){
                createCompFeature(key, newConfiguredData);
            }else{
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
                }
            }
         }
        }
        newPoint.setHashMaprawValues(newConfiguredData);
        try{
            k = Integer.parseInt(tempCast.getKTextField().getText());
        }catch (NumberFormatException ex){
            view.sendErrorFrame("Invalid K value was provided");
        }
        String result = MainController.dataModel.findkNN(testKey, newPoint, k, distanceMetric, tempCast.getMinkPolynomial());
        view.addTestCaseResult("(" + distanceMetric + ") Expected " + testKey + ": " + tempCast.getExpectedValue() + "           Actual " + result);
        tempCast.dispose();
        if(e.getActionCommand().equals("Add Test"))
        {
        	dataModel.addTest(result.split(testKey+": ")[1].replaceAll("\\s+", ""), tempCast.getExpectedValue().replaceAll("\\s+", ""));
        	view.promptTestCase();
        }
        else if(e.getActionCommand().equals("Done"))
        {
        	dataModel.addTest(result.split(testKey+": ")[1].replaceAll("\\s+", ""), tempCast.getExpectedValue().replaceAll("\\s+", ""));
        	dataModel.getSuccessRate();
        	dataModel.initTestStats();
        	view.initTestStats();
        }
        
	}

}
