package Restaurant;
import Orders.*;
import Dishes.Dishes;
import Orders.Orders;
import Tables.Tables;
import Users.Users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {

    @XmlElement(name="users")
    private Users users=null;

    @XmlElement(name="dishes")
    private Dishes dishes=null;

    @XmlElement(name="tables")
    private Tables tables=null;




    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }
}
