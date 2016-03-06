package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;
import dad.jfx.model.Usuario;
import dad.jfx.services.ServiceException;
import dad.jfx.services.ServiceLocator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PantallaPrincipalController {
	@FXML
	private Button conectarButton;
	
	@FXML
	private Button aniadirJornadaButton;
	
	@FXML
	private Button editarJornadaButton;
	
	@FXML 
	private TableView jornadasTable = new TableView();
	@FXML
	private TableColumn jornadasFechaColumn;
	@FXML
	private TableColumn jornadasCentroColumn;
	@FXML
	private TableColumn jornadasAreaColumn;
	@FXML
	private TableColumn jornadasTurnoColumn;
	
	private ObservableList jornadasList;
	
	@FXML 
	private TableView centrosTable = new TableView();
	@FXML
	private TableColumn centrosIdColumn;
	@FXML
	private TableColumn centrosNombreColumn;
	@FXML
	private TableColumn centrosDireccionColumn;
	
	private ObservableList centrosList;
	
	@FXML
	private TableView turnosTable = new TableView();
	@FXML
	private TableColumn turnosIdColumn;
	@FXML
	private TableColumn turnosAliasColumn;
	@FXML
	private TableColumn turnosCentroColumn;
	@FXML
	private TableColumn turnosHorasColumn;
	
	private ObservableList turnosList;
	
	@FXML
	private TableView areaTable = new TableView();
	@FXML
	private TableColumn areasIdColumn;
	@FXML
	private TableColumn areasNombreColumn;
	@FXML
	private TableColumn areasCentroColumn;

	private ObservableList areasList;
	
	private Usuario usuario;
	
	@FXML
	private void initialize(){
		System.out.println("inicializando el controlador");
		usuario = new Usuario();
		usuario.setClave("prueba1234");
		usuario.setId_usuario(1);
		usuario.setCorreo_electronico("ggarciagarcia@gmail.com");
		usuario.setNombre_usuario("Gabriel Garcia");
		initTables();
	}
	
	@FXML
	private void onConectarButtonHandle(ActionEvent e){
//		initTables();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Oops!");
		alert.setHeaderText("Función no implementada.");
		alert.setContentText("Lo sentimos, pero esta función no esta implementada actualmente.");
		alert.showAndWait();
	}
	
	@FXML
	private void onDesconectarButtonHandle(ActionEvent e){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Oops!");
		alert.setHeaderText("Función no implementada.");
		alert.setContentText("Lo sentimos, pero esta función no esta implementada actualmente.");
		alert.showAndWait();
	}

	private void initTables() {
		try {
			jornadasList = FXCollections.observableArrayList(ServiceLocator.getJornadaService().listarJornadas(usuario));
		} catch (ServiceException e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar las jornadas.");
			alert.showAndWait();
		}
		
		jornadasTable.setEditable(false);
		
		jornadasFechaColumn.setCellValueFactory(new PropertyValueFactory("fecha"));
		jornadasCentroColumn.setCellValueFactory(new PropertyValueFactory("id_centro"));
		jornadasAreaColumn.setCellValueFactory(new PropertyValueFactory("id_area"));
		jornadasTurnoColumn.setCellValueFactory(new PropertyValueFactory("id_turno"));
		jornadasTable.setItems(jornadasList);
		
		try {
			centrosList = FXCollections.observableArrayList(ServiceLocator.getCentroService().listarCentros());
		} catch (ServiceException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los centros de trabajo.");
			alert.showAndWait();
		}
		
		jornadasTable.setEditable(false);
		
		centrosIdColumn.setCellValueFactory(new PropertyValueFactory("id_centro"));
		centrosNombreColumn.setCellValueFactory(new PropertyValueFactory("nombre_centro"));
		centrosDireccionColumn.setCellValueFactory(new PropertyValueFactory("direccion_centro"));
		centrosTable.setItems(centrosList);
		
		try {
			areasList = FXCollections.observableArrayList(ServiceLocator.getAreaService().listarAreas());
		} catch (ServiceException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar las áreas de trabajo.");
			alert.showAndWait();
		}
		areaTable.setEditable(false);
		
		areasIdColumn.setCellValueFactory(new PropertyValueFactory("id_area"));
		areasNombreColumn.setCellValueFactory(new PropertyValueFactory("nombre_area"));
		areasCentroColumn.setCellValueFactory(new PropertyValueFactory("id_centro"));
		
		areaTable.setItems(areasList);
		
		try {
			turnosList = FXCollections.observableArrayList(ServiceLocator.getTurnoService().listarTurnos());
		} catch (ServiceException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los turnos.");
			alert.showAndWait();
		}
		
		turnosTable.setEditable(false);
		
		turnosIdColumn.setCellValueFactory(new PropertyValueFactory("id_turno"));
		turnosAliasColumn.setCellValueFactory(new PropertyValueFactory("alias"));
		turnosCentroColumn.setCellValueFactory(new PropertyValueFactory("id_centro"));
		turnosHorasColumn.setCellValueFactory(new PropertyValueFactory("num_horas"));
		
		turnosTable.setItems(turnosList);
		
	}
	
	@FXML
	private void onAniadirJornadaButtonHandle(ActionEvent e){
		Stage stage;
		Parent root;
		stage = (Stage)aniadirJornadaButton.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("InsertarWindow.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	private void onEliminarJornadaButtonHandle(ActionEvent e){
//		boolean correcto = true;
		try {
			ServiceLocator.getJornadaService().eliminarJornadaLaboral(
					((JornadaLaboral)jornadasTable.getSelectionModel().getSelectedItem()).getFecha());
			
		} catch (ServiceException e1) {
//			correcto = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido eliminar la jornada laboral.");
			alert.showAndWait();
		} catch (NullPointerException e2) {
//			correcto = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No ha seleccionado ninguna jornada laboral.");
			alert.showAndWait();
		}
//		if(correcto){
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Operación realizada correctamente");
//			alert.setContentText("Jornada laboral eliminada correctamente.");
//			alert.showAndWait();
//			initTables();
//		}
	}
	
	@FXML
	private void onEditarJornadaButtonHandle(ActionEvent e){
		Stage stage;
		Parent root;
		stage = (Stage)editarJornadaButton.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("EditarWindow.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
