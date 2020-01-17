package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Pokemon;
import services.PokemonService;
import utils.BattleSimulator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BattleSimulatorController implements Initializable {


    @FXML
    private TextField ivAttack;

    @FXML
    private TextField ivDefense;

    @FXML
    private TextField ivStamina;

    @FXML
    private TextField level;

    @FXML
    private ComboBox<String> pokemonNameListComboBox1;

    @FXML
    private ComboBox<String> pokemonNameListComboBox2;

    @FXML
    public ComboBox pokemonFastMoveListComboBox1;

    @FXML
    public ComboBox pokemonFastMoveListComboBox2;

    @FXML
    public ComboBox pokemonChargedMoveListComboBox1;

    @FXML
    public ComboBox pokemonChargedMoveListComboBox2;

    private PokemonService pokemonService = new PokemonService();
    private BattleSimulator battleSimulator = new BattleSimulator();

    public BattleSimulatorController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPokemonNameData();
    }

    @FXML
    protected void handleSimulateButtonAction() {
    }

    private void setPokemonNameData() {
        ObservableList pokemonNameList = FXCollections.observableList(pokemonService.getAllPokemonNamesList());

        pokemonNameListComboBox1.getItems().clear();
        pokemonNameListComboBox2.getItems().clear();
        pokemonNameListComboBox1.getItems().addAll(pokemonNameList);
        pokemonNameListComboBox2.getItems().addAll(pokemonNameList);
    }

    public void handleLoadPokemonOneData(ActionEvent actionEvent) throws SQLException {
        loadData(pokemonNameListComboBox1, pokemonFastMoveListComboBox1, pokemonChargedMoveListComboBox1);
    }

    public void handleLoadPokemonTwoData(ActionEvent actionEvent) throws SQLException {
        loadData(pokemonNameListComboBox2, pokemonFastMoveListComboBox2, pokemonChargedMoveListComboBox2);
    }

    private void loadData(ComboBox<String> pokemonNameListComboBox1, ComboBox pokemonFastMoveListComboBox1, ComboBox pokemonChargedMoveListComboBox1) throws SQLException {
        String pokemonName = pokemonNameListComboBox1.getSelectionModel().getSelectedItem();
        Pokemon selectedPokemon = pokemonService.getPokemonByName(pokemonName);

        ObservableList pokemonFastMoveNameList = FXCollections.observableList(pokemonService.getPokemonFastMoveNameList(selectedPokemon));
        ObservableList pokemonChargedMoveNameList = FXCollections.observableList(pokemonService.getPokemonChargedMoveNameList(selectedPokemon));

        pokemonFastMoveListComboBox1.getItems().clear();
        pokemonChargedMoveListComboBox1.getItems().clear();
        pokemonFastMoveListComboBox1.getItems().addAll(pokemonFastMoveNameList);
        pokemonChargedMoveListComboBox1.getItems().addAll(pokemonChargedMoveNameList);
    }
}
