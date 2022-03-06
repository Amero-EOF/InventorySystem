import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * 
 * This class controls the specified fooditem's quantity, and expiry dates. It can remove/add quantities, and initializes the food item.
 * Student Name: Amero Defranco
 * Student Number: 040935555
 * Course: CST8130 - Data Structures
 * Date: 19/11/17
 * @author Amero Defranco
 *
 */

public class InventoryItem {
	/** Stores the item quantity for the inventory item as an int **/
	private int itemQuantityInStock;
	/** Stores the food item for the inventory item**/
	private FoodItem item;
	/** Stores the expiry dates for all the items  **/
	private Map<LocalDate,Integer> expiries;
	/**
	 * Initializes the type of food item.
	 * @param scanner Scanner used for user input.
	 */
	public InventoryItem(Scanner scanner) {
		System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");

		switch(scanner.next().toUpperCase()) {
			case "F":
				item = new Fruit();
				break;
				
			case "V":
				item = new Vegetable();
				break;
				
			case "P":
				item = new Preserve();
				break;
				
			default:
				System.out.println("Invalid Entry");
		}
	}
	/**
	 * This method adds the quantity and expiry date for the item, as well as initializes the food item.
	 * @param scanner Scanner used for user input.
	 * @return Returns a boolean whether adding an item was a success(True) or not(False).
	 */
	public boolean addItem(Scanner scanner) {
		expiries = new TreeMap<LocalDate,Integer>();
		boolean quit = false;
		boolean check = item.addItem(scanner);
		while (!quit) {
			System.out.print("Enter the quantity for the item: ");
			try {
				itemQuantityInStock = Integer.parseInt(scanner.next());
				if (itemQuantityInStock <= 0) {
					throw new Exception();
				}
				quit = true;
			} catch(Exception e) {
				System.out.println("Invalid entry");
			}
		}
		if (check) {
			
			LocalDate placeholder = null;
			boolean exit = false;
			while (!exit) {
				try {
					System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
					String date = scanner.next();
					if (date.toUpperCase().compareTo("NONE") == 0) {
						placeholder = LocalDate.MAX;
					} else {
						placeholder = LocalDate.parse(date);
					}
					
					expiries.put(placeholder, (Integer)itemQuantityInStock);
					exit = true;
				} catch(Exception e) {
					System.out.println("Could not create date from input, please use format yyyy-mm-dd");
					System.out.println(e.getMessage());
				}
			}
		}
		return true;
	}
	/**
	 * returns an integer item code.
	 * @return integer value that is the Item code of the food item.
	 */
	public int getItemCode() {
		return item.getItemCode();
	}
	/**
	 * This method takes the inputCode and checks whether it was a success or not.
	 * @param scanner Scanner for user input.
	 * @return Returns a boolean whether initializing the input code was a success(True) or not(False).
	 */
	public boolean inputCode(Scanner scanner) {
		while (!item.inputCode(scanner)) {
			System.out.println("Invalid Entry");
		}
		return true;
	}
	/**
	 * Displays all expiry information.
	 */
	public void printExpirySummary() {
		
		System.out.print(this.toString() + "\n" + "Expiry Details:\n");
		expiries.forEach((k,v) -> System.out.println(((k == LocalDate.MAX) ? "No Expiry":k) + ": " + v));
	}
	/**
	 * Removes all expired items from the quantity.
	 * @param today LocalDate, used for checking if an item is expired.
	 */
	public void removeExpiredItems(LocalDate today) {
		ArrayList<LocalDate> itemsToRemove = new ArrayList<LocalDate>();
		for (Map.Entry<LocalDate, Integer> element : expiries.entrySet()) {
			if (element.getKey().isEqual(today) || element.getKey().isBefore(today)) {
				itemQuantityInStock -= element.getValue();
				itemsToRemove.add(element.getKey());
				
			}
		}
		for (LocalDate i : itemsToRemove) {
			expiries.remove(i);
		}
	}
	/**
	 * This method updates the quantity based on whether the amount is negative or positive.
	 * @param scanner Scanner for user input.
	 * @param amount Int which is the amount to either add or remove from the quantity.
	 * @return Returns a boolean whether initializing the input code was a success(True) or not(False).
	 */
	public boolean updateQuantity(Scanner scanner, int amount) {
		LocalDate placeholder = null;
		if (amount > 0) {
			
			boolean exit = false;
			while (!exit) {
				try {
					System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
					String date = scanner.next();
					if (date.toUpperCase().compareTo("NONE") == 0) {
						placeholder = LocalDate.MAX;
					} else {
						placeholder = LocalDate.parse(date);
					}
					
					exit = true;
				} catch(Exception e) {
					System.out.println("Could not create date from input, please use format yyyy-mm-dd");
					System.out.println(e.getMessage());
				}
			}
			for (Map.Entry<LocalDate, Integer> element : expiries.entrySet()) {
				if (element.getKey().isEqual(placeholder)) {
					expiries.put(element.getKey(), element.getValue() + amount);
					itemQuantityInStock += amount;
					return true;
					
				}
			}
			expiries.put(placeholder, amount);
		} else {
			if (itemQuantityInStock < amount*-1) {
				System.out.println("Error - Amount to sell is more than quantity");
				return false;
			}
			if (expiries.size() > 0) {
				int counter = amount;
				ArrayList<LocalDate> itemsToRemove = new ArrayList<LocalDate>();
				for (Map.Entry<LocalDate, Integer> element : expiries.entrySet()) {
					if (element.getValue() < counter) {
						counter -= element.getValue();
						itemsToRemove.add(element.getKey());
					} else {
						expiries.put(element.getKey(), element.getValue() + counter);
						
					}
				}
				for (LocalDate i : itemsToRemove) {
					expiries.remove(i);
				}
			} else {
				expiries.put((LocalDate) expiries.keySet().toArray()[0], expiries.get(expiries.keySet().toArray()[0]) + amount);
			}
		}
		
		itemQuantityInStock += amount;
		return true;
		
	}
	/**
	 * To string method that returns all the inventory item information.
	 * @return String returns all the inventory item information.
	 */
	public String toString() {
		return item.toString() + " qty: " + itemQuantityInStock;
	}
	
	
}
