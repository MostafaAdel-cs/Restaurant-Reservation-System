package Gui.subGui;

import Gui.MangerWindow;
import Logic.Logic;
import Users.Manger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class AddTableWindow {



    public void showStage(Manger user, Logic logic) {
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene;
        Label tableNumber=new Label("Table Number");
        TextField inputTableNumber=new TextField();
        ChoiceBox<Boolean> smokingInput=new ChoiceBox();
        smokingInput.getItems().addAll(true,false);
        Label smoking=new Label("Smoking");
        Label numberOfSeats=new Label("Number Of Seats");
        TextField inputSeats=new TextField();
        Button add=new Button("Add Table");
        Button close =new Button("Close");
        Label wrong=new Label("Wrong Entity");
        wrong.setVisible(false);
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(smoking,0,1);
        grid.add(smokingInput,2,1);
        grid.add(numberOfSeats,0,2);
        grid.add(inputSeats,2,2);
        grid.add(wrong,2,3);
        grid.add(add,1,4);
        grid.add(close,1,5);
        scene=new Scene(grid,600,400);
        stage.setScene(scene);
        stage.show();
        close.setOnAction(e->stage.close());
        add.setOnAction(e->{addTable(user,logic,inputSeats.getText(),smokingInput.getValue(),wrong,stage); });


    }

    private void addTable(Manger user,Logic logic, String s, Boolean smoking,Label wrong,Stage stage) {
        try{
            wrong.setVisible(false);
            int seats=Integer.parseInt(s);
            logic.addTable(user, seats, smoking);
            stage.close();
        }catch (Exception e)
        {
            wrong.setVisible(true);
        }


    }
}
