import javax.swing.JButton;

//JButton class
public class BtnRun extends JButton{
	public int state;
	public static int created = 0;
	//We will add this State property to the button to record the current state
	public BtnRun(){
		state = created;
		
	}
	//Method to access the current button state
	public int getBtnState(){
		return state;
	}
	
	//Method to update the button state
	public void setBtnState(int newState){
		state = newState;
	}
	
	
	
	
	

}
