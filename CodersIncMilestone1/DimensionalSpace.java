
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Iterator;
import java.util.Set;

/**
* Class to hold the points from the dataset. This class calculates the sum, mean, and standard 
* deviation of all the known points which are used to standardize the points to give more accurate
* calculations. This class also contains the K-nearest neighbour function which calculates the value
* of a new point.
* 
* @author Darren and Andrew
* @version Milestone 1
**/

public class DimensionalSpace {
    private HashMap<String, Cell> mean;
    private HashMap<String, Cell> stddev;
    private HashMap<String, Cell> sum;
    private HashMap<String, String> cellTypes;
    private int numberOfPoints;
    private ArrayList<Point> points;

  
    /**
    * Constructor for instances of the DimensionalSpace class. Initializes the class variables and sets the
    * current number of points to 0.
    */
    public DimensionalSpace(){
        mean = new HashMap<String, Cell>();
        stddev = new HashMap<String, Cell>();
        sum = new HashMap<String, Cell>();
        points = new ArrayList<Point>();
        cellTypes = new HashMap<String, String>();
    }
  
    /**
    * Adds the given ArrayList of points to the DimensionalSpace's list of points. Calls the addPt funtion
    * on each point in the ArrayList.
    * 
    * @param pts 		An ArrayList of Point objects to be added to the space.
    **/
    public void addPts(ArrayList<Point> pts){
        points.addAll(pts);
    }
    
    //TODO: DOCUMENT
    //TODO: Finish 1
    public void findSum(Point pt){
    }
    
    /**
    * Finds the average value for each of the values wich define the points.
    */
    //TODO: DOCUMENT
    //TODO: Finish 2
    public void findMean(){

    }
    
    //TODO: DOCUMENT
    //TODO: FINISH 4
    public void findStdDev(Point pt){
    
    }
    
    //TODO: DOCUMENT
    //TODO: FINISH 3
    //TODO: TEST 1
    public void findStatistics(){
        numberOfPoints = points.size();
        for (Point pt : points){
            this.findSum(pt);
        }

        this.findMean();
    }

    /**
    * Sets the mean values to the given HashMap.
    * 
    * @param newMean			HashMap containing the mean for each key.
    */
    public void setMean(HashMap<String, Cell> newMean){
        mean = newMean;
    }

    /**
    * @return				HashMap containing the the mean values for the dataset.
    */
    public HashMap<String, Cell> getMean(){
        return mean;
    }
  
    /**
    * Sets the standard deviation values to the given HashMap.
    * 
    * @param newDev		HashMap containing the standard deviation for each key.
    **/
    public void setStdDev(HashMap<String, Cell> newDev){
        stddev = newDev;
    }
  
    /**
    * @return				HashMap containing the the standard deviation values for the dataset.
    **/
    public HashMap<String, Cell> getStdDev(){
        return stddev;
    }

    /**
    * Sets the summed values to the given HashMap.
    * 
    * @param newSum		HashMap containing the sum for each key.
    */
    public void setSum(HashMap<String, Cell> newSum){
        sum = newSum;
    }

    /**
    * @return				HashMap containing the the summed values for the dataset.
    */
    public HashMap<String, Cell> getSum(){
        return sum;
    }
    
    /**
    * Sets the space's points to the given ArrayList
    * 
    * @param pts				ArrayList of the points to be used for the space.
    */
    public void setPoints(ArrayList<Point> pts){
        points = pts;
        numberOfPoints = pts.size();
    }
  
    /**
    * @return				The ArrayList of Points in the space.
    */
    public ArrayList<Point> getPoints(){
        return points;
    }
    
    /**
    * @return			The current number of points in the space.
    */
    public int getNumberOfPoints() {
         return numberOfPoints;
    }

    //TODO: DOCUMENT
    public void setCellTypes(HashMap<String, String> types){
        cellTypes = types;
    }

    //TODO: DOCUMENT
    public HashMap<String, String> getCellTypes(){
        return cellTypes;
    }
}
