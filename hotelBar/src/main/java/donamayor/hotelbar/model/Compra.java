package donamayor.hotelbar.model;

import javafx.beans.property.*;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Compra {

    private int idHabitacion;

    private List<ProductoComprado> pcs;

    public Compra() {
        this.pcs = new ArrayList<>();
    }







    public int getIdHabitacion() {

        return idHabitacion;
    }



    public void setIdHabitacion(int idHabitacion) {

        this.idHabitacion = idHabitacion;
    }



    public List<ProductoComprado> getPcs() {

        return pcs;
    }

    public void setPcs(List<ProductoComprado> pcs) {

        this.pcs = new ArrayList<>(pcs);
    }

    public int getCantidad(int id_producto) {
        for (ProductoComprado pc : pcs) {
            if (pc.getProducto().getId_producto() == id_producto) {
                return pc.getCantidad();
            }
        }
        return 0;
    }



}
