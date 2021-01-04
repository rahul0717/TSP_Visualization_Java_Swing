
public class appController {
	//Controller class which delegates all the method call of other class
	// Here we are implementing the Singleton Design pattern.
	//Class variables
	appDataProcessing processObj;
	appDataRead readObj;
	appDraw drawObj;
	appComputeTSP tspObj;
	int[][] sData;
	static appController controllerObj = null;
	//The Constructor is made private to implement the design pattern.
	// We will use constructor to access all the cross-class methods calls.
	private appController(appDraw draw){
		processObj = new appDataProcessing();
		readObj = new appDataRead();
		drawObj = draw;
		tspObj = new appComputeTSP();
	}
	
	//Method to call the get control object method.
	public void computeGetCtrlObj(){
		tspObj.computeGetCtrlObj();
	}
	
	//Method which other class can utilize to access the single object instance 
	public static appController getControllerInstance(appDraw draw){
		if(controllerObj == null){
			controllerObj  = new appController(draw);
		}
		return controllerObj;
	}
	//Method to return the created controller object
	public static appController getControllerInstance(){
		return controllerObj;
	}
	
	//Method to read the file
	public String readFile(String filePath){
		String data;
		data = readObj.Readfile(filePath);
		return data;		
	}
	
	//Method to process the symmetric data string
	public float[][] processSymmetricData(String data){
		float [][] tspData;
		tspData = processObj.processSymmetricData(data);
		return tspData;
	}
	
	//Method to calculate the distance between two points
	public int findDistance(int p1X, int p1Y, int p2X, int p2Y){
		int distance = processObj.findDistance(p1X, p1Y, p2X, p2Y);
		return distance;
	}
	
	//Method to sort the scaled data
	public int[][] sortData(int[][] tspScaledData){
		int[][] sortedData= processObj.sortTspInput(tspScaledData);
		sData = sortedData;
		return sortedData;
	}
	
	//Method to sort the original TSP data to match the sorted-scaled data
	public int[][] sortTspInputOriginal(float[][] tspOriginalData){
		int[][] sortedOriginalData= processObj.sortTspInputOriginal(tspOriginalData);
		return sortedOriginalData;
	}
	//Method to return sorted data
	public int[][] getSortedData(){
		return sData;
	}
	
	//Method initialize the visited data nodes array
	public void initialiseVisitedNodes(int dataSize){
		tspObj.initialiseVisitedNodes(dataSize);
	}
	
	//Method to check if all the nodes are visited
	public boolean checkVisitedNodes(){
		return tspObj.checkVisitedNodes();
	}
	//Method to compute the path with scaled pointed to draw
	public int[] tspCompute(int startCity, int[][] sortedData){
		return tspObj.tspCompute(startCity, sortedData);
	}
	
	//Method to compute the path with original pointed to display the actual distance
	public void tspComputeOriginalData(int startCity, int[][] tspOriginalData){
		tspObj.tspComputeOriginalData(startCity, tspOriginalData);
	}
	
	//Method to get the minimum distance
	public int getMinCost(){
		return tspObj.getMinCost();
	}


}
