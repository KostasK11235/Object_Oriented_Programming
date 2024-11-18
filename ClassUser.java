import java.util.Scanner;

public abstract class User {

    private String name;
    private String email;

    Scanner keyboard = new Scanner(System.in);
    
    //methods
    public void setUserName(String Name)
    {
        name = Name;
    }

    public void setUserEmail(String newEmail)
    {
        email = newEmail;
    }

   public abstract String getUserDetails();

    public String getUserName()
    {
        return name;
    }

    publiC String getUsersEmail() { return email; }
}
