package Maths;

import DataModel.CellSimple;

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
	 * @param target	The target DataModel.CellSimple
	 * @param current	The DataModel.CellSimple to which the distance is to be measured.
	 * @return			The distance as a float value.
	 */

	public abstract float calcDistance(CellSimple target, CellSimple current);
}
