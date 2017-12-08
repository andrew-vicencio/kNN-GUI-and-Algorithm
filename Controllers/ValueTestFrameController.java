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
 * @version Milestone 4
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
		String testChildren[] = tempCast.getTestChildren();
        
		System.out.println("Test key: " + testKey);
		System.out.println("Number of children: " + testChildren.length);
		System.out.println("First child: " + testChildren[0]);
		
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
        
        String[] result = new String[testChildren.length];
        for(int i = 0; i < result.length; i++ )
        {
        	result[i]= MainController.dataModel.findkNN(testChildren[i], newPoint, k, distanceMetric, tempCast.getMinkPolynomial());
        	view.incrementTestCount();
        	view.addTestCaseResult("(" + distanceMetric + ") Expected " + testChildren[i] + ": " + tempCast.getExpectedValue()[i] + "           Actual " + result[i]);
        }
        		
        
        tempCast.dispose();
        
        if(e.getActionCommand().equals("Add Test"))
        {
        	for(int i = 0; i < result.length; i++)
        	{
        		dataModel.addTest(result[i].split(testKey+": ")[1].replaceAll("\\s+", ""), tempCast.getExpectedValue()[i].replaceAll("\\s+", ""));
            	view.promptTestCase();
        	}
        	
        }
        else if(e.getActionCommand().equals("Done"))
        {
        	for(int i = 0; i < result.length; i++)
        	{
        		String s = result[i].replaceAll("\\s+", "");
        		String t = tempCast.getExpectedValue()[i].replaceAll("\\s+", "");
        		dataModel.addTest(s, t);
        	}
        	dataModel.getSuccessRate();
        	dataModel.initTestStats();
        	view.initTestStats();
        }
        
	}

}
