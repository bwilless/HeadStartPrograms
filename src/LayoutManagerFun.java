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
		
		JButton bN = new JButton("North");
		JButton bS = new JButton("South");
		JButton bE = new JButton("East");
		JButton bW = new JButton("West");
		JButton bCenter = new JButton("Center");

		frame.getContentPane().add(BorderLayout.NORTH, bN);
		frame.getContentPane().add(BorderLayout.SOUTH, bS);
		frame.getContentPane().add(BorderLayout.EAST, bE);
		frame.getContentPane().add(BorderLayout.WEST, bW);
		frame.getContentPane().add(BorderLayout.CENTER, bCenter);
		
		frame.setSize(200, 200);
		frame.setVisible(true);
		
	}
	
	
}
