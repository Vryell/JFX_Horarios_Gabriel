package application;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class HorariosApp extends Application {
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			showPantallaPrincipal();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showPantallaPrincipal() throws IOException{
		URL url = getClass().getResource("/application/PrincipalWindow.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		Scene scene = new Scene(loader.load());
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
