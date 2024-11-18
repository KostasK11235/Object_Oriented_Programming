import java.util.Scanner;

public class NoteBook extends Item {
    private int sections; // αριθμος θεμάτων

    Scanner keyboard = new Scanner(System.in);

    //constructor
    public NoteBook(String Name)
    {
        name = Name;
    }

    //methods
    public String getDetails()
    {
        return " Sections: " + this.sections;
    }

    public void setMoreItemInfo()
    {
        System.out.println("Enter the number of sections in the notebook");
        sections = keyboard.nextInt();
        String nchar = keyboard.nextLine();
    }
}
