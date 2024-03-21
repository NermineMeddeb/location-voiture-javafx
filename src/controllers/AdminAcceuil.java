package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Employee;
import service.EmployeeService;

public class AdminAcceuil implements Initializable {


	ObservableList<String> typeList = FXCollections.observableArrayList("Admin","Employée");

    @FXML
    private TableView<Employee> clientTab;

    @FXML
    private TableColumn<Employee,String> nomCol;

    @FXML
    private TableColumn<Employee,String> prenomCol;

    @FXML
    private TableColumn<Employee,String> posteCol;    
    
    @FXML
    private Text total;

    @FXML
    private Text totalActif;

    @FXML
    private Text totalInactif;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;
    @FXML
    private TextField poste;

    @FXML
    private TextField salaire;

    @FXML
    private TextField telephone;

    @FXML
    private ChoiceBox<String> type;    
    @FXML
    private TextField email;
    
    @FXML
    private TextField id;
    
    @FXML
    void charger(MouseEvent event) {
        Employee selectedEmployee = clientTab.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            // Set the selected item's name in the TextField
        	id.setText(String.valueOf(selectedEmployee.getIdemployee()));
        	nom.setText(selectedEmployee.getNom());
        	prenom.setText(selectedEmployee.getPrenom());
        	poste.setText(selectedEmployee.getPoste());
        	salaire.setText(String.valueOf(selectedEmployee.getSalaire()));
        	telephone.setText(selectedEmployee.getTelephone());
        	if(selectedEmployee.getType()=="admin") {
        		type.setValue("Admin");
        	}else {
        		type.setValue("Employée");
        	}
        	
        	email.setText(selectedEmployee.getEmail());
        }

    }

	
	void loadData(){
		type.setItems(typeList);
		
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		posteCol.setCellValueFactory(new PropertyValueFactory<>("poste"));
		clientTab.setItems(EmployeeService.getAll());
		
		total.setText(String.valueOf(EmployeeService.EmployeeTotal()));
		totalActif.setText(String.valueOf(EmployeeService.EmployeeTotalPresent()));
		totalInactif.setText(String.valueOf(EmployeeService.EmployeeTotalInactive()));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
	}



    @FXML
    void delete(MouseEvent event) {
    	int totalCount=0;
    	totalCount= EmployeeService.delete(Integer.parseInt(id.getText()));
    	showMsg(totalCount,"updated", "updated");
    	loadData();
    }

    @FXML
    void update(MouseEvent event) {
    	int totalCount=0;
    	String type="";
    	if(this.type.getValue()=="Admin") {
    		type="admin";
    	}else {
    		type="employee";
    	}

    	totalCount= EmployeeService.updatewithPwd(Integer.parseInt(id.getText()),nom.getText(), prenom.getText(), poste.getText(),  Integer.parseInt(salaire.getText()), email.getText(), telephone.getText(), type.toString());
    	showMsg(totalCount,"updated", "updated");
    	
    	loadData();
    }
    
    public void showMsg(int totalCount ,String msg, String description) {
    	if(totalCount != 0) {
    		Alert errorAlert = new Alert(AlertType.INFORMATION);
    		errorAlert.setHeaderText(msg);
    		errorAlert.setContentText(description);
    		errorAlert.showAndWait();
    	}else {
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
