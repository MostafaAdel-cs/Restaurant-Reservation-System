package Data;

import Restaurant.Restaurant;
import Users.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Data {


    public Restaurant loadFromXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Restaurant restaurant = (Restaurant) unmarshaller.unmarshal(new File("src/Data/RestaurantData.xml"));
        return restaurant;
    }


    public void saveToXml(Restaurant restaurant) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
       marshaller.marshal(restaurant,new File("src/Data/RestaurantData.xml"));
    }




}
