package DataModel;

import java.util.ArrayList;

/**
 * DataModel.CompositeCell provides functionality for a grouped set of attributes within a DataModel.Point.
 * 
 * @author Darren
 * @version Milestone 2
 *
 */
public class CellComposite extends Cell {

	private ArrayList<Cell> value;
	
	/**
	 * Constructor for DataModel.CompositeCell. Sets the cell's key through the super class' constructor and initializes
	 * the subcell list.
	 * 
	 * @param key	The cell's key
	 */
	public CellComposite(String key) {
		super(key);
		value = new ArrayList<Cell>();
	}
	
	/**
	 * getSubCells returns the list of Cells that are contained within the DataModel.CompositeCell
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
			} else if (c instanceof CellComposite){
				Cell d = ((CellComposite)c).getSubCell(key);
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
	 * @param f		The DataModel.Cell to be added to the list.
	 */
	public void addCell(Cell f) {
		value.add(f);
	}
	
	/**
	 * Generates a string representation of the cell.
	 * 
	 * @return		A string with the composite cells key, followed by the string representation of every sub-cell.
	 */
	public String toString(){
		String finalString = "";
        for (Cell x: value) {		          
            CellSimple i = (CellSimple)x;		
            if(finalString == ""){		
                finalString = i.toString();		
            }else{		
                finalString = finalString + ", " + i.toString();		
            }			
        }

        return finalString;
    }
}
