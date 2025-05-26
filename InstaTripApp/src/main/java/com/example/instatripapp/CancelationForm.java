package com.example.instatripapp;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;



class CancelationForm  extends FormScreen {
    Request req;
    DataSourceManager manager;
    public CancelationForm(Request req,DataSourceManager manager){
        super("Φορμα ακυρωσης συνεργασιας", 700, 700);
        renderGrid(500);
        this.req=req;
        this.manager=manager;
        renderCancelForm();
    }
    public void renderCancelForm(){
        Label ReasonToCancel= new Label("Λογος Ακυρωσης");
        TextField ReasonToCancelField = new TextField();



        submitButton.setOnAction(e->{
            if(ScreenConnector.check_respect(ReasonToCancelField.getText())){
                //αποθηκευση στην βαση
                CancellationContents cancel=new CancellationContents(req,manager);
            }
            else{
                ScreenRedirect.launchErrorMsg("ο λογος ακυρωσης δεν περνα τον ελεγχο εγγυροτητας");
            }
        });


        renderFormElements(new Label[]{ReasonToCancel}, new TextField[]{ReasonToCancelField});
    }
}
