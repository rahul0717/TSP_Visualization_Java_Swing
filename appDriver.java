import java.awt.*;

import javax.swing.JFrame;
public class appDriver {

	public static void main(String[] args) {
		appDraw window = new appDraw();
		appController controllerObj = appController.getControllerInstance(window);
		controllerObj.drawObj.start();
	}

}
