package Maths;

import DataModel.CellSimple;

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
