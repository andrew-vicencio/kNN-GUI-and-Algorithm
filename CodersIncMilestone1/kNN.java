package CodersInc;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Darren
 *
 */
public abstract class kNN {
	
	protected DimensionalSpace ds;
	
	public kNN (DimensionalSpace ds) {
		this.ds = ds;
	}

	public abstract Cell findKNN(String targetKey, Point targetPoint, int neighbours);
	
	public Cell findValue(ArrayList<Tuple<Float, Cell>> closestKNeighbours, String targetKey) {
		if (closestKNeighbours.get(0).getValue2() instanceof SimpleCell) {
			if (((SimpleCell)closestKNeighbours.get(0).getValue2()).getValue() instanceof String) {
				return calculateSimpleCell(closestKNeighbours, "String", targetKey);
			} else if (((SimpleCell)closestKNeighbours.get(0).getValue2()).getValue() instanceof Integer) {
				return calculateSimpleCell(closestKNeighbours, "Integer", targetKey);
			} else if (((SimpleCell)closestKNeighbours.get(0).getValue2()).getValue() instanceof Float) {
				return calculateSimpleCell(closestKNeighbours, "Float", targetKey);
			}
		} else {
			return calculateCompositeCell(closestKNeighbours, targetKey);
		}
		return null;
	}

	public CompositeCell calculateCompositeCell(ArrayList<Tuple<Float, Cell>> closestKNeighbours, String targetKey) {
		
		ArrayList<Cell> cellsToCalc = ((CompositeCell)closestKNeighbours.get(0).getValue2()).getSubCells();
		CompositeCell target = new CompositeCell(targetKey);
		
		for (Cell c: cellsToCalc) {
			String currentKey = c.getKey();
			ArrayList<Tuple<Float, Cell>> subList = new ArrayList<Tuple<Float, Cell>>(closestKNeighbours.size());
			for(int i = 0; i < closestKNeighbours.size(); i++) {
				subList.set(i, new Tuple<Float, Cell>(closestKNeighbours.get(i).getValue1(), ((CompositeCell)closestKNeighbours.get(i).getValue2()).getSubCell(c.getKey())));
			}
			
			if (c instanceof SimpleCell) {
				if (((SimpleCell) c).getValue() instanceof String) {
					target.addCell(calculateSimpleCell(subList, "String", c.getKey()));
				} else if (((SimpleCell) c).getValue() instanceof Integer) {
					target.addCell(calculateSimpleCell(subList, "Integer", c.getKey()));
				} else if (((SimpleCell) c).getValue() instanceof Float) {
					target.addCell(calculateSimpleCell(subList, "Float", c.getKey()));
				}
			} else {
				target.addCell(calculateCompositeCell(subList, c.getKey()));
			}
		}
		
		return target;
	}

	public Cell calculateSimpleCell(ArrayList<Tuple<Float, Cell>> closestKNeighbours, String type, String targetKey) {
		
		switch (type){
			case "String":
				HashMap<String, Integer> stringCount = new HashMap<String, Integer>();
				String s;
				
				for (int i= 0; i < closestKNeighbours.size(); i++) {
					s = (String)((SimpleCell)closestKNeighbours.get(i).getValue2()).getValue();
					if (stringCount.containsKey(s)) {
						stringCount.put(s, stringCount.get(s) + 1);
					} else {
						stringCount.put(s, 1);
					}
				}
				
				s = (String)((SimpleCell)closestKNeighbours.get(0).getValue2()).getValue();
				int count = stringCount.get(s);
				
				for (String key: stringCount.keySet()) {
					if (stringCount.get(key) > count) {
						s = key;
						count = stringCount.get(key);
					} else if (stringCount.get(key) == count) {
						for (Tuple<Float, Cell> t: closestKNeighbours) {
							if (((String)((SimpleCell)t.getValue2()).getValue()).equals(key)) {
								s = key;
								break;
							}
						}
					}
				}
				return new SimpleCell<String>(targetKey, s);
				
			case "Integer":
				int iValue = 0;
				
				for (Tuple<Float, Cell> t: closestKNeighbours) {
					iValue += (int)((SimpleCell)t.getValue2()).getValue();
				}
				return new SimpleCell<Integer>(targetKey, iValue/closestKNeighbours.size());
				
			case "Float":
				float fValue = 0;
				
				for (Tuple<Float, Cell> t: closestKNeighbours) {
					fValue += (float)((SimpleCell)t.getValue2()).getValue();
				}
				return new SimpleCell<Float>(targetKey, fValue/closestKNeighbours.size());
		}
		
		return null;
	}
	
	/**
	   * Simple Tuple class to hold 2 ordered values for use in the findKNN function.
	   * 
	   * @author Darren
	   *
	   * @param <E>	Type of the first value.
	   * @param <K>	Type of the second value
	   */
	protected class Tuple<E, K> {
		private E value1;
		private K value2;
		
		/**
		 * Tuple constructor.
		 * 
		 * @param val1		The first value of type E.
		 * @param val2		The second value of type K.
		 */
		public Tuple(E val1, K val2) {
			this.value1 = val1;
			this.value2 = val2;
		}

		/**
		 * @return		The first value.
		 */
		public E getValue1() {
			return value1;
		}

		/**
		 * @param x	The new value for the first value (of type E).
		 */
		public void setValue1(E x) {
			this.value1 = x;
		}
		
		/**
		 * @return		The second value.
		 */
		public K getValue2() {
			return value2;
		}
		
		/**
		 * @param x	The new value for the second value (of type K).
		 */
		public void setValue2(K x) {
			this.value2 = x;
		}
	  }
}
