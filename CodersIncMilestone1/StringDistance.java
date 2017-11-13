

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
