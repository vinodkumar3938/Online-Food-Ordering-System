package OFS;


import java.io.Serializable;

class MenuItem implements Serializable {
    private int itemId;
    private String name;
    private String description;
    private double price;
    private String category;

    public MenuItem(int itemId, String name, String description, double price, String category) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + ", Name: " + name + ", Description: " + description + 
               ", Price: " + price + ", Category: " + category;
    }
}