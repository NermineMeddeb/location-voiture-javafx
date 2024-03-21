package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VoitureController {

    @FXML
    private TextField code;

    @FXML
    private Button btnAdd;

    @FXML
    private Button delete;

    @FXML
    void ajouter(ActionEvent event) {
    	System.out.print("ajouter tbn");

    }

    @FXML
    void delete(ActionEvent event) {

    }

}
