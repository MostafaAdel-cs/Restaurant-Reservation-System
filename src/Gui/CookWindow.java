package Gui;

import Users.Cook;
import Users.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CookWindow {
    private Stage stage;
    private Scene scene;
    private Cook user=new Cook();
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public CookWindow(Stage primaryStage) {
        this.stage=primaryStage;
    }


    public void prepareScene() {

        Label orders = new Label("Orders:");
        Button orderId = new Button();
        Label orderDishes = new Label("Order Dishes:");
        Label Dishes = new Label();
        Button done = new Button("Done cooking");
        Button back = new Button("Back");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(orders, 0, 0);
        grid.add(orderId, 0, 1);
        grid.add(orderDishes, 1, 0);
        grid.add(Dishes, 1, 1, 2, 5);
        grid.add(done, 2, 3);
        grid.add(back, 2, 4);
        scene = new Scene(grid, 600, 400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
    }



    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }
    public void setUser(User user)
    {

    }


}
