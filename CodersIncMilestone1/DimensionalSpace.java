package CodersInc;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;
import java.util.Iterator;
import java.util.Set;

/**
 * Class to hold the points from the dataset. This class calculates the sum, mean, and standard 
 * deviation of all the known points which are used to standardize the points to give more accurate
 * calculations. This class also contains the K-nearest neighbour function which calculates the value
 * of a new point.
 * 
 * @author Darren and Andrew
 * @version Milestone 1
 */

public class DimensionalSpace {
  private ConcurrentHashMap<String, Float> mean;
  private ConcurrentHashMap<String, Float> stddev;
  private ConcurrentHashMap<String, Float> sum;
  private int numberOfPoints;
  private ArrayList<Point> points;

  
  /**
   * Constructor for instances of the DimensionalSpace class. Initializes the class variables and sets the
   * current number of points to 0.
   */
  public DimensionalSpace(){
    mean = new ConcurrentHashMap<String, Float>();
    stddev = new ConcurrentHashMap<String, Float>();
    sum = new ConcurrentHashMap<String, Float>();
    points = new ArrayList<Point>();
    numberOfPoints = 0;
  }
  
  
  /**
   * Adds the given ArrayList of points to the DimensionalSpace's list of points. Calls the addPt funtion
   * on each point in the ArrayList.
   * 
   * @param pts 		An ArrayList of Point objects to be added to the space.
   */
  public void addPts(ArrayList<Point> pts){
	  if (pts.size() == 0) return;
	  
	  for (Point pt: pts) {
		  addPt(pt);
	  }
  }
  
  
  /**
   * Adds the given Point to the DimensionalSpace's list of points. Also adds the values from each of the
   * point's "coordinates" to the corresponding sum value.
   * 
   * @param pt 			The Point to be added to the space.
   */
  public void addPt(Point pt){
    points.add(pt);
    numberOfPoints++;
  }
  
  public void findStatistics() {
	  findSum();
	  findMean();
	  findStdDev();
  }
  
  public void findSum() {
	  HashMap<String, Cell> cellList;
	  for (Point pt: points) {
		  cellList = pt.getRawValues();
		  for (String k: cellList.keySet()) {
			  Cell c = cellList.get(k);
			  
			  if (c instanceof SimpleCell) {
				  if (!(((SimpleCell)c).getValue() instanceof String)) {
					  calcSimpleSum((SimpleCell)c);
				  }
			  } else {
				  calcComplexSum((CompositeCell)c);
			  }
		  }
	  }
  }
  
  private void calcComplexSum(CompositeCell c) {
	  ArrayList<Cell> subCells = c.getSubCells();
	  
	  for (Cell subC: subCells) {
		  if (subC instanceof SimpleCell) {
			  if (!(((SimpleCell)subC).getValue() instanceof String)) {
				  calcSimpleSum((SimpleCell)subC);
			  }
		  } else {
			  calcComplexSum((CompositeCell)subC);
		  }
	  }
  }


  private void calcSimpleSum(SimpleCell c) {
	  float val;
	  if (c.getValue() instanceof Integer) {
		  val = (float)(int)c.getValue();
	  } else {
		  val = (float)c.getValue();
	  }
	  
	  if (sum.containsKey(c.getKey())) {
		  sum.put(c.getKey(), val + sum.get(c.getKey()));
	  } else {
		  sum.put(c.getKey(), val);
	  }
  }


  /**
   * Finds the average value for each of the values wich define the points.
   */
  public void findMean(){
      for (String key: sum.keySet()) {
    	  mean.put(key, sum.get(key) / numberOfPoints);
      }
  }
  
  public void findStdDev(){
	  
  }
  
  /**
   * FindkNN finds the "cost" value for the given point using a KNN algorithm
   * with the k nearest points
   * 
   * @param targetPoint		The point whose value is to be found
   * @param k				The number of nearest neighbours to query
   * 
   * @return				The unknown value of the given point
   */
  public float findkNN(Point targetPoint, int k){
	  	return 0;	    
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
  
//  public HashMap<String, String> getKeyTypes() {
//	  return keyTypes;
//  }
//  
//  public void setKeyTypes(HashMap<String, String> keyTypes) {
//	  this.keyTypes = keyTypes;
//  }
//  
//  public void addKeyType(String key, String type) {
//	  keyTypes.put(key, type);
//  }
}
