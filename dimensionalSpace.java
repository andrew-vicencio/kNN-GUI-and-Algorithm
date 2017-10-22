import java.util.*;

public class dimensionalSpace {
  HashMap<String, Integer> mean;
  HashMap<String, Integer> stddev;
  HashMap<String, Integer> sum;
  HashMap<String, Integer> n;
  HashMap<String, Point> Points;

  public class dimensionalSpace (){
    mean = new HashMap<String, Integer>();
    stddev = new HashMap<String, Integer>();
    sum = new HashMap<String, Integer>();
    n = new HashMap<String, Integer>();
    pts = new HashMap<String, Integer>();
  }
  
  public class addPts(Point pts[]){
    for(int i = 0; i < pts.length; i++){
      addPt(pts[i]);
    }
  }
  
  /*
  * This class 
  *
  */
  public class addPt(Point pt){
    Points.add(pt);
    
  }

  private float findMean(){
  }

  private float findStdDev(){
  }

  public int FindkNN(){
  }
  
  public void setMean(HashMap<String, Integer> newMean){
    mean = newMean;
  }

  public  getMean(){
    return mean;
  }

  public void setStdDev(HashMap<String, Integer> newDev){
    stddev = newDev;
  }

  public HashMap<String, Integer> getStdDev(){
    return stddev;
  }

  public void setSum(HashMap<String, Integer> newSum){
    sum = newSum;
  }

  public HashMap<String, Integer> getSum(){
    return sum;
  }
}
