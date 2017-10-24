import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;

public class dimensionalSpace {
  private ConcurrentHashMap<String, Integer> mean;
  private ConcurrentHashMap<String, Integer> stddev;
  private ConcurrentHashMap<String, Integer> sum;
  private ConcurrentHashMap<String, Integer> n;
  private ArrayList<Point> Points;

  public dimensionalSpace(){
    mean = new ConcurrentHashMap();
    stddev = new ConcurrentHashMap();
    sum = new ConcurrentHashMap();
    n = new ConcurrentHashMap();
    Points = new ArrayList();
  }
  
  public void addPts(ArrayList<Point> pts){
    for(int i = 0; i < pts.length; i++){
      addPt(pts[i]);
    }
    findMean();
    findStdDev();
  }
  
  public void addPt(Point pt){
    String attribute;
    Integer val;
    ArrayList<String> attr = pt.getAttributes();
    Points.add(pt);
    for (String item : attr){
      attribute = attr.get(i);
      val = pt.getValue(attribute);
      if(!mean.containsKey(attribute)){
        sum.put(attribute, val);
        n.put(attribute, 1);
      } else {
        sum.replace(attrbute, sum.get(attribute) + val);
        n.replace(attribute, n.get(attribute) + 1);
      }
  }

  private float findMean(){
   String key;
    Integer val;
    for (Map.Entry<String, Integer> entry : sum.entrySet()){
      key = entry.getKey();
      val = entry.getValue();
      mean.replace(key, val / n.get(key));
    }
  }

  public int FindkNN(){
    return 0;
  }
  
  public void setMean(HashMap<String, Integer> newMean){
    mean = newMean;
  }

  public HashMap<String, Integer> getMean(){
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

  public void setPoints(ArrayList<Point> pts){
    Points = pts;
  }

  public ArrayList<Point> getPoints(){
    return Points;
  }
}
