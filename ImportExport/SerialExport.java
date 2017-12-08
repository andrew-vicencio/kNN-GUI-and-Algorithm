package ImportExport;

import DataModel.CellComposite;
import DataModel.CellSimple;
import DataModel.DimensionalSpace;
import DataModel.Point;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerialExport {
	public void exportDimensionalSpace(File file, DimensionalSpace ds) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(ds);
		out.close();
	}
	
	public void exportDimensionalSpace(String filename, DimensionalSpace ds) throws IOException {
		exportDimensionalSpace(new File(filename), ds);
	}
	
	public void exportPoint(String filename, Point pt) throws IOException {
		exportPoint(new File(filename), pt);
	}
	
	public void exportPoint(File file, Point pt) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(pt);
		out.close();
	}
	
	public static void main(String[] args) {
		DimensionalSpace ds = new DimensionalSpace();
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		
		CellComposite cc1 = new CellComposite("t1");
		CellComposite cc2 = new CellComposite("t1");
		CellComposite cc3 = new CellComposite("t1");
		
		CellSimple<Integer> c1I = new CellSimple<Integer>("s1", 44);
		CellSimple<Float> c1F = new CellSimple<Float>("s2", (float) 3.45);
		CellSimple<String> c1S = new CellSimple<String>("s3", "Cat");
		
		CellSimple<Integer> c2I = new CellSimple<Integer>("s1", 54);
		CellSimple<Float> c2F = new CellSimple<Float>("s2", (float) 4.52);
		CellSimple<String> c2S = new CellSimple<String>("s3", "Dog");

		CellSimple<Integer> c3I = new CellSimple<Integer>("s1", 66);
		CellSimple<Float> c3F = new CellSimple<Float>("s2", (float) 5.26);
		CellSimple<String> c3S = new CellSimple<String>("s3", "Dog");
		
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

		p1.addAttribute(new CellSimple<Integer>("a1", 15));
		p2.addAttribute(new CellSimple<Integer>("a1", 28));
		p3.addAttribute(new CellSimple<Integer>("a1", 35));

		p1.addAttribute(new CellSimple<Float>("a2", (float) 158.964));
		p2.addAttribute(new CellSimple<Float>("a2", (float) 2.5169));
		p3.addAttribute(new CellSimple<Float>("a2", (float) 25.369));
		
		ds.addPt(p1);
		ds.addPt(p2);
		ds.addPt(p3);
		SerialExport SE= new SerialExport();
		try {
			SE.exportDimensionalSpace("dimensionalSpace.txt", ds);
			SE.exportPoint("point.txt", p1);
		} catch (IOException e) {
			System.out.println("There was an IOException");
		}
		System.out.println("Done");
	}
}
