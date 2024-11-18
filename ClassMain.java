import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        //ΔΗΜΙΟΥΡΓΟΥΜΕ ΤΗ ΛΙΣΤΑ ΤΩΝ ΠΡΟΪΟΝΤΩΝ (3 items per class)
        EShop shop = new EShop();

        boolean more = true;

        while(more)
        {
            shop.addItem();
            System.out.print("Add another item in shop list?(y/n)");
            String answer = keyboard.nextLine();
            if(answer.equalsIgnoreCase("n"))
                more = false;
        }

        /*Items to add in E-shop list
        Pens: Name: Blue Pen , Price: 1.2 €, Stock: 150 , Color: blue , Tip Size: 0.03
              Name: Red Pen , Price: 1.2 €, Stock: 150 , Color: red , Tip Size: 0.03
              Name: Black Pen, Price: 1.2 €, Stock: 150 , Color: black , Tip Size: 0.03
        Pencils: Name: PencilVane, Price: 2 €, Stock: 80, Tip Size: 0.05 , Type: HB
                 Name: Regular Pencil, Price: 1 €, Stock: 200, Tip Size: 0.05, Type: B
                 Name: Mechanical Pencil, Price: 1.7 €, Stock: 130, Tip Size: 0.02, Type: H
        NoteBooks: Name: My notebook, Price: 4.5 €, Stock: 75, Number Of Sections: 4
                   Name: Green notebook, Price: 3 €, Stock: 125, Number of Sections: 3
                   Name: Blue notebook, Price: 2.5 €, Stock: 90, Number of Sections: 2
        Paper: Name: White paper, Price: 0.1 €, Stock: 500, Weigh: 2, Pages: 500
               Name: Blue paper, Price: 0.2 €, Stock: 350, Weigh: 2, Pages: 350
               Name: Green paper, Price: 0.2 €, Stock: 400, Weigh: 2, Pages: 400
         */




        //ΔΗΜΙΟΥΡΓΟΥΜΕ 1 OWNER ΚΑΙ 2 BUYERS

        more = true;
        while(more)
        {
            shop.addBuyer();
            System.out.println("Add another buyer on list?(y/n)");
            String answer = keyboard.nextLine();
            if(answer.equalsIgnoreCase("n"))
                more = false;
        }

        /*
        Owner owner = new Owner("KOSTAS");
        owner.setUserEmail("Owner_Kostas@eshopmail.gr");

        //BUYERS
        Buyer buyer1 = new Buyer("JILL");
        buyer1.setUserEmail("Its_Jill@eshopmail.gr");

        Buyer buyer2 = new Buyer("BILL");
        buyer2.setUserEmail("Its_Bill@eshopmail.gr");

         */

        Menu menu = new Menu();

        menu.LogIn(shop);
    }
}
