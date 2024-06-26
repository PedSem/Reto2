package com.example.torneotabla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlOpenB implements Initializable {

    @FXML
    private Button btnImportarDatosB;

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
    private TableColumn<Jugador,Integer> RankingFinal;

    @FXML
    private TableColumn<Jugador,Integer> RankingInicial;

    @FXML
    private TableColumn<Jugador,String> fideid;

    @FXML
    private TableColumn<Jugador,String>  nombre;

    @FXML
    private TableColumn<Jugador,Integer> elo;

    @FXML
    private TableColumn<Jugador,String>  pais;

    @FXML
    private TableColumn<Jugador,Boolean>  cv;

    @FXML
    private TableColumn<Jugador,Boolean>  hotel;

    @FXML
    private TableColumn<Jugador,String>  torneo;

    private ObservableList<Jugador> jugadores;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Jugador> jugadores = getJugador();
        this.tablaRanking.setItems(jugadores);
        this.RankingFinal.setCellValueFactory(new PropertyValueFactory<>("RangoFinal"));
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.fideid.setCellValueFactory(new PropertyValueFactory<>("FIDEID"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.elo.setCellValueFactory(new PropertyValueFactory<>("ELO"));
        this.pais.setCellValueFactory(new PropertyValueFactory<>("Pais"));
        this.cv.setCellValueFactory(new PropertyValueFactory<>("CV"));
        this.hotel.setCellValueFactory(new PropertyValueFactory<>("Hotel"));
        this.torneo.setCellValueFactory(new PropertyValueFactory<>("NomTorneo"));

        btnImportarDatosB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LecturaCSV.introducirJugadores("OPEN B");
                cargar();
            }
        });

    }

    public void cargar(){
        ObservableList<Jugador> jugadores = getJugador();
        this.tablaRanking.setItems(jugadores);
        this.RankingFinal.setCellValueFactory(new PropertyValueFactory<>("RangoFinal"));
        this.RankingInicial.setCellValueFactory(new PropertyValueFactory<>("RangoInicial"));
        this.fideid.setCellValueFactory(new PropertyValueFactory<>("FIDEID"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.elo.setCellValueFactory(new PropertyValueFactory<>("ELO"));
        this.pais.setCellValueFactory(new PropertyValueFactory<>("Pais"));
        this.cv.setCellValueFactory(new PropertyValueFactory<>("CV"));
        this.hotel.setCellValueFactory(new PropertyValueFactory<>("Hotel"));
        this.torneo.setCellValueFactory(new PropertyValueFactory<>("NomTorneo"));

    }

    @FXML
    private void insertarJugador(javafx.event.ActionEvent actionEvent) {

        try {
            jugadores = getJugador();
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("btnInsertarJugadorB.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Asigno el controlador
            ControlInsertarOpenB controlador = loader.getController();
            controlador.initAtributtes(jugadores);

            // Creo el Scene
            Scene scene = new Scene(root);

            //Cargo la hoja de estilos
            scene.getStylesheets().add(getClass().getResource("StylesInsertarModificar.css").toExternalForm());

            //Cargo el Stage
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Insertar Nuevo Jugador");
            stage.showAndWait();

            cargar();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
    public static ObservableList<Jugador> getJugador(){
        ObservableList<Jugador> obs = FXCollections.observableArrayList();
        Connection cnx;
        try {
            cnx = Conection.getConection();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM jugador where NomTorneo = 'OPEN B'");

            while (rs.next()) {
                int rinicial = rs.getInt("RangoInicial");
                String fideid = rs.getString("FIDEID");
                String nom = rs.getString("Nombre");
                int elo = rs.getInt("ELO");
                String pais = rs.getString("Pais");
                boolean cv = rs.getBoolean("CV");
                boolean hot = rs.getBoolean("Hotel");
                int rfinal = rs.getInt("RangoFinal");
                String nomtorneo = rs.getString("NomTorneo");

                Jugador j = new Jugador(rinicial,fideid,nom,elo,pais,cv,hot,rfinal,nomtorneo);
                obs.add(j);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obs;
    }


    @FXML
    void modificarJugador(ActionEvent event) {

        /* La idea aquí es primero seleccionar un jugador en la TableView y después ya darle a modificar para hacer los cambios*/
        Jugador j = this.tablaRanking.getSelectionModel().getSelectedItem();

        try {
            jugadores = getJugador();
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("btnModificarJugadorB.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Asigno el controlador
            ControlModificarOpenB controlador = loader.getController();
            controlador.initAtributtes(jugadores,j);

            // Creo el Scene
            Scene scene = new Scene(root);

            //Cargo la hoja de estilos
            scene.getStylesheets().add(getClass().getResource("StylesInsertarModificar.css").toExternalForm());

            //Cargo el Stage
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Modificar Jugador");
            stage.showAndWait();

            // Se asigna la persona devuelta
            Jugador jSeleccionado = controlador.getJugador();

            if (jSeleccionado != null) {
                tablaRanking.refresh();
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException {

        Jugador j = this.tablaRanking.getSelectionModel().getSelectedItem();

        Connection cnx = Conection.getConection();
        Statement stm = cnx.createStatement();

        PreparedStatement ps = cnx.prepareStatement("DELETE FROM jugador where NomTorneo = ?  and RangoInicial = ?");

        ps.setString(1,j.getNomTorneo());
        ps.setInt(2,j.getRangoInicial());

        ps.execute();
        ps.close();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText("Se ha eliminado correctamente");
        alert.showAndWait();

        cargar();
    }



    @FXML
    private void jugadorOpaPremio(javafx.event.ActionEvent actionEvent) {

        try {

            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JugadorOptaPremioOpenB.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Asigno el controlador
            ControlJugadorOptaPremioOpenB controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);

            //Cargo la hoja de estilos
            scene.getStylesheets().add(getClass().getResource("StylesJugadorOptaPremio.css").toExternalForm());

            //Cargo el Stage
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Lista de jugadores y premios a los que optan");
            stage.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }


    @FXML
    private void listaGanadores(javafx.event.ActionEvent actionEvent) {

        try {

            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GanadoresOpenB.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Asigno el controlador
            ControlGanadoresOpenB controlador = loader.getController();

            // Creo el Scene
            Scene scene = new Scene(root);

            //Cargo la hoja de estilos
            scene.getStylesheets().add(getClass().getResource("StylesTableViews.css").toExternalForm());

            //Cargo el Stage
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Lista de ganadores OPEN B");
            stage.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void meterRFinal(){
        LecturaCSV.introducirfinal("OPEN B");
        tablaRanking.refresh();
        cargar();
    }



   public void closeWindows() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));

            Parent root = loader.load();

            // Obtengo el controlador
            //ControladorMenu controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            //Cargo la hoja de estilos del Menu
            scene.getStylesheets().add(getClass().getResource("StylesMenu.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Benidorm Chess 2023");
            stage.show();

            // Indico que debe hacer al cerrar
            //stage.setOnCloseRequest(e -> controlador.closeWindows());

            Stage myStage = (Stage) this.btnImportarDatosB.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
