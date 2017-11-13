

public class NumericalDistance extends DistanceAlg {

	@Override
	public float calcDistance(SimpleCell target, SimpleCell current) {
		
		return (float) Math.pow((float)current.getValue() - (float)target.getValue(), 2);
	}

}
