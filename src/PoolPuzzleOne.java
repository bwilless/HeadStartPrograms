
public class PoolPuzzleOne {

	public static void main(String[] args) {
		
		int x = 0;
		
		while(/* add line here */ x < 4) {
			
			// add line here
			System.out.print("a");
			
			if ( x < 1) {
				
				// add line here
			    //System.out.print("a");
			    //System.out.print(" ");
			    //System.out.print("noise");
				
				System.out.print(" pass 1");
			}
			
			// add line here
			
				
			if (/* add line here */ x > 0 ){
			
				// add line here
				System.out.print(" pass 2");
			}
			
			if (/* add line here */  x > 3) {
			
				// add line here
				System.out.print(" pass 3");
			}
		
			System.out.print(" x = " + x);
			
			System.out.println("");
		
			// add line here 
			x = x + 1;
			
	
		}
	}
}


/* 
 *  x > 0
 *  x > 1
 *  x > 3
 *  
 *  x < 1
 *  				x < 4
 * 
 *  				x = x + 1;
 *  x = x + 2;
 
 *  x = x - 1;
 *  x = x - 2;
 * 
 */









