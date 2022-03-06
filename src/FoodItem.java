import java.util.Scanner;

/**
 * 
 * This class is the parent of Objects Vegetable, Fruit, and Preserve. It holds all relevant information that's related to all of it's child classes.
 * Student Name: Amero Defranco
 * Student Number: 040935555
 * Course: CST8130 - Data Structures
 * Date: 19/11/17
 * @author Amero Defranco
 *
 */

public class FoodItem {
	
	/** Stores the item code for the food item as an int **/
	private int itemCode;
	/** Stores the item name for the food item as a String **/
	private String itemName;
	/** Stores the item price for the food item as a float **/
	private float itemPrice;
	/** Stores the item cost for the food item as a float **/
	private float itemCost;
	/**
	 * Default Constructor
	 */
	public FoodItem() {
		
	}
	
	/**
	 * This method sets up all of data members for the food items.
	 * @param scanner Scanner used for user input .
	 * @return Returns a boolean whether adding an item was a success(True) or not(False).
	 */
	public boolean addItem(Scanner scanner) {
		
		System.out.print("Enter the name for item: ");
		itemName = scanner.next();
		boolean quit = false;
		
		quit = false;
		
		while (!quit) {
			System.out.print("Enter the cost of the items: ");
			try {
				itemCost = Float.parseFloat(scanner.next());
				if (itemCost <= 0) {
					throw new Exception();
				}
				quit = true;
			} catch(Exception e) {
				System.out.println("Invalid entry");
			}
		}
		quit = false;
		
		while (!quit) {
			System.out.print("Enter the sales price of the item: ");
			try {
				itemPrice = Float.parseFloat(scanner.next());
				if (itemPrice <= 0) {
					throw new Exception();
				}
				quit = true;
			} catch(Exception e) {
				System.out.println("Invalid entry");
			}
		}
		return true;
	}
	
	/**
	 * This method takes the inputCode and checks whether it was a success or not.
	 * @param scanner Scanner for user input.
	 * @return Returns a boolean whether initializing the input code was a success(True) or not(False).
	 */
	public boolean inputCode(Scanner scanner) {
		System.out.print("Enter the item code for the item: ");
		try {
			itemCode = Integer.parseInt(scanner.next());
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * returns an integer item code.
	 * @return integer value that is the Item code of the food item.
	 */
	public int getItemCode() {
		return this.itemCode;
	}

	/**
	 * Compares two Food items item codes
	 * @param otherItem the other item to compare.
	 * @return integer value, 0 if x==y, negative integer if x {@literal <} y, positive integer greater than 0 if x {@literal>} y
	 */
	public int compareTo(FoodItem otherItem) {
		return Integer.compare(this.itemCode, otherItem.itemCode);
	}
	/**
	 * This method creates a formatted string with all the data members values. 
	 * @return String that has the data members values with correct formatting.
	 */
	public String toString() {
		
		return "Item: " + itemCode + " " + itemName + " price: $" + itemPrice + " cost: $" + itemCost;
	}



}
