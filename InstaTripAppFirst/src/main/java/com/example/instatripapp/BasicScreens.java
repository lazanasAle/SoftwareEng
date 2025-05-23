package com.example.instatripapp;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;


import java.util.List;
import java.util.Objects;

// super class for all screens
class Screen{
    protected GridPane grid;
    protected Stage stage;
    protected final int widthOfScreen;
    protected final int heightOfScreen;
    protected final String screenTitle;

    public Screen(String title,int widthOfScreen, int heightOfScreen) {
        this.widthOfScreen = widthOfScreen;
        this.heightOfScreen = heightOfScreen;
        this.screenTitle = title;
        this.grid = new GridPane();
        this.stage = new Stage();

        renderStage();
    }

    private void renderStage(){
        stage.setTitle(screenTitle);
        stage.setResizable(false);
        stage.setWidth(widthOfScreen);
        stage.setHeight(heightOfScreen);
        stage.show();
    }

    protected void renderGrid(int sizeOfContainer) {
        StackPane root = new StackPane();

        grid.setAlignment(Pos.CENTER); // center contents inside grid
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setId("grid-box");
        grid.setMaxWidth(sizeOfContainer);
        grid.setMaxHeight(sizeOfContainer);


        // Add grid to root
        root.getChildren().addAll(grid);

        Scene scene = new Scene(root, widthOfScreen, heightOfScreen);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/universal_styling.css")).toExternalForm());
        stage.setScene(scene);
    }

    protected void renderLabel(String label) {
        Text l = new Text(label);
        l.setId("welcome_label");
        grid.add(l, 0, 0 ,2,1); // Span 4 columns
        GridPane.setHalignment(l, HPos.CENTER); // Center horizontally
    }

}



// see comments in ListScreen.java
class MainScreen extends ListScreen {
    String usertype;
    String name;
    static DataSourceManager manager;
    long userID;
    static Object user;
    long particularId;

    //crete 3 constructrors one for each user

    public MainScreen(String usertype,String name,DataSourceManager manager,long userID) {
        // screen methods
        super("Κύριο Μενού", 650, 500);
        this.name=name;
        this.usertype=usertype;
        this.manager=manager;
        this.userID=userID;

        renderGrid(400);
        renderLabel("Καλώς ήρθατε στο InstaTrip κ. "+name);
        renderMenu();
    }

    public static void GetFunction(String Action){
        switch (Action){
            case "Επιλογή Πακέτου":
                //Customer.searchpack();
                System.out.println("Επιλογη Πακετου");
                break;
            case "Πληρωμή Πακέτου":
                System.out.println("Πληρωμη Πακετου");
                break;
                //Customer.paypack();
            case "Δημιουργια Πακετου":{
                TourAgency Agent=(TourAgency) user;
                Agent.createPackage(manager);
                //System.out.println("Δημιουργια Πακετου");
                }
                break;
            case "Τα πακετα μου": {
                TourAgency Agent = (TourAgency) user;
                Agent.finalizePackage(manager);
            }
                break;
            case "Αναζητηση Συνεργατη": {
                ExtPartner extPartner = (ExtPartner) user;
                extPartner.SearchCooparation(manager);
            }
                break;
            case "Τροποιηση Συνεργασιας":
                System.out.println("Πληρωμη Πακετου");
                break;
            case "Ακυρωση Συνεργαιας":
                System.out.println("Πληρωμη Πακετου");
                break;
            default:
                System.out.println("problem");
                break;
        }
    }

    private void renderMenu() {
        //System.out.println(usertype);
        if("client".equals(this.usertype)){
            String[] menuOptions = {"Επιλογή Πακέτου", "Πληρωμή Πακέτου"};
            renderListMain(Arrays.asList(menuOptions));
            particularId=ScreenConnector.GetPartID(manager,usertype,userID);
            user=new Customer();

        } else if ("tour_office".equals(this.usertype)) {
            String[] menuOptions = {"Δημιουργια Πακετου","Τα πακετα μου"};
            renderListMain(Arrays.asList(menuOptions));
            particularId=ScreenConnector.GetPartID(manager,usertype,userID);
            user=new TourAgency(particularId);
        } else if ("partner".equals(this.usertype)) {
            String[] menuOptions = {"Αναζητηση Συνεργατη","Τροποιηση Συνεργασιας","Ακυρωση Συνεργαιας"};
            renderListMain(Arrays.asList(menuOptions));
            particularId=ScreenConnector.GetPartID(manager,usertype,userID);
            user=new ExtPartner(particularId,manager);
        }
        else{
            ScreenRedirect.launchErrorMsg("Not Valid");
        }




    }


}





class ErrorMessage extends Screen {
    public ErrorMessage(String message) {
        // screen methods
        super("Παράθυρο Σφάλματος", 800, 300);
        renderGrid(50);
        renderLabel("ΣΦΑΛΜΑ:");
        displayMessage(message);

    }
    public void displayMessage(String message) {
        Text errorMessage = new Text(message);
        Button okButton = new Button("OK");
        errorMessage.setId("error_message");
//        errorMessage.setStyle("-fx-background-color: red;");

        okButton.setOnAction(e -> stage.close());
        // place the error message and button in the grid
        grid.add(errorMessage, 0, 1,2,1);
        grid.add(okButton, 0, 2,2,1);
        GridPane.setHalignment(errorMessage, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
        GridPane.setHalignment(okButton, javafx.geometry.HPos.CENTER); // Center the label in the grid cell
    }
}

class LoginPage extends FormScreen{
    String username;
    String password;
    String typeuser;
    long keyus;
    DataSourceManager manager;
    public LoginPage(DataSourceManager manager) {
        super("Σύνδεση Χρήστη", 700, 600);
        renderGrid(400);
        renderLabel("INSTATRIP");
        renderLoginForm();
        this.manager=manager;
        manager.connect();
    }

    private void renderLoginForm() {
        // Create labels and text fields for the login form
        Label usernameLabel = new Label("Όνομα Χρήστη:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Κωδικός Πρόσβασης:");
        PasswordField passwordField = new PasswordField();

        // Render the form
        renderFormElements(
                new Label[]{usernameLabel, passwordLabel},
                new TextField[]{usernameField, passwordField});

        submitButton.setOnAction(e->{
            username=new String(usernameField.getText());
            password=new String(passwordField.getText());
            if((keyus=CheckIn())>0){
                this.stage.close();
                IndentifyUser(keyus);
            }
        });
    }
    public long CheckIn(){
        manager.connect();
        return(ScreenConnector.IsValidUser(password,username,manager));
    }
    public void IndentifyUser(long keyus){
        typeuser=ScreenConnector.GetUserType(username,manager);
        ScreenRedirect.GetToMain(typeuser,username,manager,keyus);

    }
}

