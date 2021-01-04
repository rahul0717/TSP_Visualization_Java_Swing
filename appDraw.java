import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

public class appDraw extends JFrame implements ActionListener {
	//This is the JFrame class
	// This are the class level variables

	private JFrame window;
	private JLabel distanceCoveredText;
	private JLabel distanceCovered;
	private JLabel iterationNumberText;
	private JLabel iterationNumber;
	private JPanel panelTopMain;
	private JPanel panelTopLeft;
	private JPanel panelTopRight;
	private plottingPanel panelBottom;
	private JPanel panelDistance;
	private JPanel panelIteration;
	private JPanel panelOpenBtn;
	private JPanel panelRunBtn;
	private JButton openBtn;
	private BtnRun runBtn;
	private JLabel filePathLabel;
	private JLabel startCityLabel;
	private JTextField startCityTxtBox;
	private String filePath;
	private String data;
	private float tspInput[][];
	appController controllerObj;
	private int[][] scaledTspInput;
	private int[][] sortedTspInput;
	private static int created = 0;
	private static int running = 1;
	private static int stopped = 2;
	private int[] tspResult = new int[3];
	private int startCity;
	private float minX;
	private float minY;
	private float maxX;
	private float maxY;
	private float xScaleFactor;
	private float yScaleFactor;
	private int iteration = 1;
	private Integer lastVisitedCity = null;

	// Constructor 
	public appDraw() {
//		window = new JFrame("TSP Compute");
//		controllerObj = appController.getControllerInstance();
//		drawBasicWindow();

	}
	// Function to get the controller object
	public void start(){
		window = new JFrame("TSP Compute");
		controllerObj = appController.getControllerInstance();
		drawBasicWindow();

	}

	//Function to draw the initial frame setup and components
	public void drawBasicWindow() {
		//Initializing the frame elements
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
		panelTopMain = new JPanel();
		panelBottom = new plottingPanel();
		panelTopLeft = new JPanel();
		panelTopRight = new JPanel();
		panelDistance = new JPanel();
		panelIteration = new JPanel();
		panelOpenBtn = new JPanel();
		panelRunBtn = new JPanel();
		openBtn = new JButton();
		runBtn = new BtnRun();
		distanceCoveredText = new JLabel();
		distanceCovered = new JLabel();
		iterationNumberText = new JLabel();
		iterationNumber = new JLabel();
		filePathLabel = new JLabel();
		startCityLabel = new JLabel();
		startCityTxtBox = new JTextField("0", 10);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);

		//Adding  layouts and creating the skeletal structure
		panelTopMain.setLayout(new BoxLayout(panelTopMain, BoxLayout.X_AXIS));
		panelTopLeft.setLayout(new BoxLayout(panelTopLeft, BoxLayout.Y_AXIS));
		panelTopRight.setLayout(new BoxLayout(panelTopRight, BoxLayout.Y_AXIS));
		panelDistance.setLayout(new BoxLayout(panelDistance, BoxLayout.X_AXIS));
		panelIteration.setLayout(new BoxLayout(panelIteration, BoxLayout.X_AXIS));
		panelRunBtn.setLayout(new BoxLayout(panelRunBtn, BoxLayout.X_AXIS));
		panelTopMain.setBackground(Color.lightGray);
		panelTopLeft.setBackground(Color.lightGray);
		panelTopRight.setBackground(Color.lightGray);
		panelDistance.setBackground(Color.lightGray);
		panelIteration.setBackground(Color.lightGray);
		panelOpenBtn.setBackground(Color.lightGray);
		panelRunBtn.setBackground(Color.lightGray);
		panelBottom.setBackground(Color.white);

		//Setting the size of each panel
		panelTopMain.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 100));
		panelTopLeft
				.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75), 100));
		panelTopRight
				.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25), 100));
		panelDistance
				.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25), 50));
		panelIteration
				.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25), 50));
		panelOpenBtn
				.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75), 50));
		panelRunBtn.setMaximumSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75), 50));
		

		//adding properties to the elements
		filePathLabel.setVisible(false);
		startCityLabel.setText("Start City (Default: 0): ");
		startCityLabel.setMaximumSize(new Dimension(20, 20));
		openBtn.setText("Open");
		runBtn.setText("Run");
		runBtn.setEnabled(false);
		openBtn.addActionListener(this);
		runBtn.addActionListener(this);
		distanceCoveredText.setText("Best Distance: ");
		distanceCoveredText.setAlignmentX(LEFT_ALIGNMENT);
		distanceCoveredText.setToolTipText("Shows optimum distance so far");
		distanceCovered.setText("0");
		iterationNumberText.setText("Iteration: ");
		iterationNumberText.setToolTipText("Shows the iteration in which we are in");
		iterationNumber.setText("0");

		//Adding all the elements to their respective panels and then adding the panel to the frame
		panelDistance.add(Box.createRigidArea(new Dimension(0, 20)));
		panelOpenBtn.add(openBtn);
		panelOpenBtn.add(filePathLabel);
		panelRunBtn.add(startCityLabel);
		panelRunBtn.add(startCityTxtBox);
		panelRunBtn.add(runBtn);

		panelTopLeft.add(panelOpenBtn);
		panelTopLeft.add(panelRunBtn);

		panelDistance.add(distanceCoveredText);
		panelDistance.add(distanceCovered);

		panelIteration.add(iterationNumberText);
		panelIteration.add(iterationNumber);

		panelTopRight.add(panelDistance);
		panelTopRight.add(panelIteration);

		panelTopMain.add(panelTopLeft);
		panelTopMain.add(panelTopRight);

		window.add(panelTopMain);
		window.add(panelBottom);

	}
	//Function to scale the original data
	public int[][] scaleDataPoints(){
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height -100 ;
		minX = Integer.MAX_VALUE;
		maxX = -1;
		minY = Integer.MAX_VALUE;
		maxY = -1 ;
		float xRange ;
		float yRange;
		float xScaleFactor;
		float yScaleFactor;
		int [][] scaledTspInput = new int[tspInput.length][2];
		
		for(int i= 0; i < tspInput.length; i++){
				if(tspInput[i][0] > maxX)	{
					maxX = tspInput[i][0];
				}
				if(tspInput[i][0] < minX){
					minX = tspInput[i][0];
				}
		}
		
		for(int i= 0; i < tspInput.length; i++){
			if(tspInput[i][1] > maxY)	{
				maxY = tspInput[i][1];
			}
			if(tspInput[i][1] < minY){
				minY = tspInput[i][1];
			}
		}
		xRange = maxX - minX;
		yRange = maxY - minY;
		xScaleFactor = screenWidth/xRange;
		yScaleFactor = screenHeight/yRange;
		
	
		
		for(int i= 0; i < tspInput.length; i++){
			scaledTspInput[i][0] = (int)((tspInput[i][0] - minX) * xScaleFactor);
			scaledTspInput[i][1] = (int)((tspInput[i][1] - minY) * yScaleFactor);
		}
		
		return scaledTspInput;
	}
	
	//Function to validate the start city
	public boolean validateStartCity(int startCity, int totalCity){
		if(startCity < totalCity) return true; 
		return false;
	}

	//Function to implement the action listener
	public void actionPerformed(ActionEvent e) {
		//Action listener for Open BTN
		if (e.getSource() == openBtn) {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int r = j.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				filePath = j.getSelectedFile().getAbsolutePath();
				if(filePath.contains(".tsp")){
					filePathLabel.setText(filePath);
					filePathLabel.setVisible(true);
					data = controllerObj.readFile(filePath);
					tspInput = controllerObj.processSymmetricData(data);
					scaledTspInput = new int[tspInput.length][2];
					scaledTspInput = scaleDataPoints();
					sortedTspInput = controllerObj.sortData(scaledTspInput);
					controllerObj.initialiseVisitedNodes(sortedTspInput.length);
					panelBottom.plotPoint();
					runBtn.setEnabled(true);
				}else{
					filePathLabel.setText("Please check the file which you have chosen");
					filePathLabel.setVisible(true);					
				}
				
				
			}
		}
		////Action listener for Run BTN
		else if (e.getSource() == runBtn) {
			try{
				startCity = Integer.parseInt(startCityTxtBox.getText());
				if(lastVisitedCity == null) {
					lastVisitedCity = startCity;
				}
				controllerObj.computeGetCtrlObj();
				if (sortedTspInput != null && validateStartCity(startCity,sortedTspInput.length)) {
					if(runBtn.getBtnState() == created || runBtn.getBtnState() == stopped ){
						openBtn.setEnabled(false);
						runBtn.setBtnState(running);
						runBtn.setText("Stop");
						Thread t = new Thread(new tspHandeler());
						t.start();			
					}
					else if(runBtn.getBtnState() == running){
						runBtn.setBtnState(stopped);
						runBtn.setText("Run");
					}

				} else {
					startCityTxtBox.setText("Please check the city number you have entered");
				}

			}catch(NumberFormatException ex){
				startCityTxtBox.setText("Please enter a number less than the total cities");
			}
			
			
		}
	}
	//Inner class to implement Runnable
	public class tspHandeler implements Runnable{

		//Creating a new thread which will handle the drawing lines functionality
		@Override
		public void run() {
			int p1X, p1Y, p2X, p2Y;
			int [][] sortedOriginalData ;
			String distance;
			while(runBtn.getBtnState() == running&&!controllerObj.checkVisitedNodes() && lastVisitedCity < sortedTspInput.length){
				tspResult = controllerObj.tspCompute(lastVisitedCity, sortedTspInput);
				sortedOriginalData = controllerObj.sortTspInputOriginal(tspInput);
				controllerObj.tspComputeOriginalData(lastVisitedCity, sortedOriginalData);
				p1X = sortedTspInput[lastVisitedCity][0];
				p1Y = sortedTspInput[lastVisitedCity][1];
				p2X = tspResult[1];
				p2Y = tspResult[2];
				panelBottom.drawLine(p1X,p1Y, p2X,p2Y);
				distance = controllerObj.getMinCost() + "";
				distanceCovered.setText(distance);
				iterationNumber.setText(iteration+"");
				lastVisitedCity = tspResult[0];
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				iteration ++;
			}
			
		}
		
	}
	

	


}
