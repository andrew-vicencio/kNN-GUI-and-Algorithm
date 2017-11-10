package CodersInc;

import java.util.ArrayList;

public class CompositeCell extends Cell {

	private ArrayList<Cell> features;
	
	public CompositeCell(String key) {
		super(key);
		features = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getSubCells() {
		return features;
	}
	
	public void setSubCells(ArrayList<Cell> features) {
		this.features = features;
	}
	
	public void addCell(Cell f) {
		features.add(f);
	}
}
