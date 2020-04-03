package Gui;

import Gui.subGui.AddDishWindow;
import Gui.subGui.AddTableWindow;
import Logic.Logic;
import Users.Manger;
import Users.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.lang.management.ManagementFactory;

public class MangerWindow {
    private  Stage stage;
    private  Scene scene;
    private Manger user;
    private Logic logic;
    private AddTableWindow addTableWindow=new AddTableWindow();
    private AddDishWindow addDishWindow=new AddDishWindow();
    public MangerWindow(Stage stage,Logic logic) {
        this.stage = stage; this.logic=logic;

    }
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }



    public void prepareMainScene()
    {
        Button back = new Button("Back");
        Button showUsers=new Button("Show Users");
        Button showTables=new Button("Show Tables");
        Button showOrders=new Button("Show Orders");
        Button showDishes=new Button("Show Dishes");
        Label totalGain=new Label();
        setTotalGain(totalGain);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(totalGain,1,0);
        grid.add(showUsers,0,1);
        grid.add(showDishes,2,1);
        grid.add(showTables,0,2);
        grid.add(showOrders,2,2);
        grid.add(back,1,3);

        totalGain.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
        showUsers.setOnAction(e->{userScene(); showScene();});
        showOrders.setOnAction(e->{orderScene(); showScene();});
        showDishes.setOnAction(e->{dishesScene(); showScene();});
        showTables.setOnAction(e->{tablesScene(); showScene();});
    }

    private void setTotalGain(Label totalGain) {
        logic.setTotalGain(totalGain);
    }

    private void dishesScene(){
        Label dishes=new Label();
        setDishes(dishes);
        Button addDish=new Button("Add Dish");
        Button back=new Button("Back");
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(dishes,0,0);
        grid.add(addDish,0,1);
        grid.add(back,0,2);

        dishes.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        back.setOnAction(e->{prepareMainScene(); showScene();});
        addDish.setOnAction(e->{addDish();});
    }

    private void addDish() {
        addDishWindow.showStage(user,logic);
        dishesScene();
        showScene();
    }

    private void setDishes(Label dishes) {
        logic.setDishes(user,dishes);
    }

    private void tablesScene()
    {
        Label tables=new Label();
        setTables(tables);
        Button addTable=new Button("Add Table");
        Button back = new Button("Back");
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(tables,0,0);
        grid.add(addTable,0,1);
        grid.add(back,0,2);

        tables.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        back.setOnAction(e->{prepareMainScene(); showScene();});
        addTable.setOnAction(e->{addTable(user,logic);});
    }

    private void addTable(Manger user, Logic logic) {
        addTableWindow.showStage(user,logic);
        tablesScene();
        showScene();
    }

    private void setTables(Label tables) {
        logic.setTables(user,tables);
    }

    private void orderScene()
    {
        Button back = new Button("Back");
        Label orders=new Label();
        setOrders(orders);
        Label choosePaidOrder=new Label("Choose Paid Order");
        TextField paidOrderInput=new TextField();
        Button paid=new Button("Paid");
        Label wrong=new Label("Wrong Entity");
        Label orderNotServedOrCookedYet=new Label("Order Not Served Or Cooked Yet");

        wrong.setVisible(false);
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(orders,0,0);
        grid.add(choosePaidOrder,0,1);
        grid.add(paidOrderInput,1,1);
        grid.add(paid,0,3);
        grid.add(wrong,0,2);
        grid.add(orderNotServedOrCookedYet,1,2);
        orderNotServedOrCookedYet.setVisible(false);
        grid.add(back,1,3);

        orders.setTextFill(Color.web("#FFFFFF"));
        choosePaidOrder.setTextFill(Color.web("#FFFFFF"));
        wrong.setTextFill(Color.web("#FFFFFF"));
        orderNotServedOrCookedYet.setTextFill(Color.web("#FFFFFF"));

        back.setOnAction(e->{prepareMainScene(); showScene();});
        paid.setOnAction(e->orderPaid(paidOrderInput.getText(),wrong,orderNotServedOrCookedYet));
        scene=new Scene(grid,600,400);
    }

    private void orderPaid(String paidOrder, Label wrong, Label order) {
        try{
            wrong.setVisible(false);
            int orderNumber=Integer.parseInt(paidOrder);

            if(!logic.setOrderAsPaid(orderNumber))
                order.setVisible(true);
            else
                order.setVisible(false);

            orderScene();
            showScene();

        }catch (Exception e)
        {
            wrong.setVisible(true);
        }

    }

    private void setOrders(Label orders) {
        logic.setOrdersToManger(user,orders);

    }


    private void userScene()
    {
         Button back = new Button("Back");
        Label users=new Label();
        setUsers(users);
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(users,0,0);
        grid.add(back,0,1);

        users.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);

        back.setOnAction(e->{prepareMainScene(); showScene();});
    }

    private void setUsers(Label users) {

    logic.setUsers(user,users);
    }


    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }


    public void setUser(User user)
    {
      this.user=logic.turnToManger(user);
    }
}
