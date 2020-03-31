package Users;

import Orders.Order;
import Tables.Table;
import Tables.Tables;
import com.sun.org.apache.xpath.internal.operations.Or;

public class Customer extends User {
    private Order order;
    private int NumberOfchosenTable;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getNumberOfchosenTable() {
        return NumberOfchosenTable;
    }

    public void setNumberOfchosenTable(int numberOfchosenTable)
    {
        NumberOfchosenTable = numberOfchosenTable;
    }
}
