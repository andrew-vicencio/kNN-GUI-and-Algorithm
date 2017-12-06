package DataModel;


import View.View;
import Maths.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;

/**
 * Class to hold the points from the dataset. This class calculates the sum, mean, and standard 
 * deviation of all the known points which are used to standardize the points to give more accurate
 * calculations. This class also contains the K-nearest neighbour function which calculates the value
 * of a new point.
 * 
 * @author Darren and Andrew
 * @version Milestone 2
 */

public class DimensionalSpace {
  private ConcurrentHashMap<String, Float> mean;
  private ConcurrentHashMap<String, Float> stddev;
  private ConcurrentHashMap<String, Float> sum;
  private HashMap<String, String> cellTypes;
  private HashMap<String, String> cellDistanceMetrics;
  private int numberOfPoints;
  private int numberOfFields;
  private ArrayList<Point> points;
  private View view;
  private ArrayList<String> distanceMetrics;
  private String[] stringMetrics;
  private String[] numberMetrics;
  private int testSuccess;
  private int testFailure;
  
  /**
   * Constructor for instances of the DataModel.DimensionalSpace class. Initializes the class variables and sets the
   * current number of points to 0.
   */
  public DimensionalSpace(){
    mean = new ConcurrentHashMap<String, Float>();
    stddev = new ConcurrentHashMap<String, Float>();
    sum = new ConcurrentHashMap<String, Float>();
    points = new ArrayList<Point>();
    cellDistanceMetrics = new HashMap<String, String>();
    numberOfPoints = 0;
    numberOfFields = 0;
    cellTypes = new HashMap<String, String>();
    distanceMetrics = new ArrayList<String>();
    distanceMetrics.add("Manhattan");
    distanceMetrics.add("Minkowski");
    distanceMetrics.add("Chebyshev");
    distanceMetrics.add("Euclidean");
    testSuccess = 0;
    testFailure = 0;
    stringMetrics = new String[] {"Hamming", "Equal", "Character Value"};
    numberMetrics = new String[] {"Difference", "Equality", "Standard Deviation"};  
  }
  
  
  /**
   * Adds the given ArrayList of points to the DataModel.DimensionalSpace's list of points. Calls the addPt funtion
   * on each point in the ArrayList.
   * 
   * @param pts 		An ArrayList of DataModel.Point objects to be added to the space.
   */
  public void addPts(ArrayList<Point> pts){
	  if (pts.size() == 0) return;
	  
	  for (Point pt: pts) {
		  addPt(pt);
	  }
  }
  
  
  /**
   * Adds the given DataModel.Point to the DataModel.DimensionalSpace's list of points. Also adds the values from each of the
   * point's "coordinates" to the corresponding sum value.
   * 
   * @param pt 			The DataModel.Point to be added to the space.
   */
  public void addPt(Point pt){
    points.add(pt);
    numberOfPoints++;
  }
  
  /**
   * Calculates the statistical values for the dimensional space.
   */
  public void findStatistics() {
	  if (numberOfPoints <= 1) return;
	  findSum();
	  findMean();
	  findStdDev();
	  normalize();
  }
  
  /**
   * Calculates all of the sums for numeric parameters.
   */
  public void findSum() {
	  HashMap<String, Cell> cellList;
	  for (Point pt: points) {
		  cellList = pt.getRawValues();
		  for (String k: cellList.keySet()) {
			  Cell c = cellList.get(k);
			  
			  if (c instanceof CellSimple) {
				  if (!(((CellSimple)c).getValue() instanceof String)) {
					  calcSimpleSum((CellSimple)c);
				  }
			  } else {
				  calcComplexSum((CellComposite)c);
			  }
		  }
	  }
  }
  
  /**
   * Increments the sum for all of the numeric values contained within a DataModel.CompositeCell
   * 
   * @param c		The DataModel.CompositeCell to be used to increment the corresponding sums.
   */
  private void calcComplexSum(CellComposite c) {
	  ArrayList<Cell> subCells = c.getSubCells();
	  
	  for (Cell subC: subCells) {
		  if (subC instanceof CellSimple) {
			  if (!(((CellSimple)subC).getValue() instanceof String)) {
				  calcSimpleSum((CellSimple)subC);
			  }
		  } else {
			  calcComplexSum((CellComposite)subC);
		  }
	  }
  }

  /**
   * Increments the sum for the key corresponding to a numeric DataModel.SimpleCell
   * 
   * @param c		The DataModel.SimpleCell to use to increment the sum
   */
  private void calcSimpleSum(CellSimple c) {
	  float val;
	  
	  try {
		  if (c.getValue() instanceof Integer) {
			  val = (float)(int)c.getValue();
		  } else {
			  val = (float)c.getValue();
		  }
	  } catch (NullPointerException ne) {
		  val = 0;
	  }
	  
	  if (sum.containsKey(c.getKey())) {
		  sum.put(c.getKey(), val + sum.get(c.getKey()));
	  } else {
		  sum.put(c.getKey(), val);
	  }
  }


  /**
   * Finds the average value for each of the numeric values wich define the points.
   */
  public void findMean(){
      for (String key: sum.keySet()) {
    	  mean.put(key, sum.get(key) / numberOfPoints);
      }
  }
  
  /**
   * findStdDev calculates the standard deviation for every numeric value.
   */
   public void findStdDev(){
	  float val, u;
	  for (String k: mean.keySet()) {
		  val = 0;
		  u = mean.get(k);
		  
		  for (Point pt: points) {
			  try {
				  try {
					  val += (float)(int)((CellSimple)pt.getCell(k)).getValue();
				  } catch (ClassCastException te) {
					  try {
						  val += (float)((CellSimple)pt.getCell(k)).getValue();
					  } catch (ClassCastException te3) {}
				  }
			  } catch (NullPointerException ne) {}
		  }
		  
		  val = (float) Math.pow(val - u, 2);
		  stddev.put(k, (float) Math.sqrt(val / (numberOfPoints - 1)));
	  }
  }
  
  /**
   * normalize normalizes every numerical valuepoint in the space based on the calculated means and standard deviations.
   */
  public void normalize() {
	  for (Point pt: points) {
		  pt.normalize(mean, stddev);
	  }
  }
  
  /**
   * FindkNN finds the "cost" value for the given point using a KNN algorithm
   * with the k nearest points
   * 
   * @param targetKey		The key of the value to be found.
   * @param targetPoint		The point whose value is to be found
   * @param neighbours		The number of nearest neighbours to query
   * @param metric			The distance metric to be used
   * @param n				The order to use (only applicable when metric is "Minkowski")				
   * 
   * @return				The unknown value of the given point
   */
  public String findkNN(String targetKey, Point targetPoint, int neighbours, String metric, int n){
	  kNN calculator;
	  
	  switch (metric) {
	  		case "Manhattan":
	  			calculator = new ManhattanKNN(this);
	  			break;
	  		case "Minkowski":
	  			calculator = new MinkowskiKNN(this, n);
	  			break;
	  		case "Chebyshev":
	  			calculator = new ChebyshevKNN(this);
	  			break;
	  		default:
	  			calculator = new EuclideanKNN(this);
	  			break;
	  }
	  
	  Cell resultCell = calculator.findKNN(targetKey, targetPoint, neighbours);
	  
	  return resultCell.toString();	    
  }
  
  /**
   * Sets the mean values to the given HashMap.
   * 
   * @param newMean			HashMap containing the mean for each key.
   */
  public void setMean(ConcurrentHashMap<String, Float> newMean){
    mean = newMean;
  }

  /**
   * @return				HashMap containing the the mean values for the dataset.
   */
  public ConcurrentHashMap<String, Float> getMean(){
    return mean;
  }
  
  /**
   * Sets the standard deviation values to the given HashMap.
   * 
   * @param newDev		HashMap containing the standard deviation for each key.
   */
  public void setStdDev(ConcurrentHashMap<String, Float> newDev){
    stddev = newDev;
  }
  
  /**
   * @return				HashMap containing the the standard deviation values for the dataset.
   */
  public ConcurrentHashMap<String, Float> getStdDev(){
    return stddev;
  }

  /**
   * Sets the summed values to the given HashMap.
   * 
   * @param newSum		HashMap containing the sum for each key.
   */
  public void setSum(ConcurrentHashMap<String, Float> newSum){
    sum = newSum;
  }

  /**
   * @return				HashMap containing the the summed values for the dataset.
   */
  public ConcurrentHashMap<String, Float> getSum(){
    return sum;
  }

  
  /**
   * Sets the space's points to the given ArrayList
   * 
   * @param pts				ArrayList of the points to be used for the space.
   */
  public void setPoints(ArrayList<Point> pts){
    points = pts;
    numberOfPoints = pts.size();
  }
  
  /**
   * @return				The ArrayList of Points in the space.
   */
  public ArrayList<Point> getPoints(){
    return points;
  }
  
  
  /**
   * @return				The current number of points in the space.
   */
  public int getNumberOfPoints() {
	  return numberOfPoints;
  }

  public void addPoint(Point x){
        points.add(x);
        numberOfPoints = points.size();
        view.updateDisplay(x);
    }

  public int cellTypeComp(String key){
        int x = 0;
        String[] keys = cellTypes.keySet().toArray(new String[0]);

        for (String i:keys) {
            if(i.contains(key)){
                x++;
            }

        }
        return x;
    }

   public void setView(View view)
    {
    	this.view = view;
    }

     public boolean cellTypesLessThanTwo(){
        if(numberOfFields <2){
            return true;
        }
        return false;
    }

    public void setSingleCellType(String key, String type){

        if(!key.contains(".")){

            numberOfFields++;
        }

        cellTypes.put(key, type);
    }

    /**
     *  Sets all the Cell Types
     * @param types
     */
    public void setCellTypes(HashMap<String, String> types){
        cellTypes = types;
    }

    /**
     * Returns all the hashmap cell types
     * @return
     */
    public HashMap<String, String> getCellTypes(){
        return cellTypes;
    }
    
    /**
     * Returns an ArrayList of the names of the distance metrics available
     * @return
     */
    public ArrayList<String> getDistanceMetrics(){
    	return distanceMetrics;
    }
    
    /**
     * Reinitialize the test statistics to 0 sucesses and 0 failures
     */
    public void initTestStats()
    {
    	testSuccess = 0;
    	testFailure = 0;
    }
    
    /**
     * Checks the actual test result with an expected String, and increments testSuccess or testFailure accordingly
     * @param actual: String
     * @param expected: String
     */
    public void addTest(String actual, String expected)
    {
    	if(actual.equals(expected))
    	{
    		testSuccess++;
    	}
    	else
    	{
    		testFailure++;
    	}
    }
    
    /**
     * Checks the actual test result with an expected String, and increments testSuccess or testFailure accordingly
     * @param actual: int
     * @param expected: String
     */
    public void addTest(int actual, String expected)
    {
    	String actualString = Integer.toString(actual);
    	if(actualString.equals(expected))
    	{
    		testSuccess++;
    	}
    	else
    	{
    		testFailure++;
    	}
    }
    
    /**
     * Checks the actual test result with an expected String, and increments testSuccess or testFailure accordingly
     * @param actual: float
     * @param expected: String
     */
    public void addTest(float actual, String expected)
    {
    	String actualString = Float.toString(actual);
    	if(actualString.equals(expected))
    	{
    		testSuccess++;
    	}
    	else
    	{
    		testFailure++;
    	}
    }
    
    /**
     * Displays final test rate to the view
     */
    public void getSuccessRate()
    {
    	view.showSuccessRate(testSuccess, testFailure);
    }
    
    public void addDistanceMetric(String featureName, String metric)
    {
    	cellDistanceMetrics.put(featureName, metric);
    }
    
    public String[] getNumberMetrics()
    {
    	return numberMetrics;
    }
    
    public String[] getStringMetrics()
    {
    	return stringMetrics;
    }
    
    /**
     * Loads the tabulated data provided by Prof. Esfandiari into the model, and displays it to the view.
     */
	public void presetTestData() {
		setSingleCellType("Ball","comp");
    	setSingleCellType("Ball.Distance","float");
    	setSingleCellType("Ball.Direction","int");
    	setSingleCellType("Goal","comp");
    	setSingleCellType("Goal.Distance","float");
    	setSingleCellType("Goal.Direction","int");
    	setSingleCellType("FCT","comp");
    	setSingleCellType("FCT.Distance","float");
    	setSingleCellType("FCT.Direction","int");
    	setSingleCellType("FCB","comp");
    	setSingleCellType("FCB.Distance","float");
    	setSingleCellType("FCB.Direction","int");
    	setSingleCellType("Action","String");
    	view.enableDataInput(true);
    	
    	Point p1 = new Point();
    	CellSimple<Float> c11 = new CellSimple<Float>("Ball.Distance", (float) 1.9);
    	CellSimple<Integer> c12 = new CellSimple<Integer>("Ball.Direction", -167);
    	CellSimple<Float> c13 = new CellSimple<Float>("Goal.Distance", (float) 63.8);
    	CellSimple<Integer> c14 = new CellSimple<Integer>("Goal.Direction", 31);
    	CellSimple<Float> c15 = new CellSimple<Float>("FCT.Distance", (float) 39.1);
    	CellSimple<Integer>c16 = new CellSimple<Integer>("FCT.Direction", -41);
    	CellSimple<Float> c17 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c18 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c19 = new CellSimple<String>("Action", "Kick");
    	p1.addAttribute(c11);
    	p1.addAttribute(c12);
    	p1.addAttribute(c13);
    	p1.addAttribute(c14);
    	p1.addAttribute(c15);
    	p1.addAttribute(c16);
    	p1.addAttribute(c17);
    	p1.addAttribute(c18);
    	p1.addAttribute(c19);
    	view.setUpFeatures(p1);
    	addPoint(p1);
    	
    	
    	Point p2 = new Point();
    	CellSimple<Float> c21 = new CellSimple<Float>("Ball.Distance", (float)1.9);
    	CellSimple<Integer> c22 = new CellSimple<Integer>("Ball.Direction", 50);
    	CellSimple<Float> c23 = new CellSimple<Float>("Goal.Distance", (float)63.8);
    	CellSimple<Integer> c24 = new CellSimple<Integer>("Goal.Direction", 31);
    	CellSimple<Float> c25 = new CellSimple<Float>("FCT.Distance", (float)39.1);
    	CellSimple<Integer> c26 = new CellSimple<Integer>("FCT.Direction", -41);
    	CellSimple<Float> c27 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c28 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c29 = new CellSimple<String>("Action", "Kick");
    	p2.addAttribute(c21);
    	p2.addAttribute(c22);
    	p2.addAttribute(c23);
    	p2.addAttribute(c24);
    	p2.addAttribute(c25);
    	p2.addAttribute(c26);
    	p2.addAttribute(c27);
    	p2.addAttribute(c28);
    	p2.addAttribute(c29);
    	addPoint(p2);
    	
    	Point p3 = new Point();
    	CellSimple<Float> c31 = new CellSimple<Float>("Ball.Distance", (float) 1.8);
    	CellSimple<Integer> c32 = new CellSimple<Integer>("Ball.Direction", 2);
    	CellSimple<Float> c33 = new CellSimple<Float>("Goal.Distance",(float) 61.9);
    	CellSimple<Integer> c34 = new CellSimple<Integer>("Goal.Direction", 31);
    	CellSimple<Float> c35 = new CellSimple<Float>("FCT.Distance", (float)39.1);
    	CellSimple<Integer> c36 = new CellSimple<Integer>("FCT.Direction", -41);
    	CellSimple<Float> c37 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c38 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c39 = new CellSimple<String>("Action", "Kick");
    	p3.addAttribute(c31);
    	p3.addAttribute(c32);
    	p3.addAttribute(c33);
    	p3.addAttribute(c34);
    	p3.addAttribute(c35);
    	p3.addAttribute(c36);
    	p3.addAttribute(c37);
    	p3.addAttribute(c38);
    	p3.addAttribute(c39);
    	addPoint(p3);
    	
    	
    	Point p4 = new Point();
    	CellSimple<Float> c41 = new CellSimple<Float>("Ball.Distance",(float) 1.8);
    	CellSimple<Integer> c42 = new CellSimple<Integer>("Ball.Direction", -85);
    	CellSimple<Float> c43 = new CellSimple<Float>("Goal.Distance",(float) 53.5);
    	CellSimple<Integer> c44 = new CellSimple<Integer>("Goal.Direction", -4);
    	CellSimple<Float> c45 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c46 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c47 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c48 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c49 = new CellSimple<String>("Action", "Kick");
    	p4.addAttribute(c41);
    	p4.addAttribute(c42);
    	p4.addAttribute(c43);
    	p4.addAttribute(c44);
    	p4.addAttribute(c45);
    	p4.addAttribute(c46);
    	p4.addAttribute(c47);
    	p4.addAttribute(c48);
    	p4.addAttribute(c49);
    	addPoint(p4);
    	
    	Point p5 = new Point();
    	CellSimple<Float> c51 = new CellSimple<Float>("Ball.Distance", (float)19.2);
    	CellSimple<Integer> c52 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c53 = new CellSimple<Float>("Goal.Distance", (float)24.6);
    	CellSimple<Integer> c54 = new CellSimple<Integer>("Goal.Direction", -17);
    	CellSimple<Float> c55 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c56 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c57 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c58 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c59 = new CellSimple<String>("Action", "Dash");
    	p5.addAttribute(c51);
    	p5.addAttribute(c52);
    	p5.addAttribute(c53);
    	p5.addAttribute(c54);
    	p5.addAttribute(c55);
    	p5.addAttribute(c56);
    	p5.addAttribute(c57);
    	p5.addAttribute(c58);
    	p5.addAttribute(c59);
    	addPoint(p5);
    	
    	Point p6 = new Point();
    	CellSimple<Float> c61 = new CellSimple<Float>("Ball.Distance", (float)15.9);
    	CellSimple<Integer> c62 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c63 = new CellSimple<Float>("Goal.Distance", (float)22.3);
    	CellSimple<Integer> c64 = new CellSimple<Integer>("Goal.Direction", -18);
    	CellSimple<Float> c65 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c66 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c67 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c68 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c69 = new CellSimple<String>("Action", "Dash");
    	p6.addAttribute(c61);
    	p6.addAttribute(c62);
    	p6.addAttribute(c63);
    	p6.addAttribute(c64);
    	p6.addAttribute(c65);
    	p6.addAttribute(c66);
    	p6.addAttribute(c67);
    	p6.addAttribute(c68);
    	p6.addAttribute(c69);
    	addPoint(p6);
    	
    	Point p7 = new Point();
    	CellSimple<Float> c71 = new CellSimple<Float>("Ball.Distance", (float)14.5);
    	CellSimple<Integer> c72 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c73 = new CellSimple<Float>("Goal.Distance", (float)20.7);
    	CellSimple<Integer> c74 = new CellSimple<Integer>("Goal.Direction", -20);
    	CellSimple<Float> c75 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c76 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c77 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c78 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c79 = new CellSimple<String>("Action", "Dash");
    	p7.addAttribute(c71);
    	p7.addAttribute(c72);
    	p7.addAttribute(c73);
    	p7.addAttribute(c74);
    	p7.addAttribute(c75);
    	p7.addAttribute(c76);
    	p7.addAttribute(c77);
    	p7.addAttribute(c78);
    	p7.addAttribute(c79);
    	addPoint(p7);
    	
    	Point p8 = new Point();
    	CellSimple<Float> c81 = new CellSimple<Float>("Ball.Distance", (float)11);
    	CellSimple<Integer> c82 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c83 = new CellSimple<Float>("Goal.Distance", null);
    	CellSimple<Integer> c84 = new CellSimple<Integer>("Goal.Direction", null);
    	CellSimple<Float> c85 = new CellSimple<Float>("FCT.Distance", (float)44.8);
    	CellSimple<Integer> c86 = new CellSimple<Integer>("FCT.Direction",-5);
    	CellSimple<Float> c87 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c88 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c89 = new CellSimple<String>("Action", "Dash");
    	p8.addAttribute(c81);
    	p8.addAttribute(c82);
    	p8.addAttribute(c83);
    	p8.addAttribute(c84);
    	p8.addAttribute(c85);
    	p8.addAttribute(c86);
    	p8.addAttribute(c87);
    	p8.addAttribute(c88);
    	p8.addAttribute(c89);
    	addPoint(p8);
    	
    	Point p9 = new Point();
    	CellSimple<Float> c91 = new CellSimple<Float>("Ball.Distance", (float)45.7);
    	CellSimple<Integer> c92 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c93 = new CellSimple<Float>("Goal.Distance", (float)96.6);
    	CellSimple<Integer> c94 = new CellSimple<Integer>("Goal.Direction", 2);
    	CellSimple<Float> c95 = new CellSimple<Float>("FCT.Distance", (float)55.6);
    	CellSimple<Integer> c96 = new CellSimple<Integer>("FCT.Direction",-37);
    	CellSimple<Float> c97 = new CellSimple<Float>("FCB.Distance", (float)55.6);
    	CellSimple<Integer> c98 = new CellSimple<Integer>("FCB.Direction", 40);
    	CellSimple<String> c99 = new CellSimple<String>("Action", "Dash");
    	p9.addAttribute(c91);
    	p9.addAttribute(c92);
    	p9.addAttribute(c93);
    	p9.addAttribute(c94);
    	p9.addAttribute(c95);
    	p9.addAttribute(c96);
    	p9.addAttribute(c97);
    	p9.addAttribute(c98);
    	p9.addAttribute(c99);
    	addPoint(p9);
    	
    	Point p10 = new Point();
    	CellSimple<Float> c101 = new CellSimple<Float>("Ball.Distance", (float)50.4);
    	CellSimple<Integer> c102 = new CellSimple<Integer>("Ball.Direction", -1);
    	CellSimple<Float> c103 = new CellSimple<Float>("Goal.Distance", (float)101.5);
    	CellSimple<Integer> c104 = new CellSimple<Integer>("Goal.Direction", 14);
    	CellSimple<Float> c105 = new CellSimple<Float>("FCT.Distance", (float)75.4);
    	CellSimple<Integer> c106 = new CellSimple<Integer>("FCT.Direction",-24);
    	CellSimple<Float> c107 = new CellSimple<Float>("FCB.Distance", (float)46.2);
    	CellSimple<Integer> c108 = new CellSimple<Integer>("FCB.Direction", 40);
    	CellSimple<String> c109 = new CellSimple<String>("Action", "Turn");
    	p10.addAttribute(c101);
    	p10.addAttribute(c102);
    	p10.addAttribute(c103);
    	p10.addAttribute(c104);
    	p10.addAttribute(c105);
    	p10.addAttribute(c106);
    	p10.addAttribute(c107);
    	p10.addAttribute(c108);
    	p10.addAttribute(c109);
    	addPoint(p10);
    	
    	Point p11 = new Point();
    	CellSimple<Float> c111 = new CellSimple<Float>("Ball.Distance", (float)41.4);
    	CellSimple<Integer> c112 = new CellSimple<Integer>("Ball.Direction", 0);
    	CellSimple<Float> c113 = new CellSimple<Float>("Goal.Distance", (float)90.1);
    	CellSimple<Integer> c114 = new CellSimple<Integer>("Goal.Direction", 18);
    	CellSimple<Float> c115 = new CellSimple<Float>("FCT.Distance", (float)65.1);
    	CellSimple<Integer> c116 = new CellSimple<Integer>("FCT.Direction",-27);
    	CellSimple<Float> c117 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c118 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c119 = new CellSimple<String>("Action", "Turn");
    	p11.addAttribute(c111);
    	p11.addAttribute(c112);
    	p11.addAttribute(c113);
    	p11.addAttribute(c114);
    	p11.addAttribute(c115);
    	p11.addAttribute(c116);
    	p11.addAttribute(c117);
    	p11.addAttribute(c118);
    	p11.addAttribute(c119);
    	addPoint(p11);
    	
    	Point p12 = new Point();
    	CellSimple<Float> c121 = new CellSimple<Float>("Ball.Distance", (float)14.5);
    	CellSimple<Integer> c122 = new CellSimple<Integer>("Ball.Direction", 15);
    	CellSimple<Float> c123 = new CellSimple<Float>("Goal.Distance", (float)60.1);
    	CellSimple<Integer> c124 = new CellSimple<Integer>("Goal.Direction", 27);
    	CellSimple<Float> c125 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c126 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c127 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c128 = new CellSimple("FCB.Direction", null);
    	CellSimple<String> c129 = new CellSimple<String>("Action", "Turn");
    	p12.addAttribute(c121);
    	p12.addAttribute(c122);
    	p12.addAttribute(c123);
    	p12.addAttribute(c124);
    	p12.addAttribute(c125);
    	p12.addAttribute(c126);
    	p12.addAttribute(c127);
    	p12.addAttribute(c128);
    	p12.addAttribute(c129);
    	addPoint(p12);
    	
    	Point p13 = new Point();
    	CellSimple<Float> c131 = new CellSimple<Float>("Ball.Distance", (float)41.4);
    	CellSimple<Integer> c132 = new CellSimple<Integer>("Ball.Direction", 3);
    	CellSimple<Float> c133 = new CellSimple<Float>("Goal.Distance", (float)94.7);
    	CellSimple<Integer> c134 = new CellSimple<Integer>("Goal.Direction", 4);
    	CellSimple<Float> c135 = new CellSimple<Float>("FCT.Distance", (float)55.1);
    	CellSimple<Integer> c136 = new CellSimple<Integer>("FCT.Direction",-36);
    	CellSimple<Float> c137 = new CellSimple<Float>("FCB.Distance", (float)53.5);
    	CellSimple<Integer> c138 = new CellSimple<Integer>("FCB.Direction", 43);
    	CellSimple<String> c139 = new CellSimple<String>("Action", "Turn");
    	p13.addAttribute(c131);
    	p13.addAttribute(c132);
    	p13.addAttribute(c133);
    	p13.addAttribute(c134);
    	p13.addAttribute(c135);
    	p13.addAttribute(c136);
    	p13.addAttribute(c137);
    	p13.addAttribute(c138);
    	p13.addAttribute(c139);
    	addPoint(p13);
    	
    	Point p14 = new Point();
    	CellSimple<Float> c141 = new CellSimple<Float>("Ball.Distance", (float)23.2);
    	CellSimple<Integer> c142 = new CellSimple<Integer>("Ball.Direction", 0);
    	CellSimple<Float> c143 = new CellSimple<Float>("Goal.Distance", (float)76.9);
    	CellSimple<Integer> c144 = new CellSimple<Integer>("Goal.Direction", 2);
    	CellSimple<Float> c145 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c146 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c147 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c148 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c149 = new CellSimple<String>("Action", "Turn");
    	p14.addAttribute(c141);
    	p14.addAttribute(c142);
    	p14.addAttribute(c143);
    	p14.addAttribute(c144);
    	p14.addAttribute(c145);
    	p14.addAttribute(c146);
    	p14.addAttribute(c147);
    	p14.addAttribute(c148);
    	p14.addAttribute(c149);
    	addPoint(p14);
    	
    	Point p15 = new Point();
    	CellSimple<Float> c151 = new CellSimple<Float>("Ball.Distance", (float)12);
    	CellSimple<Integer> c152 = new CellSimple<Integer>("Ball.Direction", 24);
    	CellSimple<Float> c153 = new CellSimple<Float>("Goal.Distance", null);
    	CellSimple<Integer> c154 = new CellSimple<Integer>("Goal.Direction", null);
    	CellSimple<Float> c155 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c156 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c157 = new CellSimple<Float>("FCB.Distance", (float)42.7);
    	CellSimple<Integer> c158 = new CellSimple<Integer>("FCB.Direction", -40);
    	CellSimple<String> c159 = new CellSimple<String>("Action", "Turn");
    	p15.addAttribute(c151);
    	p15.addAttribute(c152);
    	p15.addAttribute(c153);
    	p15.addAttribute(c154);
    	p15.addAttribute(c155);
    	p15.addAttribute(c156);
    	p15.addAttribute(c157);
    	p15.addAttribute(c158);
    	p15.addAttribute(c159);
    	addPoint(p15);
    	
    	Point p16 = new Point();
    	CellSimple<Float> c161 = new CellSimple<Float>("Ball.Distance", null);
    	CellSimple<Integer> c162 = new CellSimple<Integer>("Ball.Direction", null);
    	CellSimple<Float> c163 = new CellSimple<Float>("Goal.Distance", (float)26.3);
    	CellSimple<Integer> c164 = new CellSimple<Integer>("Goal.Direction", 2);
    	CellSimple<Float> c165 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c166 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c167 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c168 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c169 = new CellSimple<String>("Action", "Turn");
    	p16.addAttribute(c161);
    	p16.addAttribute(c162);
    	p16.addAttribute(c163);
    	p16.addAttribute(c164);
    	p16.addAttribute(c165);
    	p16.addAttribute(c166);
    	p16.addAttribute(c167);
    	p16.addAttribute(c168);
    	p16.addAttribute(c169);
    	addPoint(p16);
    	
    	Point p17 = new Point();
    	CellSimple<Float> c171 = new CellSimple<Float>("Ball.Distance", (float)3.5);
    	CellSimple<Integer> c172 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c173 = new CellSimple<Float>("Goal.Distance", (float)56.1);
    	CellSimple<Integer> c174 = new CellSimple<Integer>("Goal.Direction", 4);
    	CellSimple<Float> c175 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c176 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c177 = new CellSimple<Float>("FCB.Distance", null);
    	CellSimple<Integer> c178 = new CellSimple<Integer>("FCB.Direction", null);
    	CellSimple<String> c179 = new CellSimple<String>("Action", "Dash");
    	p17.addAttribute(c171);
    	p17.addAttribute(c172);
    	p17.addAttribute(c173);
    	p17.addAttribute(c174);
    	p17.addAttribute(c175);
    	p17.addAttribute(c176);
    	p17.addAttribute(c177);
    	p17.addAttribute(c178);
    	p17.addAttribute(c169);
    	addPoint(p17);
    	
    	Point p18 = new Point();
    	CellSimple<Float> c181 = new CellSimple<Float>("Ball.Distance", (float)10);
    	CellSimple<Integer> c182 = new CellSimple<Integer>("Ball.Direction", 1);
    	CellSimple<Float> c183 = new CellSimple<Float>("Goal.Distance", (float)61.3);
    	CellSimple<Integer> c184 = new CellSimple<Integer>("Goal.Direction", -31);
    	CellSimple<Float> c185 = new CellSimple<Float>("FCT.Distance", null);
    	CellSimple<Integer> c186 = new CellSimple<Integer>("FCT.Direction",null);
    	CellSimple<Float> c187 = new CellSimple<Float>("FCB.Distance", (float)41.4);
    	CellSimple<Integer> c188 = new CellSimple<Integer>("FCB.Direction", 43);
    	CellSimple<String> c189 = new CellSimple<String>("Action", "Dash");
    	p18.addAttribute(c181);
    	p18.addAttribute(c182);
    	p18.addAttribute(c183);
    	p18.addAttribute(c184);
    	p18.addAttribute(c185);
    	p18.addAttribute(c186);
    	p18.addAttribute(c187);
    	p18.addAttribute(c188);
    	p18.addAttribute(c189);
    	addPoint(p18);
    	
    	view.enableTesting(true);
    	view.enableNewDataSet(false);
	}

}
