package tom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tom.services.ITomService;


import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class LoginController{

    private ITomService service;

    private MainController mainWindowController;

    private Parent mainP;

    private Stage selfStage;

    private Integer idJucator;
    @FXML
    private Button loginButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;


    public void setServices(ITomService services, Parent mainP,MainController mainWindowController,Stage selfStage){

        this.selfStage=selfStage;
        this.mainP = mainP;
        this.service=services;
        this.mainWindowController = mainWindowController;
    }

    private void showError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!!!");
        alert.setHeaderText(error);
        alert.showAndWait();
    }


    @FXML
    void handleLogin(ActionEvent event) {

        String username = loginUsername.getText();
        String password = loginPassword.getText();
        if(service.login(username,password,mainWindowController)) {
            idJucator = service.getJucatorId(username,password);
            openMainWindow();
        }
        else
            showError("Username sau parola gresita!");

    }

    private void openMainWindow(){

        mainWindowController.setServices(service,idJucator);

        Stage stage = new Stage();
        stage.setTitle("TOM");
        stage.setScene(new Scene(mainP));
        stage.show();

        selfStage.close();


    }



}