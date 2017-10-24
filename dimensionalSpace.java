import java.util.*;

public class DimensionalSpace {
  HashMap<String, Integer> mean;
  HashMap<String, Integer> stddev;
  HashMap<String, Integer> sum;
  int numberOfPoints;
  ArrayList<Point> points;

  public DimensionalSpace(){
    mean = new HashMap<String, Integer>();
    stddev = new HashMap<String, Integer>();
    sum = new HashMap<String, Integer>();
    numberOfPoints = 0;
    points = new ArrayList<Point>();
  }
  
  public void addPts(Point pts[]){
    for(int i = 0; i < pts.length; i++){
      addPt(pts[i]);
    }
    numberOfPoints = pts.length;
    generateStats();
  }
  
  public void addPt(Point pt){
    points.add(pt);
  }

  private void generateStats() {	  
	  for (String key: points.get(0).getAttributes()) {
		  generateSum(key);
		  findMean(key);
		  findStdDev(key);
	  }
  }
  
  private void generateSum(String key) {
	  ArrayList<String> keys = points.get(0).getAttributes();
	  int total = 0;
	  
	  for (Point pt: points) {
		  total += pt.getValue(key);
	  }
		  
	  sum.put(key, total);
  }
  
  private void findMean(String key){
	  mean.put(key, sum.get(key) / numberOfPoints);
  }

  private void findStdDev(String key){
	  int summation = 0;
	  
	  for (Point pt: points) {
		  summation += (pt.getValue(key) - mean.get(key)) ^ 2;
	  }
	  
	  stddev.put(key, (int)Math.sqrt(summation / numberOfPoints));
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
    points = pts;
  }

  public ArrayList<Point> getPoints(){
    return points;
  }
}
