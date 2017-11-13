package CodersInc;

import java.util.ArrayList;

public class CompositeCell extends Cell {

	private ArrayList<Cell> value;
	
	public CompositeCell(String key) {
		super(key);
		value = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getSubCells() {
		return value;
	}
	
	public void setSubCells(ArrayList<Cell> features) {
		this.value = features;
	}
	
	public Cell getSubCell(String key) {
		for (Cell c: value) {
			if(c.getKey().equals(key)) {
				return c;
			}
		}
		return null;
	}
	
	public void addCell(Cell f) {
		value.add(f);
	}
}
