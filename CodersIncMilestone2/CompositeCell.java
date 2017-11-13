package CodersInc;

import java.util.ArrayList;

/**
 * CompositeCell provides functionality for a grouped set of attributes within a Point.
 * 
 * @author Darren
 * @version Milestone 2
 *
 */
public class CompositeCell extends Cell {

	private ArrayList<Cell> value;
	
	/**
	 * Constructor for CompositeCell. Sets the cell's key through the super class' constructor and initializes
	 * the subcell list.
	 * 
	 * @param key	The cell's key
	 */
	public CompositeCell(String key) {
		super(key);
		value = new ArrayList<Cell>();
	}
	
	/**
	 * getSubCells returns the list of Cells that are contained within the CompositeCell
	 * 
	 * @return		The list of cells
	 */
	public ArrayList<Cell> getSubCells() {
		return value;
	}
	
	/**
	 * setSubCells sets the list of subCells to a given list of cells
	 * 
	 * @param features	The new list of subCells
	 */
	public void setSubCells(ArrayList<Cell> features) {
		this.value = features;
	}
	
	/**
	 * getSubCell returns a single cell that has the given key. Returns null if no such cell exists.
	 * 
	 * @param key	The key of the desired cell
	 * @return		The cell that has the given key. Null if there isn't a cell with that key.
	 */
	public Cell getSubCell(String key) {
		for (Cell c: value) {
			if(c.getKey().equals(key)) {
				return c;
			} else if (c instanceof CompositeCell){
				Cell d = ((CompositeCell)c).getSubCell(key);
				if (d != null) {
					return d;
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds a single cell to the list of subCells.
	 * 
	 * @param f		The Cell to be added to the list.
	 */
	public void addCell(Cell f) {
		value.add(f);
	}
}
