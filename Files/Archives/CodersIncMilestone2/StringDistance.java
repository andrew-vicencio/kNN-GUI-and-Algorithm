
/**
 * Calculates a distance between two String values. This algorithm uses the Hamming Distance calculation. This
 * returns a distance by calculating the number of switches it would take to transform one String to another.
 * For example, "Cat" and "Dog" would have a distance of 3 between them, while "Cat" and "Catch" would have 
 * distance of 2 between them.
 * 
 * @author Darren
 * @version Milestone 2
 *
 */

public class StringDistance extends DistanceAlg {

	@Override
	public float calcDistance(SimpleCell target, SimpleCell current) {

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
	}

}
