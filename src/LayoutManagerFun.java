import javax.swing.*;
import java.awt.*;


public class LayoutManagerFun {

	// Boarder layout N,S,E,W, and center regions for JFrame
	// Flow layout word processor style with word wrap for JPanel
	// Box layout stacks components (widgets)

	public static void main (String[] args) {
		
		LayoutManagerFun gui = new LayoutManagerFun();
		gui.go();
	}

	public void go() {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton bN = new JButton("North");
		JButton bS = new JButton("South");
		JButton bW = new JButton("West");
		JButton bE = new JButton("East");
		JButton b1 = new JButton("Button1");
		JButton b2 = new JButton("Button2");
		JButton b3 = new JButton("Button3");

		JButton bCenter = new JButton("Center");

		panel.add(bE);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		
		frame.getContentPane().add(BorderLayout.EAST, panel);
		
		frame.getContentPane().add(BorderLayout.NORTH, bN);
		frame.getContentPane().add(BorderLayout.SOUTH, bS);
		frame.getContentPane().add(BorderLayout.WEST, bW);
		frame.getContentPane().add(BorderLayout.CENTER, bCenter);
		
		frame.setSize(200, 200);
		frame.setVisible(true);
		
	}
	
	
}
