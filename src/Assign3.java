import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class runs all the menu options and displays the menu.
 * Student Name: Amero Defranco
 * Student Number: 040935555
 * Course: CST8130 - Data Structures
 * Date: 19/11/17
 * @author Amero Defranco
 *
 */
public class Assign3 {
	
	/**
	 * Main method that holds the options for the menu
	 * @param args pre run arguements
	 */
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(Pattern.compile("[\\r\\n]+"));
		Inventory inventory = new Inventory();

		while (true) {
			displayMenu();
			switch(scanner.next()) {
				case "1":
					inventory.addItem(scanner);
					break;
				case "2":
					System.out.println(inventory.toString());
					break;
				case "3":
					inventory.updateQuantity(scanner, true);
					break;
				case "4":
					inventory.updateQuantity(scanner, false);
					break;
				case "5":
					inventory.searchForItem(scanner);
					break;
				case "6":
					inventory.removeExpired(date);
					break;
				case "7":
					inventory.printExpiry(scanner);
					break;
				case "8":
					try {
						System.out.print("Please enter today's date (yyyy-mm-dd): ");
						date = LocalDate.parse(scanner.next());
					} catch (Exception e) {
						System.out.println("Could not create date from input, please use format yyyy-mm-dd");
						System.out.println(e.getMessage());
					}
					break;
				case "9":
					return;
				default:
					break;
					
			}
		}
	}
	
	/**
	 * Prints out the menu.
	 */
	public static void displayMenu() {
		System.out.println("1: Add Item to Inventory\n"
						 + "2: Display Current Inventory\n"
						 + "3: Buy Item(s)\n"
						 + "4: Sell Item(s)\n"
						 + "5: Search for Item\n"
						 + "6: Remove Expired Items\n"
						 + "7: Print Expiry\n"
						 + "8: Change Today's Date\n"
						 + "9: To Exit");
	}
}
