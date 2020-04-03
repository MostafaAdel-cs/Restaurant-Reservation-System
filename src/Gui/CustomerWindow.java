package Gui;

import Logic.Logic;
import Tables.Tables;
import Users.Customer;
import Users.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Dishes.Dish;
public class CustomerWindow {
    private Logic logic;
    private Customer user=new Customer();


    private  Label chooseTable=new Label("Choose Table");
    private  Label appetizer=new Label("Appetizer");
    private  Label mainCourse=new Label( "Main-Course");
    private  Label dessert=new Label("Dessert");
    private  Label receipt =new Label();
    private Label sum=new Label();
    private Label noTable=new Label("Must Choose Table Before Ordering");
    private Label servingOrder=new Label("Can't Change Table Order is Getting Served");


    private  ChoiceBox<String> choosetable =new ChoiceBox<>();
    private  ChoiceBox<String> aPpetizer =new ChoiceBox<>();
    private  ChoiceBox<String> mainourse =new ChoiceBox<>();
    private  ChoiceBox<String> dEssert =new ChoiceBox<>();




    private  Button choose_Table=new Button("Reserve");
    private  Button makeOrder =new Button("Make Order/Change Order");
    private  Button changeTable=new Button("Choose/Change Table");
    private  Button back=new Button("Back");
    private  Button showReceipt=new Button("Show Receipt");
    private  Button addToOrder=new Button("Add To Order");
    private Button removeOrder=new Button("Remove Order");

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
        noTable.setVisible(false);
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        servingOrder.setVisible(false);
        grid.add(servingOrder,0,0);
        grid.add(changeTable,0,1);
        grid.add(showReceipt,0,2);
        grid.add(noTable,0,3);
        grid.add(makeOrder,1,1);
        grid.add(back,2,0);
        grid.add(removeOrder,1,2);

        servingOrder.setTextFill(Color.web("#FFFFFF"));
        noTable.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        changeTable.setOnAction(e->{setTablesScene(); showScene();});
        showReceipt.setOnAction(e->{setReciptScene(); showScene();});
        makeOrder.setOnAction(e->customerOrders(noTable));
        back.setOnAction(e->{mainWindow.prepareScene(); mainWindow.showScene();});
        removeOrder.setOnAction(e->removeOrderClicked());

    }

    private void removeOrderClicked() {
        if(logic.checkIfOrderIsCooked(user))
        {

            servingOrder.setVisible(true);
        }
        else
            logic.removeOrder(user);
    }

    private void customerOrders(Label noTable) {
       if(logic.checkIfCustomerHaveTable(user))
       {
           if(!logic.checkIfOrderIsCooked(user)){
           setOrderScene();
           showScene();
       }
           else{
               servingOrder.setVisible(true);

           }
       }
       else
       {
           noTable.setVisible(true);

       }

    }

    private  void setTablesScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        setTablesList(choosetable);
        grid.add(chooseTable, 0, 0);
        grid.add(choosetable, 1, 0);
        grid.add(choose_Table, 0, 1);
        grid.add(back, 1, 1);
        scene =new Scene(grid,600,400);

        chooseTable.setTextFill(Color.web("#FFFFFF"));

        back.setOnAction(e->{setMainScene(); showScene();});
       choose_Table.setOnAction(e->reserveTable(choosetable.getValue()));

    }

    private void setTablesList(ChoiceBox<String> choosetable) {

       logic.getFreeTables(choosetable);


    }

    private void reserveTable(String table) {
        logic.reserveTableForUser(user,table);
    }

    private  void setOrderScene(){

        GridPane grid=new GridPane();
        mainourse.getItems().clear();
        aPpetizer.getItems().clear();
        dEssert.getItems().clear();
        for(Dish dish:logic.getRestaurant().getDishes().getDishes())
        {
            if(dish.getType().contentEquals("main_course"))
                mainourse.getItems().add(dish.getName());
            else if(dish.getType().contentEquals("appetizer"))
                aPpetizer.getItems().add(dish.getName());
            else
                dEssert.getItems().add(dish.getName());
        }
        mainourse.getItems().add(null);
        aPpetizer.getItems().add(null);
        dEssert.getItems().add(null);

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(appetizer,0,0);
        grid.add(aPpetizer,0,1);
        grid.add(mainCourse,1,0);
        grid.add(mainourse,1,1);
        grid.add(dessert,2,0);
        grid.add(dEssert,2,1);
        grid.add(addToOrder,0,2);
        grid.add(back,1,2);

        appetizer.setTextFill(Color.web("#FFFFFF"));
        mainCourse.setTextFill(Color.web("#FFFFFF"));
        dessert.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        back.setOnAction(e->{setMainScene(); showScene();});
        addToOrder.setOnAction(e->addToOrderClicked(aPpetizer.getValue(),mainourse.getValue(),dEssert.getValue()));

    }

    private  void setReciptScene()
    {

        GridPane grid=new GridPane();
        setReceipt(receipt,sum);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color: #252525");
        grid.add(receipt,0,0);
        grid.add(back,0,2);
        grid.add(sum,0,1);

        receipt.setTextFill(Color.web("#FFFFFF"));
        sum.setTextFill(Color.web("#FFFFFF"));

        scene=new Scene(grid,600,400);
        back.setOnAction(e->{setMainScene(); showScene();});

    }

    private void setReceipt(Label receipt,Label sum) {
        logic.setReceipt(user,receipt,sum);

    }


    private void addToOrderClicked(String appetizer, String mainCourse, String dessert)
    {
        logic.addOrderToUser(user,appetizer,mainCourse,dessert);

    }




    public void showScene(){
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User user)
    {
       this.user=logic.turnToCustomer(user);
    }
}
