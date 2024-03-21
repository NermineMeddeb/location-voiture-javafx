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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Client;
import model.Voiture;
import service.ClientService;
import service.VoitureService;

public class EmployeeAcceuil implements Initializable {

    @FXML
    private AnchorPane APClient;
    
    @FXML
    private Text totalclient;

    @FXML
    private TableView<Client> clientTab; 

    @FXML
    private TableColumn<Client, Integer> colid; 
    @FXML
    private TableColumn<Client, String> colnom; 
    @FXML
    private TableColumn<Client, String> colprenom; 

    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField telephone;

    @FXML
    private TextField email;

    @FXML
    private TextField searchField;
    
    @FXML
    private Button Gclient;

    @FXML
    private Button Gvoiture;
    
    @FXML
    private AnchorPane APVoiture;

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
    private CheckBox disponibiliteVoiture;

    @FXML
    private Button searchVoiture;
   
   @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadData(); 
        loadDataVoiture();
    }
    
    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == Gclient) {
            APClient.setVisible(true);
            APVoiture.setVisible(false);

        } else if (event.getSource() == Gvoiture) {
            APClient.setVisible(false);
            APVoiture.setVisible(true);

        }
    }
    @FXML
    void charger(MouseEvent event) {
        Client selectedClient = clientTab.getSelectionModel().getSelectedItem();
   
        
        if (selectedClient != null) {
            id.setText(String.valueOf(selectedClient.getIdclient()));
            adresse.setText(selectedClient.getAdresse());
            nom.setText(selectedClient.getNom());
            prenom.setText(selectedClient.getPrenom());
            telephone.setText(selectedClient.getTelephone());
            email.setText(selectedClient.getEmail());
        }
    }

    void loadData() {        
        
        colid.setCellValueFactory(new PropertyValueFactory<>("idclient"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        // Charger les données dans la TableView à partir du service EmployeeService.getAll() si nécessaire
        clientTab.setItems(ClientService.getAll());
    }

    @FXML
    void delete(MouseEvent event) {
        // Vérifier si un client est sélectionné avant de supprimer
        Client selectedClient = clientTab.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            int totalCount = ClientService.delete(selectedClient.getIdclient());
            showMsg(totalCount, "updated", "updated");
            loadData(); // Recharger les données après la suppression
        }
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
    void recherche(MouseEvent event) {
    	if(searchField.getText()!="") {
    		clientTab.setItems(ClientService.getAllByNom(searchField.getText()));
    	}else {
    		loadData();
    	}
    }

    @FXML
    void update(MouseEvent event) {
    	int totalCount=0;

    	totalCount= ClientService.updateWithoutPwd(Integer.parseInt(id.getText()),nom.getText(), prenom.getText(), adresse.getText(), email.getText());
    	showMsg(totalCount,"updated", "updated");
    	loadData();
    	}
    
    
    
    
    
    
    
    
    	//partie gestion voiture 
    	 @FXML
    	    void addVoiture(MouseEvent event) {
    		 Voiture newVoiture = new Voiture( marqueVoiture.getText(),modeleVoiture.getText(),typeCarburantVoiture.getText(),disponibiliteVoiture.isSelected() );
    	        int result = VoitureService.add(newVoiture);
    	        showMsg(result, "Ajout de voiture", "La voiture a été ajoutée avec succès.");
    	        loadDataVoiture();
    	    }

    	   
    	   @ FXML
    	    void searchVoiture(MouseEvent event) {
    	    	if(searchFiledVoiture.getText()!="") {
    	    		tabVoiture.setItems(VoitureService.getAllByNom(searchFiledVoiture.getText()));
    	    	}else {
    	    		loadDataVoiture();
    	    	}
    	    }

    	    @FXML
    	    void updateVoiture(MouseEvent event) {
    	    	int totalCount=0;

    	    	totalCount= VoitureService.update(Integer.parseInt(idVoiture.getText()),marqueVoiture.getText(), modeleVoiture.getText(), disponibiliteVoiture.isSelected(), typeCarburantVoiture.getText());
    	    	showMsg(totalCount,"updated", "updated");
    	    	
    	    	loadDataVoiture();}
    	   
    	    

    	    @FXML
    	    void deleteVoiture(MouseEvent event) {
    	        Voiture selectedVoiture = tabVoiture.getSelectionModel().getSelectedItem();
    	        if (selectedVoiture != null) {
    	            int id = selectedVoiture.getId();
    	            int result = VoitureService.delete(id);
    	            showMsg(result, "Suppression de voiture", "La voiture a été supprimée avec succès.");
    	            loadDataVoiture();
    	        }
    	    }

    	    void loadDataVoiture() {
    	    	idVoitureCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	    	marqueVoitureCol.setCellValueFactory(new PropertyValueFactory<>("marque")); 
    	    	modeleVoitureCol.setCellValueFactory(new PropertyValueFactory<>("modele")); 
    	    	typeCarburantVoitureCol.setCellValueFactory(new PropertyValueFactory<>("type_carburant")); 
    	    	disponibiliteVoitureCol.setCellValueFactory(new PropertyValueFactory<>("disponibilite")); 

    	        tabVoiture.setItems(VoitureService.getAll());
    	    }
    	    
    	    @FXML
    	    void chargerVoiture(MouseEvent event) {
    	        Voiture selectedVoiture = tabVoiture.getSelectionModel().getSelectedItem();

    	        if (selectedVoiture != null) {
    	            idVoiture.setText(String.valueOf(selectedVoiture.getId())); 
    	            marqueVoiture.setText(selectedVoiture.getMarque());
    	            modeleVoiture.setText(selectedVoiture.getModele());
    	            typeCarburantVoiture.setText(selectedVoiture.getType_carburant());
    	            
    	            disponibiliteVoiture.setSelected(selectedVoiture.isDisponibilite());
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
