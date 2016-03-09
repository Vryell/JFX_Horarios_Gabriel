package application;

import java.io.IOException;
import java.sql.SQLException;

import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;
import dad.jfx.services.ServiceException;
import dad.jfx.services.ServiceLocator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class PantallaPrincipalController {
	private HorariosApp main;
	@FXML 
	BorderPane panelPrincipal;
	
	@FXML
	private Button conectarButton;
	
	@FXML
	private Button aniadirJornadaButton;
	
	@FXML
	private Button editarJornadaButton;
	
	@FXML 
	private TableView<JornadaLaboral> jornadasTable;
	@FXML
	private TableColumn<?,?> jornadasFechaColumn;
	@FXML
	private TableColumn<?,?> jornadasCentroColumn;
	@FXML
	private TableColumn<?,?> jornadasAreaColumn;
	@FXML
	private TableColumn<?,?> jornadasTurnoColumn;
	
	private ObservableList<JornadaLaboral> jornadasList;
	
	@FXML 
	private TableView<Centro> centrosTable;
	@FXML
	private TableColumn<?,?> centrosIdColumn;
	@FXML
	private TableColumn<?,?> centrosNombreColumn;
	@FXML
	private TableColumn<?,?> centrosDireccionColumn;
	
	private ObservableList<Centro> centrosList;
	
	@FXML
	private TableView<Turno> turnosTable;
	@FXML
	private TableColumn<?,?> turnosIdColumn;
	@FXML
	private TableColumn<?,?> turnosAliasColumn;
	@FXML
	private TableColumn<?,?> turnosCentroColumn;
	@FXML
	private TableColumn<?,?> turnosHorasColumn;
	@FXML
	private TableColumn<?, ?> turnosDescripcionColumn;
	
	private ObservableList<Turno> turnosList;
	
	@FXML
	private TableView<Area> areaTable;
	@FXML
	private TableColumn<?,?> areasIdColumn;
	@FXML
	private TableColumn<?,?> areasNombreColumn;
	@FXML
	private TableColumn<?,?> areasCentroColumn;

	private ObservableList<Area> areasList;
	
	
	@FXML
	private void initialize(){
		initTables();
		jornadasTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					initTables();
				}
			}
		});
	}
	
	public void initTables() {
		try {
			jornadasList = FXCollections.observableArrayList(ServiceLocator.getJornadaService().listarJornadas());
		} catch (ServiceException e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar las jornadas.");
			alert.showAndWait();
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		turnosDescripcionColumn.setCellValueFactory(new PropertyValueFactory("descripcion"));
		
		turnosTable.setItems(turnosList);
	}
	
	@FXML
	private void onAniadirJornadaButtonHandle(ActionEvent e) throws IOException{
		main.showPantallaInsertar();
		jornadasTable.requestFocus();
//		Stage stage;
//		Parent root;
//		stage = (Stage)aniadirJornadaButton.getScene().getWindow();
//		try {
//			root = FXMLLoader.load(getClass().getResource("InsertarWindow.fxml"));
//			Scene scene = new Scene(root);
//			stage.setScene(scene);
//			stage.setTitle("Insertar Jornada");
//			stage.show();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}
	
	@FXML
	private void onEliminarJornadaButtonHandle(ActionEvent e){
		try {
			ServiceLocator.getJornadaService().eliminarJornadaLaboral(
					((JornadaLaboral)jornadasTable.getSelectionModel().getSelectedItem()).getFecha());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Operación realizada correctamente");
			alert.setContentText("Jornada laboral eliminada correctamente.");
			alert.showAndWait();
			initTables();
		} catch (ServiceException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido eliminar la jornada laboral.");
			alert.showAndWait();
		} catch (NullPointerException e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No ha seleccionado ninguna jornada laboral.");
			alert.showAndWait();
		}
		catch (SQLException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido eliminar la jornada laboral.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void onEditarJornadaButtonHandle(ActionEvent e) throws IOException{
		try{
			main.showPantallaEditar((JornadaLaboral)jornadasTable.getSelectionModel().getSelectedItem());
			jornadasTable.requestFocus();
		} catch(NullPointerException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No ha seleccionado ninguna jornada laboral.");
			alert.showAndWait();
		}
	}
	
	public void setMain(HorariosApp main){
		this.main = main;
	}
	
	@FXML
	private void onRecargarButtonHandle(ActionEvent e){
		initTables();
	}
}
