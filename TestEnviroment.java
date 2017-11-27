
import DataModel.Point;
import DataModel.CellSimple;
import DataModel.DimensionalSpace;
import View.View;

/**
 * Class to test the Maths.kNN algorithm using a dataset and a test point.
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

    }

    /**
     * KNN test for the first dataset.
     *
     * @param k	The number of neighbours to use.
     * @return	The calculated point value.
     */


    /**
     * Determines how accurate the Maths.kNN function if a known value for the point
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
    	view.getDataModel().setSingleCellType("Ball","comp");
    	view.getDataModel().setSingleCellType("Ball.Distance","float");
    	view.getDataModel().setSingleCellType("Ball.Direction","int");
    	view.getDataModel().setSingleCellType("Goal","comp");
    	view.getDataModel().setSingleCellType("Goal.Distance","float");
    	view.getDataModel().setSingleCellType("Goal.Direction","int");
    	view.getDataModel().setSingleCellType("FCT","comp");
    	view.getDataModel().setSingleCellType("FCT.Distance","float");
    	view.getDataModel().setSingleCellType("FCT.Direction","int");
    	view.getDataModel().setSingleCellType("FCB","comp");
    	view.getDataModel().setSingleCellType("FCB.Distance","float");
    	view.getDataModel().setSingleCellType("FCB.Direction","int");
    	view.getDataModel().setSingleCellType("Action","String");
    	view.setUpFeatures();
    	view.enableDataInput(true);
    	
    	Point p1 = new Point();
    	CellSimple c11 = new CellSimple("Ball.Distance", 1.9);
    	CellSimple c12 = new CellSimple("Ball.Direction", -167);
    	CellSimple c13 = new CellSimple("Goal.Distance", 63.8);
    	CellSimple c14 = new CellSimple("Goal.Direction", 31);
    	CellSimple c15 = new CellSimple("FCT.Distance", 39.1);
    	CellSimple c16 = new CellSimple("FCT.Direction", -41);
    	CellSimple c17 = new CellSimple("FCB.Distance", "");
    	CellSimple c18 = new CellSimple("FCB.Direction", "");
    	CellSimple c19 = new CellSimple("Action", "Kick");
    	p1.addAttribute(c11);
    	p1.addAttribute(c12);
    	p1.addAttribute(c13);
    	p1.addAttribute(c14);
    	p1.addAttribute(c15);
    	p1.addAttribute(c16);
    	p1.addAttribute(c17);
    	p1.addAttribute(c18);
    	p1.addAttribute(c19);
    	view.getDataModel().addPoint(p1);
    	
    	Point p2 = new Point();
    	CellSimple c21 = new CellSimple("Ball.Distance", 1.9);
    	CellSimple c22 = new CellSimple("Ball.Direction", 50);
    	CellSimple c23 = new CellSimple("Goal.Distance", 63.8);
    	CellSimple c24 = new CellSimple("Goal.Direction", 31);
    	CellSimple c25 = new CellSimple("FCT.Distance", 39.1);
    	CellSimple c26 = new CellSimple("FCT.Direction", -41);
    	CellSimple c27 = new CellSimple("FCB.Distance", "");
    	CellSimple c28 = new CellSimple("FCB.Direction", "");
    	CellSimple c29 = new CellSimple("Action", "Kick");
    	p2.addAttribute(c21);
    	p2.addAttribute(c22);
    	p2.addAttribute(c23);
    	p2.addAttribute(c24);
    	p2.addAttribute(c25);
    	p2.addAttribute(c26);
    	p2.addAttribute(c27);
    	p2.addAttribute(c28);
    	p2.addAttribute(c29);
    	view.getDataModel().addPoint(p2);
    	
    	Point p3 = new Point();
    	CellSimple c31 = new CellSimple("Ball.Distance", 1.8);
    	CellSimple c32 = new CellSimple("Ball.Direction", 2);
    	CellSimple c33 = new CellSimple("Goal.Distance", 61.9);
    	CellSimple c34 = new CellSimple("Goal.Direction", 31);
    	CellSimple c35 = new CellSimple("FCT.Distance", 39.1);
    	CellSimple c36 = new CellSimple("FCT.Direction", -41);
    	CellSimple c37 = new CellSimple("FCB.Distance", "");
    	CellSimple c38 = new CellSimple("FCB.Direction", "");
    	CellSimple c39 = new CellSimple("Action", "Kick");
    	p3.addAttribute(c31);
    	p3.addAttribute(c32);
    	p3.addAttribute(c33);
    	p3.addAttribute(c34);
    	p3.addAttribute(c35);
    	p3.addAttribute(c36);
    	p3.addAttribute(c37);
    	p3.addAttribute(c38);
    	p3.addAttribute(c39);
    	view.getDataModel().addPoint(p3);
    	
    	Point p4 = new Point();
    	CellSimple c41 = new CellSimple("Ball.Distance", 1.8);
    	CellSimple c42 = new CellSimple("Ball.Direction", -85);
    	CellSimple c43 = new CellSimple("Goal.Distance", 53.5);
    	CellSimple c44 = new CellSimple("Goal.Direction", -4);
    	CellSimple c45 = new CellSimple("FCT.Distance", "");
    	CellSimple c46 = new CellSimple("FCT.Direction","");
    	CellSimple c47 = new CellSimple("FCB.Distance", "");
    	CellSimple c48 = new CellSimple("FCB.Direction", "");
    	CellSimple c49 = new CellSimple("Action", "Kick");
    	p4.addAttribute(c41);
    	p4.addAttribute(c42);
    	p4.addAttribute(c43);
    	p4.addAttribute(c44);
    	p4.addAttribute(c45);
    	p4.addAttribute(c46);
    	p4.addAttribute(c47);
    	p4.addAttribute(c48);
    	p4.addAttribute(c49);
    	view.getDataModel().addPoint(p4);
    	
    	Point p5 = new Point();
    	CellSimple c51 = new CellSimple("Ball.Distance", 19.2);
    	CellSimple c52 = new CellSimple("Ball.Direction", 1);
    	CellSimple c53 = new CellSimple("Goal.Distance", 24.6);
    	CellSimple c54 = new CellSimple("Goal.Direction", -17);
    	CellSimple c55 = new CellSimple("FCT.Distance", "");
    	CellSimple c56 = new CellSimple("FCT.Direction","");
    	CellSimple c57 = new CellSimple("FCB.Distance", "");
    	CellSimple c58 = new CellSimple("FCB.Direction", "");
    	CellSimple c59 = new CellSimple("Action", "Dash");
    	p5.addAttribute(c51);
    	p5.addAttribute(c52);
    	p5.addAttribute(c53);
    	p5.addAttribute(c54);
    	p5.addAttribute(c55);
    	p5.addAttribute(c56);
    	p5.addAttribute(c57);
    	p5.addAttribute(c58);
    	p5.addAttribute(c59);
    	view.getDataModel().addPoint(p5);
    	
    	

    }

}
