import java.util.Scanner;

public class Pencil extends Item{
    private double tipSize; // in mm
    private String type; 

    Scanner keyboard = new Scanner(System.in);

    //constructor
    public Pencil(String Name)
    {
        name = Name;
    }

    //methods
    public String getDetails()
    {
        return " Tip size " + this.tipSize  + ", Type " + this.type;
    }

    public void setMoreItemInfo()
    {
        System.out.println("Enter pencil's tipSize:");
        tipSize = keyboard.nextDouble();
        String nchar = keyboard.nextLine();
        System.out.println("Enter pencil's type(H,B,HB):");
        type = keyboard.nextLine();
    }
}
