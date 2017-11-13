package CodersInc;

public class NumericalDistance extends DistanceAlg {

	@Override
	public float calcDistance(SimpleCell target, SimpleCell current) {
		float val1, val2;
		
		if (target.getValue() instanceof Integer){
			val1 = (float) ((int)target.getValue());
			val2 = (float) ((int)current.getValue());
		} else {
			val1 = (float) target.getValue();
			val2 = (float) current.getValue();
		}
		
		return Math.abs(val1 - val2);
	}

}
