package OFS;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;

public class FoodOrderingApplication {
    private static OrderManager system = new OrderManager();
    private static Scanner scanner = new Scanner(System.in);
    private static LinkedList<MenuItem> linkedList = new LinkedList<>();
    private static Stack<MenuItem> stack = new Stack<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nOnline Food Ordering System:");
            System.out.println("1. Add Menu Items");
            System.out.println("2. View All Menu Items");
            System.out.println("3. Search Menu Item");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. Sort Menu Items");
            System.out.println("6. View Priority Menu");
            System.out.println("7. Push to Cart");
            System.out.println("8. Pop from Cart");
            System.out.println("9. View Cart");
            System.out.println("10. Generate Bill");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMenuItems();
                    break;
                case 2:
                    system.viewAllMenuItems();
                    break;
                case 3:
                    searchMenuItem();
                    break;
                case 4:
                    deleteMenuItem();
                    break;
                case 5:
                    sortMenuItems();
                    break;
                case 6:
                    system.viewPriorityQueue();
                    break;
                case 7:
                    pushToStack();
                    break;
                case 8:
                    popFromStack();
                    break;
                case 9:
                    viewStack();
                    break;
                case 10:
                    generateBill();
                    break;
                case 11:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMenuItems() {
        System.out.print("Enter the number of items to add: ");
        int numberOfItems = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numberOfItems; i++) {
            System.out.println("Adding item " + (i + 1) + ":");

            System.out.print("Enter Item ID: ");
            int itemId = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("Enter Category: ");
            String category = scanner.nextLine();

            MenuItem menuItem = new MenuItem(itemId, name, description, price, category);
            system.addMenuItem(menuItem);
            linkedList.add(menuItem); 
            stack.push(menuItem); 
            System.out.println("Menu item added successfully!");
        }
    }

    private static void searchMenuItem() {
        System.out.println("Search Menu Item by:");
        System.out.println("1. Item ID");
        System.out.println("2. Name");
        System.out.println("3. Category");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        MenuItem menuItem = null;
        switch (choice) {
            case 1:
                System.out.print("Enter Item ID: ");
                int itemId = scanner.nextInt();
                scanner.nextLine(); 
                menuItem = system.searchMenuItemById(itemId);
                break;
            case 2:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                menuItem = system.searchMenuItemByName(name);
                break;
            case 3:
                System.out.print("Enter Category: ");
                String category = scanner.nextLine();
                menuItem = system.searchMenuItemByCategory(category);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        if (menuItem != null) {
            System.out.println("Menu Item Found: " + menuItem);
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private static void deleteMenuItem() {
        System.out.print("Enter Item ID to delete: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); 

        system.deleteMenuItemById(itemId);
        System.out.println("Menu item deleted successfully!");
    }

    private static void sortMenuItems() {
        System.out.println("Sort Menu Items by:");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("3. Category");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                system.sortMenuItemsByName();
                break;
            case 2:
                system.sortMenuItemsByPrice();
                break;
            case 3:
                system.sortMenuItemsByCategory();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        System.out.println("Menu items sorted successfully!");
        system.viewAllMenuItems(); 
    }


    private static void pushToStack() {
        System.out.println("Pushing an item to cart:");
        System.out.print("Enter Item ID: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); 

        MenuItem menuItem = system.searchMenuItemById(itemId);
        if (menuItem != null) {
            stack.push(menuItem);
            System.out.println("Item pushed to cart: " + menuItem);
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private static void popFromStack() {
        if (!stack.isEmpty()) {
            MenuItem menuItem = stack.pop();
            System.out.println("Popped item from cart: " + menuItem);
        } else {
            System.out.println("Cart is empty.");
        }
    }

    private static void viewStack() {
        System.out.println("Items in Cart:");
        double totalPrice = 0;
        for (MenuItem menuItem : stack) {
            System.out.println(menuItem);
            totalPrice += menuItem.getPrice();
        }
        System.out.println("Total Price of Items in Cart: " + totalPrice);
    }

    private static void generateBill() {
        System.out.println("Generating Bill for Items in Cart:");
        double totalPrice = 0;
        for (MenuItem menuItem : stack) {
            System.out.println("Item: " + menuItem.getName() + " | Price: " + menuItem.getPrice());
            totalPrice += menuItem.getPrice();
        }
        System.out.println("Total Amount to Pay: " + totalPrice);
    }

}