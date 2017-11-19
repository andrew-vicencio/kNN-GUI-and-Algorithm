package CodersInc;

/**
 * Cell is an abstract class providing functions and a key variable for the SimpleCell and CompositeCell subclasses.
 * 
 * @author Darren
 * @version Milestone 2
 *
 */
public abstract class Cell {
	private String key;
	
	/**
	 * Constructer for a Cell. Sets the key of the cell to the given value.
	 * 
	 * @param key	The Cell's key value.
	 */
	public Cell (String key) {
		this.key = key;
	}
	
	/**
	 * Returns the key value for this cell.
	 * 
	 * @return	The Cell's key value.
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key of hte cell to a new value.
	 * 
	 * @param key	The new key for the cell
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
