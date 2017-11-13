package CodersInc;

<<<<<<< HEAD
/**
 * Calculates the distance between two numerical SimpleCells. Simply returns the difference between the given values.
 * 
 * @author Darren
 * @version Milestone 2
 *
 */
=======
>>>>>>> Refactoring
public class NumericalDistance extends DistanceAlg {

	@Override
	public float calcDistance(SimpleCell target, SimpleCell current) {
<<<<<<< HEAD
		float val1, val2;
		
		if (target.getValue() instanceof Integer){
			val1 = (float) ((int)target.getValue());
			val2 = (float) ((int)current.getValue());
		} else {
			val1 = (float) target.getValue();
			val2 = (float) current.getValue();
		}
		
		return Math.abs(val1 - val2);
=======
		
		return (float) Math.pow((float)current.getValue() - (float)target.getValue(), 2);
>>>>>>> Refactoring
	}

}
