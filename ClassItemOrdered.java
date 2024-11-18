public class ItemOrdered {
    private String name;
    private double cost;
    private int quantity;

    //constructors

    public ItemOrdered(String Name,int amount,double price)
    {
        name = Name;
        quantity = amount;
        cost = price;
    }

    public ItemOrdered(String Name, int amount)
    {
        name = Name;
        quantity = amount;
    }
    
    //methods
    public void setQuantity(int wantedQuantity)
    {
        quantity = wantedQuantity + this.quantity;
    }
    public void setCost(double price) { cost = price; }
    public String ItemsName()
    {
        return name;
    }
    public int getQuantity() { return quantity; }
    public double getItemsPrice() { return cost; }
}
