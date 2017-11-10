import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EuclideanKNN implements kNN {
	
	DimensionalSpace ds;
	
	public EuclideanKNN(DimensionalSpace ds) {
		this.ds = ds;
	}

	/**
	   * FindkNN finds the "cost" value for the given point using a KNN algorithm
	   * with the k nearest points
	   * 
	   * @param targetKey		The key to be found
	   * @param targetPoint		The point whose value is to be found
	   * @param neighbours		The number of nearest neighbours to query
	   * 
	   * @return				The unknown value of the given point
	   */
	@Override
	public float findKNN(String targetKey, Point targetPoint, int neighbours) {
	  	
		ArrayList<Point> points = ds.getPoints();
		int numberOfPoints = ds.getNumberOfPoints();
		ConcurrentHashMap<String, Integer> mean = ds.getMean();
		
	    assert(neighbours > 0);
	    if (neighbours > points.size()) neighbours = points.size();
	    
	    targetPoint.standardise(mean, numberOfPoints);
		
	    // Initialize required instance variables
		HashMap<String, Integer> targetValues = targetPoint.getStdValues();
		HashMap<String, Integer> currentPtValues;
		HashMap<Integer, Tuple<Integer, Integer>> closestKNeighbours = new HashMap<Integer, Tuple<Integer, Integer>>();
		int distance, targetCost;
		boolean distValueDisplacement;
		Tuple<Integer, Integer> displacedPoint, placeHolder;
		
		// Initialize an empty hashmap to hold the closest neighbours
		for (int i = 1; i <= neighbours; i++) {
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
			for (int i = 1; i <= neighbours; i++) {
				
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
					} else if (distance == closestKNeighbours.get(i).getValue2() && i == neighbours) {
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
					} else if (distance == closestKNeighbours.get(i).getValue2() && i == neighbours) {
						closestKNeighbours.replace(i, new Tuple<Integer, Integer>((displacedPoint.getValue1() + closestKNeighbours.get(i).getValue1()) / 2, distance));
					}
				}
			}
		}
		
		// Initialize the target cost
		targetCost = 0;
		
		// Return the average cost of the nearest neighbour(s) as the target point's cost
		for (int i = 1; i <= neighbours; i++) {
			targetCost += closestKNeighbours.get(i).getValue1();
		}
		
		targetPoint.setPointValue(targetCost / neighbours);
	    return targetPoint.getPointValue();
	}

}
