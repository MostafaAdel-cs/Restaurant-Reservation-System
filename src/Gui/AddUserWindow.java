package Gui;

import Gui.subGui.UserAddedSuccessfully;
import Logic.Logic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

public class AddUserWindow {
    private Scene scene;
    private Stage stage;
    private Logic logic;
    private UserAddedSuccessfully userAddedSuccessfully=new UserAddedSuccessfully();
    public AddUserWindow(Stage stage,Logic logic) {
        this.stage = stage; this.logic=logic;
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
                chooseRole.getItems().addAll("Manger","Client","Cooker","Waiter");

          Button submit=new Button("Submit");
          Button back =new Button("Back");
            Label passwordsNotSame=new Label("Confirm Password");
            Label usernameTaken=new Label("Username Taken");
            Label enterData=new Label("Enter Proper Data");
            usernameTaken.setVisible(false);
            passwordsNotSame.setVisible(false);
            enterData.setVisible(false);
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add( name , 0, 0);


        grid.add( textName , 4, 0);
        grid.add( userName , 0, 1);
        grid.add( textUserName , 4, 1);
        grid.add(usernameTaken,5,1);
        grid.add( role , 0, 2);
        grid.add( chooseRole , 4, 2);
        grid.add( password , 0, 3);
        grid.add( textPassword , 4, 3);
        grid.add( conPassword , 0, 4);
        grid.add( passwordsNotSame , 5, 4);


        grid.add( textConPassword , 4, 4);
        grid.add(submit,0,5);
        grid.add(back,4,5);
        grid.add( enterData , 1, 6);
        scene=new Scene(grid,600,400);
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
        submit.setOnAction(e->submitClicked(textName.getText(),textPassword.getText(),textConPassword.getText(),textUserName.getText(),chooseRole.getValue(),passwordsNotSame,usernameTaken,enterData));

    }

    private void submitClicked(String name, String password, String conpassword, String username, String role, Label passwordsNotSame,Label usernameTaken,Label enterData)
    {
        if(!conpassword.isEmpty()&&!password.isEmpty()&&!username.isEmpty()&&role!=null&&!name.isEmpty())
        {
            enterData.setVisible(false);
            if(logic.isUsernameTaken(username))
            {
                usernameTaken.setVisible(true);

            }
            else if(!password.contentEquals(conpassword)){
                passwordsNotSame.setVisible(true);
            }
            else {
                logic.addUser(name, password, username, role);
                userAddedSuccessfully.userAdded();
                mainWindow.prepareScene();
                mainWindow.showScene();
            }
        }else
        {
            enterData.setVisible(true);
        }

    }

    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }




}
