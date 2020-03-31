package Gui;

import Dishes.Dishes;
import Logic.Logic;
import Users.Customer;
import Users.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Dishes.Dish;
public class CustomerWindow {
    private Logic logic;
    private Customer user=new Customer();

    private  Label chooseTable=new Label("Choose Table");
    private  Label appetizer=new Label("Appetizer");
    private  Label mainCourse=new Label( "Main-Course");
    private  Label dessert=new Label("Dessert");
    private  Label recipt=new Label("Recipt");




    private  ChoiceBox<String> choosetable =new ChoiceBox<>();
    private  ChoiceBox<String> aPpetizer =new ChoiceBox<>();
    private  ChoiceBox<String> mainourse =new ChoiceBox<>();
    private  ChoiceBox<String> dEssert =new ChoiceBox<>();




    private  Button choose_Table=new Button("Reserve");
    private  Button makeOrder =new Button("Make Order");
    private  Button changeTable=new Button("Change Table");
    private  Button back=new Button("Back");
    private  Button showReceipt=new Button("Show Receipt");
    private  Button addToOrder=new Button("Add To Order");


    private MainWindow mainWindow;
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    private Stage stage;
    private Scene scene;

    public CustomerWindow(Stage stage, Logic logic) {
        this.stage = stage;
        this.logic=logic;
    }




    public void setMainScene(){
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(changeTable,0,0);
        grid.add(showReceipt,0,1);

        grid.add(makeOrder,1,0);
        grid.add(back,1,1);
        scene=new Scene(grid,600,400);
        changeTable.setOnAction(e->{setTablesScene(); showScene();});
        showReceipt.setOnAction(e->{setReciptScene(); showScene();});
        makeOrder.setOnAction(e->{setOrderScene(); showScene();});
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});

    }

    private  void setTablesScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(chooseTable, 0, 0);
        grid.add(choosetable, 1, 0);
        grid.add(choose_Table, 0, 1);
        grid.add(back, 1, 1);
        scene =new Scene(grid,600,400);
        back.setOnAction(e->{setMainScene(); showScene();});
    }
    private  void setOrderScene(){

        GridPane grid=new GridPane();
        for(Dish dish:logic.getRestaurant().getDishes().getDishes())
        {
            if(dish.getType().contentEquals("main_course"))
                mainourse.getItems().add(dish.getName());
            else if(dish.getType().contentEquals("appetizer"))
                aPpetizer.getItems().add(dish.getName());
            else
                dEssert.getItems().add(dish.getName());
        }
        grid.setAlignment(Pos.CENTER);
        grid.add(appetizer,0,0);
        grid.add(aPpetizer,0,1);
        grid.add(mainCourse,1,0);
        grid.add(mainourse,1,1);
        grid.add(dessert,2,0);
        grid.add(dEssert,2,1);
        grid.add(addToOrder,0,2);
        grid.add(back,1,2);
        scene=new Scene(grid,600,400);
        back.setOnAction(e->{setMainScene(); showScene();});
        addToOrder.setOnAction(e->addToOrderClicked(aPpetizer.getValue(),mainourse.getValue(),dEssert.getValue()));

    }

    private void addToOrderClicked(String appetizer, String mainCourse, String dessert)
    {

    logic.addOrderToUser( user,appetizer,mainCourse,dessert);
    logic.printOrder( user);
    }

    private  void setReciptScene()
    {
        recipt.setText(logic.getRecipt( user));
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(recipt,0,0);
        grid.add(back,0,1);
        scene=new Scene(grid,600,400);
        back.setOnAction(e->{setMainScene(); showScene();});

    }

    public void showScene(){
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User user)
    {

    }
}
