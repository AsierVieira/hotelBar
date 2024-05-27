package donamayor.hotelbar.test;

import donamayor.hotelbar.model.Compra;
import donamayor.hotelbar.model.CompraDAO;
import donamayor.hotelbar.model.Producto;
import donamayor.hotelbar.model.ProductoComprado;

import java.util.ArrayList;
import java.util.List;

public class TestCompra {

    public static void main(String[] args) {
        Compra c1=new Compra();
        c1.setIdHabitacion(1);
        c1.setIdCompra(1);

        ArrayList<ProductoComprado> pcs=new ArrayList<>();
        pcs.add(new ProductoComprado(new Producto(1,"galleta","cookie",0.5,"snack",null,null,null),2));
        pcs.add(new ProductoComprado(new Producto(2,"chocolate","chocolate",2.0,"snack",null,null,null),3));

        c1.setPcs(pcs);

        CompraDAO.insertCompra(c1);


    }
}
