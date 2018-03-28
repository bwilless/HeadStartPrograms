import javax.swing.*;
import java.awt.event.*;

public class Button {
	
	JFrame frame;
	JButton button;
	
	public static void main (String[] args) {
		
		Button buttonApp = new Button();
		buttonApp.setupGui();
		buttonApp.go();
	}
	
	public void go() {
		
		// Create a button
		button = new JButton();
		button.setText("Brian's Button!");
		button.addActionListener(new innerButtonHandler());
		
		// Add it to the frame
		frame.getContentPane().add(button);
	}
	
	public void setupGui() {
		
		frame = new JFrame("This is my test application");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	class innerButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			if(button.getText().equals("Brian's Button!")) {
				button.setText("Dexter's Button!");
			} else if (button.getText().equals("Dexter's Button!")) {
				button.setText("Brian's Button!");
			}
		}
	}
}
