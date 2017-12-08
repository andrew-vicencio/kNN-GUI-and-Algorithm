package Maths;

import DataModel.CellSimple;

/**
 * Calculates the distance between two numerical SimpleCells. Simply returns 0 if the values are equal, and 1 otherwise.
 * 
 * @author Darren
 * @version Milestone 4
 *
 */
public class NumericalEquality extends DistanceAlg {

	@Override
	public float calcDistance(CellSimple target, CellSimple current) {

		float val1, val2;
		
		try {
			if (target.getValue() instanceof Integer){
				val1 = (float) ((int)target.getValue());
				val2 = (float) ((int)current.getValue());
			} else {
				val1 = (float) target.getValue();
				val2 = (float) current.getValue();
			}
			
			if (val1 == val2) {
				return 0;
			} else {
				return 1;
			}
			
		} catch (NullPointerException ne) {
			return 0;
		}
	}

}
