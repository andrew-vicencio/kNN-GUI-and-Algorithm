package Maths;

import DataModel.*;
import Maths.NumericalDifference;
import Maths.kNN;

import java.util.*;


/**
 * Maths.EuclideanKNN extends the Maths.kNN abstract method and uses an euclidean distance metric for the calculations.
 * The formula is [distance] = sqrt(sum((endPoint - origin) ^ 2)) where the sum adds the values for every dimension.
 * 
 * @author Darren
 * @version Milestone 4
 *
 */
public class EuclideanKNN extends MinkowskiKNN {

	
	/**
	 * Constructor for Maths.EuclideanKNN. Takes a DataModel.DimensionalSpace object that it will work in. Calls the Maths.kNN constructor.
	 * 
	 * @param ds	The DataModel.DimensionalSpace the the function will work in.
	 */
	public EuclideanKNN(DimensionalSpace ds) {
		super(ds, 2);
	}

}
