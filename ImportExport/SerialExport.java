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
	

}
