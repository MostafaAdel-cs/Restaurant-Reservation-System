package Gui.subGui;

import Logic.Logic;
import Users.Manger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class AddDishWindow {

    public void showStage(Manger user, Logic logic) {
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene;
        Label name=new Label("Name");
        Label type =new Label("Type");
        Label price=new Label("Price");
        TextField nameInput=new TextField();
        TextField priceInput=new TextField();
        ChoiceBox<String> typeInput=new ChoiceBox();
        Button close=new Button("Close");
        Button addDish=new Button("Add Dish");
        Label wrong=new Label("Wrong Entity");
        wrong.setVisible(false);
        typeInput.getItems().addAll("dessert","appetizer","main_course");
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(name,0,0);
        grid.add(nameInput,1,0);
        grid.add(type,0,1);
        grid.add(typeInput,1,1);
        grid.add(price,0,2);
        grid.add(priceInput,1,2);
        grid.add(wrong,1,3);
        grid.add(close,0,4);
        grid.add(addDish,1,4);

        name.setTextFill(Color.web("#FFFFFF"));
        type.setTextFill(Color.web("#FFFFFF"));
        price.setTextFill(Color.web("#FFFFFF"));
        wrong.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        stage.setScene(scene);
        stage.show();
        close.setOnAction(e->stage.close());
        addDish.setOnAction(e->addDish(user,logic,nameInput.getText(),typeInput.getValue(),priceInput.getText(),wrong,stage));

    }

    private void addDish(Manger user, Logic logic, String name, String type, String priceInputText, Label wrong,Stage stage) {
        try{
            wrong.setVisible(false);
            int price=Integer.parseInt(priceInputText);
            logic.addDish(user,name,type,price);
            stage.close();
        }catch (Exception e)
        {
            wrong.setVisible(true);
        }

    }
}
