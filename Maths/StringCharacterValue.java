package Maths;

import DataModel.CellSimple;

/**
 * Calculates the distance between two String SimpleCells. Simply returns the difference in the sum of the character
 * ASCII values.
 * 
 * @author Darren
 * @version Milestone 4
 *
 */
public class StringCharacterValue extends DistanceAlg {

	/* (non-Javadoc)
	 * @see Maths.DistanceAlg#calcDistance(DataModel.CellSimple, DataModel.CellSimple)
	 */
	@Override
	public float calcDistance(CellSimple target, CellSimple current) {
		
		try {
			String targetString = (String) target.getValue();
			String currentString = (String) current.getValue();
			int targVal = 0;
			int currVal = 0;
			
			for (char c: targetString.toCharArray()) {
				targVal += (int) c;
			}
			
			for (char c: currentString.toCharArray()) {
				currVal += (int) c;
			}
			
			return Math.abs(targVal - currVal);
		} catch (NullPointerException ne) {
			return 0;
		}
	}

}
