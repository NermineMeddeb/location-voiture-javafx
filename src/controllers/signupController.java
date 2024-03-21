package controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import model.Employee;
import service.signupService;

public class signupController {

	// crée une liste observable contenant les types de connexion possibles : "Client", "Admin" et "Employée".
	ObservableList<String> typeList = FXCollections.observableArrayList("Client","Admin","Employée");

    @FXML
    private TextField email;

    @FXML
    private ChoiceBox<String> type;

    @FXML
    private TextField nom;

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField telephone;

    //Elle initialise la boîte de choix avec les éléments de typeList et définit la valeur par défaut sur "Client".
    @FXML
   private void initialize() {
    	type.setItems(typeList);
    	type.setValue("Employée");
    }

    @FXML
    void signup(MouseEvent event) {
    	int totalCount=0;
    	switch(type.getValue().toString()) {
  	  case "Client":
  		  Client newClient = new Client(nom.getText(), prenom.getText(), adresse.getText(), telephone.getText(), email.getText(), pwd.getText());
  		  totalCount=signupService.signupClient(newClient);
  				navigateToAcceuilOrErrorMsg(event,totalCount,"/views/acceuilClient.fxml","Acceuil client");
  	    break;
  	  case "Admin":
  		  Employee admin = new Employee(nom.getText(), prenom.getText(), null, 0, email.getText(), telephone.getText(), null,  pwd.getText()) ;
  		  totalCount= signupService.signupAdmin(admin);
  				navigateToAcceuilOrErrorMsg(event,totalCount,"/views/acceuilAdmin.fxml","Acceuil admin");
  	    break;
  	  case "Employée":
  		  Employee employee = new Employee(nom.getText(), prenom.getText(), null, 0, email.getText(), telephone.getText(), null,  pwd.getText()) ;
  		  totalCount= signupService.signupEmployee(employee);
  				navigateToAcceuilOrErrorMsg(event,totalCount,"/views/acceuilEmployee.fxml","Acceuil employée");
    	    break;    	
  	}

    }
    
    
    public void navigateToAcceuilOrErrorMsg(MouseEvent event,int totalCount,String url,String title) {
    	if(totalCount != 0){
			navigateTo(event,url,title);
    	}else {
    		ErrorMsg();
    	}

    }
    
    public void navigateTo(MouseEvent event,String url,String title) {
		System.out.println("navigateTo !");
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(url));
			Scene scene = new Scene(root);
			Stage primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primarystage.setScene(scene);
			primarystage.setTitle(title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void ErrorMsg() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("sign up failed");
		errorAlert.setContentText("try another one !!!!! if you are a huker please use ammar ammar to pirate our app");
		errorAlert.showAndWait();
		System.out.println("ko !");
    }


}
