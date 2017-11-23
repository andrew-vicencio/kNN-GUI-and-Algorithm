package DataModel;

import DataModel.Cell;

/**
 * DataModel.CellSimple stores a single value of given type E.
 * 
 * @author Darren
 * @version Milestone 2
 *
 * @param <E>	Type of the value to be stored.
 */
public class CellSimple<E> extends Cell {
	
	private E value;
	
	/**
	 * Constructor for a DataModel.CellSimple. Sets the key and value of the cell.
	 * 
	 * @param key		The key of the cell.
	 * @param value		The cell's value, of type E
	 */
	public CellSimple(String key, E value) {
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

}
