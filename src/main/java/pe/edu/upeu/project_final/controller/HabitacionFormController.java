
package pe.edu.upeu.project_final.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.utils.UtilAlerts;

public class HabitacionFormController {

    public static UtilAlerts utilAlerts = new UtilAlerts();

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtNumero, txtNumeroPiso, txtNumeroHabitacion,
            txtPrecio, txtEstado;

    @FXML
    private ComboBox<String> cbTipo;

    private Habitacion habitacion;
    private boolean saved = false;

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll(
                "simple",
                "doble",
                "matrimonial");
    }

    public boolean isSaved() {
        return saved;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    // Validar los campos obligatorios
    private boolean validate() {

        if (txtNumero.getText().isEmpty()
                && txtNumeroHabitacion.getText().isEmpty()) {

            Alert alert = utilAlerts.mostrarAlerts(
                    "",
                    "numero y numero de habitacion obligatorio",
                    "ERROR");

            alert.show();
            return false;
        }

        return true;
    }

    // Cerrar la ventana
    private void cerrar() {
        Stage stage = (Stage) txtNumero.getScene().getWindow();
        stage.close();
    }

    // Cancelar
    @FXML
    private void handleCancel() {
        cerrar();
    }

    // Guardar o actualizar habitación
    @FXML
    private void handleSave() {
        if (validate()) {
           if (habitacion == null){
               habitacion = new Habitacion();
               habitacion.setNumero(Integer.parseInt(txtNumero.getText()));
               habitacion.setNumeroPiso(Integer.parseInt(txtNumeroPiso.getText()));
               habitacion.setNumeroHabitacion(Integer.parseInt(txtNumeroHabitacion.getText()));
               habitacion.setPrecio(Double.parseDouble(txtPrecio.getText()));
               habitacion.setEstado(txtEstado.getText());
               habitacion.setTipo(cbTipo.getValue());
           }
        }
        }
    // Cargar los datos de una habitación para editar
    public void setHabitacion(Habitacion h) {
        this.habitacion = h;
        if (h != null) {
            lblTitulo.setText("Editar Habitación");
            txtNumero.setText(
                    String.valueOf(h.getNumero()));

            txtNumeroPiso.setText(
                    String.valueOf(h.getNumeroPiso()));

            txtNumeroHabitacion.setText(
                    String.valueOf(h.getNumeroHabitacion()));

            cbTipo.setValue(h.getTipo());

            txtPrecio.setText(
                    String.valueOf(h.getPrecio()));

            txtEstado.setText(h.getEstado());

        }
    }
}
