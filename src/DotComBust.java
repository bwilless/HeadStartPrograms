import java.util.ArrayList;

public class DotComBust {

	// Declare, instantiate, and initialize the private variables	
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	private	int numGuesses = 0;                           		

	private void setUpGame() {

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
//			System.out.println("Setting up " + dotComToSet.getName());
		}
		
	}
	
	private void startPlaying() {
		
		while(!dotComList.isEmpty()) {
			String userGuess = helper.getUserInput("Enter a guess: ");
			checkUserGuess(userGuess);
			
		}
		
		finishGame();
	}
	
	
	private void checkUserGuess(String userGuess) {
		

		numGuesses++;
		String result = "Miss";
		
		for(int i = 0; i < dotComList.size(); i++) {
			
//				System.out.println("Checking: " + dotComList.get(i).getName());
			
				result = dotComList.get(i).checkYourself(userGuess);
				if(result == "Hit") {
					break;
				}
				if(result == "Kill") {
					dotComList.remove(i);
					break;
			}
				
		}
		
		System.out.println(result);
		
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
	
}
