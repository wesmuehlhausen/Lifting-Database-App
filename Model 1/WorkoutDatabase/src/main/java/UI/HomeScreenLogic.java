package UI;

import Database.PostgreSQL;
import ProjectObjects.User;

public class HomeScreenLogic {

    private User user;
    private PostgreSQL psqlDatabase;

    public HomeScreenLogic(String username,PostgreSQL psqlDatabase){
        this.psqlDatabase = psqlDatabase;
        user = new User(username, psqlDatabase.getName(username));
    }

    public String getName(){
        return user.getName();
    }



}
