<<<<<<< HEAD
=======

>>>>>>> a05919257ec096a91b5795e014854144cfe93af4
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Class to hold a point from the dataset.
 * 
 * @author Gabrielle and Andrew
 * @version Milestone 1
 *
 */

public class Point 
{
	private HashMap<String, Integer> rawValues;
    private HashMap<String, Integer> stdValues;
	private int pointValue;
	
	/**
	 * Point constructor for a point with a given value.
	 * 
	 * @param ptVal		The value of the point
	 */
	public Point(int ptVal) {
		this.pointValue = ptVal;
		rawValues = new HashMap<String, Integer>();
    	stdValues = new HashMap<String, Integer>();
	}
	
	/**
	 * Point constructor for a point with an undefined value. Calls the
	 * Point(int ptVal) constructor, assigning the point a value of 0.
	 */
	public Point() {
		this(0);
	}
	
	/**
	 * Adds a coordinate pair value to the Point
	 * 
	 * @param att		The key for the value
	 * @param value		The value of the key
	 */
	public void addAttribute(String att, int value) {
		rawValues.put(att, value);
	}

    /**
     * Returns the set of keys that are currently being used by the point.
     * 
     * @return		A Set of keys in string format.
     */
    public Set<String> getAttributes() {
    	return rawValues.keySet();
    }

	
	/**
	 * Returns the value of the given key in the point.
	 * 
	 * @param att		The key of the value to be retrieved
	 * @return			The value of the key
	 */
	public int getValue(String att) {
		return rawValues.get(att);
	}
	
	/**
	 * Standardizes the raw values of the point, given the mean values and the number
	 * of points in the sample. Uses the formula (x - s)/u, where x is the raw value,
	 * s is the value's standard deviation across the sample, and u is the value's average
	 * Across the sample.
	 * 
	 * @param mean		Map of the mean values.
	 * @param n		Number of points in the sample.
	 */
	public void standardise(AbstractMap<String, Integer> mean, int n){
		 for (String attr : rawValues.keySet()){
			 int X = rawValues.get(attr);
			 stdValues.put(attr, (X - findStdDev(X, mean.get(attr), n))/mean.get(attr));
		 }
	}
	
	/**
	 * Finds the standard deviation for the value based on the sample mean and number
	 * of points.
	 * 
	 * TODO: this should be done in DimensionalSpace using the entire sample.
	 * 
	 * @param X			The value to be used.
	 * @param mean		The sample mean for that value
	 * @param n			The number of points in the sample
	 * @return			The stdDev value
	 */
	private int findStdDev(int X, int mean, int n) {
		 return (int) (Math.pow((X - mean),2)/n);
	}

	/**
	 * Sets the value for this point.
	 * 
	 * @param ptVal		The new value for the point.
	 */
	public void setPointValue(int ptVal) {
		 this.pointValue = ptVal;
	}

	/**
	 * @return			The point's value.
	 */
	public int getPointValue() {
		 return this.pointValue;
	}

	/**
	 * @return			The calculated standard deviation values.
	 */
	public HashMap<String, Integer> getStdValues() {
		 return stdValues;
	}

	/**
	 * Sets the standard deviation values.
	 * 
	 * @param stdValues		The HashMap containing the new standard deviations.
	 */
	public void setStdValues(HashMap<String, Integer> stdValues) {
		 this.stdValues = stdValues;
	}
}
