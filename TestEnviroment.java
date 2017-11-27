
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
    	
    	Point p6 = new Point();
    	CellSimple c61 = new CellSimple("Ball.Distance", 15.9);
    	CellSimple c62 = new CellSimple("Ball.Direction", 1);
    	CellSimple c63 = new CellSimple("Goal.Distance", 22.3);
    	CellSimple c64 = new CellSimple("Goal.Direction", -18);
    	CellSimple c65 = new CellSimple("FCT.Distance", "");
    	CellSimple c66 = new CellSimple("FCT.Direction","");
    	CellSimple c67 = new CellSimple("FCB.Distance", "");
    	CellSimple c68 = new CellSimple("FCB.Direction", "");
    	CellSimple c69 = new CellSimple("Action", "Dash");
    	p6.addAttribute(c61);
    	p6.addAttribute(c62);
    	p6.addAttribute(c63);
    	p6.addAttribute(c64);
    	p6.addAttribute(c65);
    	p6.addAttribute(c66);
    	p6.addAttribute(c67);
    	p6.addAttribute(c68);
    	p6.addAttribute(c69);
    	view.getDataModel().addPoint(p6);
    	
    	Point p7 = new Point();
    	CellSimple c71 = new CellSimple("Ball.Distance", 14.5);
    	CellSimple c72 = new CellSimple("Ball.Direction", 1);
    	CellSimple c73 = new CellSimple("Goal.Distance", 20.7);
    	CellSimple c74 = new CellSimple("Goal.Direction", -20);
    	CellSimple c75 = new CellSimple("FCT.Distance", "");
    	CellSimple c76 = new CellSimple("FCT.Direction","");
    	CellSimple c77 = new CellSimple("FCB.Distance", "");
    	CellSimple c78 = new CellSimple("FCB.Direction", "");
    	CellSimple c79 = new CellSimple("Action", "Dash");
    	p7.addAttribute(c71);
    	p7.addAttribute(c72);
    	p7.addAttribute(c73);
    	p7.addAttribute(c74);
    	p7.addAttribute(c75);
    	p7.addAttribute(c76);
    	p7.addAttribute(c77);
    	p7.addAttribute(c78);
    	p7.addAttribute(c79);
    	view.getDataModel().addPoint(p7);
    	
    	Point p8 = new Point();
    	CellSimple c81 = new CellSimple("Ball.Distance", 11);
    	CellSimple c82 = new CellSimple("Ball.Direction", 1);
    	CellSimple c83 = new CellSimple("Goal.Distance", "");
    	CellSimple c84 = new CellSimple("Goal.Direction", "");
    	CellSimple c85 = new CellSimple("FCT.Distance", 44.8);
    	CellSimple c86 = new CellSimple("FCT.Direction",-5);
    	CellSimple c87 = new CellSimple("FCB.Distance", "");
    	CellSimple c88 = new CellSimple("FCB.Direction", "");
    	CellSimple c89 = new CellSimple("Action", "Dash");
    	p8.addAttribute(c81);
    	p8.addAttribute(c82);
    	p8.addAttribute(c83);
    	p8.addAttribute(c84);
    	p8.addAttribute(c85);
    	p8.addAttribute(c86);
    	p8.addAttribute(c87);
    	p8.addAttribute(c88);
    	p8.addAttribute(c89);
    	view.getDataModel().addPoint(p8);
    	
    	Point p9 = new Point();
    	CellSimple c91 = new CellSimple("Ball.Distance", 45.7);
    	CellSimple c92 = new CellSimple("Ball.Direction", 1);
    	CellSimple c93 = new CellSimple("Goal.Distance", 96.6);
    	CellSimple c94 = new CellSimple("Goal.Direction", 2);
    	CellSimple c95 = new CellSimple("FCT.Distance", 55.6);
    	CellSimple c96 = new CellSimple("FCT.Direction",-37);
    	CellSimple c97 = new CellSimple("FCB.Distance", 55.6);
    	CellSimple c98 = new CellSimple("FCB.Direction", 40);
    	CellSimple c99 = new CellSimple("Action", "Dash");
    	p9.addAttribute(c91);
    	p9.addAttribute(c92);
    	p9.addAttribute(c93);
    	p9.addAttribute(c94);
    	p9.addAttribute(c95);
    	p9.addAttribute(c96);
    	p9.addAttribute(c97);
    	p9.addAttribute(c98);
    	p9.addAttribute(c99);
    	view.getDataModel().addPoint(p9);
    	
    	Point p10 = new Point();
    	CellSimple c101 = new CellSimple("Ball.Distance", 10);
    	CellSimple c102 = new CellSimple("Ball.Direction", 1);
    	CellSimple c103 = new CellSimple("Goal.Distance", 61.3);
    	CellSimple c104 = new CellSimple("Goal.Direction", -31);
    	CellSimple c105 = new CellSimple("FCT.Distance", "");
    	CellSimple c106 = new CellSimple("FCT.Direction","");
    	CellSimple c107 = new CellSimple("FCB.Distance", 41.4);
    	CellSimple c108 = new CellSimple("FCB.Direction", 43);
    	CellSimple c109 = new CellSimple("Action", "Dash");
    	p10.addAttribute(c101);
    	p10.addAttribute(c102);
    	p10.addAttribute(c103);
    	p10.addAttribute(c104);
    	p10.addAttribute(c105);
    	p10.addAttribute(c106);
    	p10.addAttribute(c107);
    	p10.addAttribute(c108);
    	p10.addAttribute(c109);
    	view.getDataModel().addPoint(p10);
    	
    	view.enableTesting(true);
    	

    }

}
