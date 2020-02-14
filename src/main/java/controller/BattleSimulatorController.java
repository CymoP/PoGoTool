package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ChargedMove;
import model.FastMove;
import model.Pokemon;
import model.SelectedPokemon;
import services.MoveService;
import services.NavigationService;
import services.PokemonService;
import utils.BattleSimulator;

import java.io.File;
import java.io.IOException;
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
    public Button simulateButton;

    @FXML
    private ComboBox<String> pokemonOneNameListComboBox;

    @FXML
    private ComboBox<String> pokemonTwoNameListComboBox;

    @FXML
    public ComboBox pokemonOneFastMoveListComboBox;

    @FXML
    public ComboBox pokemonTwoFastMoveListComboBox;

    @FXML
    public ComboBox pokemonOneChargedMoveListComboBox;

    @FXML
    public ComboBox pokemonTwoChargedMoveListComboBox;

    private PokemonService pokemonService = new PokemonService();
    private BattleSimulator battleSimulator = new BattleSimulator();
    private MoveService moveService = new MoveService();

    public BattleSimulatorController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPokemonNameData();
    }

    @FXML
    protected void handleSimulateButtonAction() throws IOException {
        SelectedPokemon opponentOne = buildSelectedPokemon(pokemonOneNameListComboBox, levelPokemonOne, ivAttackPokemonOne, ivDefensePokemonOne, ivStaminaPokemonOne, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        SelectedPokemon opponentTwo = buildSelectedPokemon(pokemonTwoNameListComboBox, levelPokemonTwo, ivAttackPokemonTwo, ivDefensePokemonTwo, ivStaminaPokemonTwo, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);

        battleSimulator.simulatorAlgorithm(opponentOne, opponentTwo);

        NavigationService.gotoReport();
    }

    public void handleLoadPokemonOneData(ActionEvent actionEvent) throws SQLException {
        loadData(pokemonOneNameListComboBox, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        loadImage(pokemonOneNameListComboBox, pokemonOneImageView);
    }

    public void handleLoadPokemonTwoData(ActionEvent actionEvent) throws SQLException {
        loadData(pokemonTwoNameListComboBox, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);
        loadImage(pokemonTwoNameListComboBox, pokemonTwoImageView);
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

    private SelectedPokemon buildSelectedPokemon(ComboBox<String> pokemonNameListComboBox, TextField levelPokemonTextField, TextField ivAttackTextField, TextField ivDefenseTextField, TextField ivStaminaTextField, ComboBox pokemonFastMoveListComboBox, ComboBox pokemonChargedMoveListComboBox) {
        String pokemonName = pokemonNameListComboBox.getSelectionModel().getSelectedItem();
        Pokemon selectedPokemon = pokemonService.getPokemonByName(pokemonName);
        Double selectPokemonLevel = Double.parseDouble(levelPokemonTextField.getText());
        int selectedPokemonIVAttack = Integer.parseInt(ivAttackTextField.getText());
        int selectedPokemonIVDefense = Integer.parseInt(ivDefenseTextField.getText());
        int selectedPokemonIVStamina = Integer.parseInt(ivStaminaTextField.getText());
        String fastMoveName = pokemonFastMoveListComboBox.getSelectionModel().getSelectedItem().toString();
        FastMove selectedPokemonFastMove = moveService.getFastMoveDetailsByName(selectedPokemon, fastMoveName);
        String chargedMoveName = pokemonChargedMoveListComboBox.getSelectionModel().getSelectedItem().toString();
        ChargedMove selectedPokemonChargedMove = moveService.getChargedMoveDetailsByName(selectedPokemon, chargedMoveName);

        return new SelectedPokemon(selectedPokemon, selectPokemonLevel, selectedPokemonIVAttack, selectedPokemonIVDefense, selectedPokemonIVStamina, selectedPokemonFastMove, selectedPokemonChargedMove);
    }

    private void setPokemonNameData() {
        ObservableList pokemonNameList = FXCollections.observableList(pokemonService.getAllPokemonNamesList());

        pokemonOneNameListComboBox.getItems().clear();
        pokemonTwoNameListComboBox.getItems().clear();
        pokemonOneNameListComboBox.getItems().addAll(pokemonNameList);
        pokemonTwoNameListComboBox.getItems().addAll(pokemonNameList);
    }

    private void verifyIVTextFieldInput(TextField ivTextField) {
        ivTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Integer parsedInt = Integer.parseInt(newValue);

                if (parsedInt > 15 || parsedInt < 0 || parsedInt == null) {
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
