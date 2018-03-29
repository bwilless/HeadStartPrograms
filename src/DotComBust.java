import java.util.ArrayList;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;


public class DotComBust implements ActionListener {

	// Declare, instantiate, and initialize the private variables	
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	private	int numGuesses = 0;                           		
	
	private JFrame frame;
	
	enum Result {HIT, MISS, KILL}
	
	private void setUpGame() {

		frame = new JFrame("Game of Killer.Com");
		
		JPanel panel = new JPanel();
		GamePiece[] gameBoard = new GamePiece[49];

		char alpha = 'a';
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(365, 262);
		frame.setVisible(true);

		// Create the DotCom objects		
		DotCom dc1 = new DotCom();
		DotCom dc2 = new DotCom();
		DotCom dc3 = new DotCom();
		
		// Set the names for thre DotCom objects
		dc1.setName("Willess.Com");
		dc2.setName("Google.com");
		dc3.setName("Mitel.com");

		//  Add the DotCom objects to our ArrayList
		dotComList.add(dc1);
		dotComList.add(dc2);
		dotComList.add(dc3);

		// Output simple instructions		
		System.out.println("Welcome to the game DotComBust!");
		System.out.println("The object of the game is to sink 3 dot.com's");
		System.out.print(dc1.getName() + " " + dc1.getName() + " " + dc3.getName() + "\n");
				System.out.println("Try to sink them all in the fewest number of guesses");
		
		for(DotCom dotComToSet: dotComList) {
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocation(newLocation);
		}
		
	}
	
	private void startPlaying() {

		while(!dotComList.isEmpty()) {
		}
		
		finishGame();
	}
	
	
	private Result checkUserGuess(String userGuess) {
		
		numGuesses++;
		Result retVal = Result.MISS;
		String result = "Miss";
		
		for(int i = 0; i < dotComList.size(); i++) {
			
				result = dotComList.get(i).checkYourself(userGuess);
				
				if(result == "Hit") {
					retVal = Result.HIT;
					break;
				}
				if(result == "Kill") {
					dotComList.remove(i);
					retVal = Result.KILL;
					break;
			}
		}
		
		return retVal;
	}
	
	private void finishGame() {
		System.out.println("All Dot Coms are dead!  Your stock options are worthless!");
		if (numGuesses <= 18) {
			System.out.println("it only too you " + numGuesses + " guesses.");
			System.out.println("You got out before your options sank.");
		} else {
			System.out.println("Too you long enough. " + numGuesses + " guesses.");
			System.out.println("Fish are dancing with your options!");
		}
	}
	
	
	public static void main(String[] args) {

		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlaying();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		GamePiece piece = (GamePiece) e.getSource();
		Result myResult = checkUserGuess(piece.cellLabel);
		Color newColor = null; 
		
		if (Result.HIT == myResult){
			newColor = Color.RED;
		} else if (Result.KILL == myResult){
			newColor = Color.RED;
		} else if (Result.MISS == myResult){
			newColor = Color.GRAY;
		}
	
		piece.setBackground(newColor);
		
		if(dotComList.isEmpty()) {
			finishGame();
		}
		
	}
	
	// @todo  
	
	class GamePiece extends JButton {
		
		 private String cellLabel;
		 int gameBoardIndex;
		 
		 GamePiece() {
			 super("   ");
		 }
		
	}
	
	
}
