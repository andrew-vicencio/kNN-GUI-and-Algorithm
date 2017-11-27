import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;

/**
 * Class to hold a point from the dataset.
 * 
 * @author Gabrielle and Andrew
 * @version Milestone 2
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

    public Point() {
        rawValues = new HashMap<String, Cell>();
        stdValues = new HashMap<String, Cell>();

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


      public void setHashMaprawValues( HashMap<String,Cell> f){
        rawValues = f;
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
    public Cell getCell(String att) {

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
     * Calculates the normalized values for this point.
     * 
     * @param mean
     * @param stddev
     */
    public void normalize(ConcurrentHashMap<String, Float> mean, ConcurrentHashMap<String, Float> stddev) {
    	float val;
    	for (String k: mean.keySet()) {
    		if (((SimpleCell)getCell(k)).getValue() instanceof Integer) {
    			val = (float)(int)((SimpleCell)getCell(k)).getValue();
    		} else {
    			val = (float)((SimpleCell)getCell(k)).getValue();
    		}
    		stdValues.put(k, new SimpleCell<Float>(k, (val - mean.get(k) / stddev.get(k))));
    	}
    }

    /**
    * @return			The calculated standard deviation values.
    */
     public HashMap<String, Cell> getStdValues() {
        return stdValues;
    }

     /**
      * @return			The point's raw values.
      */
       public HashMap<String, Cell> getRawValues() {
          return rawValues;
      }
     
    /**
    * Sets the standard deviation values.
    * 
    * @param stdValues		The HashMap containing the new standard deviations.
    */
    public void setStdValues(HashMap<String, Cell> stdValues) {
        this.stdValues = stdValues;
    }

	 public String toString()
    {
        System.out.println("Size of Point" + rawValues.values().size());
        String finalString = "";
        for (Cell x: rawValues.values()) {
            System.out.println(x.getKey());
            if(x instanceof CompositeCell){
                if(finalString == ""){
                    finalString =    ((CompositeCell)(x)).toString();
                }else{
                    finalString = finalString +", " +  ((CompositeCell)(x)).toString();
                }

            }else{
                if(finalString == ""){
                    finalString =  ((SimpleCell)(x)).toString();
                }else{
                    finalString = finalString + ", " + ((SimpleCell)(x)).toString();
                }

            }

        }

		return finalString;
    	//TODO. Preferred format is a series of "keyName: value"



    }
}
