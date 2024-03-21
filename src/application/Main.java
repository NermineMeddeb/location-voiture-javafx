package application;
	
import dao.SingletonConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import service.EmployeeService;
import service.VoitureService;
import javafx.scene.*;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initialiser la connexion à la base de données
		    SingletonConnection.getCon();
		    
		    VoitureService vs = new VoitureService();
		    vs.getAll();
		    
		    EmployeeService es = new EmployeeService();
		    es.getAll();

			Parent root = FXMLLoader.load(getClass().getResource("/views/acceuilClient.fxml"));
			Scene scene = new Scene(root);//pas de parmetre pour la taille pour qu'elle s'ajuste automatiquement
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

