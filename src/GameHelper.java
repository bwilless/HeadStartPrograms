import java.io.*;
import java.util.*;

public class GameHelper {

	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize1 = 49;
	private int [] grid = new int[gridSize1];
	private int comCount = 0;
	
	public ArrayList<String> placeDotCom(int comSize){
		
		ArrayList<String> alphaCells = new ArrayList<String>();
		String temp = null;
		int [] coords = new int[comSize];
		int attempts = 0;
		boolean success = false;
		int location = 0;
		
		comCount++;
		int incr = 1;
		if((comCount % 2) == 1) {
			incr = gridLength;
		}
	
		while(!success & attempts++ < 200) {
			location = (int) (Math.random() * gridSize1);
			
			int x = 0;
			success = true;
			while (success && x < comSize) {
				if(grid[location] == 0) {
					coords[x++] = location;
					location += incr;
					if(location >= gridSize1) {
						success = false;
					}
					if(x > 0 && location % gridLength == 0) {
						success = false;
					}
				} else {
					success = false;
				}
			}
		}
	
		int x = 0;
		int row = 0;
		int column = 0;
		
		while(x < comSize) {
			grid[coords[x]] = 1;
			row = (int) (coords[x] / gridLength);
			column = coords[x] % gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		}
		return alphaCells;
	}
}
