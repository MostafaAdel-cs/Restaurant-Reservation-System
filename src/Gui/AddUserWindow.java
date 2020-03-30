package Gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddUserWindow {
    private Scene scene;
    private Stage stage;

    public AddUserWindow(Stage stage) {
        this.stage = stage;
    }
    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void prepareScene()
    {

         Label name=new Label("Name");
        TextField textName=new TextField();
        Label userName=new Label("User Name");
             Label role=new Label("Role");
             Label password=new Label("Password");
             Label conPassword=new Label("Confirm Password");
             TextField textUserName=new TextField();
          PasswordField textPassword=new PasswordField();
          PasswordField textConPassword=new PasswordField();
          ChoiceBox<String> chooseRole=new ChoiceBox<>();

          Button submit=new Button("Submit");
          Button back =new Button("Back");


        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add( name , 0, 0);
        grid.add( textName , 4, 0);
        grid.add( userName , 0, 1);
        grid.add( textUserName , 4, 1);
        grid.add( role , 0, 2);
        grid.add( chooseRole , 4, 2);
        grid.add( password , 0, 3);
        grid.add( textPassword , 4, 3);
        grid.add( conPassword , 0, 4);
        grid.add( textConPassword , 4, 4);
        grid.add(submit,0,5);
        grid.add(back,4,5);
        scene=new Scene(grid,600,400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
    }
    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }




}
