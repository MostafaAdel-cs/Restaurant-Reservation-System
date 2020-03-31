package Logic;

import Data.Data;
import Restaurant.Restaurant;
import Users.Manger;
import Users.User;

import javax.xml.bind.JAXBException;

public class Logic {

    private Restaurant restaurant;
    private Data data = new Data();


    public void load() throws JAXBException {
        restaurant = data.loadFromXml();
    }

    public boolean checkUsers(String username, String password) {

        for (User user : restaurant.getUsers().getUsers()) {
            if (username.contentEquals(user.getUserName()) && password.contentEquals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public boolean isUsernameTaken(String username)
    {
        for (User user : restaurant.getUsers().getUsers()) {
            if(user.getUserName().contentEquals(username))
            {
                return true;
            }

        }
        return false;
    }

    public User getUser(String username) {
        for (User user : restaurant.getUsers().getUsers()) {
            if (username.contentEquals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

    public String getRole(User user) {

        if(user.getRole().contentEquals("Manager"))
        {
            return "Manager";
        }
        else if(user.getRole().contentEquals("Client"))
        {
            return "Client";
        }
        else if(user.getRole().contentEquals("Cook"))
        {
            return "Cook";
        }
        else
        {
            return "Waiter";
        }

    }

    private void downCastUsers(){

    }

    public void addUser(String name, String password, String username, String role) {
        User user=new User();
        user.setName(name);
        user.setUserName(username);
        user.setPassword(password);
        user.setRole(role);
        restaurant.getUsers().getUsers().add(user);
    }
}