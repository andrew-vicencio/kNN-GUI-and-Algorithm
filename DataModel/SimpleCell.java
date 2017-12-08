package DataModel;

import DataModel.Cell;
import java.io.Serializable;

/**
 * DataModel.SimpleCell stores a single value of given type E.
 * 
 * @author Darren
 * @version Milestone 4
 *
 * @param <E>	Type of the value to be stored.
 */
public class SimpleCell<E> extends Cell implements Serializable {
	
	private E value;
	
	/**
	 * Constructor for a DataModel.SimpleCell. Sets the key and value of the cell.
	 * 
	 * @param key		The key of the cell.
	 * @param value		The cell's value, of type E
	 */
	public SimpleCell(String key, E value) {
		super(key);
		this.value = value;
	}
	
	/**
	 * Returns the value of the cell
	 * 
	 * @return		The cell's value
	 */
	public E getValue() {
		return value;
	}
	
	/**
	 * Sets the value of the cell to a new value.
	 * 
	 * @param value		The cell's new value.
	 */
	public void setValue(E value) {
		this.value = value;		
	}

	public String toString(){
	    return getKey() + ": " + value;
    }
	
	public String toXML() {
		String finalString = "<SimpleCell key=\"" + super.getKey() + "\">" + System.lineSeparator();
		finalString = finalString + "<Value>" + value.toString() + "</Value>" + System.lineSeparator(); 
		finalString = "</SimpleCell>";
		return finalString;
	}
}
