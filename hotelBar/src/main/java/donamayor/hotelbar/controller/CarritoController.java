package donamayor.hotelbar.controller;

import donamayor.hotelbar.App;
import donamayor.hotelbar.model.Compra;
import donamayor.hotelbar.model.CompraDAO;
import donamayor.hotelbar.model.HabitacionDAO;
import donamayor.hotelbar.model.ProductoComprado;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CarritoController {

    private int currentRow = 0;
    private int currentCol = 0;
    private static final int MAX_COLS = 1;

    private List<ProductoComprado> productosComprados;

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
        productosComprados = App.getProductosComprados().stream()
                .filter(p -> p.getCantidad() > 0)
                .collect(Collectors.toList());

        HabitacionDAO habitacionDAO = new HabitacionDAO();
        List<String> habitaciones = habitacionDAO.getAllHabitaciones();
        choiceBoxHabitaciones.setItems(FXCollections.observableList(habitaciones));

        btnComprar.setDisable(true);

        choiceBoxHabitaciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean habilitarBotones = newValue != null && !newValue.isEmpty();
            btnComprar.setDisable(!habilitarBotones);
            actualizarProductos();
        });

        mostrarCarrito();
    }

    private void mostrarCarrito() {
        gridPane.getChildren().clear();
        currentRow = 0;
        currentCol = 0;

        for (ProductoComprado p : productosComprados) {
            crearHboxDeProducto(p);
        }
    }

    private void crearHboxDeProducto(ProductoComprado p) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #ffff;");

        Label lblNombre = new Label(p.getProducto().getNombre_es());
        lblNombre.setFont(Font.font("PT Sans", 24));
        hBox.getChildren().add(lblNombre);

        Label lblCantidad = new Label("CANTIDAD: " + p.getCantidad());
        lblCantidad.setFont(Font.font("PT Sans", 24));
        hBox.getChildren().add(lblCantidad);

        gridPane.add(hBox, currentCol, currentRow);
        currentCol++;
        if (currentCol >= MAX_COLS) {
            currentCol = 0;
            currentRow++;
        }
    }

    @FXML
    void comprar() throws IOException {
        if (choiceBoxHabitaciones.getValue() == null) {
            mostrarMensajeSeleccionarHabitacion();
        } else {
            // Crear la compra y guardar la compra
            Compra compra = new Compra();
            compra.setIdHabitacion(Integer.parseInt(choiceBoxHabitaciones.getValue()));
            compra.setPcs(productosComprados);

            CompraDAO.insertCompra(compra);

            // Limpiar la selección
            App.resetProductosComprados();
            App.setRoot("final");
        }
    }

    @FXML
    void cancelar() throws IOException {
        // Limpiar la selección
        App.resetProductosComprados();
        App.setRoot("primeraescena");
    }

    @FXML
    void atras() throws IOException {
        App.setRoot("segundaescena");
    }

    private void actualizarProductos() {
        int numeroProductos = productosComprados.size();
        labelProductos.setText("PRODUCTOS: " + numeroProductos);
    }

    private void mostrarMensajeSeleccionarHabitacion() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, seleccione una habitación.");
        alert.showAndWait();
    }
}
