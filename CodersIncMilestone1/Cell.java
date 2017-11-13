package CodersInc;

/**
 * Cell is an abstract class providing functions and a key variable for the SimpleCell and CompositeCell subclasses.
 * 
 * @author Darren
 *
 */
public abstract class Cell {
	private String key;
	
	public Cell (String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
