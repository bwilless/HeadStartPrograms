import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TextArea1 implements ActionListener {

	JFrame frame;
	private final int BOARD_SIZE = 49;
	
	
	public static void main (String[] agrs) {
		
		TextArea1 gui = new TextArea1();
		gui.go();
	}
	
	public void go() {
		frame = new JFrame();
		JPanel panel = new JPanel();
		GamePiece[] gameBoard = new GamePiece[49];

		char alpha = 'A';
		int arrayIndex = 0;
		
		for(int j = 0; j < 7; j++) {
			for (int i = 0; i < 7; i++) {

				gameBoard[arrayIndex] = new GamePiece();
				gameBoard[arrayIndex].addActionListener(this);
				gameBoard[arrayIndex].cellLabel = alpha + Integer.toString(i);
				gameBoard[arrayIndex].gameBoardIndex = arrayIndex;
				panel.add(gameBoard[arrayIndex]);
				arrayIndex++;
			}
			
			alpha +=1;
			
		}
		

		frame.getContentPane().add(BorderLayout.CENTER, panel);
		
		frame.setSize(365, 262);
		frame.setVisible(true);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Frame Size: " + frame.getSize());
		GamePiece piece = (GamePiece) e.getSource();
		piece.setBackground(Color.GREEN);
	}
	
		
	class GamePiece extends JButton {
		
		 private String cellLabel;
		 int gameBoardIndex;
		 
		 GamePiece() {
			 super("   ");
		 }
		
	}
	
}
