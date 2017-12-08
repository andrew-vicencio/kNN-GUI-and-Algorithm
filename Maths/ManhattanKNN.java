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
 * Maths.ManhattanKNN extends the Maths.kNN abstract method and uses an Manhattan distance metric for the calculations.
 * The formula is [distance] = sum(endPoint - origin) where the sum adds the values for every dimension.
 * 
 * @author Darren
 * @version Milestone 3
 *
 */
public class ManhattanKNN extends MinkowskiKNN {
	
	/**
	 * Constructor for Maths.ManhattanKNN. Takes a DataModel.DimensionalSpace object that it will work in and the order
	 * to be used for the calculation. Calls the Maths.kNN constructor.
	 * 
	 * @param ds	The DataModel.DimensionalSpace the the function will work in.
	 */
	public ManhattanKNN(DimensionalSpace ds) {
		super(ds, 1);
	}

}
