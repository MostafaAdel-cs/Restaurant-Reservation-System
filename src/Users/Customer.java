package Users;

import Orders.Order;
import Tables.Table;
import Tables.Tables;

public class Customer extends User {
    Order order;
    Tables availableTables;
    Table chosenTable;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Tables getAvailableTables() {
        return availableTables;
    }

    public void setAvailableTables(Tables availableTables) {
        this.availableTables = availableTables;
    }

    public Table getChosenTable() {
        return chosenTable;
    }

    public void setChosenTable(Table chosenTable) {
        this.chosenTable = chosenTable;
    }
}
