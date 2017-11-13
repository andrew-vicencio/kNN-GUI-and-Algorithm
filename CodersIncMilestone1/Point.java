package CodersInc;
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
    private HashMap<String, Cell> rawValues;
    private HashMap<String, Cell> stdValues;
	
    /**
    * Point constructor for a point with a given value.
    * 
    * @param ptVal		The value of the point
    */
<<<<<<< HEAD
    public Point() {
=======
    public Point(int ptVal) {
>>>>>>> Refactoring
        rawValues = new HashMap<String,Cell>();
        stdValues = new HashMap<String,Cell>();
    }
	
    /**
    * Adds a coordinate pair value to the Point
    * 
    * @param att		The key for the value
    * @param value		The value of the key
    */
    public void addAttribute(Cell f) {
        rawValues.put(f.getKey(), f);
    }

    /**
    * Returns the set of keys that are currently being used by the point.
    * 
    * @return		A Set of keys in string format.
<<<<<<< HEAD
    */
=======
    **/
>>>>>>> Refactoring
    public Set<String> getAttributes() {
    	return rawValues.keySet();
    }

    /**
    * Returns the value of the given key in the point.
    * 
    * @param att		The key of the value to be retrieved
    * @return			The value of the key
    */
    public Cell getCell(String att) {
<<<<<<< HEAD
    	Cell targ = rawValues.get(att);
    	
    	if (targ == null) {
    		for (String k: rawValues.keySet()) {
    			targ = rawValues.get(k);
    			if (targ instanceof CompositeCell) {
    				targ = ((CompositeCell)targ).getSubCell(att);
    				if (targ != null) {
    					break;
    				}
    			}
    		}
    	}
    	
    	return targ;
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
    /**public void standardise(AbstractMap<String, Integer> mean, int n){
        for (String attr : rawValues.keySet()){
	    int X = rawValues.get(attr);
	    stdValues.put(attr, (X - findStdDev(X, mean.get(attr), n))/mean.get(attr));
	}
    }**/
    
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
    /**private int findStdDev(int X, int mean, int n) {
        return (int) (Math.pow((X - mean),2)/n);
    }**/

    /**
    * @return			The calculated standard deviation values.
    */
     public HashMap<String, Cell> getStdValues() {
        return stdValues;
    }

    /**
    * Sets the standard deviation values.
    * 
    * @param stdValues		The HashMap containing the new standard deviations.
    */
    public void setStdValues(HashMap<String, Cell> stdValues) {
        this.stdValues = stdValues;
=======
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
    **/
    public void standardise(AbstractMap<String, Integer> mean, int n){
>>>>>>> Refactoring
    }
}
