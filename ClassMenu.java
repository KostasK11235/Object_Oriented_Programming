import java.util.Scanner;

public class Menu {
    Scanner keyboard = new Scanner(System.in);

    public void LogIn(EShop eShop)
    {
        boolean continue_using = true;
        while(continue_using) {
            System.out.println("Enter your email to connect: ");
            String emailLog = keyboard.nextLine();


            if (eShop.CheckEmail(emailLog))
            {
                if (eShop.getUser(emailLog).getUserDetails().equalsIgnoreCase("true"))
                {
                    ownerOptions((Owner)eShop.getUser(emailLog), eShop);
                } else {
                    menuOptions((Buyer)eShop.getUser(emailLog), eShop);
                }
            } else {
                System.out.print("The email you entered does not match a buyer/owner in our list." + '\n' + "Would you like to add this user in buyers list?(y/n)");
                String ans = keyboard.nextLine();
                if (ans.equalsIgnoreCase("y")) {
                    eShop.addBuyer();
                    System.out.println("Registration was successful.");
                }
                else
                {
                    System.out.println("Exit or continue using?(exit/continue)");
                    String yeahOrNah = keyboard.nextLine();
                    if(yeahOrNah.equalsIgnoreCase("exit"))
                        continue_using = false;
                }
            }
        }
    }

    private void menuOptions(Buyer customer,EShop eShop)
    {
        System.out.println("Hello: " + customer.getUserName() + "! Welcome to our EShop.");
        System.out.println(customer.getUserDetails());

        System.out.println("Menu options: ");
        System.out.println("1. Browse store" + '\n' + "2. View cart" + '\n' + "3. Check Out" + '\n' + "4. Back" +
                '\n' + "5. Log out" + '\n' + "6. Exit");
        System.out.println("----------------------------------------------");


        System.out.print("Enter a number between 1-6 to choose an option: ");
        int optionNum = keyboard.nextInt();
        String nchar = keyboard.nextLine();
        System.out.println("----------------------------------------------");

        boolean exit = false;
        while(!exit)
        {

            while(optionNum<1 || optionNum>6)
            {
                System.out.println("Enter a number between 1-6 to proceed.");
                System.out.print("Enter the number of the option you want to continue with: ");
                int newNumber = keyboard.nextInt();
                String Nchar = keyboard.nextLine();
                optionNum = newNumber;
            }

            switch (optionNum)
            {
                case(1):
                {
                    BrowseStore(customer,eShop);
                    System.out.print("Would you like to go back or exit?(back/exit/no)" +
                                        "\n(Warning! : choosing back from this point will return you to log-in screen.) ");
                    String back_exit = keyboard.nextLine();
                    if(back_exit.equalsIgnoreCase("back"))
                    {
                        exit = true;
                        LogOut();
                    }
                    else if(back_exit.equalsIgnoreCase("exit"))
                    {
                        Exit();
                    }
                    else
                    {
                        System.out.print("Enter the option's number you want to continue with: ");
                        optionNum = keyboard.nextInt();
                        break;
                    }
                    break;
                }
                case(2):
                {
                    boolean done_notDone = ViewCart(eShop,customer);
                    if(done_notDone)
                    {
                        exit = true;
                        nchar = keyboard.nextLine();
                        break;
                    }
                    else {
                        System.out.print("Would you like to go back or exit?(back/exit/no)");
                        String back_exit = keyboard.nextLine();
                        if (back_exit.equalsIgnoreCase("back")) {
                            optionNum = Back(optionNum);
                            break;
                        } else if (back_exit.equalsIgnoreCase("exit")) {
                            Exit();
                        } else {
                            System.out.print("Enter the option's number you want to continue with: ");
                            optionNum = keyboard.nextInt();
                            break;
                        }
                    }

                    break;
                }
                case(3):
                {
                    boolean done_not = CheckOut(customer);
                    if(done_not)
                    {
                        exit = true;
                        nchar = keyboard.nextLine();
                        break;
                    }
                    else {
                        String charr = keyboard.nextLine();
                        System.out.print("Would you like to go back or exit?(back/exit/no)");
                        String back_exit = keyboard.nextLine();
                        if (back_exit.equalsIgnoreCase("back")) {
                            optionNum = Back(optionNum);
                            break;
                        } else if (back_exit.equalsIgnoreCase("exit")) {
                            Exit();
                        } else {
                            System.out.println("\n" + "------------------------------------");
                            eShop.showCategories();
                            System.out.print("Enter the option's number you want to continue with: ");
                            optionNum = keyboard.nextInt();
                            nchar = keyboard.nextLine();
                            break;
                        }
                    }
                    break;
                }
                case(4):
                {
                    optionNum = Back(optionNum);
                    break;
                }
                case(5):
                {
                    String nullChar = keyboard.nextLine();
                    LogOut();
                    exit = true;
                    break;
                }
                case(6):
                {
                    Exit();
                    break;
                }
            }

        }

    }

    //ΜΕΝΟΥ ΓΙΑ ΧΡΗΣΤΗ BUYER
    private void BrowseStore(Buyer customer, EShop eShop)
    {
        System.out.println("\t\t\tE-Shop\t\t\t");

        eShop.showCategories();
        System.out.println("\n" + "------------------------------------");

        String continueBrowse = "y";
        while(continueBrowse.equalsIgnoreCase("y"))
        {
            System.out.println("Which category of products you would like to see(full name)?");
            String ans = keyboard.nextLine();
            String copyAns = ans;

            String keepCategory = "y";
            while (keepCategory.equalsIgnoreCase("y"))
            {
                String catOK = eShop.showProductsInCategory(ans);
                while(!(catOK.equalsIgnoreCase("ok")))
                {
                    System.out.println("No such category in our list! \nPlease re-enter the category you want to see.");
                    ans = keyboard.nextLine();
                    catOK = eShop.showProductsInCategory(ans);
                    copyAns = ans;
                }
                String item = eShop.showProduct();
                if(item.equalsIgnoreCase("empty_category"))
                {
                    System.out.println("This category is empty.");
                    System.out.println("Would you like to remain in the same category?(y/n)");
                    keepCategory = keyboard.nextLine();
                    if (keepCategory.equalsIgnoreCase("y")) {
                        keepCategory = "y";
                        ans = copyAns;
                    } else
                        keepCategory = "n";
                }
                else {
                    while (item.equalsIgnoreCase("not in list")) {
                        System.out.println("Enter a product from the list!");
                        item = eShop.showProduct();
                    }

                    System.out.println("Would you like to add that product in your cart? (y/n)");
                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("y")) {
                        customer.placeOrder(eShop, item);
                    }

                    System.out.println("Would you like to remain in the same category?(y/n)");
                    keepCategory = keyboard.nextLine();
                    if (keepCategory.equalsIgnoreCase("y")) {
                        keepCategory = "y";
                        ans = copyAns;
                    } else
                        keepCategory = "n";
                }
            }

            System.out.println("Would you like to continue browsing at the store?(y/n)");
            continueBrowse = keyboard.nextLine();
        }

    }

    private boolean ViewCart(EShop eShop,Buyer buyer)
    {
        System.out.println("----------------------------------------" +
                "\n" + "Your cart: ");
        String cart_state = buyer.getBuyerCart();
        if(cart_state.equalsIgnoreCase("empty"))
        {
            System.out.println("Add items in your cart.");
            return false;
        }
        else {
            System.out.println("---------------------------------");
            System.out.println("How would you like to proceed?");
            System.out.println("1. Change an item's quantity or remove an item from your list" + "\n2. Clear your cart" + "\n3. Check out");
            System.out.print("Enter your option number(1-3): ");
            int optNum = keyboard.nextInt();
            String nLine = keyboard.nextLine();

            while (optNum < 1 || optNum > 3) {
                System.out.println("Option number must be between 1-3.");
                System.out.print("Enter your option number(1-3): ");
                optNum = keyboard.nextInt();
            }

            switch (optNum) {
                case (1): {
                    String alterItemOrdered = "y";

                    while (alterItemOrdered.equalsIgnoreCase("y")) {
                        System.out.print("You want to remove or change the quantity of an item you ordered?(remove/change)");
                        String answer = keyboard.nextLine();
                        if (answer.equalsIgnoreCase("remove")) {
                            System.out.print("Which item would you like to remove from your list?");
                            String ans = keyboard.nextLine();
                            buyer.removeOrder(eShop, ans);
                        } else if (answer.equalsIgnoreCase("change")) {
                            System.out.println("Which item's quantity you would like to change?");
                            String ans = keyboard.nextLine();
                            System.out.print("You want to raise or lessen the quantity of that items?(r/l)");
                            String up_low = keyboard.nextLine();
                            if (up_low.equalsIgnoreCase("r")) {
                                System.out.print("Enter how many units you want to add: ");
                                int units = keyboard.nextInt();
                                String nChar = keyboard.nextLine();
                                buyer.changeQuantity(eShop, ans, units);
                            } else if(up_low.equalsIgnoreCase("l")){
                                System.out.print("Enter how many units you want to subtract: ");
                                int units = keyboard.nextInt();
                                String nChar = keyboard.nextLine();
                                buyer.changeQuantity(eShop, ans, -units);
                            }

                        }

                        System.out.print("Would you like to make another change or remove an item from your cart?(y/n)");
                        alterItemOrdered = keyboard.nextLine();
                    }
                    return false;

                }

                case (2): {
                    buyer.clearBuyersCart(eShop);
                    return false;

                }

                case (3): {
                    boolean done = buyer.checkOutBuyer();
                    if(done)
                    {
                        return true;
                    }
                    else { return false;}
                }
            }
            return false;
        }
    }

    private boolean CheckOut(Buyer buyer)
    {
        return buyer.checkOutBuyer();
    }

    private int Back(int optionNum)
    {
        return optionNum-1;
    }

    private void LogOut()
    {
        System.out.println("Connect with another account?(y/n)");
        String answer = keyboard.nextLine();
        if(answer.equalsIgnoreCase("n"))
        {
            Exit();
        }
    }

    private void Exit()
    {
        System.out.println("Thank you for visiting our e-shop." + '\n' + "We hope to visit us again.");
        System.exit(0);
    }


    //MENU ΓΙΑ ΧΡΗΣΤΗ OWNER

    private void ownerOptions(Owner owner,EShop shop)
    {
        System.out.println("Hello " + owner.getUserName() + " !");
        System.out.println("Name: " + owner.getUserName() + ", Email: " + owner.getUsersEmail() + ", Administrator: " + owner.getUserDetails());
        System.out.println("----------------------------------");
        System.out.println("What would you like to do?");
        System.out.println("1. Browse store\n" + "2. Check Status\n" + "3. Back\n" + "4. Log out\n" + "5. Exit");
        
        System.out.println("----------------------------------");
        System.out.println("With which option you would like to proceed (1-5)?");
        int option = keyboard.nextInt();


        boolean managing = true;
        while(managing)
        {

            while(option<1 ||option>5)
            {
                System.out.print("Option number must be between 1 and 5!");
                option  = keyboard.nextInt();
            }

            switch(option)
            {
                case(1):
                {
                    ownerBroseStore(owner,shop);
                    System.out.print("Would you like to go back/exit? (back/exit/no)" +
                            "\n(Warning! : choosing back from this point will return you to log-in screen.) ");
                    String back_exit = keyboard.nextLine();
                    if(back_exit.equalsIgnoreCase("back"))
                    {
                        LogOut();
                        managing = false;
                    }
                    else if(back_exit.equalsIgnoreCase("exit"))
                    {
                        Exit();
                    }
                    else
                    {
                        System.out.print("Enter the option's number you want to continue with: ");
                        option = keyboard.nextInt();
                        break;
                    }
                    break;
                }
                case(2):
                {
                    CheckStatus(shop);
                    System.out.print("Would you like to go back/exit? (back/exit/no)");
                    String back_exit = keyboard.nextLine();
                    if(back_exit.equalsIgnoreCase("back"))
                    {
                        option = Back(option);
                        break;
                    }
                    else if(back_exit.equalsIgnoreCase("exit"))
                    {
                        Exit();
                    }
                    else
                    {
                        System.out.print("Enter the option's number you want to continue with: ");
                        option = keyboard.nextInt();
                        String line = keyboard.nextLine();
                        break;
                    }
                    break;
                }
                case(3):
                {
                    option = Back(option);
                    break;
                }
                case(4):
                {
                    managing = false;
                    String nullCHAR = keyboard.nextLine();
                    LogOut();
                    break;
                }
                case(5):
                {
                    Exit();
                    break;
                }
            }
        }


    }

    private void ownerBroseStore(Owner owner, EShop shop)
    {
        String nCHAR = keyboard.nextLine();
        System.out.println("\t\t\tE-Shop\t\t\t");

        shop.showCategories();
        System.out.println("\n" + "------------------------------------");

        String continueBrowse = "y";
        while(continueBrowse.equalsIgnoreCase("y"))
        {

            System.out.print("Which category of products you would like to see?");
            String ans = keyboard.nextLine();
            String copyAns = ans;

            String keepCategory = "y";
            while (keepCategory.equalsIgnoreCase("y"))
            {
                String catOK = shop.showProductsInCategory(ans);
                while(!(catOK.equalsIgnoreCase("ok")))
                {
                    System.out.println("No such category in our list! \nPlease re-enter the category you want to see.");
                    ans = keyboard.nextLine();
                    catOK = shop.showProductsInCategory(ans);
                    copyAns = ans;
                }
                String item = shop.showProduct();
                if(item.equalsIgnoreCase("empty_category"))
                {
                    System.out.println("Tha category of products is empty.");
                }
                else {
                    while (item.equalsIgnoreCase("not in list")) {
                        System.out.println("Enter a product from the list!");
                        item = shop.showProduct();
                    }

                    System.out.println("Would you like to alter current item's stock?(y/n)");
                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("y")) {
                        shop.updateItemStock(item);
                    }

                    System.out.println("Would you like to remain in the same category?(y/n)");
                    keepCategory = keyboard.nextLine();
                    if (keepCategory.equalsIgnoreCase("y")) {
                        keepCategory = "y";
                        ans = copyAns;
                    } else
                        keepCategory = "n";
                }
            }

            System.out.println("Would you like to continue browsing at the store?(y/n)");
            continueBrowse = keyboard.nextLine();
        }

    }

    private void CheckStatus(EShop shop)
    {
        int numberOfCustomers = shop.getNumberOfCustomers();
        if(numberOfCustomers == 0)
        {
            System.out.println("There are no customers in list.");
            String nCHAR = keyboard.nextLine();
        }
        else {
            shop.checkStatus();

            String nCHAR = keyboard.nextLine();
            System.out.print("Would you like to check a buyer's cart?(y/n)");
            String answer = keyboard.nextLine();

            while (answer.equalsIgnoreCase("y")) {
                System.out.print("Enter the email of the buyer: ");
                String buyersEmail = keyboard.nextLine();
                if (shop.CheckEmail(buyersEmail)) {
                    ((Buyer) shop.getUser(buyersEmail)).getBuyerCart();
                    System.out.println("----------------------------------------");
                    System.out.println("Would you like to remove that buyer from buyers list?(y/n)");
                    String remove = keyboard.nextLine();
                    if (remove.equalsIgnoreCase("y")) {
                        ((Buyer) shop.getUser(buyersEmail)).clearBuyersCart(shop);
                        System.out.println(shop.removeBuyer((shop.getUser(buyersEmail)).getUserName()));
                    }
                }

                System.out.println("Would you like to check another buyer's cart?(y/n)");
                answer = keyboard.nextLine();
            }
        }
    }
}
