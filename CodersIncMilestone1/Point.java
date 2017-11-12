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
    public Point(int ptVal) {
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
    **/
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
    }
}
