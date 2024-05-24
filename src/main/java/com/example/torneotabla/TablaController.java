package com.example.torneotabla;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TablaController {
    @FXML
    private TableView<JugadoroptaPremio> tablaJugadores;

    //todo para obtener los datos de la tabla
    public ObservableList<JugadoroptaPremio> obtenerDatosDeTabla() {
        return tablaJugadores.getItems();
    }
}

