package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Voiture;
import service.VoitureService;

public class ClientAcceuil  implements Initializable {
    @FXML
    private TableView<Voiture> tabVoiture;

    @FXML
    private TableColumn<Voiture, Integer> idVoitureCol;

    @FXML
    private TableColumn<Voiture, String> marqueVoitureCol;

    @FXML
    private TableColumn<Voiture, String> modeleVoitureCol;

    @FXML
    private TableColumn<Voiture, String> typeCarburantVoitureCol;

    @FXML
    private TableColumn<Voiture, Boolean> disponibiliteVoitureCol;

    @FXML
    private TextField searchFiledVoiture;

    @FXML
    private TextField idVoiture;

    @FXML
    private TextField marqueVoiture;

    @FXML
    private TextField modeleVoiture;

    @FXML
    private TextField typeCarburantVoiture;

  
    @FXML
    void searchVoiture(MouseEvent event) {
        if (!searchFiledVoiture.getText().isEmpty()) {
            tabVoiture.setItems(VoitureService.getAllByNom(searchFiledVoiture.getText()));
        } else {
            loadDataVoiture();
        }
    }

    void loadDataVoiture() {
        idVoitureCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        marqueVoitureCol.setCellValueFactory(new PropertyValueFactory<>("marque"));
        modeleVoitureCol.setCellValueFactory(new PropertyValueFactory<>("modele"));
        typeCarburantVoitureCol.setCellValueFactory(new PropertyValueFactory<>("type_carburant"));
        disponibiliteVoitureCol.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        tabVoiture.setItems(VoitureService.getAllVoitureDispo());
    }

    @FXML
    void chargerVoiture(MouseEvent event){
        Voiture selectedVoiture = tabVoiture.getSelectionModel().getSelectedItem();

        if (selectedVoiture != null) {
            idVoiture.setText(String.valueOf(selectedVoiture.getId()));
            marqueVoiture.setText(selectedVoiture.getMarque());
            modeleVoiture.setText(selectedVoiture.getModele());
            typeCarburantVoiture.setText(selectedVoiture.getType_carburant());
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadDataVoiture();
	}
	
    @FXML
    void reserver(MouseEvent event) {
    	int totalCount=0;
    	totalCount= VoitureService.resserveVoiture(Integer.parseInt(idVoiture.getText()));
    	showMsg(totalCount,"updated", "merci de contacter l'agence pour confirmer ta reservation avant 1h");
    	loadDataVoiture();

    }
    
    public void showMsg(int totalCount, String msg, String description) {
        if (totalCount != 0) {
            Alert errorAlert = new Alert(AlertType.INFORMATION);
            errorAlert.setHeaderText(msg);
            errorAlert.setContentText(description);
            errorAlert.showAndWait();
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("failed");
            errorAlert.setContentText("failed app");
            errorAlert.showAndWait();
        }
    }
    
    @FXML
    void logout(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de la déconnexion");
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Connexion");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
