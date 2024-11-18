import java.util.Scanner;

public class Paper extends Item{
    private int weight; // in grams
    private int pages;

    Scanner keyboard = new Scanner(System.in);

    //constructor
    public Paper(String Name)
    {
        name = Name;
    }

    //methods
    public String getDetails()
    {
        return " Weight: " + this.weight  + "gr Pages: " + this.pages;
    }

    public void setMoreItemInfo()
    {
        System.out.println("Enter paper's weight:");
        weight = keyboard.nextInt();
        String Nchar = keyboard.nextLine();
        System.out.println("Enter paper's pages:");
        pages = keyboard.nextInt();
        String nchar = keyboard.nextLine();
    }
}
