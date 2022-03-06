import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class contains the inventory that takes in food items.
 * Student Name: Amero Defranco
 * Student Number: 040935555
 * Course: CST8130 - Data Structures
 * Date: 19/11/17
 * @author Amero Defranco
 *
 */

public class Inventory {
	/** FoodItem array **/
	private ArrayList<InventoryItem> inventory;
	/** Number of items in the inventory FoodItem array**/
	private int numItems;
	/**
	 * Default Constructor that initializes the inventory to 20 items
	 */
	public Inventory() {
		inventory = new ArrayList<InventoryItem>();
		numItems = 0;
	}
	
	/**
	 * Method that checks whether a food item already exists.
	 * @param itemCode The item to check whether it already exists.
	 * @return Int value that represents an item's index if it already exists, otherwise -1.
	 */
	public int alreadyExists(int itemCode) {
		
		for (int i = 0; i < numItems; i++) {
			if (Integer.compare(inventory.get(i).getItemCode(),itemCode) == 0) {
				return i;
			}
		}
		
		return -1;
	}
	/**
	 * This method takes user input to initialize a fruit/vegetable/preserve FoodItem.
	 * @param scanner Scanner used for user input.
	 * @return Returns a boolean whether adding the item was a success(True) or not(False).
	 */
	public boolean addItem(Scanner scanner) {
		InventoryItem newItem = new InventoryItem(scanner);
		newItem.inputCode(scanner);

		if (alreadyExists(newItem.getItemCode()) == 0) {
			System.out.println("Error - Item code already exists");
			return false;
		}
		
		if (newItem.addItem(scanner)) {
			inventory.add(newItem);
			numItems++;
			this.sort();
			return true;
		} else {
			System.out.print("Could not add item");
		}
		
		return false;
	}
	/**
	 * This method is used to update the quantity of a FoodItem by taking it's item code and attempting to update it.
	 * @param scanner Scanner used for user input.
	 * @param buyOrSell Boolean used to distinguish the difference between whether the user is buying or selling true if buying, false if selling.
	 * @return Returns a boolean whether updating the item quantity was a success(True) or not(False).
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {

		
		String buyOrSellString = "";
		
		if (buyOrSell) {
			buyOrSellString = "buy";
		} else {
			buyOrSellString = "sell";
		}
		
		if (inventory.size() == 0) {
			System.out.println("Error...could not " + buyOrSellString + " item, inventory is empty");
			return false;
		}
		
		int codeCheck;
		InventoryItem itemToBuyOrSell;
		
		try {
			System.out.print("Enter the item code for the item: ");
			codeCheck = Integer.parseInt(scanner.next());

		} catch (Exception e) {
			System.out.println("Error...could not " + buyOrSellString + " item");
			return false;
		}
		codeCheck = alreadyExists(codeCheck);
		
		if (codeCheck == -1) {
			System.out.println("Code not found in inventory...");
			System.out.println("Error...could not " + buyOrSellString + " item");
			return false;
		}
		itemToBuyOrSell = inventory.get(codeCheck);
		int quantity = 0;
		
		
		System.out.print("Enter valid quantity to " + buyOrSellString + ": ");
		try {
			quantity = Integer.parseInt(scanner.next());
		} catch(Exception e) {
			System.out.println("Invalid quantity...");
			System.out.println("Error...could not " + buyOrSellString + " item");
			return false;
		}
		
		
		if (buyOrSell) {
			itemToBuyOrSell.updateQuantity(scanner, quantity);
		} else {
			boolean success = itemToBuyOrSell.updateQuantity(scanner,-1 * quantity);
			if (!success) {
				System.out.println("Insufficient stock in inventory...");
				System.out.println("Error...could not " + buyOrSellString + " item");
			}
		}
		
		return true;
	}
	/**
	 * 
	 * This method uses binary search to find the item code inputted by the user.
	 * @param scanner used for user input.
	 */
	public void searchForItem(Scanner scanner) {
		
		System.out.print("Enter the code for the item: ");
		int code;
		try {
			code = Integer.parseInt(scanner.next());
		} catch (Exception e) {
			System.out.println("Invalid entry");
			return;
		}
		int right = numItems - 1;
		int left = 0;
		System.out.print(right);
		while (left <= right) {
			
			int middle = (left + right)/2;
			
			if (inventory.get(middle).getItemCode() > code) {
				right = middle - 1;
			} else if (inventory.get(middle).getItemCode() < code) {
				left = middle + 1;
			} else if(inventory.get(middle).getItemCode() == code) {
				System.out.print(inventory.get(middle).toString());
				return;
			}
		}
		System.out.println("Code not found in inventory...");
	}

	/**
	 * Sort method using insertion sort, to sort all the food items by item code.
	 */
	private void sort() {

		for (int i = 1; i < numItems - 1; i++) {
            InventoryItem numberToInsert = inventory.get(i);
            int index = i;
            while (index > 0 && inventory.get(index - 1).getItemCode() > numberToInsert.getItemCode()) {
            	inventory.set(index, inventory.get(index - 1));
                index = index-1;
            }
            inventory.set(index, numberToInsert); 
        }
	}
	/**
	 * This method gets the item code to print all of the expiries of that item code.
	 * @param scanner Scanner for user input.
	 */
	public void printExpiry(Scanner scanner) {
		System.out.print("Enter the item code for the item: ");
		int code;
		try {
			code = Integer.parseInt(scanner.next());
		} catch (Exception e) {
			System.out.println("Invalid entry");
			return;
		}
		code = alreadyExists(code);
		if (code != -1) {
			inventory.get(code).printExpirySummary();
		}
		
	}
	/**
	 * Removes all expired items from the quantity of every item in the inventory.
	 * @param today LocalDate, used for checking if an item is expired.
	 */
	public void removeExpired(LocalDate today) {
		for (InventoryItem item : inventory) {
			item.removeExpiredItems(today);
		}
	}
	
	/**
	 * Creates a formatted list of all items in the inventory.
	 * @return Returns a string with a formatted list of all the items in the inventory 
	 */
	public String toString() {
		String inventoryString = "Inventory:";
		for (int i=0; i < numItems; i++) {
			inventoryString = inventoryString + "\n" + inventory.get(i).toString();
		}
		
		return inventoryString + "\n";
	}
}
