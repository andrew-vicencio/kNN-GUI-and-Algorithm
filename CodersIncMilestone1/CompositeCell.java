package CodersInc;

import java.util.ArrayList;

public class CompositeCell extends Cell {

	private ArrayList<Cell> features;
	
	public CompositeCell(String key) {
		super(key);
		features = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getSubFeatures() {
		return features;
	}
	
	public void setSubFeatures(ArrayList<Cell> features) {
		this.features = features;
	}
	
	public void addFeature(Cell f) {
		features.add(f);
	}
}
