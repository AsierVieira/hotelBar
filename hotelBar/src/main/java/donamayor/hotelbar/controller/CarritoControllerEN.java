package donamayor.hotelbar.controller;

import donamayor.hotelbar.App;
import donamayor.hotelbar.model.Compra;
import donamayor.hotelbar.model.CompraDAO;
import donamayor.hotelbar.model.HabitacionDAO;
import donamayor.hotelbar.model.ProductoComprado;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Currency;
import java.util.List;

public class CarritoControllerEN {

    private int currentRow = 0;
    private int currentCol = 0;
    private static final int MAX_COLS = 1;



    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnComprar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label labelProductos;

    @FXML
    private ChoiceBox<String> choiceBoxHabitaciones;

    @FXML
    public void initialize() {


        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<String> habitaciones = habitacionDAO.getAllHabitaciones();
        choiceBoxHabitaciones.setItems(FXCollections.observableList(habitaciones));

        btnComprar.setDisable(true);

        choiceBoxHabitaciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean habilitarBotones = newValue != null && !newValue.isEmpty();
            btnComprar.setDisable(!habilitarBotones);


            actualizarTotalProductos();
            App.carrito.setIdHabitacion(choiceBoxHabitaciones.getSelectionModel().getSelectedIndex()+1);
        });

        mostrarCarrito();
        crearHboxDeTotal();
    }

    private void mostrarCarrito() {
        gridPane.getChildren().clear();
        currentRow = 0;
        currentCol = 0;

        for (ProductoComprado p : App.carrito.getPcs()) {
            crearHboxDeProducto(p);
        }
    }

    private void crearHboxDeProducto(ProductoComprado p) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #ffff;");

        Label lblNombre = new Label(p.getProducto().getNombre_en());
        lblNombre.setFont(Font.font("PT Sans", 24));
        hBox.getChildren().add(lblNombre);

        Label lblCantidad = new Label("         QUANTITY: " + p.getCantidad());
        lblCantidad.setFont(Font.font("PT Sans", 24));
        hBox.getChildren().add(lblCantidad);


        Label lblPrecioUnidad = new Label("    UNIT PRICE: " + p.getProducto().getPrecio() + " "+Currency.getInstance("EUR").getSymbol());
        lblPrecioUnidad.setFont(Font.font("PT Sans", 24));
        hBox.getChildren().add(lblPrecioUnidad);

        Label lblPrecioTotal = new Label("    PRICE : " + (p.getProducto().getPrecio()*p.getCantidad()) +" "+ Currency.getInstance("EUR").getSymbol());
        lblPrecioTotal.setFont(Font.font("PT Sans", 24));
        hBox.getChildren().add(lblPrecioTotal);

        Button btnEliminar = new Button("X");
        //aumentar el tamaño del botón
        btnEliminar.setMinSize(60, 60);
        btnEliminar.setOnAction(e -> eliminarProducto(p));
        hBox.getChildren().add(btnEliminar);

        gridPane.add(hBox, currentCol, currentRow);
        currentCol++;
        if (currentCol >= MAX_COLS) {
            currentCol = 0;
            currentRow++;
        }
    }

    //crear un hbox con el precio total de la compra
    private void crearHboxDeTotal() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #ffff;");

        //for para mostrar el total
        double suma = 0;
        for( ProductoComprado producto :App.carrito.getPcs()){
            suma+=producto.getProducto().getPrecio()*producto.getCantidad();
        }
        Label lblTotal = new Label("TOTAL: " + suma +" "+ Currency.getInstance("EUR").getSymbol());
        lblTotal.setFont(Font.font("PT Sans", 45));
        hBox.getChildren().add(lblTotal);

        gridPane.add(hBox, currentCol, currentRow);
        currentCol++;
        if (currentCol >= MAX_COLS) {
            currentCol = 0;
            currentRow++;
        }

    }

    private void eliminarProducto(ProductoComprado producto) {
        App.carrito.getPcs().remove(producto);
        mostrarCarrito(); // Refrescar la vista del carrito
        actualizarTotalProductos(); // Actualizar el contador de productos
        crearHboxDeTotal();
    }

    @FXML
    void comprar() throws IOException {
        if (choiceBoxHabitaciones.getValue() == null) {
            mostrarMensajeSeleccionarHabitacion();
        } else {

            CompraDAO.insertCompra(App.carrito);

            // Limpiar la selección
            App.carrito = new Compra();
            App.setRoot("finalinglés");
        }
    }

    @FXML
    void cancelar() throws IOException {
        // Limpiar la selección
        App.carrito = new Compra();
        App.setRoot("primeraescenainglés");
    }

    @FXML
    void atras() throws IOException {
        App.setRoot("segundaescenainglés");
    }

    private void actualizarTotalProductos() {
            int numeroProductos=0;
        for(ProductoComprado pc:App.carrito.getPcs()){
            numeroProductos+=pc.getCantidad();
        }

        labelProductos.setText( numeroProductos+ " PRODUCTS" );
    }

    private void mostrarMensajeSeleccionarHabitacion() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, seleccione una habitación.");
        alert.showAndWait();
    }
}
