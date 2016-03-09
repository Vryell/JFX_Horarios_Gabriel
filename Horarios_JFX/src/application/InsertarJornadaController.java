package application;

import java.io.IOException;
import java.sql.SQLException;

import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.Turno;
import dad.jfx.services.ServiceException;
import dad.jfx.services.ServiceLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InsertarJornadaController {
	private HorariosApp main;
	
	@FXML
	private Button volverButton;
	
	@FXML
	private Button aceptarButton;
	
	@FXML
	private DatePicker fechaDatePicker;
	
	@FXML
	private ComboBox<Centro> centrosComboBox;
	private ObservableList centrosList;
	@FXML
	private ComboBox<Area> areasComboBox;
	private ObservableList areasList;
	
	@FXML
	private ComboBox<Turno> turnosComboBox;
	private ObservableList turnosList;
	
	
	@FXML
	private void initialize(){
		try {
			centrosList = FXCollections.observableArrayList(ServiceLocator.getCentroService().listarCentros());
		} catch (ServiceException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los centros de trabajo.");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los centros de trabajo.");
			alert.showAndWait();
		}
		centrosComboBox.setItems(centrosList);
	}
	
	@FXML
	private void onCentrosComboBoxItemSelected(ActionEvent e){
		try {
			areasList = FXCollections.observableArrayList(ServiceLocator.getAreaService().listarAreasDeCentro(
					centrosComboBox.getSelectionModel().getSelectedItem().getId_centro()));
		} catch (ServiceException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar las áreas de trabajo.");
			alert.showAndWait();
		} catch (SQLException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar las áreas de trabajo.");
			alert.showAndWait();
		}
		areasComboBox.setItems(areasList);
		try {
			turnosList = FXCollections.observableArrayList(
					ServiceLocator.getTurnoService().listarTurnosDeCentro(
					centrosComboBox.getSelectionModel().getSelectedItem().getId_centro()));
		} catch (ServiceException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los turnos.");
			alert.showAndWait();
		} catch (SQLException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los turnos.");
			alert.showAndWait();
		}
		turnosComboBox.setItems(turnosList);
	}
	
	@FXML
	private void onVolverButtonHandle(ActionEvent e){
		main.getInsertarStage().close();
//		Stage stage;
//		Parent root;
//		stage = (Stage)volverButton.getScene().getWindow();
//		try {
//			root = FXMLLoader.load(getClass().getResource("PrincipalWindow.fxml"));
//			Scene scene = new Scene(root);
//			stage.setScene(scene);
//			stage.show();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
	
	@FXML
	private void onAceptarButtonHandle(ActionEvent e){
		try {
			ServiceLocator.getJornadaService().insertarJornadaLaboral(fechaDatePicker.getValue().toString(), 
					centrosComboBox.getSelectionModel().getSelectedItem(), 
					areasComboBox.getSelectionModel().getSelectedItem(), 
					turnosComboBox.getSelectionModel().getSelectedItem());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Operación realizada correctamente");
			alert.setContentText("Jornada laboral insertada correctamente.");
			alert.showAndWait();
		} catch (ServiceException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido insertar la jornada laboral.");
			alert.showAndWait();
		} catch (SQLException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido insertar una nueva jornada laboral.");
			alert.showAndWait();
		}
	}
	
	public void setMain(HorariosApp main){
		this.main = main;
	}
	
	
}
	