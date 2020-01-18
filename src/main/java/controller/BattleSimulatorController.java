package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pokemon;
import services.PokemonService;
import utils.BattleSimulator;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BattleSimulatorController implements Initializable {


    @FXML
    public TextField ivStaminaPokemonOne;

    @FXML
    public TextField ivDefensePokemonOne;

    @FXML
    public TextField ivAttackPokemonOne;

    @FXML
    public TextField levelPokemonOne;

    @FXML
    public TextField levelPokemonTwo;

    @FXML
    public TextField ivAttackPokemonTwo;

    @FXML
    public TextField ivDefensePokemonTwo;

    @FXML
    public TextField ivStaminaPokemonTwo;

    @FXML
    public ImageView pokemonOneImageView;

    @FXML
    public ImageView pokemonTwoImageView;

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
        loadImage(pokemonNameListComboBox1, pokemonOneImageView);
    }

    public void handleLoadPokemonTwoData(ActionEvent actionEvent) throws SQLException {
        loadData(pokemonNameListComboBox2, pokemonFastMoveListComboBox2, pokemonChargedMoveListComboBox2);
        loadImage(pokemonNameListComboBox2, pokemonTwoImageView);
    }

    public void levelPokemonOneFieldListener() {
        verifyLevelTextFieldInput(levelPokemonOne);
    }

    public void levelPokemonTwoFieldListener() {
        verifyLevelTextFieldInput(levelPokemonTwo);
    }

    public void ivAttackPokemonOneListener() {
        verifyIVTextFieldInput(ivAttackPokemonOne);
    }

    public void ivDefensePokemonOneListener() {
        verifyIVTextFieldInput(ivDefensePokemonOne);
    }

    public void ivStaminaPokemonOneListener() {
        verifyIVTextFieldInput(ivStaminaPokemonOne);
    }

    public void ivAttackPokemonTwoListener() {
        verifyIVTextFieldInput(ivAttackPokemonTwo);
    }

    public void ivDefensePokemonTwoListener() {
        verifyIVTextFieldInput(ivDefensePokemonTwo);
    }

    public void ivStaminaPokemonTwoListener() {
        verifyIVTextFieldInput(ivStaminaPokemonTwo);
    }

    private void loadImage(ComboBox<String> pokemonNameListComboBox2, ImageView pokemonImageView) {
        String pokemonName = pokemonNameListComboBox2.getSelectionModel().getSelectedItem().toLowerCase();
        File file = new File("src/main/resources/images/" + pokemonName + ".png");
        Image image = new Image(file.toURI().toString());
        pokemonImageView.setImage(image);
    }

    private void verifyIVTextFieldInput(TextField ivTextField) {
        ivTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Integer parsedInt = Integer.parseInt(newValue);

                if (parsedInt > 15 || parsedInt < 1 || parsedInt == null) {
                    ivTextField.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                    //error message
                } else {
                    ivTextField.setStyle(" -fx-border-color: black;");
                }
            } catch (NumberFormatException e) {
                ivTextField.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                // error message
            }
        });
    }

    private void verifyLevelTextFieldInput(TextField levelTextField) {
        levelTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Double parsedDouble = Double.parseDouble(newValue);

                if (parsedDouble > 40 || parsedDouble < 1) {
                    levelTextField.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                    //error message
                } else {
                    levelTextField.setStyle(" -fx-border-color: black;");
                }
            } catch (NumberFormatException e) {
                levelTextField.setStyle("-fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: red;");
                // error message
            }
        });
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
