import java.util.ArrayList;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.lang.Math;

public class Point 
{
	private HashMap<String, Integer> rawValues;
  private HashMap<String, Integer> stdValues;
	private int cost;
    private ArrayList<String> attributes;
	
	public Point(int cost)
	{
		this.cost = cost;
    rawValues = new HashMap();
    stdValues = new HashMap();
    attributes = new ArrayList();
	}
	public Point()
	{
    this(0);
	}
	
	public void addAttribute(String att, Integer value)
	{
		rawValues.put(att, value);
    attributes.add(att);
	}

  public ArrayList<String> getAttributes(){
    return attributes;
  }

	// Formerly .getAttribute, changed because it returns
  // the Value and not the attribute
	public Integer getValue(String att)
	{
		return rawValues.get(att);
	}
	
  /*
  * standardise uses the value (X) and mean (u) of every attribute,
  * as well as the number of points (n) to standardize according to a 
  * a standard distribution using the formula:
  * (X - stdDev)/u
  */
  public void standardise(AbstractMap<String, Integer> mean, int n){
    for (String attr : attributes){
      Integer X = rawValues.get(attr);
      stdValues.put(attr, (X - findStdDev(X, mean.get(attr), n))/mean.get(attr));
    }
  }
  
  /*
  * findStdDev finds the population stdDeviation by using the
  * formula:
  * (X - u)(p(x))
  * where X is the value of the attribute, u is the mean
  * of the attribute, and p(x) is the probability of the attribute
  * in this case just 1 divided by the number of points (n)
  *
  * TODO: Change this, this is population stdDev but can do
  * sample stdDev in dimensionalSpace
  */
  private int findStdDev(Integer X, Integer mean, int n){
    return (Math.pow((X - mean),2)/n);
  }

	public void setCost(int cost)
	{
		this.cost = cost;
	}
	
	public int getCost()
	{
		return this.cost;
	}

}
