package pe.edu.upeu.project_final.modelo;

import javafx.beans.property.*;

public class Habitacion {

    private final IntegerProperty idHabitacion =
            new SimpleIntegerProperty();

    private final IntegerProperty numero =
            new SimpleIntegerProperty();

    private final IntegerProperty numeroPiso =
            new SimpleIntegerProperty();

    private final IntegerProperty numeroHabitacion =
            new SimpleIntegerProperty();

    private final StringProperty tipo =
            new SimpleStringProperty();

    private final DoubleProperty precio =
            new SimpleDoubleProperty();

    private final StringProperty estado =
            new SimpleStringProperty();

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, int numero, int numeroPiso,
                      int numeroHabitacion, String tipo,
                      double precio, String estado) {

        setIdHabitacion(idHabitacion);
        setNumero(numero);
        setNumeroPiso(numeroPiso);
        setNumeroHabitacion(numeroHabitacion);
        setTipo(tipo);
        setPrecio(precio);
        setEstado(estado);
    }

    public final int getIdHabitacion() {
        return idHabitacion.get();
    }

    public final void setIdHabitacion(int value) {
        this.idHabitacion.set(value);
    }

    public final IntegerProperty idHabitacionProperty() {
        return idHabitacion;
    }

    public final int getNumero() {
        return numero.get();
    }

    public final void setNumero(int value) {
        this.numero.set(value);
    }

    public final IntegerProperty numeroProperty() {
        return numero;
    }

    public final int getNumeroPiso() {
        return numeroPiso.get();
    }

    public final void setNumeroPiso(int value) {
        this.numeroPiso.set(value);
    }

    public final IntegerProperty numeroPisoProperty() {
        return numeroPiso;
    }

    public final int getNumeroHabitacion() {
        return numeroHabitacion.get();
    }

    public final void setNumeroHabitacion(int value) {
        this.numeroHabitacion.set(value);
    }

    public final IntegerProperty numeroHabitacionProperty() {
        return numeroHabitacion;
    }

    public final String getTipo() {
        return tipo.get();
    }

    public final void setTipo(String value) {
        this.tipo.set(value);
    }

    public final StringProperty tipoProperty() {
        return tipo;
    }

    public final double getPrecio() {
        return precio.get();
    }

    public final void setPrecio(double value) {
        this.precio.set(value);
    }

    public final DoubleProperty precioProperty() {
        return precio;
    }

    public final String getEstado() {
        return estado.get();
    }

    public final void setEstado(String value) {
        this.estado.set(value);
    }

    public final StringProperty estadoProperty() {
        return estado;
    }

}