package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import utils.ColourChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * BattleSimulatorController is the class for controlling user interactions on the battle simulator window to the model
 */
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
    private ComboBox<String> pokemonOnePokemonListComboBox;

    @FXML
    private ComboBox<String> pokemonTwoPokemonListComboBox;

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
    private ColourChooser colourChooser = ColourChooser.getInstance();

    private static final String POKEMON_IMAGES_FILE_PATH = "src/main/resources/images/";
    private static final String COLOUR_BLACK_HEXCODE = "e1e1e1";

    public BattleSimulatorController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPokemonNameData();
    }

    @FXML
    protected void handleSimulateButtonAction() throws IOException {
        SelectedPokemon opponentOne = buildSelectedPokemon(pokemonOnePokemonListComboBox, levelPokemonOne, ivAttackPokemonOne, ivDefensePokemonOne, ivStaminaPokemonOne, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        SelectedPokemon opponentTwo = buildSelectedPokemon(pokemonTwoPokemonListComboBox, levelPokemonTwo, ivAttackPokemonTwo, ivDefensePokemonTwo, ivStaminaPokemonTwo, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);

        battleSimulator.simulatorAlgorithm(opponentOne, opponentTwo);

        NavigationService.gotoBattleReport();
    }

    public void handleLoadPokemonOneData() {
        loadPokemonMoveSetData(pokemonOnePokemonListComboBox, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        loadPokemonImage(pokemonOnePokemonListComboBox, pokemonOneImageView);
        setPokemonComboBoxColourByType(pokemonOnePokemonListComboBox);
    }

    public void handleLoadPokemonTwoData() {
        loadPokemonMoveSetData(pokemonTwoPokemonListComboBox, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);
        loadPokemonImage(pokemonTwoPokemonListComboBox, pokemonTwoImageView);
        setPokemonComboBoxColourByType(pokemonTwoPokemonListComboBox);
    }

    public void handlePokemonOneFastMoveComboBoxColor() {
        setFastMoveComboBoxColourByType(pokemonOneFastMoveListComboBox);
    }

    public void handlePokemonOneChargedMoveComboBoxColor() {
        setChargedMoveComboBoxColourByType(pokemonOneChargedMoveListComboBox);
    }

    public void handlePokemonTwoFastMoveComboBoxColor() {
        setFastMoveComboBoxColourByType(pokemonTwoFastMoveListComboBox);
    }

    public void handlePokemonTwoChargedMoveComboBoxColor() {
        setChargedMoveComboBoxColourByType(pokemonTwoChargedMoveListComboBox);
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

    private void loadPokemonImage(ComboBox pokemonListComboBox, ImageView pokemonImageView) {
        Pokemon pokemon = (Pokemon) pokemonListComboBox.getSelectionModel().getSelectedItem();
        String pokemonName = pokemon.getPokemonName().toLowerCase();

        File file = new File(POKEMON_IMAGES_FILE_PATH + pokemonName + ".png");
        Image image = new Image(file.toURI().toString());
        pokemonImageView.setImage(image);
    }

    private SelectedPokemon buildSelectedPokemon(ComboBox pokemonNameListComboBox, TextField levelPokemonTextField, TextField ivAttackTextField, TextField ivDefenseTextField, TextField ivStaminaTextField, ComboBox pokemonFastMoveListComboBox, ComboBox pokemonChargedMoveListComboBox) {
        Pokemon pokemon = (Pokemon) pokemonNameListComboBox.getSelectionModel().getSelectedItem();
        Pokemon selectedPokemon = pokemonService.getPokemonByName(pokemon.getPokemonName());
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
        ObservableList pokemonNameList = FXCollections.observableList(pokemonService.getPokemonList());

        pokemonOnePokemonListComboBox.getItems().clear();
        pokemonTwoPokemonListComboBox.getItems().clear();
        pokemonOnePokemonListComboBox.getItems().addAll(pokemonNameList);
        pokemonTwoPokemonListComboBox.getItems().addAll(pokemonNameList);
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

    private void loadPokemonMoveSetData(ComboBox pokemonListComboBox, ComboBox pokemonFastMoveListComboBox, ComboBox pokemonChargedMoveListComboBox) {
        Pokemon pokemon = (Pokemon) pokemonListComboBox.getSelectionModel().getSelectedItem();

        ObservableList pokemonFastMoveNameList = FXCollections.observableList(pokemon.getFastMoveList());
        ObservableList pokemonChargedMoveNameList = FXCollections.observableList(pokemon.getChargedMoveList());

        pokemonFastMoveListComboBox.getItems().clear();
        pokemonChargedMoveListComboBox.getItems().clear();
        pokemonFastMoveListComboBox.getItems().addAll(pokemonFastMoveNameList);
        pokemonChargedMoveListComboBox.getItems().addAll(pokemonChargedMoveNameList);
    }

    private void setFastMoveComboBoxColourByType(ComboBox fastMoveComboBox) {
        FastMove fastMove = (FastMove) fastMoveComboBox.getSelectionModel().getSelectedItem();
        String colour;

        if (fastMove != null) {
            colour = colourChooser.chooseColour(fastMove.getType().getTypeName());
        } else {
            colour = COLOUR_BLACK_HEXCODE;
        }
        String style = "-fx-background-color: " + colour;

        fastMoveComboBox.setStyle(style);
    }

    private void setChargedMoveComboBoxColourByType(ComboBox chargedMoveComboBox) {
        ChargedMove chargedMove = (ChargedMove) chargedMoveComboBox.getSelectionModel().getSelectedItem();
        String colourHexcode;

        if (chargedMove != null) {
            colourHexcode = colourChooser.chooseColour(chargedMove.getType().getTypeName());
        } else {
            colourHexcode = COLOUR_BLACK_HEXCODE;
        }

        String style = "-fx-background-color: " + colourHexcode;

        chargedMoveComboBox.setStyle(style);
    }

    private void setPokemonComboBoxColourByType(ComboBox pokemonComboBox) {
        Pokemon pokemon = (Pokemon) pokemonComboBox.getSelectionModel().getSelectedItem();
        String colour;

        if (pokemon != null) {
            colour = colourChooser.chooseColour(pokemon.getPokemonType().getTypeName());
        } else {
            colour = COLOUR_BLACK_HEXCODE;
        }

        String style = "-fx-background-color: " + colour;

        pokemonComboBox.setStyle(style);
    }
}
