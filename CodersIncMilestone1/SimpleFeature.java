package CodersInc;

public class SimpleFeature<E> extends Feature {
	
	private E value;
	
	public SimpleFeature(String key, E value) {
		super(key);
		this.value = value;
	}
	
	public E getValue() {
		return value;
	}
	
	public void setValue(E value) {
		this.value = value;		
	}

}
