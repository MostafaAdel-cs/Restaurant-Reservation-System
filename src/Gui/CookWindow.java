package Gui;

import Logic.Logic;
import Users.Cook;
import Users.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CookWindow {
    private Stage stage;
    private Scene scene;
    private Cook user;
    Logic logic;
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public CookWindow(Stage primaryStage, Logic logic) {
        this.stage=primaryStage; this.logic=logic;
    }


    public void prepareScene() {

        Label order=new Label("Orders");
        Label orders=new Label();
        setOrders(orders);
        TextField orderCooked=new TextField();
        Label chooseOrder=new Label("Choose Order Number");
        Button cooked=new Button("Done");
        Button back = new Button("Back");
        Label wrongEntry=new Label("Wrong Entry");
        wrongEntry.setVisible(false);
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(order,1,0);
        grid.add(orders,1,1);
        grid.add(chooseOrder,0,2);
        grid.add(orderCooked,3,2);
        grid.add(wrongEntry,4,2);
        grid.add(cooked,1,3);
        grid.add(back,1,4);

        order.setTextFill(Color.web("#FFFFFF"));
        orders.setTextFill(Color.web("#FFFFFF"));
        chooseOrder.setTextFill(Color.web("#FFFFFF"));
        wrongEntry.setTextFill(Color.web("#FFFFFF"));

        scene = new Scene(grid, 600, 400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
        cooked.setOnAction(e->orderChoosen(orderCooked.getText(),wrongEntry));
    }

    private void orderChoosen(String order, Label wrongEntry) {
        try {

            int o = Integer.parseInt(order);
            if(o!=0){
            logic.setOrderAsCooked(o);
            prepareScene();
            showScene();
        }
            else
                wrongEntry.setVisible(true);

        }
        catch (Exception e)
        {
           wrongEntry.setVisible(true);
        }


        }

    private void setOrders(Label orders) {
        logic.setOrders(user,orders);
    }


    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }
    public void setUser(User user)
    {
        this.user=logic.turnToCook(user);
    }


}
