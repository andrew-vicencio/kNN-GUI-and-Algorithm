
public interface kNN {

	public float findKNN(String targetKey, Point targetPoint, int neighbours);
	
	/**
	   * Simple Tuple class to hold 2 ordered values for use in the findKNN function.
	   * 
	   * @author Darren
	   *
	   * @param <E>	Type of the first value.
	   * @param <K>	Type of the second value
	   */
	public class Tuple<E, K> {
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
