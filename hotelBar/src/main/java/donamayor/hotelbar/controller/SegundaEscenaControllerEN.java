package donamayor.hotelbar.controller;

import donamayor.hotelbar.App;
import donamayor.hotelbar.model.Producto;
import donamayor.hotelbar.model.ProductoComprado;
import donamayor.hotelbar.model.ProductoDAO;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class SegundaEscenaControllerEN {

    private int currentRow = 0, currentCol = 0; // Libre posición en el GridPane
    private static final int MAX_COLS = 2;



    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnVinos, btnSnacks, btnBebidas, btnTodos;

    @FXML
    public void initialize() {

        mostrarProductos(ProductoDAO.getProductos());

        btnVinos.setOnAction(e -> mostrarVinos());
        btnSnacks.setOnAction(e -> mostrarSnacks());
        btnBebidas.setOnAction(e -> mostrarBebidas());
        btnTodos.setOnAction(e -> mostrarTodos());
    }

    private void mostrarTodos() {
        List<Producto> listaFiltrada = new ArrayList<>();
        for (Producto p : ProductoDAO.getProductos()) {
            if (p.getTipo().equalsIgnoreCase("Vino") || p.getTipo().equalsIgnoreCase("Snack") || p.getTipo().equalsIgnoreCase("Bebida")) {
                listaFiltrada.add(p);
            }
        }
        mostrarProductos(listaFiltrada);
    }

    private void mostrarProductos(List<Producto> productosFiltrados) {
        gridPane.getChildren().clear();
        currentRow = 0;
        currentCol = 0;

        for (Producto p : productosFiltrados) {
            crearHboxDeProducto(p);
        }

    }

    private void crearHboxDeProducto(Producto p) {
        HBox hBoxDeProducto = new HBox(10);
        hBoxDeProducto.setAlignment(Pos.CENTER);
        hBoxDeProducto.setPadding(new Insets(10, 20, 10, 20));

        Label lblNombre = new Label(p.getNombre_en());
        lblNombre.setFont(new Font("PT Sans", 30));
        Label lblPrecio = new Label(String.valueOf(p.getPrecio()) + Currency.getInstance("EUR").getSymbol());
        lblPrecio.setFont(new Font("PT Sans", 18));

        Label lblCantidad = new Label(String.valueOf(App.carrito.getCantidad(p.getId_producto())));
        lblCantidad.setFont(new Font("PT Sans", 30));

        Button btnMas = new Button("+");
        Button btnMenos = new Button("-");

        Region espacio = new Region();
        espacio.setPrefWidth(10);

        btnMas.setOnAction(actionEvent -> {
            int cantidad = Integer.parseInt(lblCantidad.getText());
            lblCantidad.setText(String.valueOf(cantidad + 1));
            actualizarCantidadProducto(p, cantidad + 1);
        });

        btnMenos.setOnAction(actionEvent -> {
            int cantidad = Integer.parseInt(lblCantidad.getText());
            if (cantidad > 0) {
                lblCantidad.setText(String.valueOf(cantidad - 1));
                actualizarCantidadProducto(p, cantidad - 1);
            }
        });

        hBoxDeProducto.getChildren().addAll(lblNombre, lblPrecio, espacio, btnMenos, lblCantidad, btnMas);

        gridPane.add(hBoxDeProducto, currentCol, currentRow);

        currentCol++;
        if (currentCol >= MAX_COLS) {
            currentCol = 0;
            currentRow++;
        }
    }

    private void actualizarCantidadProducto(Producto producto, int nuevaCantidad) {
        for (ProductoComprado productoComprado : App.carrito.getPcs()) {
            if (productoComprado.getProducto().getId_producto()==(producto.getId_producto())) {
                productoComprado.setCantidad(nuevaCantidad);
                return;
            }
        }
        App.carrito.getPcs().add(new ProductoComprado(producto, nuevaCantidad));
    }

    @FXML
    private void mostrarVinos() {
        List<Producto> listaFiltrada = new ArrayList<>();
        for (Producto p : ProductoDAO.getProductos()) {
            if (p.getTipo().equalsIgnoreCase("Vino")) {
                listaFiltrada.add(p);
            }
        }
        mostrarProductos(listaFiltrada);
    }

    @FXML
    private void mostrarSnacks() {
        List<Producto> listaFiltrada = new ArrayList<>();
        for (Producto p : ProductoDAO.getProductos()) {
            if (p.getTipo().equalsIgnoreCase("Snack")) {
                listaFiltrada.add(p);
            }
        }
        mostrarProductos(listaFiltrada);
    }

    @FXML
    private void mostrarBebidas() {
        List<Producto> listaFiltrada = new ArrayList<>();
        for (Producto p : ProductoDAO.getProductos()) {
            if (p.getTipo().equalsIgnoreCase("Bebida")) {
                listaFiltrada.add(p);
            }
        }
        mostrarProductos(listaFiltrada);
    }




    @FXML
    private void carrito() throws IOException {
        App.setRoot("carritoinglés");
    }

    @FXML
    void atras() throws IOException {
        App.setRoot("primeraescenainglés");
    }
}
