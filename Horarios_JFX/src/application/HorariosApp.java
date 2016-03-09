package application;
	
import java.io.IOException;
import java.net.URL;

import dad.jfx.model.JornadaLaboral;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class HorariosApp extends Application {
	 @FXML
	 private Button insertarButton;
	
	private Stage primaryStage;
	
	private Stage editarStage;
	
	private Stage insertarStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			primaryStage.setTitle("Gestor de horarios");
			showPantallaPrincipal();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showPantallaPrincipal() throws IOException{
		URL url = getClass().getResource("/application/PrincipalWindow.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		Scene scene = new Scene(loader.load());
		PantallaPrincipalController principalController = (PantallaPrincipalController)loader.getController();
		principalController.setMain(this);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showPantallaInsertar() throws IOException{
		URL url = getClass().getResource("/application/InsertarWindow.fxml");
		FXMLLoader loader = new FXMLLoader(url); 
		Scene scene = new Scene(loader.load());
		insertarStage = new Stage();
		
		InsertarJornadaController insertarController = (InsertarJornadaController)loader.getController();
		insertarController.setMain(this);
		
		insertarStage.setScene(scene);
		insertarStage.show();
	}
	
	public void showPantallaEditar(JornadaLaboral jornada) throws IOException{
		URL url = getClass().getResource("/application/EditarWindow.fxml");
		FXMLLoader loader = new FXMLLoader(url); 
		Scene scene = new Scene(loader.load());
		editarStage = new Stage();
		
		EditarJornadaController editarController = (EditarJornadaController)loader.getController();
		editarController.setMain(this, jornada);
		
		editarStage.setScene(scene);
		editarStage.show();
	}
	
	public Stage getEditarStage(){
		return this.editarStage;
	}
	
	public Stage getInsertarStage(){
		return this.insertarStage;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
