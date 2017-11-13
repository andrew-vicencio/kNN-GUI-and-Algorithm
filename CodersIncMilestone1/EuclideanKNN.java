package CodersInc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EuclideanKNN extends kNN {
	
	public EuclideanKNN(DimensionalSpace ds) {
		super(ds);
	}

	/**
	   * findKNN finds the "cost" value for the given point using a euclidean KNN algorithm
	   * with the k nearest points
	   * 
	   * @param targetKey		The key to be found
	   * @param targetPoint		The point whose value is to be found
	   * @param neighbours		The number of nearest neighbours to query
	   * 
	   * @return				The unknown value of the given point
	   */
	public Cell findKNN(String targetKey, Point targetPoint, int neighbours) {
	  	
		NumericalDistance nDist = new NumericalDistance();
		StringDistance sDist = new StringDistance();
		
		ArrayList<Point> points = ds.getPoints();
		
		if (neighbours <= 0) neighbours = 1;
	    if (neighbours > points.size()) neighbours = points.size();
		
	    ArrayList<Tuple<Float, Cell>> closestKNeighbours = new ArrayList<Tuple<Float, Cell>>(neighbours);
		Tuple<Float, Cell> displacedPoint, placeHolder;
		float distance;
		boolean displacement;
		HashMap<String, Cell> currentPtValues;
		HashMap<String, Cell> targetPtValues = targetPoint.getStdValues();
		
		for (int i = 0; i < neighbours; i++) {
			closestKNeighbours = null;
		}
		
		for (Point pt: points) {
			if (!(pt.equals(targetPoint))) {
				displacement = false;
				displacedPoint = null;
				distance = 0;
				currentPtValues = pt.getStdValues();
				
				for (String key: currentPtValues.keySet()) {
					if (!(key.equals(targetKey))) {
						if (currentPtValues.get(key) instanceof SimpleCell) {
							if (((SimpleCell)currentPtValues.get(key)).getValue() instanceof String) {
								distance += sDist.calcDistance((SimpleCell)targetPtValues.get(key), (SimpleCell)currentPtValues.get(key));
							} else {
								distance += nDist.calcDistance((SimpleCell)targetPtValues.get(key), (SimpleCell)currentPtValues.get(key));
							}
						} else {
							// Composite
						}
					}
				}
				
				distance = (float)Math.sqrt(distance);
				
				for (int i = 0; i < neighbours; i ++) {
					if (!displacement) {
						// If the current spot has not been used yet
						if (closestKNeighbours.get(i).getValue2() == null) {
							closestKNeighbours.set(i, new Tuple<Float, Cell>(distance, pt.getCell(targetKey)));
							break;
							
						// If the current point is displacing other point(s)
						} else if (distance < closestKNeighbours.get(i).getValue1()) {
							displacedPoint = closestKNeighbours.get(i);
							closestKNeighbours.set(i, new Tuple<Float, Cell>(distance, pt.getCell(targetKey)));
							displacement = true;
						}
					} else {
						// If the current spot has not been used yet
						if (closestKNeighbours.get(i).getValue2() == null) {
							closestKNeighbours.set(i, new Tuple<Float, Cell>(distance, pt.getCell(targetKey)));
							break;
							
						// If the current point is displacing other point(s)
						} else if (distance < closestKNeighbours.get(i).getValue1()) {
							placeHolder = closestKNeighbours.get(i);
							closestKNeighbours.set(i, displacedPoint);
							displacedPoint = placeHolder;
						}
					}
				}
			}
		}
		
		return findValue(closestKNeighbours, targetKey);
	}

}
