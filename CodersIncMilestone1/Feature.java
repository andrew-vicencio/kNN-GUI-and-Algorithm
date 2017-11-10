package CodersInc;

public abstract class Feature {
	private String key;
	
	public Feature (String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
