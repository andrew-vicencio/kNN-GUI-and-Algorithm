import java.util.HashMap;

public class Point 
{
	private HashMap<String, String> rawValues;
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
	
	public void addAttribute(String att, String value)
	{
		rawValues.put(att, value);
    attributes.add(att);
	}

  public ArrayList<String> getAttributes(){
    return attributes;
  }

	// Formerly .getAttribute, changed because it returns
  // the Value and not the attribute
	public String getValue(String att)
	{
		return rawValues.get(att);
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
