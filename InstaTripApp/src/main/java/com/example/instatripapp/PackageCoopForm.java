package com.example.instatripapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


//τι γινεται με submit button
class PackageCoopForm extends FormScreen{
    String TourAgencyName;
    String Email;
    String Place;
    String NameExtParner;
    long packnum;
    DataSourceManager manager;
    PackageCoopForm(Package pack,DataSourceManager manager){
        super("Εισαγωγη Συνεργασιας", 700, 500);
        renderGrid(200);
        this.TourAgencyName=new String(pack.getName());
        this.Email=new String(pack.getEmail());
        this.packnum= pack.getKey();
        this.manager=manager;
        System.out.println(NameExtParner);
        //this.Place=new String(pack.location);
        renderLabel("Συμπληρώστε τα στοιχεία της Συνεργασιας");
        renderPackageForm();
    }
    //δεν θα ειναι ποτε καθως παιρνει τα στοιχεια απο πριν μπορει ομως στην βαση να ειναι αδειο καποιο field
    private void renderPackageForm() {
        Label Extname = new Label("Ονομα εξωτερικου συνεργατη:");
        TextField ExtnameArea = new TextField();
        Label NameAgency = new Label("Ονομα Τξιδιωτικου Γραφειου");
        TextField NameAgencyArea = new TextField(TourAgencyName);
        Label communication = new Label("Email επικοινωνιας");
        TextField communicationArea = new TextField(Email);

        Button send = new Button("Αποστολη");

        // renderForm Details
        renderFormElements(
                new Label[]{Extname,NameAgency, communication},
                new TextField[]{ExtnameArea, NameAgencyArea, communicationArea});
        renderFormButtons(null,new Button[]{send});

        send.setOnAction(e->{
            if (ExtnameArea.getText().isEmpty()|| NameAgencyArea.getText().isEmpty()|| communicationArea.getText().isEmpty()){
                Stage anouncement=new Stage();
                Label an=new Label("Παρακαλω εισαγεται στοιχεια σε ολα τα πεδία");
                VBox layout = new VBox(15);
                layout.setPadding(new Insets(20));
                layout.setAlignment(Pos.CENTER);

                layout.getChildren().add(an);

                Scene scene = new Scene(layout, 300, 200);

                anouncement.setScene(scene);
                anouncement.initModality(Modality.APPLICATION_MODAL); // Block other windows
                anouncement.showAndWait();

                /*String[] commit=new String[3];
                commit[0]=ExtnameArea.getText();
                commit[1]=NameAgencyArea.getText();
                commit[2]=communicationArea.getText();
                System.out.println(commit[0]+commit[1]+commit[2]);
                //contents_commit(); δεν χρειαζεται η εναλλακτικη*/
            }
            else  ScreenConnector.activate(ExtnameArea.getText(),NameAgencyArea.getText(),communicationArea.getText(),packnum,manager);
            ((Stage) send.getScene().getWindow()).close();
            for (Window window : Window.getWindows()) {
                if (window instanceof Stage) {
                    ((Stage) window).close();
                }
            }
            
        });

    }



}
