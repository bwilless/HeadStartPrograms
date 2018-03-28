
public class SimpleDotComTestDrive {

	public static void main(String[] args) {
	
		// Variable declatations
		String result;
		int[] locations = {1,2,3};
		
		// Instantiate a new object
		SimpleDotCom dot = new SimpleDotCom();
				
		// Set the dotCom location
//		dot.setLocation(locations);
		
		// input a simulated user guess and get the result from the class method.
		String guess = "1";
		result = dot.checkYourself(guess);
		
		String testResult = "failed";
		
		
		// validate the results
		if(result == "Hit") {
			testResult = "passed";
		}
		
		// output the test result
		System.out.println("Test " + testResult);
		
	}	
}

