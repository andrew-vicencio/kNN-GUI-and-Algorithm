import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EuclideanKNN implements kNN {
	
	DimensionalSpace ds;
	
	public EuclideanKNN(DimensionalSpace ds) {
		this.ds = ds;
	}

	/**
	   * FindkNN finds the "cost" value for the given point using a KNN algorithm
	   * with the k nearest points
	   * 
	   * @param targetKey		The key to be found
	   * @param targetPoint		The point whose value is to be found
	   * @param neighbours		The number of nearest neighbours to query
	   * 
	   * @return				The unknown value of the given point
	   */
	@Override
	public float findKNN(String targetKey, Point targetPoint, int neighbours) {
	  	return 0;
	}

}
