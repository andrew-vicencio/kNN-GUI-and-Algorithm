package ImportExport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import DataModel.DimensionalSpace;
import DataModel.Point;

/**
 * SerialImport utilizes the Serializable interface to import DimensionalSpaces from previous uses of the program
 * 
 * @author Andrew Vicencio
 */
public class SerialImport {
	/**
	 * This importDimensionalSpace provides flexibility to the rest of the team. Instead of using a File
	 * object, it allows a file path to be used instead.
	 * 
	 * @param filename: The file path of the file to be imported from
	 * @return		The new DimensionalSpace to be used in calculations
	 * @throws IOException
	 */
	public static DimensionalSpace importDimensionalSpace(String filename) throws IOException {
		return importDimensionalSpace(new File(filename));
	}
	
	/**
	 * importDimensionalSapce imports a Serializable object from the InputStream. If this object is
	 * a DimensionalSpace object, it is returned to be used in the program.
	 * 
	 * @param file: The file to be imported from
	 * @return		The new DimensionalSpace to be used in calculations
	 * @throws IOException
	 */
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
	
	/**
	 * This importDimensionalSpace provides flexibility to the rest of the team. Instead of using a File
	 * object, it allows a file path to be used instead.
	 * 
	 * @param filename: The file path of the file to be imported from
	 * @return		The new Point to be used in the program
	 * @throws IOException
	 */
	public static Point importPoint(String filename) throws IOException {
		return importPoint(new File(filename));
	}
	
	/**
	 * importDimensionalSapce imports a Serializable object from the InputStream. If this object is
	 * a DimensionalSpace object, it is returned to be used in the program.
	 * 
	 * @param file: The file to be imported from
	 * @return		The new Point to be used in the program
	 * @throws IOException
	 */
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
