package DataModel;

import DataModel.CellComposite;
import DataModel.CellSimple;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to hold a point from the dataset.
 * 
 * @author Gabrielle and Andrew
 * @version Milestone 2
 *
 */

public class Point 
{
    private LinkedHashMap<String, Cell> rawValues;
    private LinkedHashMap<String, Cell> stdValues;
	
    /**
    * DataModel.Point constructor for a point with a given value.
    * 
    * @param ptVal		The value of the point
    */

    public Point() {
        rawValues = new LinkedHashMap<String, Cell>();
        stdValues = new LinkedHashMap<String, Cell>();

    }
	
    /**
    * Adds a coordinate pair value to the DataModel.Point
    * 
    * @param att		The key for the value
    * @param value		The value of the key
    */
    public void addAttribute(Cell f) {
        rawValues.put(f.getKey(), f);
    }


      public void setHashMaprawValues(LinkedHashMap<String,Cell> f){
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
    			if (targ instanceof CellComposite) {
    				targ = ((CellComposite)targ).getSubCell(att);
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
    		try {
	    		if (((CellSimple)getCell(k)).getValue() instanceof Integer) {
	    			val = (float)(int)((CellSimple)getCell(k)).getValue();
	    		} else {
	    			val = (float)((CellSimple)getCell(k)).getValue();
	    		}
	    		stdValues.put(k, new CellSimple<Float>(k, (val - mean.get(k) / stddev.get(k))));
    		} catch (NullPointerException ne) {}
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
    public void setStdValues(LinkedHashMap<String, Cell> stdValues) {
        this.stdValues = stdValues;
    }
    
    /**
     * Creates a string representation of the point.
     * 
     * @return	The string representation listing all of the values for the 
     */
	public String toString()
    {
        String finalString = "";
        for (Cell x: rawValues.values()) {
        	 if(x instanceof CellComposite){		
        		 if(finalString == ""){		
        			 finalString = ((CellComposite)(x)).toString();		
        		 }else{		
        			 finalString = finalString +", " +  ((CellComposite)(x)).toString();		
        	 }		
        	 }else{		
        		 	if(finalString == ""){		
        	                    finalString =  ((CellSimple)(x)).toString();		
        	                 }else{		
        	                     finalString = finalString + ", " + ((CellSimple)(x)).toString();		
        	                 }		
        	 	
        	             }	
        }
		return finalString;
    }
}
