package Logic;

import Data.Data;
import Dishes.Dish;
import Dishes.Dishes;
import Orders.Order;
import Restaurant.Restaurant;
import Tables.*;
import Users.*;

import javax.xml.bind.JAXBException;
import java.util.*;

import Dishes.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class Logic {

    private Restaurant restaurant;
    private Data data = new Data();


    public void load() throws JAXBException {
        restaurant = data.loadFromXml();
        setReservedTables();
    }
    public void save() throws JAXBException {
        for(Order o:restaurant.getOrders().getOrders())
        {
            o.setTableNumber(0);
            o.setCustomerUserName("");
            o.setNumber(0);
        }

        data.saveToXml(restaurant);
    }

    public void setReservedTables()
  {
     for(Table table:restaurant.getTables().getTables())
     {
         table.setReserved(false);
       }
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


    public void addUser(String name, String password, String username, String role){
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

//========================== Customer methods===============================================================
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

        if(user.getOrder().getNumber()==0)
        {
            int counter=0;
            for(Order o:restaurant.getOrders().getOrders())
            {
                if(o.getNumber()!=0)
                    counter+=1;
            }
            user.getOrder().setNumber(counter+1);
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

    public boolean checkIfCustomerHaveTable(Customer user) {
        if(user.getOrder().getTableNumber()==0)
            return false;
        else
            return true;
    }
    public boolean checkIfOrderIsCooked(Customer user) {
        if(user.getOrder().isCooked())
            return true;
            else return false;
    }
    public void removeOrder(Customer user) {
        for(Table t:restaurant.getTables().getTables())
            if(user.getOrder().getTableNumber()==t.getNumber())
                t.setReserved(false);
        user.getOrder().setTableNumber(0);
        user.getOrder().setNumber(0);
        try {
            for (Dish dish : user.getOrder().getDishes().getDishes())
                user.getOrder().getDishes().getDishes().remove(dish);
        }catch (Exception e)
        {
            //java.util.ConcurrentModificationException
        }
    }

    //--------------------------------------------cookmethods-------------------------------------------------
    public Cook turnToCook(User user)
    {
        Cook cook=new Cook();
        cook.setName(user.getName());
        cook.setPassword(user.getPassword());
        cook.setRole(user.getRole());
        cook.setUserName(user.getUserName());
        cook.setOrders(restaurant.getOrders());
        return cook;
    }


    public void setOrders(Cook cook, Label orders)
    {


        String n=new String();
        int counter=0;
        boolean isThereOrders=false;
        for(Order o:cook.getOrders().getOrders())
        {
            if(o.getTableNumber()==0)
                continue;
            if(!o.isCooked()){
                isThereOrders=true;
            }
        }


        if(isThereOrders){
            System.out.println(1);
            for(Order o:cook.getOrders().getOrders())
            {

            if(o.getTableNumber()==0)
            { continue;
            }
            counter+=1;

         if(!o.isCooked())
         {
             o.setNumber(counter);
             n+="Order Number "+o.getNumber()+"\n";

            for(Dish dish:o.getDishes().getDishes())
            {
                n+=dish.getName()+"\n";
            }
         }

        }
        orders.setText(n);

    }
        else
            orders.setText("No Orders");
    }

    public void setOrderAsCooked(int o) {
        for(Order or:restaurant.getOrders().getOrders())
            if(o==or.getNumber())
                or.setCooked(true);
    }


    //=================================Waitermethods============================================================
    //Waiter is basically a cook but we made a class to keep it possible to edit it or add methods

    public Waiter turnToWaiter(User user) {
        Waiter waiter=new Waiter();
        waiter.setName(user.getName());
        waiter.setPassword(user.getPassword());
        waiter.setRole(user.getRole());
        waiter.setUserName(user.getUserName());
        waiter.setOrders(restaurant.getOrders());
        return waiter;
    }

    public void setOrdersToWaiter(Waiter user, Label orders)
    {

        String n=new String();

        boolean isThereOrder=false;
        for(Order o:user.getOrders().getOrders())
        {
         if(o.isCooked()&&o.getTableNumber()!=0)
         {
             if(!o.isServed())
             {
                 isThereOrder=true;
                 n+="Order Number "+o.getNumber()+"\n";
                 n+="At Table Number "+o.getTableNumber()+"\n";
             }
         }
        }
        if(!isThereOrder)
        {
            n="No Orders To Serve";
        }
        orders.setText(n);
    }


    public void setServedOrder(int o) {
        for(Order or:restaurant.getOrders().getOrders())
            if(o==or.getNumber())
                or.setServed(true);
    }
    //=========================================Mangermethods================================

    public Manger turnToManger(User user) {
        Manger manger=new Manger();
        manger.setName(user.getName());
        manger.setRole(user.getRole());
        manger.setPassword(user.getPassword());
        manger.setUserName(user.getUserName());
        manger.setTables(restaurant.getTables());
        manger.setDishes(restaurant.getDishes());
        manger.setOrders(restaurant.getOrders());
        manger.setUsers(restaurant.getUsers());
        return manger;
    }

    public void setUsers(Manger user, Label users) {

        String u=new String();
        for(User user1:user.getUsers().getUsers())
        {
            u+="Name:"+user1.getName()+" Role:"+user1.getRole()+" Username:"+user1.getUserName()+"\n";

        }
        users.setText(u);

    }

    public void setOrdersToManger(Manger user, Label orders) {

        String or=new String();
        boolean addedOrder=false;
        for(Order o:user.getOrders().getOrders())
        {
            if(o.getTableNumber()==0)
                continue;

            addedOrder=true;
            if(o.getNumber()==0)
            or+="Username :"+o.getCustomerUserName()+"  Cook didn't see Order Yet"+"\n";
            else
                or+="Username :"+o.getCustomerUserName()+"  Order Number:"+o.getNumber()+"\n";
            for(Dish d:o.getDishes().getDishes())
            {
                or+=d.getName()+"\n";
            }
            if(o.isCooked())
            {
                or+=" cooked";
            }
            else
            {
                or+=" Not Cooked";
            }
            if (o.isServed())
            {
                 or+=" Served";
            }
            else
            {
                or+=" Not Served";
            }
            or+="\n";
        }
        if(addedOrder)
        orders.setText(or);
        else
            orders.setText("No Orders");
    }

    public void setTables(Manger user, Label tables) {
        String t=new String();
        for(Table ta:user.getTables().getTables())
        {
        t+="Table Number: "+ta.getNumber();
        if(ta.isReserved())
        {
            t+=" Table is Reserved\n";
        }
        else
            t+=" Table is Free\n";

        }
        tables.setText(t);
    }

    public void addTable(Manger user, int seats, Boolean smoking) {
        int counter=0;
        for(Table t:restaurant.getTables().getTables())
            counter++;
        Table newTable=new Table();
        newTable.setReserved(false);
        newTable.setNumber(counter+1);
        newTable.setNumberOfSeats(seats);
        newTable.setSmoking(smoking);
        restaurant.getTables().getTables().add(newTable);
    }

    public void setDishes(Manger user,Label dishes) {
        String d=new String();
        for(Dish di:user.getDishes().getDishes())
        {
            d+="Dish Name: "+di.getName()+" Price: "+di.getPrice()+" Type: "+di.getType()+"\n";
        }
        dishes.setText(d);
    }

    public void addDish(Manger user, String name, String type, int price) {
        Dish newDish=new Dish();
        newDish.setType(type);
        newDish.setPrice(price);
        newDish.setName(name);
        user.getDishes().getDishes().add(newDish);
    }

    public boolean setOrderAsPaid(int orderNumber) {
        for(Order o:restaurant.getOrders().getOrders())
        {
            if (orderNumber == o.getNumber()&&orderNumber!=0)
            {
                if (o.isCooked())
                {
                    if (o.isServed())
                    {
                        for(Table t:restaurant.getTables().getTables())
                        {
                            if(o.getTableNumber()==t.getNumber())
                            {
                                t.setReserved(false);
                            }
                        }


                        double sum=0.0;
                        for(Dish dish:o.getDishes().getDishes())
                        {

                            if(dish.getType().contentEquals("appetizer"))
                            {
                                sum+=dish.getPrice()*(1+Appetizer.getTaxes());
                            }
                            else if(dish.getType().contentEquals("dessert"))
                            {
                                sum+=dish.getPrice()*(1+Dessert.getTaxes());
                            }
                            else if(dish.getType().contentEquals("main_course"))
                                sum+=dish.getPrice()*(1+MainCourse.getTaxes());

                        }
                            try {
                                for (Dish dish : o.getDishes().getDishes())
                                    o.getDishes().getDishes().remove(dish);
                            }
                            catch (Exception e){

                            }
                        o.setServed(false);
                        o.setCooked(false);
                        o.setNumber(0);
                        o.setTableNumber(0);
                        restaurant.setMoneyGained(sum);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setTotalGain(Label totalGain) {
        totalGain.setText("Total Gain ="+restaurant.getMoneyGained());
    }
}








