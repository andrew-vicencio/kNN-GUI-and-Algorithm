import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;
import java.util.Iterator;

public class dimensionalSpace {
  private ConcurrentHashMap<String, Integer> mean;
  private ConcurrentHashMap<String, Integer> stddev;
  private ConcurrentHashMap<String, Integer> sum;
  private int numberOfPoints;
  private ArrayList<Point> Points;

  public dimensionalSpace(){
    mean = new ConcurrentHashMap<String, Integer>();
    stddev = new ConcurrentHashMap<String, Integer>();
    sum = new ConcurrentHashMap<String, Integer>();
    Points = new ArrayList<Point>();
    numberOfPoints = 0;
  }
  
  /* 
  * addPts takes an array of Points and sends them to addPt which
  * which adds a singular Point in at a time
  * 
  * return void
  */
  public void addPts(ArrayList<Point> pts){
    Iterator<Point> pair = pts.iterator();
    while(pair.hasNext()){
      addPt(pair.next());
    }
  }
  
  /*
  * addPt adds a singular Point to the Points array as well as
  * adding all of the Point's attribute to the sum array
  * TODO: This can be broken up into two method addPt then add sum
  */
  public void addPt(Point pt){
    Integer val;
    ArrayList<String> attr = pt.getAttributes();
    if(Points != null){
      Points.add(pt);
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
  
  /*
  * findMean goes through the sum hashmap and copies every key into
  * the mean hashmap and dividng the value of the key by the
  * numberOfPoints
  */
  private void findMean(){
    for (String key: sum.keySet()) {
      mean.put(key, sum.get(key) / numberOfPoints);
    }
  }

  /*
   * FindkNN finds the "cost" value for the given point using a KNN algorithm
   * with the k nearest points
   * 
   * @param targetPoint		The point whose value is to be found
   * @param k				The number of nearest neighbours to query
   * 
   * @return				The value of the given point
   */
  public int FindkNN(Point targetPoint, int k){
	  	
	    assert(k > 0);
	    if (k > Points.size()) k = Points.size();
	    
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
		for (Point pt: Points) {
			
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
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>(pt.getCost(), distance));
						break;
						
					// If the current point is displacing other point(s)
					} else if (distance < closestKNeighbours.get(i).getValue2()) {
						displacedPoint = closestKNeighbours.get(i);
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>(pt.getCost(), distance));
						distValueDisplacement = true;
						
					// If there's a tie and the last list spot has been reached
					} else if (distance == closestKNeighbours.get(i).getValue2() && i == k) {
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>((pt.getCost() + closestKNeighbours.get(i).getValue1()) / 2, distance));
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
		
		targetPoint.setCost(targetCost / k);
	    return targetPoint.getCost();
  }
  
  /*
  * generateStats generates the number of points and the mean
  */
  public void generateStats(){
    numberOfPoints = Points.size();
    findMean();
  }
  
  public void setMean(ConcurrentHashMap<String, Integer> newMean){
    mean = newMean;
  }

  public ConcurrentHashMap<String, Integer> getMean(){
    return mean;
  }

  public void setStdDev(ConcurrentHashMap<String, Integer> newDev){
    stddev = newDev;
  }

  public ConcurrentHashMap<String, Integer> getStdDev(){
    return stddev;
  }

  public void setSum(ConcurrentHashMap<String, Integer> newSum){
    sum = newSum;
  }

  public ConcurrentHashMap<String, Integer> getSum(){
    return sum;
  }

  public void setPoints(ArrayList<Point> pts){
    Points = pts;
  }

  public ArrayList<Point> getPoints(){
    return Points;
  }
  
  public int getNumberOfPoints() {
	  return numberOfPoints;
  }
  
  /*
   * Simple tuple class for use in the FindkNN function
   * Holds 2 values, each of a given type
   */
  private class Tuple<E, K> {
	private E value1;
	private K value2;
	
	public Tuple(E val1, K val2) {
		this.value1 = val1;
		this.value2 = val2;
	}

	public E getValue1() {
		return value1;
	}

	public void setValue1(E value1) {
		this.value1 = value1;
	}

	public K getValue2() {
		return value2;
	}

	public void setValue2(K value2) {
		this.value2 = value2;
	}
  }

  public static void main(String[] args){
    Point h1 = new Point(500000);
    h1.addAttribute("coordinate x", 12);
    h1.addAttribute("coordinate y", 25);
    h1.addAttribute("sq. ft.", 1200);
    h1.addAttribute("age", 2);
    Point h2 = new Point(300000);
    h2.addAttribute("coordinate x", 10);
    h2.addAttribute("coordinate y", 50);
    h2.addAttribute("sq. ft.", 1000);
    h2.addAttribute("age", 1);
    Point h3 = new Point(400000);
    h3.addAttribute("coordinate x", 30);
    h3.addAttribute("coordinate y", 100);
    h3.addAttribute("sq. ft.", 800);
    h3.addAttribute("age", 2);
    dimensionalSpace DS = new dimensionalSpace();
    ArrayList<Point> Pts = new ArrayList<Point>();
    if(Pts.add(h1)){
      System.out.println("h1 added");
    }
    if(Pts.add(h2)){
      System.out.println("h2 added");
    }
    if(Pts.add(h3)){
      System.out.println("h3 added");
    }
    // DS.addPt(h1);
    DS.addPts(Pts);
    
    DS.findMean();
    
    for (Point pt: DS.getPoints()) {
    	pt.standardise(DS.getMean(), DS.getNumberOfPoints());
    }
    
    Point t1 = new Point();
    t1.addAttribute("coordinate x", 15);
    t1.addAttribute("coordinate y", 20);
    t1.addAttribute("sq. ft.", 1500);
    t1.addAttribute("age", 1);
    System.out.println(DS.FindkNN(t1, 3));
  }
  
}
