import java.util.Scanner;

/**
 * This class is a preserve, and is a child of it's parent class FoodItem.
 * Student Name: Amero Defranco
 * Student Number: 040935555
 * Course: CST8130 - Data Structures
 * Date: 19/11/17
 * @author Amero Defranco
 *
 */
public class Preserve extends FoodItem {
	
	/** Stores the jar size as an int **/
	private int jarSize;
	
	/**
	 * Default Constructor
	 */
	public Preserve() {

	}
	
	/**
	 * This method calls to the parent class for initialization, then initializes the Data members.
	 * @param scanner Scanner that gets user input
	 * @return Returns a boolean whether adding the item was a success(True) or not(False).
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		
		boolean exit = false;
		if (super.addItem(scanner)) {

			while (!exit) {
				System.out.print("Enter the size of the jar in millilitres: ");
				try {
					jarSize = Integer.parseInt(scanner.next());
					exit = true;
				} catch(Exception e) {
					System.out.println("Invalid entry");
				}
			}

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
		return super.toString() + " size: " + jarSize + "mL";
	}
}
