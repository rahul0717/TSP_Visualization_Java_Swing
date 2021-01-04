
public class appComputeTSP {
	// Class variables
	private int minCost = 0;
	private int firstCity =0;
	private int firstCityOriginal =0;
	private boolean setFirstCity = false;
	private boolean setFirstCityOriginal = false;
	private int totalNodeVisited = 0;
	private int totalNodeVisitedOriginal = 0;
	private boolean visitedNodes [] ;
	private boolean visitedNodesOriginal [] ;
	appController controllerObj;
	
	// Function to get the controller Object
	public void computeGetCtrlObj(){
		controllerObj = appController.getControllerInstance();
	}
	//Function to check if the Controller Object is created properly
	public void printCttlObj(){
		System.out.println(controllerObj);
	}
	
	//Function to initialize the VisideNode Boolean array
	public void initialiseVisitedNodes(int dataSize){
		visitedNodes = new boolean[dataSize]; 
		visitedNodesOriginal = new boolean[dataSize]; 
	}
	
	//Function to check to check if all nodes are visited for sorted data
	public boolean checkVisitedNodes(){
		for(boolean b : visitedNodes) if(!b) return false;
	    return true;
	}
	//Function to check to check if all nodes are visited for original data
	public boolean checkVisitedNodesOriginal(){
		for(boolean b : visitedNodesOriginal) if(!b) return false;
	    return true;
	}
	
	//Function to get the minCost to complete the path
	public int getMinCost(){
		return minCost;
	}
	
	//Function to compute the path with the Sorted Scaled data
	public int[] tspCompute(int startCity, int[][] sortedData){
		int scaledMinCost = 0;
		int[]nextCity = new int[3];
		int j = 0;
		int min = Integer.MAX_VALUE;
		int curDistance = 0;
		int currentMin = 0;
		visitedNodes[startCity] = true;
		if(!setFirstCity){
			firstCity = startCity;
			setFirstCity = true;
		}
		
		while (j < sortedData.length) {
			if (totalNodeVisited >= sortedData.length || checkVisitedNodes()) {
				break;
			}
			if (j != startCity && visitedNodes[j] != true) {
				int p1X = sortedData[startCity][0];
				int p1Y = sortedData[startCity][1];
				int p2X = sortedData[j][0];
				int p2Y = sortedData[j][1];
				
				curDistance = controllerObj.findDistance(p1X, p1Y, p2X, p2Y);

				if (curDistance < min) {
					min = curDistance;
					nextCity[0] = j;
					nextCity[1] = p2X;
					nextCity[2] = p2Y;
					currentMin = min;
				}
			}
			j++;
		}
		totalNodeVisited ++;
		if(checkVisitedNodes()){
			int p1X = sortedData[firstCity][0];
			int p1Y = sortedData[firstCity][1];
			int p2X = sortedData[startCity][0];
			int p2Y = sortedData[startCity][1];
			curDistance = controllerObj.findDistance(p1X, p1Y, p2X, p2Y);
			scaledMinCost += curDistance;
			nextCity[0] = firstCity;
			nextCity[1] = sortedData[firstCity][0];
			nextCity[2] = sortedData[firstCity][1];
		}else{
			scaledMinCost += currentMin;
		}
		return nextCity;
	}
	
	//Function to compute the actual distance with the original data
	public void tspComputeOriginalData(int startCity, int[][] tspOriginalData){
		int j = 0;
		int min = Integer.MAX_VALUE;
		int curDistance = 0;
		int currentMin = 0;
		visitedNodesOriginal[startCity] = true;
		if(!setFirstCityOriginal){
			firstCityOriginal = startCity;
			setFirstCityOriginal = true;
		}
		
		while (j < tspOriginalData.length) {
			if (totalNodeVisitedOriginal >= tspOriginalData.length || checkVisitedNodesOriginal()) {
				break;
			}
			if (j != startCity && visitedNodesOriginal[j] != true) {
				int p1X = tspOriginalData[startCity][0];
				int p1Y = tspOriginalData[startCity][1];
				int p2X = tspOriginalData[j][0];
				int p2Y = tspOriginalData[j][1];
				
				curDistance = controllerObj.findDistance(p1X, p1Y, p2X, p2Y);

				if (curDistance < min) {
					min = curDistance;
					currentMin = min;
				}
			}
			j++;
		}
		totalNodeVisitedOriginal ++;
		if(checkVisitedNodesOriginal()){
			int p1X = tspOriginalData[firstCityOriginal][0];
			int p1Y = tspOriginalData[firstCityOriginal][1];
			int p2X = tspOriginalData[startCity][0];
			int p2Y = tspOriginalData[startCity][1];
			curDistance = controllerObj.findDistance(p1X, p1Y, p2X, p2Y);
			minCost += curDistance;
		}else{
			minCost += currentMin;
		}
	}

}
