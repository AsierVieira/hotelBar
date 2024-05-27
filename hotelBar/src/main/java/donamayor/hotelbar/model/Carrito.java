package donamayor.hotelbar.model;

import donamayor.hotelbar.model.ProductoComprado;


import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private static Carrito instance;
    private List<ProductoComprado> productosComprados;

    private Carrito() {
        productosComprados = new ArrayList<>();
    }

    public static Carrito getInstance() {
        if (instance == null) {
            instance = new Carrito();
        }
        return instance;
    }

    public List<ProductoComprado> getProductosComprados() {
        return productosComprados;
    }

    public void addProducto(ProductoComprado productoComprado) {
        productosComprados.add(productoComprado);
    }

    public void clear() {
        productosComprados.clear();
    }

}

