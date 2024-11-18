public class Owner extends User{
    private Boolean isAdmin = true;

    //constructors

    public Owner(String newName)
    {
        this.setUserName(newName);
    }

    //methods

    public String getUserDetails()
    {
        return isAdmin.toString();
    }
}
