package Gui;

import Logic.Logic;
import Users.Waiter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow {
    private Stage stage;
    private Scene scene;
    private  AddUserWindow addUserWindow;
    private  CookWindow cookWindow;
    private MangerWindow managerWindow;
    private WaiterWindow waiterWindow;
    private CustomerWindow customerWindow;
    private Logic logic;
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
        Button cook = new Button("Cook");
        Button waiter = new Button("Waiter");
        Button customer = new Button("Customer");
        Button manager = new Button("Manager");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(login, 1 , 0);
        grid.add(username, 0, 1);
        grid.add(usernamein, 2,1);
        grid.add(password, 0,2);
        grid.add(passwordin,2,2);
        grid.add(loginbutton,1,3);
        grid.add(adduser,1,4);

        scene=new Scene(grid,600,400);
        adduser.setOnAction(e->adduserClicked());
        loginbutton.setOnAction(e->loginClicked(usernamein.getText(),passwordin.getText()));



    }

    private void loginClicked(String username,String password)
    {

       logic.printUsers();

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
