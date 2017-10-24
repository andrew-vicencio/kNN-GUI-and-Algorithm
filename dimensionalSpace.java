import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;

public class dimensionalSpace {
  private ConcurrentHashMap<String, Integer> mean;
  private ConcurrentHashMap<String, Integer> stddev;
  private ConcurrentHashMap<String, Integer> sum;
  private int numberOfPoints;
  private ArrayList<Point> Points;

  public dimensionalSpace(){
    mean = new ConcurrentHashMap<String, Integer>();
    stddev = new ConcurrentHashMap<String, Integer>();
    sum = new ConcurrentHashMap<String, Integer>();
    Points = new ArrayList<Point>();
  }
  
  public void addPts(ArrayList<Point> pts){
    Iterator<Point> pair = pts.iterator();
    while(pair.hasNext()){
      addPt(pair.next());
    }
    findMean();
  }
  
  public void addPt(Point pt){
    String attribute;
    Integer val;
    ArrayList<String> attr = pt.getAttributes();
    if(Points != null){
      Points.add(pt);
    } else {
      System.out.println("Points not initialized");
    }
    for (String key : attr){
      val = pt.getValue(key);
      if(!mean.containsKey(key)){
        sum.put(key, val);
      } else {
        sum.replace(key, sum.get(key) + val);
      }
    }
  }

  private void findMean(){
    String key;
    Integer val;
    Iterator<Map.Entry<String, Integer>> entry = mean.entrySet().iterator();
    while (entry.hasNext()){
      Map.Entry<String, Integer> Pair = entry.next();
      key = Pair.getKey();
      val = Pair.getValue();
      mean.replace(key, val / numberOfPoints);
    }
  }

  public int FindkNN(){
    return 0;
  }

  public void generateStats(){
    findMean();
    numberOfPoints = Points.size();
  }
  
  public void setMean(ConcurrentHashMap<String, Integer> newMean){
    mean = newMean;
  }

  public ConcurrentHashMap<String, Integer> getMean(){
    return mean;
  }

  public void setStdDev(ConcurrentHashMap<String, Integer> newDev){
    stddev = newDev;
  }

  public ConcurrentHashMap<String, Integer> getStdDev(){
    return stddev;
  }

  public void setSum(ConcurrentHashMap<String, Integer> newSum){
    sum = newSum;
  }

  public ConcurrentHashMap<String, Integer> getSum(){
    return sum;
  }

  public void setPoints(ArrayList<Point> pts){
    Points = pts;
  }

  public ArrayList<Point> getPoints(){
    return Points;
  }

  public static void main(String[] args){
    Point h1 = new Point(500000);
    h1.addAttribute("coordinate x", 12);
    h1.addAttribute("coordinate y", 25);
    h1.addAttribute("sq. ft.", 1200);
    h1.addAttribute("age", 0);
    Point h2 = new Point(300000);
    h2.addAttribute("coordinate x", 10);
    h2.addAttribute("coordinate y", 50);
    h2.addAttribute("sq. ft.", 1000);
    h2.addAttribute("age", 1);
    Point h3 = new Point(400000);
    h3.addAttribute("coordinate x", 30);
    h3.addAttribute("coordinate y", 1000);
    h3.addAttribute("sq. ft.", 800);
    h3.addAttribute("age", 0);
    dimensionalSpace DS = new dimensionalSpace();
    ArrayList<Point> Pts = new ArrayList();
    if(Pts.add(h1)){
      System.out.println("h1 added");
    }
    if(Pts.add(h2)){
      System.out.println("h2 added");
    }
    if(Pts.add(h3)){
      System.out.println("h3 added");
    }
    DS.addPt(h1);
    DS.addPts(Pts);
  }
}
