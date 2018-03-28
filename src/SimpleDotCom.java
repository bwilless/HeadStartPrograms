import java.util.Random;
import java.util.ArrayList;

public class SimpleDotCom {

	private ArrayList<String> locationCells;
	
	public String checkYourself(String userInput) {

		String result = "Miss";
		
		int index = locationCells.indexOf(userInput);
		
		if(index >- 0) {
			
			locationCells.remove(index);
		
			if(locationCells.isEmpty()) {
				result = "Kill";
			}
			else {
				result = "Hit";
			}
		}

		return result;
	}
		
	public void setLocation(ArrayList<String> loc) {
		

		locationCells = loc;
		
	}
	
	
	public static void main(String[] args) {

		int numGuesses = 0;                           		
		String result = ""; 
		String guess;

		int randomNum = (int) (Math.random() * 5);							
		String randomString = Integer.toString(randomNum);
		//ArrayList<String> locations = {"1", "2", "3"};
		
		SimpleDotCom dot = new SimpleDotCom();      	
		GameHelper helper = new GameHelper();
		
	//	dot.setLocation(locations);
		
		while(result != "Kill")
		{
			
			guess = helper.getUserInput("Enter a number");
			result = dot.checkYourself(guess);
			System.out.println(result);

			numGuesses++;
		}

		System.out.println("You took " + numGuesses + " guesses");
				
	}
	

}
