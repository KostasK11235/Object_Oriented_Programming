import java.util.ArrayList;
import java.util.Scanner;

public class EShop {
    private String name;
    private Owner owner;
    private ArrayList<Item> itemsList = new ArrayList<>();
    private ArrayList<User> buyersList = new ArrayList<>();

    Scanner keyboard = new Scanner(System.in);

    //methods

    //ΠΡΟΣΘΗΚΗ ΠΡΟΙΟΝΤΩΝ
    public void addItem()
    {
        try {
            System.out.println("Enter the product's category which you want to add:");
            String category = keyboard.nextLine();

            System.out.println("Enter the name of the item you want to add in items list.");
            String newItem = keyboard.nextLine();


            for(int i=0; i<itemsList.size(); i++)
            {
              if(newItem.equalsIgnoreCase(itemsList.get(i).getName()))
                    throw new Exception("Exception: item already in list!");
            }


            if(category.equalsIgnoreCase("Pen")) {
                itemsList.add(new Pen(newItem));
                itemsList.get(itemsList.size()-1).setDescription("Pen");
                itemsList.get(itemsList.size()-1).setItem(newItem);
                itemsList.get(itemsList.size()-1).setID(itemsList.size()-1);
                itemsList.get(itemsList.size()-1).setMoreItemInfo();
            }
            else if(category.equalsIgnoreCase("Pencil")) {
                itemsList.add(new Pencil(newItem));
                itemsList.get(itemsList.size()-1).setDescription("Pencil");
                itemsList.get(itemsList.size()-1).setItem(newItem);
                itemsList.get(itemsList.size()-1).setID(itemsList.size()-1);
                itemsList.get(itemsList.size()-1).setMoreItemInfo();
            } else if(category.equalsIgnoreCase("Notebook")) {
                itemsList.add(new NoteBook(newItem));
                itemsList.get(itemsList.size()-1).setDescription("NoteBook");
                itemsList.get(itemsList.size()-1).setItem(newItem);
                itemsList.get(itemsList.size()-1).setID(itemsList.size()-1);
                itemsList.get(itemsList.size()-1).setMoreItemInfo();
            } else if(category.equalsIgnoreCase("Paper")) {
                itemsList.add(new Paper(newItem));
                itemsList.get(itemsList.size()-1).setDescription("Paper");
                itemsList.get(itemsList.size()-1).setItem(newItem);
                itemsList.get(itemsList.size()-1).setID(itemsList.size()-1);
                itemsList.get(itemsList.size()-1).setMoreItemInfo();
            } else {
                System.out.println("No such category in our products!");
            }


        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void getItemById()
    {
        try {
            System.out.println("Enter the item number you want to find.");
            int itemID = keyboard.nextInt();
            String nchar = keyboard.nextLine();
            if(itemID<0 || itemID>=itemsList.size())
                throw new Exception("Exception: ID not in list.");
            else
                System.out.println(itemsList.get(itemID).toString());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void removeItem()
    {
        boolean isOnList = false;
        System.out.println("Enter the item you want to remove from list.");
        String itemForRemove = keyboard.nextLine();

        for(int i=0; i<itemsList.size(); i++)
        {
            if(itemForRemove.equalsIgnoreCase(itemsList.get(i).getName()))
            {
                itemsList.remove(i);
                isOnList = true;
                break;
            }
        }

        if(!isOnList)
            System.out.println("The item you want to remove is not in list.");

    }

    public void addBuyer()
    {
        try
        {
            if(buyersList.size()==0)
            {
               System.out.println("Register an owner for the shop.");
                System.out.print("Enter the name of the owner to add: ");
                String newOwner = keyboard.nextLine();

                for (int i = 0; i < buyersList.size(); i++) {
                    if (newOwner.equalsIgnoreCase(buyersList.get(i).getUserName()))
                        throw new Exception("Exception: owner already exists in list.");
                }

                owner = new Owner(newOwner);
                buyersList.add(owner);
                for(User obj: buyersList)
                {
                    if(obj.getUserName().equalsIgnoreCase(newOwner))
                    {
                        System.out.print("Enter email address: ");
                        String eAddress = keyboard.nextLine();
                        obj.setUserEmail(eAddress);
                        break;
                    }
                }
            }
            else {
                System.out.print("Enter the name of the buyer to add in customer list: ");
                String newBuyer = keyboard.nextLine();

                for (int i = 0; i < buyersList.size(); i++) {
                    if (newBuyer.equalsIgnoreCase(buyersList.get(i).getUserName()))
                        throw new Exception("Exception: buyer already exists in buyers list.");
                }

                buyersList.add(new Buyer(newBuyer));
                for(User obj: buyersList)
                {
                    if (obj.getUserName().equalsIgnoreCase(newBuyer))
                    {
                        System.out.print("Enter email address: ");
                        String eAddress = keyboard.nextLine();
                        obj.setUserEmail(eAddress);
                    }
                }
            }
        }
        catch(Exception e)
       {
            System.out.println(e.getMessage());
        }
    }

    public String removeBuyer(String buyerForRemove)
    {
        for(int i=0; i<buyersList.size(); i++)
        {
            if(buyerForRemove.equalsIgnoreCase(buyersList.get(i).getUserName()))
            {
                buyersList.remove(i);
                return "Buyer removed.";
            }
        }

        return "The buyer's name you want to remove is not in list";
    }


    //Ανανεωση ποσοτητας ενος προιντος στο καταστημα
    public void updateItemStock(String product)
    {
        boolean itemNotFound = true;

        for(int i=0; i<itemsList.size(); i++)
        {
            if(product.equalsIgnoreCase(itemsList.get(i).getName()))
            {
                System.out.println("Enter number of units to add in current stock.");
                int newStock = keyboard.nextInt();
                String nchar = keyboard.nextLine();
                itemsList.get(i).setStock(newStock);
                itemNotFound = false;
                break;
            }
        }

        if(itemNotFound)
            System.out.println("The item is not in list.");
    }

    //Δειχνει τις κατηγοριες
    public void showCategories()
    {
        int penItems=0, pencilItems=0, noteBookItems=0, paperItems=0;

        for(Item obj: itemsList)
         {
            if(obj.getDescription().equalsIgnoreCase("Pen"))
                penItems++;
            else if(obj.getDescription().equalsIgnoreCase("Pencil"))
                pencilItems++;
            else if(obj.getDescription().equalsIgnoreCase("NoteBook"))
                noteBookItems++;
            else
                paperItems++;
        }

        System.out.println("The categories of our products are:");
        System.out.println("1." + " Pens" + "(" + penItems + ")");
        System.out.println("2." + " Pencils" + "(" + pencilItems + ")");
        System.out.println("3." + " NoteBooks" + "(" + noteBookItems + ")");
        System.out.println("4." + " Paper" + "(" + paperItems + ")");
    }

    //Δειχνει τα προιοντα ανα κατηγορια
    public String showProductsInCategory(String ans)
    {
        if(ans.equalsIgnoreCase("Pens"))
        {
            int i = 1;
            for(Item obj: itemsList)
            {
                if(obj.getDescription().equalsIgnoreCase("pen")) {
                    System.out.println(i + ". " + obj.getName());
                    i++;
                }
            }
            return "ok";
        }
        else if(ans.equalsIgnoreCase("pencils"))
        {
            int i = 1;
            for(Item obj: itemsList)
            {
                if(obj.getDescription().equalsIgnoreCase("pencil")) {
                    System.out.println(i + ". " + obj.getName());
                    i++;
                }
            }
            return "ok";
        }
        else if(ans.equalsIgnoreCase("notebooks"))
        {
            int i = 1;
           for(Item obj: itemsList)
            {
                if(obj.getDescription().equalsIgnoreCase("notebook")) {
                    System.out.println(i + ". " + obj.getName());
                    i++;
                }
            }
            return "ok";
        }
        else if(ans.equalsIgnoreCase("paper"))
        {
            int i = 1;
            for(Item obj: itemsList)
            {
                if(obj.getDescription().equalsIgnoreCase("paper")) {
                    System.out.println(i + ". " + obj.getName());
                    i++;
                }
            }
            return "ok";
        }
        else {
            return "not ok";
        }
    }

    public String showProduct()
    {
        System.out.println("For which product would you like to see more details?");
        String ans = keyboard.nextLine();

        int i;

        for(i=0; i<itemsList.size(); i++)
        {
            if(ans.equalsIgnoreCase(itemsList.get(i).getName()))
            {
                System.out.println(itemsList.get(i).toString());
                return itemsList.get(i).getName();
            }
        }

        if(i == itemsList.size()) { return "empty_category"; }

        return "not in list";
    }
    public void checkStatus()
    {
        int i = 1;
        for (User obj : buyersList)
        {
            if (!(obj.getUserDetails().equalsIgnoreCase("true"))) {
                System.out.println(i + ". " + obj.getUserDetails());
                i++;
            }
        }

    }
    
    
    //adjustedForShoppingCartMethods
    public int lookFor(String item,int quantity)
    {
        for(int i=0; i<this.itemsList.size(); i++)
        {
            if(item.equalsIgnoreCase(this.itemsList.get(i).getName()))
            {
                if (quantity <= this.itemsList.get(i).getStock())
                    return 0;
                else
                    return 1;
            }
        }

        return 2;
    }

     public void alterStock(String itemsName,int amount)
    {

        for(int i=0; i<itemsList.size(); i++)
        {
            if(itemsName.equalsIgnoreCase(itemsList.get(i).getName()))
            {
                itemsList.get(i).setStock(-amount);
                break;
            }
        }
    }

    public double getSelectedItemsPrice(String itemsName)
    {
        for(Item obj: itemsList)
        {
            if(itemsName.equalsIgnoreCase(obj.getName()))
                return obj.getPrice();
        }

      
        return 0;
    }

    //FOR MENU CLASS, LOG IN
    public boolean CheckEmail(String emailLog)
    {
        for(int i=0; i<buyersList.size(); i++)
        {
            if (emailLog.equalsIgnoreCase(buyersList.get(i).getUsersEmail()))
            {
                return true;
            }
        }

        return false;
    }

    //o buyer kappa δεν εμφανίζεται ποτέ, αφού η μέθοδος χρησιμοποιείται μόνο αφού έχει πιστοποιηθεί το email
    public User getUser(String e_mail)
    {
        Buyer kappa = new Buyer("kappa");

        for(int i=0; i<buyersList.size(); i++)
        {
            if (e_mail.equalsIgnoreCase(buyersList.get(i).getUsersEmail()))
            {
                return buyersList.get(i);
            }
        }
        return kappa;
    }
    
    public int getNumberOfCustomers()
    {
        int customer_count = 0;
        for (User obj : buyersList)
        {
            if (!(obj.getUserDetails().equalsIgnoreCase("true"))) {
                customer_count++;
            }
        }

        return customer_count;
    }
}
