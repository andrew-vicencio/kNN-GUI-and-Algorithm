

import java.util.ArrayList;

public class CompositeFeature extends Feature {

	private ArrayList<Feature> features;
	
	public CompositeFeature(String key) {
		super(key);
		features = new ArrayList<Feature>();
	}
	
	public ArrayList<Feature> getSubFeatures() {
		return features;
	}
	
	public void setSubFeatures(ArrayList<Feature> features) {
		this.features = features;
	}
	
	public void addFeature(Feature f) {
		features.add(f);
	}
}
