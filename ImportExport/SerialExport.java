package ImportExport;

import DataModel.CellComposite;
import DataModel.CellSimple;
import DataModel.DimensionalSpace;
import DataModel.Point;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * SerialExport exports any data model to a serialized format in a certain file
 * 
 * @author Andrew Vicencio
 */
public class SerialExport {
	/**
	 * exportDimensionalSpace exports a DimensionalSpace to a file in a serialized format
	 * 
	 * @param file: The file to be exported to
	 * @param ds: The DimensionalSpace to be serialized and exported
	 * @throws IOException
	 */
	public static void exportDimensionalSpace(File file, DimensionalSpace ds) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(ds);
		out.close();
	}
	
	/**
	 * exportDimensionalSpace allows for some flexibility for the rest of the team. Instead of
	 * having to choose a file, a file path can be used instead.
	 * 
	 * @param filename: The file path to be used for the exported file
	 * @param ds: The DimensionalSpace to be serialized and exported
	 * @throws IOException
	 */
	public static void exportDimensionalSpace(String filename, DimensionalSpace ds) throws IOException {
		exportDimensionalSpace(new File(filename), ds);
	}
	
	/**
	 * exportPoint allows for some flexibility for the rest of the team. Instead of having to
	 * choose a file, a file path can be used instead.
	 * 
	 * @param filename: The file path to be used for the exported file
	 * @param pt: The point to be exported
	 * @throws IOException
	 */
	public static void exportPoint(String filename, Point pt) throws IOException {
		exportPoint(new File(filename), pt);
	}
	
	/**
	 * exportPoint exports a Point to a file in a serialized format
	 * 
	 * @param file: The file to be exported to
	 * @param pt: The point to be exported
	 * @throws IOException
	 */
	public static void exportPoint(File file, Point pt) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(pt);
		out.close();
	}
	

}
