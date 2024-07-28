package OFS;


import java.io.*;
import java.util.*;

class OrderManager {
    private LinkedList<MenuItem> menuItems;
    private Stack<MenuItem> recentOperations;
    private PriorityQueue<MenuItem> priorityQueue;
    private final String FILE_NAME = "menuitems.dat";

    public OrderManager() {
        menuItems = new LinkedList<>();
        recentOperations = new Stack<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(MenuItem::getPrice));
        loadMenuItems();
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        recentOperations.push(menuItem);
        priorityQueue.offer(menuItem);
        saveMenuItems();
    }

    public void viewAllMenuItems() {
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem);
        }
    }

    public MenuItem searchMenuItemById(int itemId) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getItemId() == itemId) {
                return menuItem;
            }
        }
        return null;
    }

    public MenuItem searchMenuItemByName(String name) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getName().equalsIgnoreCase(name)) {
                return menuItem;
            }
        }
        return null;
    }

    public MenuItem searchMenuItemByCategory(String category) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getCategory().equalsIgnoreCase(category)) {
                return menuItem;
            }
        }
        return null;
    }

    public void deleteMenuItemById(int itemId) {
        MenuItem itemToDelete = null;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getItemId() == itemId) {
                itemToDelete = menuItem;
                break;
            }
        }
        if (itemToDelete != null) {
            menuItems.remove(itemToDelete);
            priorityQueue.remove(itemToDelete);
            saveMenuItems();
        }
    }

    public void sortMenuItemsByName() {
        menuItems.sort(Comparator.comparing(MenuItem::getName));
    }

    public void sortMenuItemsByPrice() {
        menuItems.sort(Comparator.comparingDouble(MenuItem::getPrice));
    }

    public void sortMenuItemsByCategory() {
        menuItems.sort(Comparator.comparing(MenuItem::getCategory));
    }

    private void saveMenuItems() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(menuItems);
        } catch (IOException e) {
            System.out.println("Error saving menu items: " + e.getMessage());
        }
    }

    private void loadMenuItems() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            menuItems = (LinkedList<MenuItem>) ois.readObject();
            for (MenuItem menuItem : menuItems) {
                priorityQueue.offer(menuItem);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    public void viewPriorityQueue() {
        PriorityQueue<MenuItem> copy = new PriorityQueue<>(priorityQueue);
        while (!copy.isEmpty()) {
            System.out.println(copy.poll());
        }
    }

    public void viewRecentOperations() {
        Stack<MenuItem> copy = (Stack<MenuItem>) recentOperations.clone();
        while (!copy.isEmpty()) {
            System.out.println(copy.pop());
        }
    }
}