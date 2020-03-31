package Gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MangerWindow {
    private  Stage stage;
    private  Scene scene;

    public MangerWindow(Stage stage) {
        this.stage = stage;
    }
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }



    public void prepareScene()
    {
        Label custList = new Label();
        Label totalGain = new Label();
        Button back = new Button("Back");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(custList,0,0);
        grid.add(totalGain,0,1);
        grid.add(back,0,2);
        scene=new Scene(grid,600,400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});


    }
    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }
}
