module LocationVoiture {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
