package donamayor.hotelbar.controller;

import donamayor.hotelbar.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PrimeraEscenaControllerEN {


    @FXML
    void entrar() throws IOException {
        App.setRoot("segundaescenainglés");
    }

    @FXML
    void entrarEspanol(ActionEvent event) throws IOException {
        App.setRoot("primeraescena");
    }

}









