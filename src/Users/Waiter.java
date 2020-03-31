package Users;

import Orders.Orders;

public class Waiter extends User {

    private Orders orders;


    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
