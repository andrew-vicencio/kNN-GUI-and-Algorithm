package Maths;

import DataModel.SimpleCell;

/**
 * Abstract class providing abstract method declarations for various distance algorithms
 * 
 * @author Darren
 * @version Milestone 2
 *
 */
public abstract class DistanceAlg {
	
	/**
	 * Calculates the distance between two SimpleCells containing similarily-typed values.
	 * 
	 * @param target	The target DataModel.SimpleCell
	 * @param current	The DataModel.SimpleCell to which the distance is to be measured.
	 * @return			The distance as a float value.
	 */

	public abstract float calcDistance(SimpleCell target, SimpleCell current);
}
