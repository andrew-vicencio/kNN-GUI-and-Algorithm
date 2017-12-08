package Maths;

import java.util.ArrayList;
import java.util.HashMap;

import DataModel.Cell;
import DataModel.CellComposite;
import DataModel.DimensionalSpace;
import DataModel.Point;
import DataModel.CellSimple;
import Maths.kNN.Tuple;

/**
 * Maths.ChebyshevKNN extends the Maths.kNN abstract method and uses a Chebyshev distance metric for the calculations.
 * The formula is [distance] = MAX(endPoint - origin). In other words it takes the most significant difference for 
 * each point.
 * 
 * @author Darren
 * @version Milestone 4
 *
 */
public class ChebyshevKNN extends kNN {
	
	/**
	 * Constructor for Maths.ChebyshevKNN. Takes a DataModel.DimensionalSpace object that it will work in and the order
	 * to be used for the calculation. Calls the Maths.kNN constructor.
	 * 
	 * @param ds	The DataModel.DimensionalSpace the the function will work in.
	 */
	public ChebyshevKNN(DimensionalSpace ds) {
		super(ds);
	}

	/**
	   * findKNN finds the target value for the given point using an Chebyshev KNN algorithm
	   * with the k nearest points. Calls the super class's findValue funtion once the nearest neighbours
	   * are found.
	   * 
	   * @param targetKey		The key to be found
	   * @param targetPoint		The point whose value is to be found
	   * @param neighbours		The number of nearest neighbours to query
	   * 
	   * @return				The unknown value of the given point
	   */
	@Override
	public Cell findKNN(String targetKey, Point targetPoint, int neighbours) {
		
		ds.findStatistics();
		targetPoint.normalize(ds.getMean(), ds.getStdDev());
		
		ArrayList<Point> points = ds.getPoints();
		
		if (neighbours <= 0) neighbours = 1;
	    if (neighbours > points.size()) neighbours = points.size();
		
	    ArrayList<Tuple<Float, Cell>> closestKNeighbours = new ArrayList<Tuple<Float, Cell>>(neighbours);
		Tuple<Float, Cell> displacedPoint, placeHolder;
		float distance, tempDistance;
		boolean displacement;
		HashMap<String, Cell> currentPtValues;
		HashMap<String, Cell> targetPtValues = targetPoint.getStdValues();
		
		for (int i = 0; i < neighbours; i++) {
			closestKNeighbours.add(i, null);
		}
		
		for (Point pt: points) {
			if (!(pt.equals(targetPoint)) && !pt.checkValueNull(targetKey)) {
				displacement = false;
				displacedPoint = null;
				distance = 0;
				currentPtValues = pt.getStdValues();
				
				for (String key: currentPtValues.keySet()) {
					tempDistance = 0;
					if (!(key.equals(targetKey))) {
						if (currentPtValues.get(key) instanceof CellSimple) {
							try {
								switch (metrics.get(key)) {
									case "Equality":
										da = neq;
										break;
									case "Difference":
										da = nd;
										break;
									case "Standard Deviation":
										da = nsd;
										break;
									case "Equal":
										da = seq;
										break;
									case "Hamming":
										da = sh;
										break;
									case "Character Value":
										da = scv;
										break;
									default:
										if (((CellSimple) pt.getCell(key)).getValue() instanceof Float ||((CellSimple) pt.getCell(key)).getValue() instanceof Integer) {
											da = nd;
										} else {
											da = sh;
										}
								}
							} catch (NullPointerException ne) {
								if (((CellSimple) pt.getCell(key)).getValue() instanceof Float ||((CellSimple) pt.getCell(key)).getValue() instanceof Integer) {
									da = nd;
								} else {
									da = sh;
								}
							}
							
							tempDistance = da.calcDistance((CellSimple)targetPtValues.get(key), (CellSimple)currentPtValues.get(key));
						} else {
							tempDistance = ChebyshevComposite((CellComposite) currentPtValues.get(key), (CellComposite) targetPtValues.get(key));
						}
					}
					if (tempDistance > distance) {
						distance = tempDistance;
					}
				}
				
				for (int i = 0; i < neighbours; i ++) {
					if (!displacement) {
						// If the current spot has not been used yet
						if (closestKNeighbours.get(i) == null) {

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

						if (closestKNeighbours.get(i) == null) {

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
	 * Iterates through all of the sub cells in a DataModel.CompositeCell and calculates the distance from that DataModel.CompositeCell to
	 * the target point's corresponding DataModel.CompositeCell.
	 * 
	 * @param current	The DataModel.CompositeCell of the non-target point
	 * @param target	The DataModel.CompositeCell of the target point
	 * @return			The distance between the Cells
	 */
	public float ChebyshevComposite(CellComposite current, CellComposite target) {
		ArrayList<Cell> subCells = current.getSubCells();
		float distance = 0;
		
		for (Cell c: subCells) {
			if (c instanceof CellSimple) {
				try {
					switch (metrics.get(c.getKey())) {
						case "Equality":
							da = neq;
							break;
						case "Difference":
							da = nd;
							break;
						case "Standard Deviation":
							da = nsd;
							break;
						case "Equal":
							da = seq;
							break;
						case "Hamming":
							da = sh;
							break;
						case "Character Value":
							da = scv;
							break;
						default:
							if (((CellSimple) c).getValue() instanceof Float || ((CellSimple) c).getValue() instanceof Integer) {
								da = nd;
							} else {
								da = sh;
							}
					}
				} catch (NullPointerException ne) {
					if (((CellSimple) c).getValue() instanceof Float || ((CellSimple) c).getValue() instanceof Integer) {
						da = nd;
					} else {
						da = sh;
					}
				}
				
				distance += Math.pow(da.calcDistance((CellSimple) target.getSubCell(c.getKey()), (CellSimple) c), 2);
			} else {
				distance += ChebyshevComposite((CellComposite) c, (CellComposite) target.getSubCell(c.getKey()));
			}
		}
		
		return (float) Math.sqrt(distance);
	}
}
