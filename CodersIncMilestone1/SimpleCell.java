

public class SimpleCell<E> extends Cell {
	
	private E value;
	
	public SimpleCell(String key, E value) {
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
