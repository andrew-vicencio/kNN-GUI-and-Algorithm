

import java.util.ArrayList;

import DataModel.*;
import Maths.EuclideanKNN;
import Maths.NumericalDistance;
import Maths.StringDistance;
import Maths.kNN;
import junit.framework.*;


@SuppressWarnings("unchecked")
public class MyTests extends TestCase {
	
	private DimensionalSpace ds;
	
	public void setUp() {
		ds = new DimensionalSpace();
	}
	public void testNumericalDistanceWithInteger() {
		SimpleCell<Integer> c1 = new SimpleCell<Integer>("test1", 10);
		SimpleCell<Integer> c2 = new SimpleCell<Integer>("test1", 7);
		NumericalDistance nd = new NumericalDistance();
		
		float value = nd.calcDistance(c1, c2);
		assertEquals(value, (float) 10 - 7);
		
		value = nd.calcDistance(c2, c1);
		assertEquals(value, (float) 10 - 7);
	}
	
	public void testNumericalDistanceWithFloat() {
		SimpleCell<Float> c1 = new SimpleCell<Float>("test1", (float)3.76);
		SimpleCell<Float> c2 = new SimpleCell<Float>("test1", (float)2.385);
		NumericalDistance nd = new NumericalDistance();
		
		float value = nd.calcDistance(c1, c2);
		assertTrue(Math.abs(value - ((float) 3.76 - 2.385)) < 0.000001 );
		
		value = nd.calcDistance(c2, c1);
		assertTrue(Math.abs(value - ((float) 3.76 - 2.385)) < 0.000001 );
	}
	
	public void testStringDistance() {
		SimpleCell<String> c1 = new SimpleCell<String>("test1", "Cat");
		SimpleCell<String> c2 = new SimpleCell<String>("test1", "Soup");
		StringDistance sd = new StringDistance();
		
		float value = sd.calcDistance(c1, c2);
		assertEquals(value, (float) 4);
		
		c2.setValue("Catch");
		
		value = sd.calcDistance(c1, c2);
		assertEquals(value, (float) 2);

		c2.setValue("Cat");
		
		value = sd.calcDistance(c1, c2);
		assertEquals(value, (float) 0);
	}
	
	public void testKNNCalculateSimpleCellInteger() {
		kNN.Tuple<Float, Cell> t1 = new kNN.Tuple<Float, Cell>((float) 1, new SimpleCell<Integer>("1", 3));
		kNN.Tuple<Float, Cell> t2 = new kNN.Tuple<Float, Cell>((float) 2, new SimpleCell<Integer>("1", 4));
		kNN.Tuple<Float, Cell> t3 = new kNN.Tuple<Float, Cell>((float) 3, new SimpleCell<Integer>("1", 5));
		
		kNN testKNN = new EuclideanKNN(ds);
		ArrayList<kNN.Tuple<Float, Cell>> list = new ArrayList<kNN.Tuple<Float, Cell>>(3);
		list.add(0, t1);
		list.add(1, t2);
		list.add(2, t3);
		
		SimpleCell<Integer> c1 = (SimpleCell<Integer>) testKNN.calculateSimpleCell(list, "Integer", "1");
		
		assertEquals(c1.getKey(), "1");
		
		assertEquals((int)c1.getValue(), 4);
	}
	
	public void testKNNCalculateSimpleCellFloat() {
		kNN.Tuple<Float, Cell> t1 = new kNN.Tuple<Float, Cell>((float) 1, new SimpleCell<Float>("1", (float) 3.545));
		kNN.Tuple<Float, Cell> t2 = new kNN.Tuple<Float, Cell>((float) 2, new SimpleCell<Float>("1", (float) 4.567));
		kNN.Tuple<Float, Cell> t3 = new kNN.Tuple<Float, Cell>((float) 3, new SimpleCell<Float>("1", (float) 5.532));
		
		kNN testKNN = new EuclideanKNN(ds);
		ArrayList<kNN.Tuple<Float, Cell>> list = new ArrayList<kNN.Tuple<Float, Cell>>(3);
		list.add(0, t1);
		list.add(1, t2);
		list.add(2, t3);
		
		SimpleCell<Float> c1 = (SimpleCell<Float>) testKNN.calculateSimpleCell(list, "Float", "1");
		
		assertEquals(c1.getKey(), "1");
		
		assertEquals((float)c1.getValue(), (float) 4.548);
	}
	
	public void testKNNCalculateSimpleCellString() {
		kNN.Tuple<Float, Cell> t1 = new kNN.Tuple<Float, Cell>((float) 1, new SimpleCell<String>("1", "Cat"));
		kNN.Tuple<Float, Cell> t2 = new kNN.Tuple<Float, Cell>((float) 2, new SimpleCell<String>("1", "Coder"));
		kNN.Tuple<Float, Cell> t3 = new kNN.Tuple<Float, Cell>((float) 3, new SimpleCell<String>("1", "Dog"));
		
		kNN testKNN = new EuclideanKNN(ds);
		ArrayList<kNN.Tuple<Float, Cell>> list = new ArrayList<kNN.Tuple<Float, Cell>>(3);
		list.add(0, t1);
		list.add(1, t2);
		list.add(2, t3);
		
		SimpleCell<String> c1 = (SimpleCell<String>) testKNN.calculateSimpleCell(list, "String", "1");
		
		assertEquals(c1.getKey(), "1");
		assertEquals(c1.getValue(), "Cat");
		
		t2.setValue2(new SimpleCell<String>("1", "Dog"));
		
		list.set(1, t2);
		c1 = (SimpleCell<String>) testKNN.calculateSimpleCell(list, "String", "1");
		
		assertEquals(c1.getValue(), "Dog");
	}
	
	public void testKNNCalculateCompositeCell() {
		
		CompositeCell cc1 = new CompositeCell("t1");
		CompositeCell cc2 = new CompositeCell("t1");
		
		SimpleCell<Integer> c1I = new SimpleCell<Integer>("s1", 44);
		SimpleCell<Float> c1F = new SimpleCell<Float>("s2", (float) 3.45);
		SimpleCell<String> c1S = new SimpleCell<String>("s3", "Cat");
		
		SimpleCell<Integer> c2I = new SimpleCell<Integer>("s1", 54);
		SimpleCell<Float> c2F = new SimpleCell<Float>("s2", (float) 4.52);
		SimpleCell<String> c2S = new SimpleCell<String>("s3", "Dog");
		
		cc1.addCell(c1I);
		cc2.addCell(c2I);
		
		cc1.addCell(c1F);
		cc2.addCell(c2F);
		
		cc1.addCell(c1S);
		cc2.addCell(c2S);
		
		ArrayList<kNN.Tuple<Float, Cell>> list = new ArrayList<kNN.Tuple<Float, Cell>>(2);
		list.add(0, new kNN.Tuple<Float, Cell>((float) 1, cc1));
		list.add(1, new kNN.Tuple<Float, Cell>((float) 2, cc2));
		
		kNN testKNN = new EuclideanKNN(ds);
		
		CompositeCell ccEnd = testKNN.calculateCompositeCell(list, cc1.getKey());
		
		assertEquals(ccEnd.getKey(), cc2.getKey());
		assertEquals((String)((SimpleCell<String>)ccEnd.getSubCell("s3")).getValue(), "Cat");
		assertTrue((float)(Math.abs((float)((SimpleCell<Float>)ccEnd.getSubCell("s2")).getValue()) - 3.985) < 0.000001);
		assertEquals((int)((SimpleCell<Integer>)ccEnd.getSubCell("s1")).getValue(), 49);
	}
	
	public void testEuclideanKNN() {
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		
		CompositeCell cc1 = new CompositeCell("t1");
		CompositeCell cc2 = new CompositeCell("t1");
		CompositeCell cc3 = new CompositeCell("t1");
		
		SimpleCell<Integer> c1I = new SimpleCell<Integer>("s1", 44);
		SimpleCell<Float> c1F = new SimpleCell<Float>("s2", (float) 3.45);
		SimpleCell<String> c1S = new SimpleCell<String>("s3", "Cat");
		
		SimpleCell<Integer> c2I = new SimpleCell<Integer>("s1", 54);
		SimpleCell<Float> c2F = new SimpleCell<Float>("s2", (float) 4.52);
		SimpleCell<String> c2S = new SimpleCell<String>("s3", "Dog");

		SimpleCell<Integer> c3I = new SimpleCell<Integer>("s1", 66);
		SimpleCell<Float> c3F = new SimpleCell<Float>("s2", (float) 5.26);
		SimpleCell<String> c3S = new SimpleCell<String>("s3", "Dog");
		
		cc1.addCell(c1I);
		cc2.addCell(c2I);
		cc3.addCell(c3I);
		
		cc1.addCell(c1F);
		cc2.addCell(c2F);
		cc3.addCell(c3F);
		
		cc1.addCell(c1S);
		cc2.addCell(c2S);
		cc3.addCell(c3S);
		
		p1.addAttribute(cc1);
		p2.addAttribute(cc2);
		p3.addAttribute(cc3);

		p1.addAttribute(new SimpleCell<Integer>("a1", 15));
		p2.addAttribute(new SimpleCell<Integer>("a1", 28));
		p3.addAttribute(new SimpleCell<Integer>("a1", 35));

		p1.addAttribute(new SimpleCell<Float>("a2", (float) 158.964));
		p2.addAttribute(new SimpleCell<Float>("a2", (float) 2.5169));
		p3.addAttribute(new SimpleCell<Float>("a2", (float) 25.369));
		
		ds.addPt(p1);
		ds.addPt(p2);
		ds.addPt(p3);
		
		ds.findStatistics();
		
		Point targetPoint = new Point();
		
		CompositeCell cc4 = new CompositeCell("t1");
		SimpleCell<Integer> c4I = new SimpleCell<Integer>("s1", 37);
		SimpleCell<Float> c4F = new SimpleCell<Float>("s2", (float) 5.92);
		SimpleCell<String> c4S = new SimpleCell<String>("s3", "Fish");
		cc4.addCell(c4I);
		cc4.addCell(c4F);
		cc4.addCell(c4S);
		targetPoint.addAttribute(cc4);
		targetPoint.addAttribute(new SimpleCell<Float>("a2", (float) 86.253));
		
		kNN testKNN = new EuclideanKNN(ds);
		
		Cell result = testKNN.findKNN("a1", targetPoint, 1);
		assertEquals((int)((SimpleCell<Integer>)result).getValue(), 35);
		
		result = testKNN.findKNN("a1", targetPoint, 2);
		assertEquals((int)((SimpleCell<Integer>)result).getValue(), 25);
		
		result = testKNN.findKNN("a1", targetPoint, 3);
		assertEquals((int)((SimpleCell<Integer>)result).getValue(), 26);
	}
}