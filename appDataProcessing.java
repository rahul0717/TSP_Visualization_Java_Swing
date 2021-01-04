import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class appDataProcessing {
	// The processSymmetricData function is used to process the symmetric
	// coordinates data and form a matrix.
	public float[][] processSymmetricData(String data) {
		String[] splitData = data.split("xxx");
		String temp = "";
		int dimension = Integer.parseInt(splitData[1]);
		float[][] coordinates = new float[dimension][2];
		String[] currentCoordinate;
		for (int i = 2; i < splitData.length; i++) {
			if (!splitData[i].contains("EOF")) {
				currentCoordinate = splitData[i].split(" ");
				coordinates[i - 2][0] = Float.parseFloat(currentCoordinate[1]);
				coordinates[i - 2][1] = Float.parseFloat(currentCoordinate[2]);
				// System.out.println(coordinates[i - 2][0] + " " +
				// coordinates[i - 2][1]);
			}
		}
		return coordinates;

	}

	// The findDistance function is used to find the distance between two given
	// coordinates.
	public int findDistance(int p1X, int p1Y, int p2X, int p2Y) {
		int distance = (int) (Math.sqrt(Math.pow((p2X - p1X), 2) + Math.pow((p2Y - p1Y), 2)));
		return distance;

	}
	

	//Method to sort the scaled input
	public int[][] sortTspInput(int[][] TspInput) {
		int[][] sortedData = new int[TspInput.length][2];
		ArrayList<Point> ptsList = new ArrayList<>();
		for(int i = 0; i < TspInput.length; i++) {
		    int x = TspInput[i][0];
		    int y = TspInput[i][1];
		    ptsList.add(new Point(x, y));
		}

		Collections.sort( ptsList, new Comparator<Point>() {
		       public int compare(Point x1, Point x2) {
		         int result = Double.compare(x1.getY(), x2.getY());
		         if ( result == 0 ) {
		           result = Double.compare(x1.getX(), x2.getX());
		         } 
		         return result;
		      }
		    });
		for (int i = 0; i < ptsList.size(); i++) {
	        int x = (int)ptsList.get(i).getX();
	        int y = (int) ptsList.get(i).getY();
	        sortedData[i][0] = x;
	        sortedData[i][1] = y;
	    }
		return sortedData;

	}
	
	//Method to sort the original coordinates
	public int[][] sortTspInputOriginal(float[][] TspInput) {
		int[][] sortedData = new int[TspInput.length][2];
		ArrayList<Point> ptsList = new ArrayList<>();
		for(int i = 0; i < TspInput.length; i++) {
		    int x = (int)TspInput[i][0];
		    int y = (int)TspInput[i][1];
		    ptsList.add(new Point(x, y));
		}

		Collections.sort( ptsList, new Comparator<Point>() {
		       public int compare(Point x1, Point x2) {
		         int result = Double.compare(x1.getY(), x2.getY());
		         if ( result == 0 ) {
		           result = Double.compare(x1.getX(), x2.getX());
		         } 
		         return result;
		      }
		    });
		for (int i = 0; i < ptsList.size(); i++) {
	        int x = (int)ptsList.get(i).getX();
	        int y = (int) ptsList.get(i).getY();
	        sortedData[i][0] = x;
	        sortedData[i][1] = y;
	    }
		return sortedData;

	}

}
