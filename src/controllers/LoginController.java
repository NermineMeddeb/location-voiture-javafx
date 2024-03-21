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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.LoginService;

public class LoginController {
	// crée une liste observable contenant les types de connexion possibles : "Client", "Admin" et "Employée".
	ObservableList<String> typeList = FXCollections.observableArrayList("Client","Admin","Employée");
    @FXML
    private TextField email;

    @FXML
    private TextField pwd;

    @FXML
    private Button login;

    @FXML
    private ChoiceBox type;
    
    //Elle initialise la boîte de choix avec les éléments de typeList et définit la valeur par défaut sur "Client".
    @FXML
   private void initialize() {
    	type.setItems(typeList);
    	type.setValue("Client");
    }
    
    //méthode est appelée lorsque l'utilisateur clique sur le bouton de connexion
    @FXML
    void login(MouseEvent event) {
    	int totalCount=0;
    	switch(type.getValue().toString()) {
    	  case "Client":
    		  totalCount=LoginService.loginClient(email.getText(),pwd.getText());
    				navigateToAcceuilOrErrorMsg(event,totalCount,"/views/acceuilClient.fxml","Acceuil client");
    	    break;
    	  case "Admin":
    		  totalCount= LoginService.loginAdmin(email.getText(),pwd.getText());
    				navigateToAcceuilOrErrorMsg(event,totalCount,"/views/acceuilAdmin.fxml","Acceuil admin");
    	    break;
    	  case "Employée":
    		  totalCount= LoginService.loginEmployee(email.getText(),pwd.getText());
				navigateToAcceuilOrErrorMsg(event,totalCount,"/views/acceuilEmployee.fxml","Acceuil employee");
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
		errorAlert.setHeaderText("Login failed");
		errorAlert.setContentText("try another one !!!!! if you are a huker please use ammar ammar to pirate our app");
		errorAlert.showAndWait();
		System.out.println("ko !");
    }
    
    @FXML
    void signup(MouseEvent event) {
    	navigateTo(event,"/views/signup.fxml","Sign up");
    }

}

