import java.util.HashMap;
import java.util.ArrayList;

public class Point 
{
	private HashMap<String, Integer> rawValues;
  private HashMap<string, Integer> stdValues;
	private int cost;
  private ArrayList<String> attributes;
	
	public Point(int cost)
	{
		this.cost = cost;
	}
	public Point()
	{
		cost = 0;
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
	
  public void standardise(Map<String, Integer> mean, Map<String, Integer> n){
    for (String attr : attributes){
      Integer X = rawValues.get(attr)
      stdValues.put(attr, (X - ((X - mean.get(attr))/n.get(attr)))/mean.get(attr));
    }
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
