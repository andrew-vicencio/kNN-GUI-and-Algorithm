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
  private int numberOfPoints;
    private int numberOfFields;
  private ArrayList<Point> points;
  private View view;
  
  /**
   * Constructor for instances of the DataModel.DimensionalSpace class. Initializes the class variables and sets the
   * current number of points to 0.
   */
  public DimensionalSpace(){
    mean = new ConcurrentHashMap<String, Float>();
    stddev = new ConcurrentHashMap<String, Float>();
    sum = new ConcurrentHashMap<String, Float>();
    points = new ArrayList<Point>();
    numberOfPoints = 0;
    numberOfFields = 0;
    cellTypes = new HashMap<String, String>();
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
  
  /**
   * Increments the sum for all of the numeric values contained within a DataModel.CompositeCell
   * 
   * @param c		The DataModel.CompositeCell to be used to increment the corresponding sums.
   */
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

  /**
   * Increments the sum for the key corresponding to a numeric DataModel.SimpleCell
   * 
   * @param c		The DataModel.SimpleCell to use to increment the sum
   */
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
		  if (((SimpleCell)points.get(0).getCell(k)).getValue() instanceof Integer) {
			  for (Point pt: points) {
				  val += Math.pow(((float)(int)((SimpleCell)pt.getCell(k)).getValue()) - u, 2);
			  }
		  } else {
			  for (Point pt: points) {
				  val += Math.pow(((float)((SimpleCell)pt.getCell(k)).getValue()) - u, 2);
			  }
		  }
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

    //TODO: DOCUMENT
    public void setCellTypes(HashMap<String, String> types){
        cellTypes = types;
    }

    //TODO: DOCUMENT
    public HashMap<String, String> getCellTypes(){
        return cellTypes;
    }

}
