import java.util.Scanner;

public abstract class Item {
    public String name;
    private double price;
    private String description;
    private int stock;
    private int id;

    Scanner keyboard = new Scanner(System.in);

    //methods

    public void setItem(String Name)
    {
        name = Name;
        System.out.println("Enter item's price:");
        price = keyboard.nextDouble();
        String nChar = keyboard.nextLine();
        System.out.println("Enter item's stock:");
        stock = keyboard.nextInt();
    }

    public void setName(String Name)
    {
        name = Name;
    }

    public void setID(int newID)
    {
        id = newID;
    }

    public void setDescription(String newDescription)
    {
        description = newDescription;
    }

    public void setStock(int newStock)
    {
        stock = stock + newStock;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getStock() { return stock; }

    public double getPrice() { return price; }

    public String getBasicInfo()
    {
        return "Name: " + this.name + ", Price: " + this.price +  "€ , Stock: " + this.stock +
                ", ID: " + this.id;
    }

    public String toString()
    {
        getBasicInfo();
        getDetails();
        return getBasicInfo() + ", " + getDetails();
    }
    
    //abstracts
    public abstract String getDetails();
    
    public abstract void setMoreItemInfo();
}
