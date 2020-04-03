package Gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserAddedSuccessfully {
    Stage stage=new Stage();
    Scene scene;
    public void userAdded()
    {
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane grid=new GridPane();
        Label userAdded=new Label("User Added Successfully");
        Button close=new Button("Close");
        grid.setAlignment(Pos.CENTER);
        grid.add(userAdded,0,0);
        grid.add(close,0,1);
        scene=new Scene(grid,300,300);
        stage.setScene(scene);
        stage.show();

        close.setOnAction(e->stage.close());
    }







}
