import Data.Data;
import Gui.*;
import Logic.Logic;
import Restaurant.Restaurant;
import Users.User;
import Users.Waiter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene primaryScene = null;
        primaryStage.setTitle("Restaurant System");
        Logic logic=new Logic();
        logic.load();

        AddUserWindow addUserWindow=new AddUserWindow(primaryStage,logic);

        CookWindow cookWindow=new CookWindow(primaryStage,logic);

        MangerWindow mangerWindow=new MangerWindow(primaryStage);

        WaiterWindow waiterWindow=new WaiterWindow(primaryStage,logic);

        CustomerWindow customerWindow=new CustomerWindow(primaryStage,logic);

        MainWindow mainWindow=new MainWindow(primaryStage,addUserWindow,cookWindow,mangerWindow,waiterWindow,customerWindow,logic);


        addUserWindow.setMainWindow(mainWindow);
        cookWindow.setMainWindow(mainWindow);
        mangerWindow.setMainWindow(mainWindow);
        waiterWindow.setMainWindow(mainWindow);
        customerWindow.setMainWindow(mainWindow);


        mainWindow.prepareScene();
        mainWindow.showScene();


    }


    public static void main(String[] args) throws JAXBException {
        launch(args);
    }
}
