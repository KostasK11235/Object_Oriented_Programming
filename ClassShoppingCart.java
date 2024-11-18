import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart{
    private ArrayList<ItemOrdered> orderList = new ArrayList<>(10);

    Scanner keyboard = new Scanner(System.in);
    //methods

    public void addItemOrdered(EShop obj,String itemsName,ItemOrdered tempItem) //νομιζω οτι ειναι κομπλε
    {

        try
        {
            if(obj.lookFor(itemsName, tempItem.getQuantity()) == 1)
               throw new Exception("The stock of this product is less than the desired quantity!");
            else if(obj.lookFor(itemsName, tempItem.getQuantity()) == 2)
                throw new Exception("The product you are looking for is not in the product list!");

            int i_counter=0;
            if(this.orderList.size()==0)
            {
                this.orderList.add(new ItemOrdered(tempItem.ItemsName(),tempItem.getQuantity(),obj.getSelectedItemsPrice(tempItem.ItemsName())));
                obj.alterStock(itemsName,tempItem.getQuantity());
            }
            else
            {
                for(int i =0; i<this.orderList.size(); i++)
                {
                    if(itemsName.equalsIgnoreCase(this.orderList.get(i).ItemsName()))
                    {
                        this.orderList.get(i).setQuantity(tempItem.getQuantity());
                        obj.alterStock(itemsName,tempItem.getQuantity());
                        break;
                    }
                    else
                        i_counter++;
                }

                if(i_counter == this.orderList.size())
                {
                    this.orderList.add(new ItemOrdered(tempItem.ItemsName(),tempItem.getQuantity(),obj.getSelectedItemsPrice((tempItem.ItemsName()))));
                    obj.alterStock(itemsName,tempItem.getQuantity());
                }
            }

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }

        
    }

    public void removeItemOrdered(EShop obj,String itemForRemove)
   {
        for(int i=0; i<this.orderList.size(); i++)
        {
            if(itemForRemove.equalsIgnoreCase(this.orderList.get(i).ItemsName()))
            {
                int returnedAmount = this.orderList.get(i).getQuantity();
                obj.alterStock(itemForRemove,-returnedAmount);
                this.orderList.remove(i);
                break;
            }
        }
    }

    public void changeItemOrderedQuantity(EShop obj,String answer,int newAmount)
    {
        for(int i=0; i<this.orderList.size(); i++)
        {
            if(answer.equalsIgnoreCase(this.orderList.get(i).ItemsName()))
            {
                try
                {
                    if (obj.lookFor(answer, newAmount) == 1)
                        throw new Exception("The stock of this product is less than the desired quantity!");
                    else
                    {
                        this.orderList.get(i).setQuantity(newAmount);
                        obj.alterStock(answer,newAmount);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                }
            }
        }
    }

    public String showCart()
    {
        if(this.orderList.size()==0)
        {
            System.out.println("You cart is empty.");
            return "empty";
        }
        else {
            for (int i = 0; i < this.orderList.size(); i++) {
               System.out.println(i+1 + ".Item: " + this.orderList.get(i).ItemsName() + ", Quantity: " + this.orderList.get(i).getQuantity() + ", Price: " + this.orderList.get(i).getItemsPrice()+ "€");

            }
            System.out.println("The total cost of your order is: " + calculateNet() + "€") ;
            return  "not empty";
        }
    }

    public void clearCart(EShop obj)
    {
        for(int i=orderList.size()-1; i>=0; i--)
        {
            removeItemOrdered(obj,orderList.get(i).ItemsName());
        }
    }

    public boolean checkOut(Buyer currBuyer)
    {
        System.out.println("You cart contains: ");
        showCart();

        System.out.println("Are you sure you want to check out?(y/n)");
        String checkOut = keyboard.nextLine();

        if(checkOut.equalsIgnoreCase("y"))
        {
            if (currBuyer.getBuyerCategory().equalsIgnoreCase("Gold")) {
                System.out.println("Order's total cost is: " + calculateNet() + "(Category: " + currBuyer.getBuyerCategory() + ")");
                System.out.println("Thank you for visiting us.");
                currBuyer.awardBonus(calculateNet());
                this.orderList.clear();
                return true;
            } else if (currBuyer.getBuyerCategory().equalsIgnoreCase("silver"))
            {
                if(calculateCurrierCost(currBuyer) == 1)
                {
                    System.out.print("Add more items in your cart to get them delivered.");
                    return false;
                }
                else {
                    System.out.println("Order's total cost is: " + (calculateNet() + calculateCurrierCost(currBuyer) / 2) + "(Category:" + currBuyer.getBuyerCategory() + ")");
                    System.out.println("Thank you for visiting us.");
                    currBuyer.awardBonus(calculateNet());
                    this.orderList.clear();
                    return true;
                }
            } else {
                if(calculateCurrierCost(currBuyer) == 1)
                {
                    System.out.println("Order's cost is too low to deliver it.");
                    return false;
                }
                else {
                    System.out.println("Order's total cost is: " + (calculateNet() + calculateCurrierCost(currBuyer)) + " (Category: " + currBuyer.getBuyerCategory() + ")");
                    System.out.println("Thank you for visiting us.");
                    currBuyer.awardBonus(calculateNet());
                    this.orderList.clear();
                    return true;
                }
            }

        }
        return false;
    }


    public double calculateNet()
    {
        double totalCost=0;
        for(int i=0; i<orderList.size(); i++)
        {
            totalCost += orderList.get(i).getQuantity() * orderList.get(i).getItemsPrice();
        }

        return totalCost;
    }

    public double calculateCurrierCost(Buyer currBuyer)
    {
        double currierCost = 0.2 * this.calculateNet();

        if(currBuyer.getBuyerCategory().equalsIgnoreCase("gold"))
        {
            return 0;
        }

        if(currierCost < 3)
        {
            System.out.println("The total cost of your order must be more than 3 € in order to deliver it to you.");
            return 1;
        }
        else if(currBuyer.getBuyerCategory().equalsIgnoreCase("silver"))
        {
            return currierCost/2;
        }
        else if(currBuyer.getBuyerCategory().equalsIgnoreCase("bronze"))
        {
            return currierCost;
        }

        return -1;
    }
    
}
