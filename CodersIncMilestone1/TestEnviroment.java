/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodersIncMilestone1;
import java.util.ArrayList;

/**
 * Class to test the kNN algorithm.
 * 
 * @version Milestone 1
 * @author Benjamin
 */
public class TestEnviroment {

    private DimensionalSpace dataSet;
    private int dataSize;

    /**
     * Initializes the test environment to a set of points for one of two cases.
     * 
     * @param case1		True for the first dataset, false for the second.
     */
    public TestEnviroment(boolean case1) {
    	
    	if (case1) {
	        ArrayList<Point> Pts = new ArrayList<Point>();
	        Point h1 = new Point(500000);
	        h1.addAttribute("coordinate x", 12);
	        h1.addAttribute("coordinate y", 25);
	        h1.addAttribute("sq. ft.", 1200);
	        h1.addAttribute("age", 2);
	        Pts.add(h1);
	        Point h2 = new Point(300000);
	        h2.addAttribute("coordinate x", 10);
	        h2.addAttribute("coordinate y", 50);
	        h2.addAttribute("sq. ft.", 1000);
	        h2.addAttribute("age", 1);
	        Pts.add(h2);
	        Point h3 = new Point(400000);
	        h3.addAttribute("coordinate x", 30);
	        h3.addAttribute("coordinate y", 100);
	        h3.addAttribute("sq. ft.", 800);
	        h3.addAttribute("age", 2);
	        Pts.add(h3);
	        dataSet = new DimensionalSpace();
	        dataSet.addPts(Pts);
	        dataSet.findMean();
    	} else {
    		
    		//SECOND DATASET HERE
    		
    	}
    }

    /**
     * KNN test for the first dataset.
     * 
     * @param k		The number of neighbours to use.
     * @return		The calculated point value.
     */
    public int testCostFeature1(int k) {
        Point t1 = new Point();
        t1.addAttribute("coordinate x", 15);
        t1.addAttribute("coordinate y", 20);
        t1.addAttribute("sq. ft.", 1500);
        t1.addAttribute("age", 1);
        return dataSet.findkNN(t1, k);

    }
    
    /**
     * KNN test for the second dataset.
     * 
     * @param k		The number of neighbours to use.
     * @return		The calculated point value.
     */
    public int testCostFeature2(int k) {
        Point t1 = new Point();
        
        // POINT ATTRIBUTES HERE
        
        return dataSet.findkNN(t1, k);

    }
    
    /**
     * Determines how accurate the kNN function if a known value for the point is available.
     * 
     * @param actualValue		The actual value for the point
     * @param value				The calculated value for the point
     * @return					The accuracy value
     */
    public double accuracy(int actualValue, int value) {
        return ((actualValue - (actualValue - value)) / actualValue) * 100;
    }

    /**
     * @return		The max possible value of k (the size of the dataset).
     */
    public int getSize() {
        return dataSet.getNumberOfPoints();
    }

    /**
     * main function used to test the function based on the datasets.
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
    	// Test the first case.
        TestEnviroment working = new TestEnviroment(true);
        int kValue = 0;
        double accuracy = 0;
        int value = 0;
        
        //Determine which K Value will give you the most accurate value in our test case
        for (int i = 1; i <= working.getSize(); i++) {

            value = working.testCostFeature1(i);
            double tempAccuracy = working.accuracy(400000, value);
            //Set value for original value
            if (accuracy == 0) {
                accuracy = tempAccuracy;
                kValue = i;
            } else if (tempAccuracy > accuracy) {
                // If the K value generates a more accurate value
                accuracy = tempAccuracy;
                kValue = i;
            }
        }
        //Report the out put
        System.out.println("The most useful K value is " + kValue + ".\nThe accuracy of this K value for a new point is " + accuracy + ".\n The actual value was 400000. The generated value was " + value + ".");

        // Test the second case.
        working = new TestEnviroment(false);
        kValue = 0;
        accuracy = 0;
        value = 0;
        
        //Determine which K Value will give you the most accurate value in our test case
        for (int i = 1; i <= working.getSize(); i++) {

            value = working.testCostFeature1(i);
            double tempAccuracy = working.accuracy(400000, value);
            //Set value for original value
            if (accuracy == 0) {
                accuracy = tempAccuracy;
                kValue = i;
            } else if (tempAccuracy > accuracy) {
                // If the K value generates a more accurate value
                accuracy = tempAccuracy;
                kValue = i;
            }
        }
        //Report the out put
        System.out.println("The most useful K value is " + kValue + ".\nThe accuracy of this K value for a new point is " + accuracy + ".\n The actual value was 400000. The generated value was " + value + ".");

    }

}
