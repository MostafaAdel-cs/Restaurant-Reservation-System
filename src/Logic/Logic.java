package Logic;

import Data.Data;
import Restaurant.Restaurant;
import Users.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Logic {

private Restaurant restaurant;
private Data data=new Data();


    public void load() throws JAXBException {
        restaurant=data.loadFromXml();
    }

    public void printUsers()
    {

    for(User user:restaurant.getUsers().getUsers())
        System.out.println(user.getName());

    }
}
