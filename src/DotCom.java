import java.util.*;

public class DotCom {

	private String name;
	private ArrayList<String> locationCells;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public String checkYourself(String userInput) {

		String result = "Miss";
		int index = locationCells.indexOf(userInput);
		
		if(index >= 0) {
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

}