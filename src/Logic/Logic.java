package Logic;

import Data.Data;
import Dishes.Dish;
import Dishes.Dishes;
import Orders.Order;
import Restaurant.Restaurant;
import Users.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.xml.bind.JAXBException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import Dishes.*;
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


    public void addUser(String name, String password, String username, String role) {
        User user=new User();
        user.setName(name);
        user.setUserName(username);
        user.setPassword(password);
        user.setRole(role);
        restaurant.getUsers().getUsers().add(user);
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void addOrderToUser(Customer user, String appetizer, String mainCourse, String dessert)
    {

        for(Dish dish:restaurant.getDishes().getDishes())
        {
            if(appetizer!=null) {
                if (dish.getName().contentEquals(appetizer))
                    user.getOrder().getDishes().getDishes().add(dish);
            }

            if(mainCourse!=null) {
                if (dish.getName().contentEquals(mainCourse))
                    user.getOrder().getDishes().getDishes().add(dish);
            }
            if(dessert!=null){
            if(dish.getName().contentEquals(dessert))
                user.getOrder().getDishes().getDishes().add(dish);
            }
        }
    }

    public String getRecipt(Customer user) {
        return null;

    }

    public void printOrder(Customer user)
    {
        for(Dish dish:user.getOrder().getDishes().getDishes())
            System.out.println(dish.getName()+" "+dish.getType());
    }



}