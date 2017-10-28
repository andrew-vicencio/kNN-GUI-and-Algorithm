/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *

import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class TestEnviroment {

    private DimensionalSpace dataSet;
    private int dataSize;

    
    //Set values
    public TestEnviroment() {
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
        dataSet.generateStats();
    }

    //Test the cost feature 1
    public int testCostFeature1(int k) {
        Point t1 = new Point();
        t1.addAttribute("coordinate x", 15);
        t1.addAttribute("coordinate y", 20);
        t1.addAttribute("sq. ft.", 1500);
        t1.addAttribute("age", 1);
        return dataSet.findkNN(t1, k);

    }
    
    //Accracy method for error output
    public double accuracy(int actualValue, int value) {
        return ((actualValue - (actualValue - value)) / actualValue) * 100;
    }

    public int getSize() {
        return 3;
    }

    public static void main(String[] args) {

        TestEnviroment working = new TestEnviroment();
        int kValue = 0;
        double accuracy = 0;
        int value = 0;
        
        //Determine which K Value will give you the most accrate value in our test case
        for (int i = 1; i <= working.getSize(); i++) {

            value = working.testCostFeature1(i);
            double tempAccuracy = working.accuracy(400000, value);
            //Set value for origonal value
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
        System.out.println("The most useful K value is " + kValue + ".\nThe accruacy of this K value for a new point is " + accuracy + ".\n The acctual value was 400000. The generated value was " + value + ".");

    }

}
