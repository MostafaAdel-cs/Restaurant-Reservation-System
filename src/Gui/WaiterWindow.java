package Gui;

import Logic.Logic;
import Users.User;
import Users.Waiter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WaiterWindow {
    private Stage stage;
    private Scene scene;
    private Waiter user;
    private Logic logic;
    public WaiterWindow(Stage stage, Logic logic) {
        this.stage = stage; this.logic=logic;
    }
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public void prepareScene()
    {

        Label orders=new Label();
        setOrders(orders);
        Button back = new Button("Back");
        TextField entity=new TextField();
        Label enterOrderNumber=new Label("Enter Order Number");
        Label wrongEntry=new Label("Wrong Entry");
        wrongEntry.setVisible(false);
        Button getOrder=new Button("Done");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(enterOrderNumber,0,1);
        grid.add(entity,3,1);
        grid.add(wrongEntry,4,1);
        grid.add(orders,1,0);
        grid.add(back,1,3);
        grid.add(getOrder,1,2);

        orders.setTextFill(Color.web("#FFFFFF"));
        enterOrderNumber.setTextFill(Color.web("#FFFFFF"));
        wrongEntry.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
        getOrder.setOnAction(e->getDoneOrders(entity.getText(),wrongEntry));
    }

    private void getDoneOrders(String orderNumber, Label wrongEntry) {
        int o;
        try{
            o=Integer.parseInt(orderNumber);
            logic.setServedOrder(o);
            prepareScene();
            showScene();
        }catch (Exception e)
        {
         wrongEntry.setVisible(true);
        }


    }

    private void setOrders(Label orders)
    {
    logic.setOrdersToWaiter(user,orders);
    }

    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }



    public void setUser(User user)
    {
        this.user=logic.turnToWaiter(user);   }

}
