
package pe.edu.upeu.project_final.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import pe.edu.upeu.project_final.HelloApplication;
import pe.edu.upeu.project_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.utils.UtilAlerts;

import java.util.Optional;

public class HabitacionController {

    private final UtilAlerts utilAlert = new UtilAlerts();

    @FXML
    private TableView<Habitacion> tableHabitacion;

    @FXML
    private TableColumn<Habitacion, Number> ColNum;

    @FXML
    private TableColumn<Habitacion, Integer> ColId;

    @FXML
    private TableColumn<Habitacion, String> ColNumero;

    @FXML
    private TableColumn<Habitacion, String> ColNumeroPiso;

    @FXML
    private TableColumn<Habitacion, String> ColNumeroHabitacion;

    @FXML
    private TableColumn<Habitacion, String> ColTipo;

    @FXML
    private TableColumn<Habitacion, Double> ColPrecio;

    @FXML
    private TableColumn<Habitacion, String> ColEstado;

    private final IHabitacionDao habitacionDao = new HabitacionDaoImpl();
    private ObservableList<Habitacion> listaHabitacion;

    @FXML
    public void initialize() {
        ColNum.setCellFactory(colum ->
                new TableCell<>(){
                    @Override
                    protected void updateItem(Number item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setText(null);
                        }else{
                            setText(String.valueOf(getIndex() +1));
                        }
                    }
                });

        ColId.setCellValueFactory(cellData ->
                        cellData.getValue().idhabitacionProperty().asObject());
        ColNumero.setCellValueFactory(
                cellData ->
                        cellData.getValue().numeroProperty());
        ColNumeroPiso.setCellValueFactory(cellData ->
                        cellData.getValue().numeroPisoProperty());
        ColNumeroHabitacion.setCellValueFactory(
                cellData ->
                        cellData.getValue().numeroHabitacionProperty());
        ColTipo.setCellValueFactory(
                cellData ->
                        cellData.getValue().tipoProperty());
        ColPrecio.setCellValueFactory(
                cellData ->
                        cellData.getValue().precioProperty().asObject());
        ColEstado.setCellValueFactory(cellData ->
                        cellData.getValue().estadoProperty());
        //llamar al metodo
        cargarDatos();
    }

    private void cargarDatos() {
        listaHabitacion = FXCollections.observableArrayList(
                habitacionDao.listAll());

        tableHabitacion.setItems(listaHabitacion);
    }

    @FXML
    private void handleDelete() {

        Habitacion selectId =
                tableHabitacion.getSelectionModel().getSelectedItem();

        if (selectId != null) {

            Alert confirm = utilAlert.mostrarAlerts(
                    "Confirmar eliminacion",
                    "Estas seguro de eliminar la habitacion: "
                            + selectId.getNumero() + "?",
                    "CONFIRMATION");

            Optional<ButtonType> result = confirm.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (habitacionDao.delete(selectId)) {
                    listaHabitacion.remove(selectId);
                }
            }

            // CORREGIDO: mismo error que en Cliente. El else estaba dentro del
            // if del OK y las llaves estaban desordenadas. Ahora el else
            // pertenece al if (selectId != null).
        } else {
            Alert warning = utilAlert.mostrarAlerts(
                    "Seleccion requerida",
                    "Seleccione una habitacion para eliminar",
                    "WARNING");

            warning.showAndWait();
        }
    }
    @FXML
    private void handleNew() {
        showForm(null);
    }

    @FXML
    private void handleEdit() {
        Habitacion selectHabita =
                tableHabitacion.getSelectionModel().getSelectedItem();

        if (selectHabita != null) {

            Habitacion habitaFull = habitacionDao.searchById(
                    selectHabita.getIdhabitacion());

            showForm(habitaFull);

        } else {
            Alert alert = utilAlert.mostrarAlerts(
                    "Seleccion requerida",
                    "",
                    "CONFIRMATION");

            alert.show();
        }
    }

    private void showForm(Habitacion h) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource(
                            "/pe/edu/upeu/project_final/views/habitacion-form.fxml"));

            Parent root = loader.load();

            HabitacionFormController controller = loader.getController();

            controller.setHabitacion(h);

            Stage stage = new Stage();

            stage.setTitle(h == null ?
                    "Nueva Habitacion" :
                    "Editar Habitacion");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (controller.isSaved()) {

                Habitacion habitacionProcesada = controller.getHabitacion();

                if (h == null) {

                    boolean result = habitacionDao.insert(habitacionProcesada);

                    if (result) {
                        Alert alert = utilAlert.mostrarAlerts(
                                "Success",
                                "Registro satisfactorio!!!",
                                "CONFIRMATION");
                        alert.show();

                    } else {
                        Alert alert = utilAlert.mostrarAlerts(
                                "Error",
                                "Error al insertar!!!",
                                "ERROR");
                        alert.show();
                    }

                } else {

                    boolean result = habitacionDao.update(habitacionProcesada);

                    if (result) {
                        Alert alert = utilAlert.mostrarAlerts(
                                "Update",
                                "Registro actualizado!!!",
                                "WARNING");
                        alert.show();

                    } else {
                        Alert alert = utilAlert.mostrarAlerts(
                                "Error",
                                "Error al actualizar!!!",
                                "ERROR");
                        alert.show();
                    }
                }

                cargarDatos();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
