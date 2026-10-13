
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

        // CORREGIDO: && cambiado a ||, igual que en Cliente.
        // AGREGADO: el tipo es obligatorio porque en la BD "tipo" es NOT NULL.
        // Si estaba vacio, el insert fallaba.
        if (txtNumero.getText().isEmpty()
                || txtNumeroHabitacion.getText().isEmpty()
                || cbTipo.getValue() == null) {

            Alert alert = utilAlerts.mostrarAlerts(
                    "",
                    "numero, numero de habitacion y tipo son obligatorios",
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
            // AGREGADO: try/catch porque Double.parseDouble revienta si el
            // precio esta vacio o tiene letras.
            try {
                // CORREGIDO: el if solo crea el objeto. Antes todos los set
                // estaban dentro del if, y al EDITAR no se guardaban los cambios.
                if (habitacion == null)
                    habitacion = new Habitacion();
                habitacion.setNumero(txtNumero.getText());
                habitacion.setNumeroPiso(txtNumeroPiso.getText());
                habitacion.setNumeroHabitacion(txtNumeroHabitacion.getText());
                habitacion.setPrecio(Double.parseDouble(txtPrecio.getText()));
                habitacion.setEstado(txtEstado.getText());
                habitacion.setTipo(cbTipo.getValue());
                // CORREGIDO: faltaban estas 2 lineas. Sin saved = true el
                // controller creia que cancelaste, y sin cerrar() la ventana
                // no se cerraba.
                saved = true;
                cerrar();
            } catch (NumberFormatException e) {
                Alert alert = utilAlerts.mostrarAlerts("",
                        "El precio debe ser un numero",
                        "ERROR");
                alert.show();
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
