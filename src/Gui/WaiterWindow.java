package Gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WaiterWindow {
    private Stage stage;
    private Scene scene;

    public WaiterWindow(Stage stage) {
        this.stage = stage;
    }
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public void prepareScene()
    {
        Label orders = new Label("Orders:");
        Button orderId = new Button();
        Label orderTable = new Label("Order Table:");
        Label table = new Label();
        Button done = new Button("Done serving");
        Button back = new Button("Back");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(orders,0,0);
        grid.add(orderId,0,1);
        grid.add(orderTable,1,0);
        grid.add(table,1,1);
        grid.add(done,2,3);
        grid.add(back,2,4);
        scene=new Scene(grid,600,400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
    }
    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }





}
