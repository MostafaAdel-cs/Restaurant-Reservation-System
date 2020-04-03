package Gui.subGui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserAddedSuccessfully {

    public void userAdded()
    {
        Stage stage=new Stage();
        Scene scene;
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane grid=new GridPane();
        Label userAdded=new Label("User Added Successfully");
        Button close=new Button("Close");
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(userAdded,0,0);
        grid.add(close,0,1);

        userAdded.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,300,300);
        stage.setScene(scene);
        stage.show();

        close.setOnAction(e->stage.close());
    }







}
