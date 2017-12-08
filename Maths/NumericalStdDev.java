package Maths;

import java.util.concurrent.ConcurrentHashMap;

import DataModel.CellSimple;

public class NumericalStdDev extends DistanceAlg {
	
	private ConcurrentHashMap<String, Float> stdDev, avg;
	
	public NumericalStdDev(ConcurrentHashMap<String, Float> stdDev, ConcurrentHashMap<String, Float> avg) {
		this.stdDev = stdDev;
		this.avg = avg;
	}

	@Override
	public float calcDistance(CellSimple target, CellSimple current) {

		float val1, val2, avgVal, stdDevVal;
		String key;
		
		try {
			key = target.getKey();
			if (target.getValue() instanceof Integer){
				val1 = (float) ((int)target.getValue());
				val2 = (float) ((int)current.getValue());
			} else {
				val1 = (float) target.getValue();
				val2 = (float) current.getValue();
			}
			
			avgVal = avg.get(key);
			stdDevVal = stdDev.get(key);
			
			if (val1 == avgVal) {
				if (val2 > avgVal - stdDevVal && val2 < avgVal + stdDevVal) {
					return 0;
				} else {
					return 1;
				}
			} else if (val1 > avgVal && val2 > avgVal){
				for (int i = 1; i <= 3; i++) {
					if (val1 < (avgVal + i * stdDevVal) && val2 < (avgVal + i * stdDevVal) &&
							val1 > (avgVal + (i - 1) * stdDevVal) && val2 > (avgVal + (i - 1) * stdDevVal)) {
						return 0;
					}
				}
				return 1;
			
			} else if (val1 < avgVal && val2 < avgVal){
				for (int i = 1; i <= 3; i++) {
					if (val1 > (avgVal + i * stdDevVal) && val2 > (avgVal + i * stdDevVal) &&
							val1 < (avgVal + (i - 1) * stdDevVal) && val2 < (avgVal + (i - 1) * stdDevVal)) {
						return 0;
					}
				}
				return 1;
				
			} else {
				return 1;
			}
			
		} catch (NullPointerException ne) {
			return 0;
		}
	}

}
