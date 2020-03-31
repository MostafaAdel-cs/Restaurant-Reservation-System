package Gui;

import Logic.Logic;
import Users.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.lang.management.ManagementFactory;

public class MainWindow {
    private Stage stage;
    private Scene scene;
    private  AddUserWindow addUserWindow;
    private  CookWindow cookWindow;
    private MangerWindow managerWindow;
    private WaiterWindow waiterWindow;
    private CustomerWindow customerWindow;
    private Logic logic;
    private User user;
    public MainWindow(Stage primaryStage, AddUserWindow addUserWindow, CookWindow cookWindow, MangerWindow mangerWindow, WaiterWindow waiterWindow, CustomerWindow customerWindow, Logic logic)
    {
        this.stage=primaryStage;
        this.addUserWindow=addUserWindow;
        this.cookWindow=cookWindow;
        this.managerWindow=mangerWindow;
        this.waiterWindow=waiterWindow;
        this.customerWindow=customerWindow;
        this.logic=logic;
    }

    public void prepareScene()
    {

        Label login = new Label("LOGIN:");
        Label username = new Label("Username:");
        TextField usernamein = new TextField();
        Label password = new Label("Password:");
        PasswordField passwordin = new PasswordField();
        Button loginbutton = new Button("Login");
        Button adduser = new Button("Add user");

        Label wrongData =new Label("Wrong Username or Password");
        wrongData.setVisible(false);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(login, 1 , 0);
        grid.add(wrongData,2,0);
        grid.add(username, 0, 1);
        grid.add(usernamein, 2,1);
        grid.add(password, 0,2);
        grid.add(passwordin,2,2);
        grid.add(loginbutton,1,3);
        grid.add(adduser,1,4);

        scene=new Scene(grid,600,400);
        adduser.setOnAction(e->adduserClicked());
        loginbutton.setOnAction(e->loginClicked(usernamein.getText(),passwordin.getText(),wrongData));



    }

    private void loginClicked(String username, String password, Label wrongData)
    {


       if( logic.checkUsers(username,password))
       {
           this.user=logic.getUser(username);
           String role=logic.getRole(this.user);
           if(role.contentEquals("Manager"))
           {

               managerWindow.prepareScene();
               managerWindow.showScene();
           }
           else if (role.contentEquals("Cook"))
           {

               cookWindow.prepareScene();
               cookWindow.showScene();
           }
           else if(role.contentEquals("Client"))
           {

               customerWindow.setUser(user);
               customerWindow.setMainScene();
               customerWindow.showScene();
           }
           else
           {

               waiterWindow.prepareScene();
               waiterWindow.showScene();
           }

       }
       else
       {
            wrongData.setVisible(true);
       }

    }

    private void adduserClicked() {
        addUserWindow.prepareScene();
        addUserWindow.showScene();
    }


    public void showScene()
    {
        stage.setScene(scene);
        stage.show();
    }


}
