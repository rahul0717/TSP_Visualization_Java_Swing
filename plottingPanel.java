import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//Our Panel class in which we plot and draw the lines.
public class plottingPanel extends JPanel{
	//Class Variables
	appController controllerObj;
	private BufferedImage image = new BufferedImage(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height - 100, BufferedImage.TYPE_INT_ARGB);
	//Constructor
	public plottingPanel(){
		this.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height - 100));
		controllerObj = appController.getControllerInstance();

	}
	//Function to call the repaint method
	public void draw(){
		this.repaint();
	}
	
	//Overriding the paintComponent to call buffered image object
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
		{
			g.drawImage(image, 0, 0, null);
		}
		
		
	}
	
	//Function to plot points
	public void plotPoint(){
		
		int[][] sortedData = controllerObj.getSortedData();
		Graphics2D graphicsObj = (Graphics2D)image.getGraphics();
		if(sortedData!= null){
			for(int i =0; i< sortedData.length; i++){
				graphicsObj.setColor(Color.blue);
				graphicsObj.fillOval(sortedData[i][0], sortedData[i][1], 10, 10);
			}
		} else {
			System.out.println("scd is null");
		}
		repaint();
		
	}
	
	//Function to draw the line
	public void drawLine(int x1,int y1, int x2,int y2){
		Graphics2D graphicsObj = (Graphics2D)image.getGraphics();
		graphicsObj.setColor(Color.red);
		graphicsObj.drawLine(x1+5, y1+5, x2+5, y2+5);
		repaint();

	}
	
	

}
