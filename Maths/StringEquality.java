package Maths;

import DataModel.CellSimple;

/**
 * Calculates the distance between two string SimpleCells. Simply returns 0 if the values are equal, and 1 otherwise.
 * 
 * @author Darren
 * @version Milestone 4
 *
 */
public class StringEquality extends DistanceAlg {

	@Override
	public float calcDistance(CellSimple target, CellSimple current) {
		
		try {
			String targetString = (String) target.getValue();
			String currentString = (String) current.getValue();

			if (targetString.equals(currentString)) {
				return 0;
			} else {
				return 1;
			}
			
		} catch (NullPointerException ne) {
			return 0;
		}
	}

}
