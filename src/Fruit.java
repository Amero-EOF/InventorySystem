import java.util.Scanner;
/**
 * This class is a fruit, and is a child of it's parent class FoodItem.
 * Student Name: Amero Defranco
 * Student Number: 040935555
 * Course: CST8130 - Data Structures
 * Date: 19/11/17
 * @author Amero Defranco
 *
 */
public class Fruit extends FoodItem {
	
	/** Stores the orchard name as a String **/
	private String orchardName;
	/**
	 * Default Constructor
	 */
	public Fruit() {
		
	}
	
	/**
	 * This method calls to the parent class for initialization, then initializes the Data members.
	 * @param scanner Scanner that gets user input
	 * @return Returns a boolean whether adding the item was a success(True) or not(False).
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		
		if (super.addItem(scanner)) {

			System.out.print("Enter the name of the orchard supplier: ");
			orchardName = scanner.next();
			
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * This method creates a formatted string with all the data members values including the parent class. 
	 * @return String that has the data members from the parent class values with this classes data members values with correct formatting.
	 */
	public String toString() {
		return super.toString() + " orchard supplier: " + orchardName;
	}
}
