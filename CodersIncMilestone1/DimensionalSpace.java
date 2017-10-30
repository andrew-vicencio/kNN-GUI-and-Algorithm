package CodersIncMilestone1;
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
  private ConcurrentHashMap<String, Integer> mean;
  private ConcurrentHashMap<String, Integer> stddev;
  private ConcurrentHashMap<String, Integer> sum;
  private int numberOfPoints;
  private ArrayList<Point> points;

  
  /**
   * Constructor for instances of the DimensionalSpace class. Initializes the class variables and sets the
   * current number of points to 0.
   */
  public DimensionalSpace(){
    mean = new ConcurrentHashMap<String, Integer>();
    stddev = new ConcurrentHashMap<String, Integer>();
    sum = new ConcurrentHashMap<String, Integer>();
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
    Iterator<Point> pair = pts.iterator();
    while(pair.hasNext()){
      addPt(pair.next());
    }
  }
  
  
  /**
   * Adds the given Point to the DimensionalSpace's list of points. Also adds the values from each of the
   * point's "coordinates" to the corresponding sum value.
   * 
   * @param pt 			The Point to be added to the space.
   */
  public void addPt(Point pt){
    Integer val;
    Set<String> attr = pt.getAttributes();
    if(points != null){
      points.add(pt);
    } else {
      System.out.println("Points not initialized");
    }
    for (String key : attr){
      val = pt.getValue(key);
      if(!sum.containsKey(key)){
        sum.put(key, val);
      } else {
        sum.replace(key, sum.get(key) + val);
      }
    }
    numberOfPoints++;
  }
  
  
  /**
   * Finds the average value for each of the values wich define the points.
   */
  public void findMean(){
    for (String key: sum.keySet()) {
      mean.put(key, sum.get(key) / numberOfPoints);
    }
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
  public int findkNN(Point targetPoint, int k){
	  	
	    assert(k > 0);
	    if (k > points.size()) k = points.size();
	    
	    targetPoint.standardise(mean, numberOfPoints);
		
	    // Initialize required instance variables
		HashMap<String, Integer> targetValues = targetPoint.getStdValues();
		HashMap<String, Integer> currentPtValues;
		HashMap<Integer, Tuple<Integer, Integer>> closestKNeighbours = new HashMap<Integer, Tuple<Integer, Integer>>();
		int distance, targetCost;
		boolean distValueDisplacement;
		Tuple<Integer, Integer> displacedPoint, placeHolder;
		
		// Initialize an empty hashmap to hold the closest neighbours
		for (int i = 1; i <= k; i++) {
			closestKNeighbours.put(i, new Tuple<Integer, Integer>(-1, -1));
		}
		
		// For each of the given points in the dataset, calculate the distance from the target point
		for (Point pt: points) {
			
			// Reset the instance variables for each point
			distValueDisplacement = false;
			displacedPoint = null;
			distance = 0;
			currentPtValues = pt.getStdValues();
		    
			// Factor in every value to the distance
			for(String key: currentPtValues.keySet()) {
				distance += (int) Math.pow((currentPtValues.get(key) - targetValues.get(key)), 2);
			}
			
			// Complete the Euclidean distance calculation
			distance = (int) Math.sqrt(distance);
			
			// Add the point to the nearest neighbours list in the corresponding location if applicable
			for (int i = 1; i <= k; i++) {
				
				// If entries are not being displaced, use the current point's distance and cost
				if (!distValueDisplacement) {
					
					// If the current spot has not been used yet
					if (closestKNeighbours.get(i).getValue2() == -1) {
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>(pt.getPointValue(), distance));
						break;
						
					// If the current point is displacing other point(s)
					} else if (distance < closestKNeighbours.get(i).getValue2()) {
						displacedPoint = closestKNeighbours.get(i);
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>(pt.getPointValue(), distance));
						distValueDisplacement = true;
						
					// If there's a tie and the last list spot has been reached
					} else if (distance == closestKNeighbours.get(i).getValue2() && i == k) {
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>((pt.getPointValue() + closestKNeighbours.get(i).getValue1()) / 2, distance));
					}
					
				// If entries are being displaced
				} else {
					
					// If the current spot has not yet been used
					if (closestKNeighbours.get(i).getValue2() == -1) {
						closestKNeighbours.replace(i, displacedPoint);
						break;
						
					// If the current displaced point is displacing other point(s)
					} else if (distance < closestKNeighbours.get(i).getValue2()) {
						placeHolder = closestKNeighbours.get(i);
						closestKNeighbours.replace(i, displacedPoint);
						displacedPoint = placeHolder;
						distValueDisplacement = true;
					
					// If there's a tie and the last list spot has been reached
					} else if (distance == closestKNeighbours.get(i).getValue2() && i == k) {
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>((displacedPoint.getValue1() + closestKNeighbours.get(i).getValue1()) / 2, distance));
					}
				}
			}
		}
		
		// Initialize the target cost
		targetCost = 0;
		
		// Return the average cost of the nearest neighbour(s) as the target point's cost
		for (int i = 1; i <= k; i++) {
			targetCost += closestKNeighbours.get(i).getValue1();
		}
		
		targetPoint.setPointValue(targetCost / k);
	    return targetPoint.getPointValue();
  }
  
  /**
   * Sets the mean values to the given HashMap.
   * 
   * @param newMean			HashMap containing the mean for each key.
   */
  public void setMean(ConcurrentHashMap<String, Integer> newMean){
    mean = newMean;
  }

  /**
   * @return				HashMap containing the the mean values for the dataset.
   */
  public ConcurrentHashMap<String, Integer> getMean(){
    return mean;
  }
  
  /**
   * Sets the standard deviation values to the given HashMap.
   * 
   * @param newDev		HashMap containing the standard deviation for each key.
   */
  public void setStdDev(ConcurrentHashMap<String, Integer> newDev){
    stddev = newDev;
  }
  
  /**
   * @return				HashMap containing the the standard deviation values for the dataset.
   */
  public ConcurrentHashMap<String, Integer> getStdDev(){
    return stddev;
  }

  /**
   * Sets the summed values to the given HashMap.
   * 
   * @param newSum		HashMap containing the sum for each key.
   */
  public void setSum(ConcurrentHashMap<String, Integer> newSum){
    sum = newSum;
  }

  /**
   * @return				HashMap containing the the summed values for the dataset.
   */
  public ConcurrentHashMap<String, Integer> getSum(){
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
  
  /**
   * Simple Tuple class to hold 2 ordered values for use in the findKNN function.
   * 
   * @author Darren
   *
   * @param <E>	Type of the first value.
   * @param <K>	Type of the second value
   */
  private class Tuple<E, K> {
	private E value1;
	private K value2;
	
	/**
	 * Tuple constructor.
	 * 
	 * @param val1		The first value of type E.
	 * @param val2		The second value of type K.
	 */
	public Tuple(E val1, K val2) {
		this.value1 = val1;
		this.value2 = val2;
	}

	/**
	 * @return		The first value.
	 */
	public E getValue1() {
		return value1;
	}

	/**
	 * @param x	The new value for the first value (of type E).
	 */
	public void setValue1(E x) {
		this.value1 = x;
	}
	
	/**
	 * @return		The second value.
	 */
	public K getValue2() {
		return value2;
	}
	
	/**
	 * @param x	The new value for the second value (of type K).
	 */
	public void setValue2(K x) {
		this.value2 = x;
	}
  }
}
