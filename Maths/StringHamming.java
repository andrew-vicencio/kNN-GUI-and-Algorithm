package Maths;

import DataModel.CellSimple;

/**
 * Calculates a distance between two String values. This algorithm uses the Hamming Distance calculation. This
 * returns a distance by calculating the number of switches it would take to transform one String to another.
 * For example, "Cat" and "Dog" would have a distance of 3 between them, while "Cat" and "Catch" would have 
 * distance of 2 between them.
 * 
 * @author Darren
 * @version Milestone 4
 *
 */

public class StringHamming extends DistanceAlg {

	/* (non-Javadoc)
	 * @see Maths.DistanceAlg#calcDistance(DataModel.CellSimple, DataModel.CellSimple)
	 */
	@Override
	public float calcDistance(CellSimple target, CellSimple current) {
		
		try {
			String targetString = (String) target.getValue();
			String currentString = (String) current.getValue();
			float distance = 0;
			int targLen = targetString.length();
			int currLen = currentString.length();
			int counter;
			
			distance += Math.abs(targLen - currLen);
			
			if (targLen < currLen) {
				counter = targLen;
			} else {
				counter = currLen;
			}
			
			for (int i = 0; i < counter; i ++) {
				if (currentString.charAt(i) != targetString.charAt(i)) {
					distance++;
				}
			}
			
			return distance;
		} catch (NullPointerException ne) {
			return 0;
		}
	}

}
