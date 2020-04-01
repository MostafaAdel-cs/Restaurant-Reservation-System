package Logic;

import Data.Data;
import Dishes.Dish;
import Dishes.Dishes;
import Orders.Order;
import Orders.Orders;
import Restaurant.Restaurant;
import Tables.*;
import Users.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.xml.bind.JAXBException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;

import Dishes.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class Logic {

    private Restaurant restaurant;
    private Data data = new Data();


    public void load() throws JAXBException {
        restaurant = data.loadFromXml();
    }
    private  void save() throws JAXBException {
        data.saveToXml(restaurant);
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

        if(user.getRole().contentEquals("Manger"))
        {

            return "Manger";
        }
        else if(user.getRole().contentEquals("Client"))
        {
            return "Client";
        }
        else if(user.getRole().contentEquals("Cooker"))
        {
            return "Cooker";
        }
        else
        {
            return "Waiter";
        }

    }


    public void addUser(String name, String password, String username, String role) throws JAXBException {
        User user=new User();
        user.setName(name);
        user.setUserName(username);
        user.setPassword(password);
        user.setRole(role);
        restaurant.getUsers().getUsers().add(user);
        save();
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }


  public Customer turnToCustomer( User user)
  {
      boolean flag=false;
      Customer customer=new Customer();
      for(Order order:restaurant.getOrders().getOrders())
      {
          if(user.getUserName().contentEquals(order.getCustomerUserName()))
          {
              customer.setUserName(user.getUserName());
              customer.setRole(user.getRole());
              customer.setPassword(user.getPassword());
              customer.setName(user.getName());
              restaurant.getUsers().getUsers().remove(user);
              restaurant.getUsers().getUsers().add(customer);
              customer.setOrder(order);
                flag=true;
          }
      }


      if(!flag)
      {
          customer.setUserName(user.getUserName());
          customer.setRole(user.getRole());
          customer.setPassword(user.getPassword());
          customer.setName(user.getName());
          restaurant.getUsers().getUsers().remove(user);
          restaurant.getUsers().getUsers().add(customer);
            Order order=new Order();
            Dishes dishes=new Dishes();
            List<Dish> dishes1=new ArrayList<Dish>();
            dishes.setDishes(dishes1);
            order.setDishes(dishes);
            order.setCustomerUserName(customer.getUserName());
            customer.setOrder(order);
            restaurant.getOrders().getOrders().add(order);

      }

      return customer;
  }


    public void addOrderToUser(Customer user,String appetizer,String mainCourse,String dessert) {
        if(appetizer!=null){
          for(Dish dish:restaurant.getDishes().getDishes())
          {
              if(appetizer.contentEquals(dish.getName())){
                  user.getOrder().getDishes().getDishes().add(dish);
              }
          }
        }
        if(mainCourse!=null){
            for(Dish dish:restaurant.getDishes().getDishes())
            {
                if(dish.getName().contentEquals(mainCourse)){
                    user.getOrder().getDishes().getDishes().add(dish);
                }
            }
        }
        if(dessert!=null){
            for(Dish dish:restaurant.getDishes().getDishes())
            {
                if(dessert.contentEquals(dish.getName())){
                    user.getOrder().getDishes().getDishes().add(dish);
                }
            }
        }


        try {
            save();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public void setReceipt(Customer user, Label receipt, Label sum1) {
        String r=new String();
    double sum=0.0;

        HashMap<String,Integer> map1=new HashMap();
        for(Dish dish:user.getOrder().getDishes().getDishes())
        {
            if(map1.containsKey(dish.getName()))
            {
                int old=0;
                old=map1.get(dish.getName());
                map1.remove(dish.getName());
                map1.put(dish.getName(),old+1);


            }
            else
            {

             map1.put(dish.getName(),1);
            }
        }
        Set<String> key=map1.keySet();

        for(String dish:key)
        {
            r+=dish+" x"+map1.get(dish)+"\n";
        }
        for(Dish dish:user.getOrder().getDishes().getDishes())
        {
            if(dish.getType().contentEquals("appetizer"))
            {
                sum+=dish.getPrice()*(1+Appetizer.getTaxes());
            }
            else if(dish.getType().contentEquals("dessert"))
            {
                sum+=dish.getPrice()*(1+Dessert.getTaxes());
            }
            else
            sum+=dish.getPrice()*(1+MainCourse.getTaxes());

        }

        r=r+"Appetizer Taxes ="+Appetizer.getTaxes()+"\n";
        r=r+"Dessert Taxes ="+Dessert.getTaxes()+"\n";
        r=r+"Main course Taxes ="+MainCourse.getTaxes();
        receipt.setText(r);
        sum1.setText("Total ="+String.valueOf(sum));


    }


    public void getFreeTables(ChoiceBox<String> choosetable) {

        choosetable.getItems().clear();
        String smoking=new String();
        for(Table table:restaurant.getTables().getTables())
        {
            if(!table.isReserved())
            {
                if(table.isSmoking())
                smoking="Smoking";
                else
                    smoking="Non Smoking";
                choosetable.getItems().add("Table number ="+table.getNumber()+",Seats ="+table.getNumberOfSeats()+","+smoking);
            }
        }
    }

    public void reserveTableForUser(Customer user,String table)
    {
        int x=0;
        String tnum=new String();
        for (String val: table.split("=")) {
        x++;
        if(x==2){
            tnum=val;
        }
        }
        x=0;
        for (String val: tnum.split(",")){
            x++;
            if(x==1)
                tnum=val;
        }
        int tablenumber=Integer.parseInt(tnum);

        if(user.getOrder().getTableNumber()==0) {
            user.getOrder().setTableNumber(tablenumber);

            for(Table table1:restaurant.getTables().getTables())
            {
                if(tablenumber==table1.getNumber())
                    table1.setReserved(true);
            }
        }
        else
            {
                int reservedTable;
                reservedTable=user.getOrder().getTableNumber();
                user.getOrder().setTableNumber(tablenumber);
                for(Table table1:restaurant.getTables().getTables())
                {
                    if(tablenumber==table1.getNumber())
                        table1.setReserved(true);
                }

                for(Table table1:restaurant.getTables().getTables())
                {
                    if(reservedTable==table1.getNumber())
                        table1.setReserved(false);
                }
             }
    }
}