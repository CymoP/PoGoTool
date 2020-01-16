package controller;

import data_access.PokemonDA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private ComboBox fastMove;

    @FXML
    private ComboBox chargeMove;

    @FXML
    private ComboBox<String> pokemonNameListComboBox1;

    @FXML
    private ComboBox<String> pokemonNameListComboBox2;

    private PokemonService pokemonService = new PokemonService();
    private BattleSimulator battleSimulator = new BattleSimulator();
    private PokemonDA pokemonDA = new PokemonDA();

    public BattleSimulatorController(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setPokemonNameData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleSimulateButtonAction() {}

    private void setPokemonNameData() throws SQLException {
        ObservableList pokemonNameList = FXCollections.observableList(pokemonDA.getPokemonNameList());

        pokemonNameListComboBox1.getItems().clear();
        pokemonNameListComboBox2.getItems().clear();
        pokemonNameListComboBox1.getItems().addAll(pokemonNameList);
        pokemonNameListComboBox2.getItems().addAll(pokemonNameList);
    }
}
