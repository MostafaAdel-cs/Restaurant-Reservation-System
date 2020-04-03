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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

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
        Button save=new Button("Save");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(login, 1 , 0);
        grid.add(wrongData,2,0);
        grid.add(username, 0, 1);
        grid.add(usernamein, 2,1);
        grid.add(password, 0,2);
        grid.add(passwordin,2,2);
        grid.add(loginbutton,1,3);
        grid.add(adduser,2,3);
        grid.add(save,2,4);
        scene=new Scene(grid,600,400);

        login.setTextFill(Color.web("#FFFFFF"));
        username.setTextFill(Color.web("#FFFFFF"));
        password.setTextFill(Color.web("#FFFFFF"));
        wrongData.setTextFill(Color.web("#FFFFFF"));

        adduser.setOnAction(e->adduserClicked());
        loginbutton.setOnAction(e->loginClicked(usernamein.getText(),passwordin.getText(),wrongData));
        save.setOnAction(e-> {
            try {
                logic.save();
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });



    }

    private void loginClicked(String username, String password, Label wrongData)
    {


       if( logic.checkUsers(username,password))
       {
           this.user=logic.getUser(username);
           String role=logic.getRole(this.user);
           if(role.contentEquals("Manger"))
           {
                managerWindow.setUser(user);
               managerWindow.prepareMainScene();
               managerWindow.showScene();
           }
           else if (role.contentEquals("Cooker"))
           {
                cookWindow.setUser(user);
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
                waiterWindow.setUser(user);
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
