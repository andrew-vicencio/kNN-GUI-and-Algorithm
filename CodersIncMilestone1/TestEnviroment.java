

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to test the kNN algorithm using a dataset and a test point.
 *
 * @version Milestone 1
 * @author Benjamin
 */
public class TestEnviroment {

    private DimensionalSpace dataSet;

    /**
     * Initializes the test environment to a set of points for one of two cases.
     *
     * @param case1	True for the first dataset, false for the second.
     */
    public TestEnviroment(boolean case1) {
        dataSet = new DimensionalSpace();
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

            dataSet.addPts(Pts);
            dataSet.findMean();
        } else {
            ArrayList<Point> pts = new ArrayList<Point>();
            Point h1 = new Point(2);
            h1.addAttribute("1", 2);
            h1.addAttribute("2", 2);
            h1.addAttribute("3", 2);
            h1.addAttribute("4", 2);
            h1.addAttribute("5", 2);
            h1.addAttribute("6", 2);
            h1.addAttribute("7", 3);
            h1.addAttribute("8", 3);
            h1.addAttribute("9", 3);
            h1.addAttribute("10", 2);
            h1.addAttribute("11", 2);
            h1.addAttribute("coordinate x", 5);
            h1.addAttribute("coordinate y", 1);
            h1.addAttribute("Elavation", 92);
            pts.add(h1);
            Point h2 = new Point(2);
            h2.addAttribute("1", 3);
            h2.addAttribute("2", 3);
            h2.addAttribute("3", 2);
            h2.addAttribute("4", 2);
            h2.addAttribute("5", 2);
            h2.addAttribute("6", 2);
            h2.addAttribute("7", 2);
            h2.addAttribute("8", 2);
            h2.addAttribute("9", 2);
            h2.addAttribute("10", 2);
            h2.addAttribute("11", 2);
            h2.addAttribute("coordinate x", 4);
            h2.addAttribute("coordinate y", 2);
            h2.addAttribute("Elavation", 72);
            pts.add(h2);

            Point h3 = new Point(3);
            h3.addAttribute("1", 3);
            h3.addAttribute("2", 3);
            h3.addAttribute("3", 3);
            h3.addAttribute("4", 3);
            h3.addAttribute("5", 2);
            h3.addAttribute("6", 2);
            h3.addAttribute("7", 2);
            h3.addAttribute("8", 2);
            h3.addAttribute("9", 2);
            h3.addAttribute("10", 2);
            h3.addAttribute("11", 3);
            h3.addAttribute("coordinate x", 1);
            h3.addAttribute("coordinate y", 3);
            h3.addAttribute("Elavation", 184);
            pts.add(h3);

            Point h4 = new Point(2);
            h4.addAttribute("1", 3);
            h4.addAttribute("2", 3);
            h4.addAttribute("3", 3);
            h4.addAttribute("4", 2);
            h4.addAttribute("5", 2);
            h4.addAttribute("6", 2);
            h4.addAttribute("7", 2);
            h4.addAttribute("8", 2);
            h4.addAttribute("9", 3);
            h4.addAttribute("10", 2);
            h4.addAttribute("11", 3);
            h4.addAttribute("coordinate x", 3);
            h4.addAttribute("coordinate y", 4);
            h4.addAttribute("Elavation", 149);
            pts.add(h4);

            Point h5 = new Point(2);
            h5.addAttribute("1", 3);
            h5.addAttribute("2", 3);
            h5.addAttribute("3", 3);
            h5.addAttribute("4", 2);
            h5.addAttribute("5", 3);
            h5.addAttribute("6", 3);
            h5.addAttribute("7", 3);
            h5.addAttribute("8", 2);
            h5.addAttribute("9", 3);
            h5.addAttribute("10", 2);
            h5.addAttribute("11", 3);
            h5.addAttribute("coordinate x", 2);
            h5.addAttribute("coordinate y", 5);
            h5.addAttribute("Elavation", 94);
            pts.add(h5);

            Point h6 = new Point(2);
            h6.addAttribute("1", 2);
            h6.addAttribute("2", 2);
            h6.addAttribute("3", 2);
            h6.addAttribute("4", 2);
            h6.addAttribute("5", 2);
            h6.addAttribute("6", 2);
            h6.addAttribute("7", 2);
            h6.addAttribute("8", 2);
            h6.addAttribute("9", 2);
            h6.addAttribute("10", 1);
            h6.addAttribute("11", 2);
            h6.addAttribute("coordinate x", 6);
            h6.addAttribute("coordinate y", 6);
            h6.addAttribute("Elavation", 227);
            pts.add(h6);

            dataSet.addPts(pts);
            dataSet.findMean();

        }
    }

    /**
     * KNN test for the first dataset.
     *
     * @param k	The number of neighbours to use.
     * @return	The calculated point value.
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
     * @param k	The number of neighbours to use.
     * @return	The calculated point value.
     */
    public int testCostFeature2(int k) {

        Point h7 = new Point();
        h7.addAttribute("1", 2);
        h7.addAttribute("2", 2);
        h7.addAttribute("3", 2);
        h7.addAttribute("4", 2);
        h7.addAttribute("5", 2);
        h7.addAttribute("6", 3);
        h7.addAttribute("7", 2);
        h7.addAttribute("8", 2);
        h7.addAttribute("9", 2);
        h7.addAttribute("10", 2);
        h7.addAttribute("11", 2);
        h7.addAttribute("coordinate x", 7);
        h7.addAttribute("coordinate y", 7);
        h7.addAttribute("Elavation", 585);
        return dataSet.findkNN(h7, k);

    }

    /**
     * Determines how accurate the kNN function if a known value for the point
     * is available.
     *
     * @param actualValue	The actual value for the point
     * @param value	The calculated value for the point
     * @return	The accuracy value
     */
    public double accuracy(int actualValue, int value) {
        return ((actualValue - (actualValue - value)) / actualValue) * 100;
    }

    /**
     * @return	The max possible value of k (the size of the dataset).
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
    	View view = new View();
    	view.addFeaturePanel();

    	//view.setUpFeatures();
    	//view.addFeaturePanel();
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

            value = working.testCostFeature2(i);
            double tempAccuracy = working.accuracy(2, value);
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
        System.out.println("The most useful K value is " + kValue + ".\nThe accuracy of this K value for a new point is " + accuracy + ".\n The actual value was 2. The generated value was " + value + ".");

    }

}
