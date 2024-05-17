package org.example.reto2benidormchess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlOpenA implements Initializable {

    @FXML
    private Button btnImportarDatos;

    @FXML
    private Button btnInsertarJugador;

    @FXML
    private Button btnModificarJugador;

    @FXML
    private Button btnEliminarJugador;

    @FXML
    private Button btnJugadoresPremiosOptan;

    @FXML
    private Button btnImportarGanadores;

    @FXML
    private Button btnListaGanadores;

    @FXML
    private TableView<Jugador> tablaRanking;

    @FXML
    private TableColumn RankingFinal;

    @FXML
    private TableColumn RankingInicial;

    @FXML
    private TableColumn fideid;

    @FXML
    private TableColumn nombre;

    @FXML
    private TableColumn elo;

    @FXML
    private TableColumn pais;

    @FXML
    private TableColumn cv;

    @FXML
    private TableColumn hotel;

    @FXML
    private TableColumn torneo;

    @FXML
    private ObservableList<Jugador> listaJugadores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listaJugadores = FXCollections.observableArrayList(
            new Jugador(1, "234325", "Juan Manuel Lopez", 2245, "ITA", true, false, "Open A"),
            new Jugador(2, "543675", "Angelina Blasco Duran", 2400, "ALE", false, true, "Open A"),
            new Jugador(3, "654567", "Martin", 2134, "ESP", true, true, "Open A")
        );

        this.tablaRanking.setItems(listaJugadores);

        this.RankingInicial.setCellValueFactory(new PropertyValueFactory("rangoInicial"));
        this.fideid.setCellValueFactory(new PropertyValueFactory("fideid"));
        this.nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.elo.setCellValueFactory(new PropertyValueFactory("elo"));
        this.pais.setCellValueFactory(new PropertyValueFactory("pais"));
        this.cv.setCellValueFactory(new PropertyValueFactory("cv"));
        this.hotel.setCellValueFactory(new PropertyValueFactory("h"));
        this.torneo.setCellValueFactory(new PropertyValueFactory("nombreTorneo"));

    }

   public void closeWindows() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));

            Parent root = loader.load();

            // Obtengo el controlador
            //ControladorMenu controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            //stage.setTitle("Benidorm Chess 2023");
            stage.show();

            // Indico que debe hacer al cerrar
            //stage.setOnCloseRequest(e -> controlador.closeWindows());

            Stage myStage = (Stage) this.btnImportarDatos.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
