package tom.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tom.Jucator;
import tom.Raspuns;
import tom.services.ITomObservable;
import tom.services.ITomService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MainController extends UnicastRemoteObject implements Serializable, ITomObservable {

    private ITomService service;
    private Integer idJucator;
    private Stage selfStage;
    private Stage loginStage;
    private LoginController loginController;
    private String litera;



    public void setServices(ITomService service,Integer idJucator){

        this.idJucator = idJucator;
        this.service = service;

        literaTextField.setDisable(true);


    }

    public MainController() throws RemoteException {
    }

    @FXML
    private TextField tara_textField;

    @FXML
    private TextField oras_textField;

    @FXML
    private TextField mare_textField;

    @FXML
    private ListView<Jucator> listView;

    @FXML
    private TextField literaTextField;
    @FXML
    private Button startButton;

    @FXML
    private Button raspunsButton;

    @FXML
    void handleRaspuns(ActionEvent event) {

        String tara = tara_textField.getText();
        String oras = oras_textField.getText();
        String mare = mare_textField.getText();

        Raspuns raspuns = new Raspuns(idJucator,tara,oras,mare);

        raspunsButton.setDisable(true);
        service.adaugaRaspuns(raspuns);
    }

    private void showError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!!!");
        alert.setHeaderText(error);
        alert.showAndWait();
    }

    @FXML
    void handleStartJoc(ActionEvent event) {

        if(!service.verificareStart())
        {
            showError("Nu sunt suficienti playeri");
        }
        else{
            service.startGame();
        }

    }



    @Override
    public void getLetter(String litera) {
        this.litera = litera;
        literaTextField.setText(litera);
    }

    @Override
    public void updateTables(List<Jucator> list) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listView.getItems().clear();
                for(Jucator j:list){
                    listView.getItems().add(j);
                }
            }
        });



    }

    public void enableButtons(){
        raspunsButton.setDisable(false);
    }

}
