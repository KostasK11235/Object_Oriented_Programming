public class Buyer extends User {
    private int bonus=0;
    private String buyerCategory = "Bronze"; // Bronze,Silver,Gold
    private ShoppingCart cart = new ShoppingCart();


    //constructor

    public Buyer(String Name)
    {
        this.setUserName(Name);
    }

    //methods

    public void awardBonus(double orderCost)
    {
        double bonusCalc = orderCost*0.1;
        this.bonus = (int)Math.floor(bonusCalc);
        setBuyerCategory(this.bonus);
    }

    private void setBuyerCategory(int points)
    {
        if(points> 100 && points<=200 )
            this.buyerCategory = "Silver"; // Silver 50% στα μεταφορικά
        else if(points > 200)
            this.buyerCategory = "Gold"; // Gold χωρις μεταφορικά
    }

    public void placeOrder(EShop obj,String itemsName)
    {
        System.out.println("Enter the desired quantity of the item you want: ");
        int desiredQuantity = keyboard.nextInt();
        String nchar = keyboard.nextLine();

        ItemOrdered tempItem = new ItemOrdered(itemsName,desiredQuantity);
        cart.addItemOrdered(obj,itemsName,tempItem);

    }

    public void removeOrder(EShop obj, String itemsName)
    {
        cart.removeItemOrdered(obj,itemsName);
    }

    public void changeQuantity(EShop obj,String itemsName,int newAmount)
    {
        cart.changeItemOrderedQuantity(obj,itemsName,newAmount);
    }

    public void clearBuyersCart(EShop obj)
    {
        cart.clearCart(obj);
    }

    public boolean checkOutBuyer()
    {
       return  cart.checkOut(this);
    }

    public String getUserDetails()
    {
        return "Name: " + this.getUserName() + ", points: " + this.bonus + ", category: " + this.buyerCategory + ", email: " + this.getUsersEmail();
    }

    public String getBuyerCategory(){ return this.buyerCategory; }

    public String getBuyerCart()
    {
        String cart_state = this.cart.showCart();
        if(cart_state.equalsIgnoreCase("empty"))
        {
            return "empty";
        }
        else
            return "not empty";
    }
}
