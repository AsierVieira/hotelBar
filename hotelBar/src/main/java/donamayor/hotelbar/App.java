package donamayor.hotelbar;

import donamayor.hotelbar.model.Compra;
import donamayor.hotelbar.model.Producto;
import donamayor.hotelbar.model.ProductoComprado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static Scene scene;

    public static List<ProductoComprado> productosComprados = new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primeraescena"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static List<ProductoComprado> getProductosComprados() { // esto no se si están bien
        return productosComprados;
    }

    public static void setProductosComprados(List<ProductoComprado> productosComprados) { // esto no se si está bien
        App.productosComprados = productosComprados;
    }

    public static void resetProductosComprados() {
        //llamar a la clase producto comprado y crear un nuevo objeto

        ProductoComprado p = new ProductoComprado(new Producto(), 0);
        productosComprados = new ArrayList<>();
        productosComprados.add(p);


    }

    public static void main(String[] args) {
        launch();
    }
}

