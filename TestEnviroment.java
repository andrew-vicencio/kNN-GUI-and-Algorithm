
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
    	
    	// The following is code to input the tabulated data provided. To start from scratch, comment it out
    	presetTestData(view);
    	

    }

    /**
     * Loads the tabulated data provided by Prof. Esfandiari into the model, and displays it to the view.
     * @param view
     */
	private static void presetTestData(View view) {
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
    	view.setUpFeatures(p1);
    	
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
    	CellSimple c101 = new CellSimple("Ball.Distance", 50.4);
    	CellSimple c102 = new CellSimple("Ball.Direction", -1);
    	CellSimple c103 = new CellSimple("Goal.Distance", 101.5);
    	CellSimple c104 = new CellSimple("Goal.Direction", 14);
    	CellSimple c105 = new CellSimple("FCT.Distance", 75.4);
    	CellSimple c106 = new CellSimple("FCT.Direction",-24);
    	CellSimple c107 = new CellSimple("FCB.Distance", 46.2);
    	CellSimple c108 = new CellSimple("FCB.Direction", 40);
    	CellSimple c109 = new CellSimple("Action", "Turn");
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
    	
    	Point p11 = new Point();
    	CellSimple c111 = new CellSimple("Ball.Distance", 41.4);
    	CellSimple c112 = new CellSimple("Ball.Direction", 0);
    	CellSimple c113 = new CellSimple("Goal.Distance", 90.1);
    	CellSimple c114 = new CellSimple("Goal.Direction", 18);
    	CellSimple c115 = new CellSimple("FCT.Distance", 65.1);
    	CellSimple c116 = new CellSimple("FCT.Direction",-27);
    	CellSimple c117 = new CellSimple("FCB.Distance", "");
    	CellSimple c118 = new CellSimple("FCB.Direction", "");
    	CellSimple c119 = new CellSimple("Action", "Turn");
    	p11.addAttribute(c111);
    	p11.addAttribute(c112);
    	p11.addAttribute(c113);
    	p11.addAttribute(c114);
    	p11.addAttribute(c115);
    	p11.addAttribute(c116);
    	p11.addAttribute(c117);
    	p11.addAttribute(c118);
    	p11.addAttribute(c119);
    	view.getDataModel().addPoint(p11);
    	
    	Point p12 = new Point();
    	CellSimple c121 = new CellSimple("Ball.Distance", 14.5);
    	CellSimple c122 = new CellSimple("Ball.Direction", 15);
    	CellSimple c123 = new CellSimple("Goal.Distance", 60.1);
    	CellSimple c124 = new CellSimple("Goal.Direction", 27);
    	CellSimple c125 = new CellSimple("FCT.Distance", "");
    	CellSimple c126 = new CellSimple("FCT.Direction","");
    	CellSimple c127 = new CellSimple("FCB.Distance", "");
    	CellSimple c128 = new CellSimple("FCB.Direction", "");
    	CellSimple c129 = new CellSimple("Action", "Turn");
    	p12.addAttribute(c121);
    	p12.addAttribute(c122);
    	p12.addAttribute(c123);
    	p12.addAttribute(c124);
    	p12.addAttribute(c125);
    	p12.addAttribute(c126);
    	p12.addAttribute(c127);
    	p12.addAttribute(c128);
    	p12.addAttribute(c129);
    	view.getDataModel().addPoint(p12);
    	
    	Point p13 = new Point();
    	CellSimple c131 = new CellSimple("Ball.Distance", 41.4);
    	CellSimple c132 = new CellSimple("Ball.Direction", 3);
    	CellSimple c133 = new CellSimple("Goal.Distance", 94.7);
    	CellSimple c134 = new CellSimple("Goal.Direction", 4);
    	CellSimple c135 = new CellSimple("FCT.Distance", 55.1);
    	CellSimple c136 = new CellSimple("FCT.Direction",-36);
    	CellSimple c137 = new CellSimple("FCB.Distance", 53.5);
    	CellSimple c138 = new CellSimple("FCB.Direction", 43);
    	CellSimple c139 = new CellSimple("Action", "Turn");
    	p13.addAttribute(c131);
    	p13.addAttribute(c132);
    	p13.addAttribute(c133);
    	p13.addAttribute(c134);
    	p13.addAttribute(c135);
    	p13.addAttribute(c136);
    	p13.addAttribute(c137);
    	p13.addAttribute(c138);
    	p13.addAttribute(c139);
    	view.getDataModel().addPoint(p13);
    	
    	Point p14 = new Point();
    	CellSimple c141 = new CellSimple("Ball.Distance", 23.2);
    	CellSimple c142 = new CellSimple("Ball.Direction", 0);
    	CellSimple c143 = new CellSimple("Goal.Distance", 76.9);
    	CellSimple c144 = new CellSimple("Goal.Direction", 2);
    	CellSimple c145 = new CellSimple("FCT.Distance", "");
    	CellSimple c146 = new CellSimple("FCT.Direction","");
    	CellSimple c147 = new CellSimple("FCB.Distance", "");
    	CellSimple c148 = new CellSimple("FCB.Direction", "");
    	CellSimple c149 = new CellSimple("Action", "Turn");
    	p14.addAttribute(c141);
    	p14.addAttribute(c142);
    	p14.addAttribute(c143);
    	p14.addAttribute(c144);
    	p14.addAttribute(c145);
    	p14.addAttribute(c146);
    	p14.addAttribute(c147);
    	p14.addAttribute(c148);
    	p14.addAttribute(c149);
    	view.getDataModel().addPoint(p14);
    	
    	Point p15 = new Point();
    	CellSimple c151 = new CellSimple("Ball.Distance", 12);
    	CellSimple c152 = new CellSimple("Ball.Direction", 24);
    	CellSimple c153 = new CellSimple("Goal.Distance", "");
    	CellSimple c154 = new CellSimple("Goal.Direction", "");
    	CellSimple c155 = new CellSimple("FCT.Distance", "");
    	CellSimple c156 = new CellSimple("FCT.Direction","");
    	CellSimple c157 = new CellSimple("FCB.Distance", 42.7);
    	CellSimple c158 = new CellSimple("FCB.Direction", -40);
    	CellSimple c159 = new CellSimple("Action", "Turn");
    	p15.addAttribute(c151);
    	p15.addAttribute(c152);
    	p15.addAttribute(c153);
    	p15.addAttribute(c154);
    	p15.addAttribute(c155);
    	p15.addAttribute(c156);
    	p15.addAttribute(c157);
    	p15.addAttribute(c158);
    	p15.addAttribute(c159);
    	view.getDataModel().addPoint(p15);
    	
    	Point p16 = new Point();
    	CellSimple c161 = new CellSimple("Ball.Distance", "");
    	CellSimple c162 = new CellSimple("Ball.Direction", "");
    	CellSimple c163 = new CellSimple("Goal.Distance", 26.3);
    	CellSimple c164 = new CellSimple("Goal.Direction", 2);
    	CellSimple c165 = new CellSimple("FCT.Distance", "");
    	CellSimple c166 = new CellSimple("FCT.Direction","");
    	CellSimple c167 = new CellSimple("FCB.Distance", "");
    	CellSimple c168 = new CellSimple("FCB.Direction", "");
    	CellSimple c169 = new CellSimple("Action", "Turn");
    	p16.addAttribute(c161);
    	p16.addAttribute(c162);
    	p16.addAttribute(c163);
    	p16.addAttribute(c164);
    	p16.addAttribute(c165);
    	p16.addAttribute(c166);
    	p16.addAttribute(c167);
    	p16.addAttribute(c168);
    	p16.addAttribute(c169);
    	view.getDataModel().addPoint(p16);
    	
    	Point p17 = new Point();
    	CellSimple c171 = new CellSimple("Ball.Distance", 3.5);
    	CellSimple c172 = new CellSimple("Ball.Direction", 1);
    	CellSimple c173 = new CellSimple("Goal.Distance", 56.1);
    	CellSimple c174 = new CellSimple("Goal.Direction", 4);
    	CellSimple c175 = new CellSimple("FCT.Distance", "");
    	CellSimple c176 = new CellSimple("FCT.Direction","");
    	CellSimple c177 = new CellSimple("FCB.Distance", "");
    	CellSimple c178 = new CellSimple("FCB.Direction", "");
    	CellSimple c179 = new CellSimple("Action", "Dash");
    	p17.addAttribute(c171);
    	p17.addAttribute(c172);
    	p17.addAttribute(c173);
    	p17.addAttribute(c174);
    	p17.addAttribute(c175);
    	p17.addAttribute(c176);
    	p17.addAttribute(c177);
    	p17.addAttribute(c178);
    	p17.addAttribute(c169);
    	view.getDataModel().addPoint(p17);
    	
    	Point p18 = new Point();
    	CellSimple c181 = new CellSimple("Ball.Distance", 10);
    	CellSimple c182 = new CellSimple("Ball.Direction", 1);
    	CellSimple c183 = new CellSimple("Goal.Distance", 61.3);
    	CellSimple c184 = new CellSimple("Goal.Direction", -31);
    	CellSimple c185 = new CellSimple("FCT.Distance", "");
    	CellSimple c186 = new CellSimple("FCT.Direction","");
    	CellSimple c187 = new CellSimple("FCB.Distance", 41.4);
    	CellSimple c188 = new CellSimple("FCB.Direction", 43);
    	CellSimple c189 = new CellSimple("Action", "Dash");
    	p18.addAttribute(c181);
    	p18.addAttribute(c182);
    	p18.addAttribute(c183);
    	p18.addAttribute(c184);
    	p18.addAttribute(c185);
    	p18.addAttribute(c186);
    	p18.addAttribute(c187);
    	p18.addAttribute(c188);
    	p18.addAttribute(c189);
    	view.getDataModel().addPoint(p18);
    	
    	view.enableTesting(true);
    	view.enableNewDataSet(false);
	}

}
