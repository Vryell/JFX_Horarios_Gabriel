package application;

import java.io.IOException;
import java.sql.SQLException;

import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;
import dad.jfx.services.ServiceException;
import dad.jfx.services.ServiceLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class EditarJornadaController {
	private JornadaLaboral jornada;
	
	private HorariosApp main;
	@FXML
	private Button volverButton;
	
	@FXML
	private Button aceptarButton;
	
	@FXML
	private Label fechaLabel;
	
	@FXML
	private ComboBox<Centro> centrosComboBox;
	private ObservableList<Centro> centrosList;
	@FXML
	private ComboBox<Area> areasComboBox;
	private ObservableList<Area> areasList;
	
	@FXML
	private ComboBox<Turno> turnosComboBox;
	private ObservableList<Turno> turnosList;
	
	
	@FXML
	private void initialize(){
		
//		try {
//			fechasList = FXCollections.observableArrayList(ServiceLocator.getJornadaService().listarJornadas(usuario));
//			fechaComboBox.setItems(fechasList);
//		} catch (ServiceException e) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error");
//			alert.setContentText("No se han podido listar las jornadas.");
//			alert.showAndWait();
//		} catch (SQLException e) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Error");
//			alert.setContentText("No se han podido listar las jornadas.");
//			alert.showAndWait();
//		}
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
		main.getEditarStage().close();
	}
	
	@FXML
	private void onAceptarButtonHandle(ActionEvent e){
//		boolean correcto = true;
		JornadaLaboral jornada = new JornadaLaboral();
		jornada.setFecha(fechaLabel.getText());
		jornada.setId_centro(centrosComboBox.getValue());
		jornada.setId_area(areasComboBox.getValue());
		jornada.setId_turno(turnosComboBox.getValue());
		try {
			ServiceLocator.getJornadaService().editarJornadaLaboral(jornada);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Operación realizada correctamente");
			alert.setContentText("Jornada laboral editada correctamente.");
			alert.showAndWait();
			
		} catch (ServiceException e1) {
//			correcto = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido editar la jornada laboral.");
			alert.showAndWait();
		}
			
		catch (SQLException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido editar la jornada laboral.");
			alert.showAndWait();
		}
	}
	
	public void setMain(HorariosApp main, JornadaLaboral jornada){
		this.main = main;
		this.jornada = jornada;
		rellenarJornada();
	}

	private void rellenarJornada() {
		fechaLabel.setText(jornada.getFecha());
		try {
			centrosList = FXCollections.observableArrayList(ServiceLocator.getCentroService().listarCentros());
		} catch (ServiceException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los centros.");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los centros.");
			alert.showAndWait();
		}
		centrosComboBox.setItems(centrosList);
		centrosComboBox.setValue(jornada.getId_centro());
		turnosComboBox.setItems(turnosList);
		turnosComboBox.setValue(jornada.getId_turno());
		areasComboBox.setItems(areasList);
		areasComboBox.setValue(jornada.getId_area());
		
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
}

	