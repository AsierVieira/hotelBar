package donamayor.hotelbar.model;

import javafx.beans.property.*;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Compra {
    private final IntegerProperty idCompra;
    private final IntegerProperty idHabitacion;

    private List<ProductoComprado> pcs;

    public Compra() {
        this.idCompra = new SimpleIntegerProperty();
        this.idHabitacion = new SimpleIntegerProperty();

        this.pcs = new ArrayList<>();
    }

    public int getIdCompra() {

        return idCompra.get();
    }

    public IntegerProperty idCompraProperty() {

        return idCompra;
    }

    public void setIdCompra(int idCompra) {

        this.idCompra.set(idCompra);
    }

    public int getIdHabitacion() {

        return idHabitacion.get();
    }

    public IntegerProperty idHabitacionProperty() {

        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {

        this.idHabitacion.set(idHabitacion);
    }



    public List<ProductoComprado> getPcs() {

        return Collections.unmodifiableList(pcs);
    }

    public void setPcs(List<ProductoComprado> pcs) {

        this.pcs = new ArrayList<>(pcs);
    }

    public Collection<Object> mostrarCarrito() {

        return Collections.singleton(pcs);
    }
}
