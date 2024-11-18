import java.util.Scanner;

public class Pen extends Item {
    private String color;
    private double tipSize; // in mm

    Scanner keyboard = new Scanner(System.in);
    
    //constructor
    public Pen(String Name)
    {
        name = Name;
    }
    
    //methods
    public String getDetails()
    {
        return "color: " + this.color  + ", tip size: " + this.tipSize;
    }

    public void setMoreItemInfo()
    {
        System.out.println("Enter pen's color:");
        color = keyboard.nextLine();
        System.out.println("Enter pen's tipSize:");
        tipSize = keyboard.nextDouble();
        String nchar = keyboard.nextLine();
    }
}
