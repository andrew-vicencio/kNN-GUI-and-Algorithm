package ImportExport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import DataModel.DimensionalSpace;
import DataModel.Point;

public class SerialImport {
	public static DimensionalSpace importDimensionalSpace(String filename) throws IOException {
		return importDimensionalSpace(new File(filename));
	}
	
	public static DimensionalSpace importDimensionalSpace(File file) throws IOException {
		Object obj = null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		try {
			obj = in.readObject();
		} catch (Exception e) {
			System.out.println("No object in InputStream");
			in.close();
			throw new IOException();
		}
		
		in.close();
		if (obj instanceof DimensionalSpace) {
			return (DimensionalSpace) obj;
		}
		return null;
	}
	
	public static Point importPoint(String filename) throws IOException {
		return importPoint(new File(filename));
	}
	
	public static Point importPoint(File file) throws IOException {
		Object obj = null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		try {
			obj = in.readObject();
		} catch (Exception e) {
			System.out.println("No object in InputStream");
			in.close();
			throw new IOException();
		}
		
		in.close();
		if (obj instanceof Point) {
			return (Point) obj;
		}
		return null;
		
	}
}
