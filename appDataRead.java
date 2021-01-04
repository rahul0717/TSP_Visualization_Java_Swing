import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class appDataRead {
	public String Readfile(String Filepath) {
		// This the module to read the data from the file specified in the path.
		String data = "";
		String dimension = "";
		String currentLine = "";
		String[] tempSplitArray;
		boolean dataStart = false;
		try {
			File myObj = new File(Filepath);
			Scanner myReader = new Scanner(myObj);
			boolean symmetricData = false;
			while (myReader.hasNextLine()) {
				currentLine = myReader.nextLine();
				// The string "xxx" is used as an delimiter for the symmetrical
				// Data

				if (dataStart) {
					data += currentLine + "xxx";
				}
				// We use the header information to extract the dimension from
				// the file
				// We also use the header information to identify the start of
				// the actual data
				if (currentLine.contains("NODE_COORD_SECTION")) {
					dataStart = true;
				}

				if (currentLine.contains("DIMENSION")) {
					tempSplitArray = currentLine.split(" ");
					dimension = tempSplitArray[tempSplitArray.length - 1];
					data += Boolean.toString(symmetricData) + "xxx" + dimension + "xxx";
				}

			}
//			System.out.println(data);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		// The data is passed on to the Data Processing Module in the form of a
		// String for processing.
		return data;

	}
	


}
