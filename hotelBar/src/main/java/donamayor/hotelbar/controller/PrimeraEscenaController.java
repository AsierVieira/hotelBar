package donamayor.hotelbar.controller;

import donamayor.hotelbar.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class PrimeraEscenaController {


    @FXML
    void entrar() throws IOException {
        App.setRoot("segundaescena");
    }
    }








