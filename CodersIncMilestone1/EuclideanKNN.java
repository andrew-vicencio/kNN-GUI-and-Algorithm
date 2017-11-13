package CodersInc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EuclideanKNN extends the kNN abstract method and uses an euclidean distance metric for the calculations.
 * The formula is [distance] = sqrt(sum((endPoint - origin) ^ 2)) where the sum adds the values for every dimension.
 * 
 * @author Darren
 *
 */
public class EuclideanKNN extends kNN {
	
	/**
	 * Constructor for EuclideanKNN. Takes a DimensionalSpace object that it will work in. Calls the kNN constructor.
	 * 
	 * @param ds	The DimensionalSpace the the funciton will work in.
	 */
	public EuclideanKNN(DimensionalSpace ds) {
		super(ds);
	}

	/**
	   * findKNN finds the target value for the given point using an euclidean KNN algorithm
	   * with the k nearest points. Calls the super class's findValue funtion once the nearest neighbours
	   * are found.
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
								distance += Math.pow(sDist.calcDistance((SimpleCell)targetPtValues.get(key), (SimpleCell)currentPtValues.get(key)), 2);
							} else {
								distance += Math.pow(nDist.calcDistance((SimpleCell)targetPtValues.get(key), (SimpleCell)currentPtValues.get(key)), 2);
							}
						} else {
							distance += Math.pow(EuclideanComposite((CompositeCell) currentPtValues.get(key), (CompositeCell) targetPtValues.get(key)), 2);
						}
					}
				}
				
				distance = (float) Math.sqrt(distance);
				
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
		
		Cell c = findValue(closestKNeighbours, targetKey);
		targetPoint.addAttribute(c);
		
		return c;
	}
	
	/**
	 * Iterates through all of the sub cells in a CompositeCell and calculates the distance from that CompositeCell to
	 * the target point's corresponding CompositeCell.
	 * 
	 * @param current	The CompositeCell of the non-target point
	 * @param target	The CompositeCell of the target point
	 * @return			The distance between the Cells
	 */
	public float EuclideanComposite(CompositeCell current, CompositeCell target) {
	  	
		NumericalDistance nDist = new NumericalDistance();
		StringDistance sDist = new StringDistance();
		ArrayList<Cell> subCells = current.getSubCells();
		float distance = 0;
		
		for (Cell c: subCells) {
			if (c instanceof SimpleCell) {
				if (((SimpleCell) c).getValue() instanceof String) {
					distance += Math.pow(sDist.calcDistance((SimpleCell) target.getSubCell(c.getKey()), (SimpleCell) c), 2);
				} else {
					distance += Math.pow(nDist.calcDistance((SimpleCell) target.getSubCell(c.getKey()), (SimpleCell) c), 2);
				}
			} else {
				distance += EuclideanComposite((CompositeCell) c, (CompositeCell) target.getSubCell(c.getKey()));
			}
		}
		
		return (float) Math.sqrt(distance);
	}

}
