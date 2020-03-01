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
import javafx.scene.text.Text;
import model.ChargedMove;
import model.FastMove;
import model.Pokemon;
import model.SelectedPokemon;
import services.MoveService;
import services.NavigationService;
import services.PokemonService;
import services.SelectedPokemonService;
import utils.BattleSimulator;
import utils.BattleSimulatorReport;
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
    public TextField pokemonOneLevelTextField;

    @FXML
    public TextField pokemonOneAttackIVTextField;

    @FXML
    public TextField pokemonOneDefenseIVTextField;

    @FXML
    public TextField pokemonOneStaminaIVTextField;

    @FXML
    public TextField pokemonTwoLevelTextField;

    @FXML
    public TextField pokemonTwoAttackIVTextField;

    @FXML
    public TextField pokemonTwoDefenseIVTextField;

    @FXML
    public TextField pokemonTwoStaminaIVTextField;

    @FXML
    public ImageView pokemonOneImageView;

    @FXML
    public ImageView pokemonTwoImageView;

    @FXML
    public Text pokemonOneCPText;

    @FXML
    public Text pokemonOneAttackStatText;

    @FXML
    public Text pokemonOneDefenseStatText;

    @FXML
    public Text pokemonOneStaminaStatText;

    @FXML
    public Text pokemonTwoCPText;

    @FXML
    public Text pokemonTwoAttackStatText;

    @FXML
    public Text pokemonTwoDefenseStatText;

    @FXML
    public Text pokemonTwoStaminaStatText;

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

    @FXML
    public Button simulateButton;

    @FXML
    public Text winnerText;

    private PokemonService pokemonService = new PokemonService();
    private BattleSimulator battleSimulator = new BattleSimulator();
    private MoveService moveService = new MoveService();
    private ColourChooser colourChooser = ColourChooser.getInstance();
    private BattleSimulatorReport battleSimulatorReport = BattleSimulatorReport.getInstance();

    private static final String POKEMON_IMAGES_FILE_PATH = "src/main/resources/images/";
    private static final String COLOUR_BLACK_HEXCODE = "#e1e1e1";

    public BattleSimulatorController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPokemonNameData();
    }

    @FXML
    protected void handleSimulateButtonAction() throws IOException {
        SelectedPokemon opponentOne = buildSelectedPokemon(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        SelectedPokemon opponentTwo = buildSelectedPokemon(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);

        battleSimulator.simulatorAlgorithm(opponentOne, opponentTwo);
        winnerText.setText(battleSimulatorReport.getWinner());

        NavigationService.gotoBattleReport();
    }

    public void handleLoadPokemonOneData() {
        loadPokemonMoveSetData(pokemonOnePokemonListComboBox, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        loadPokemonImage(pokemonOnePokemonListComboBox, pokemonOneImageView);
        setPokemonComboBoxColourByType(pokemonOnePokemonListComboBox);
        autoSelectFirstMovesetInList(pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        setDefaultPokemonSummaryValues(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneAttackStatText, pokemonOneDefenseStatText, pokemonOneStaminaStatText, pokemonOneCPText);
    }

    public void handleLoadPokemonTwoData() {
        loadPokemonMoveSetData(pokemonTwoPokemonListComboBox, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);
        loadPokemonImage(pokemonTwoPokemonListComboBox, pokemonTwoImageView);
        setPokemonComboBoxColourByType(pokemonTwoPokemonListComboBox);
        autoSelectFirstMovesetInList(pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);
        setDefaultPokemonSummaryValues(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoAttackStatText, pokemonTwoDefenseStatText, pokemonTwoStaminaStatText, pokemonTwoCPText);
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
        verifyLevelTextFieldInput(pokemonOneLevelTextField);
    }

    public void levelPokemonTwoFieldListener() {
        verifyLevelTextFieldInput(pokemonTwoLevelTextField);
    }

    public void ivAttackPokemonOneListener() {
        verifyIVTextFieldInput(pokemonOneAttackIVTextField);
    }

    public void ivDefensePokemonOneListener() {
        verifyIVTextFieldInput(pokemonOneDefenseIVTextField);
    }

    public void ivStaminaPokemonOneListener() {
        verifyIVTextFieldInput(pokemonOneStaminaIVTextField);
    }

    public void ivAttackPokemonTwoListener() {
        verifyIVTextFieldInput(pokemonTwoAttackIVTextField);
    }

    public void ivDefensePokemonTwoListener() {
        verifyIVTextFieldInput(pokemonTwoDefenseIVTextField);
    }

    public void ivStaminaPokemonTwoListener() {
        verifyIVTextFieldInput(pokemonTwoStaminaIVTextField);
    }

    public void pokemonOneAttackStatListener() {
        updateAttackText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneAttackStatText);

        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    public void pokemonOneDefenseStatListener() {
        updateDefenseText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneDefenseStatText);
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    public void pokemonOneStaminaStatListener() {
        updateStaminaText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneStaminaStatText);
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    public void pokemonTwoAttackStatListener() {
        updateAttackText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoAttackStatText);
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    public void pokemonTwoDefenseStatListener() {
        updateDefenseText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoDefenseStatText);
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    public void pokemonTwoStaminaStatListener() {
        updateStaminaText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoStaminaStatText);
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    public void pokemonOneLevelListener() {
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    public void pokemonTwoLevelListener() {
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    private void loadPokemonImage(ComboBox pokemonListComboBox, ImageView pokemonImageView) {
        Pokemon pokemon = (Pokemon) pokemonListComboBox.getSelectionModel().getSelectedItem();
        String pokemonName = pokemon.getPokemonName().toLowerCase();

        File file = new File(POKEMON_IMAGES_FILE_PATH + pokemonName + ".png");
        Image image = new Image(file.toURI().toString());
        pokemonImageView.setImage(image);
    }

    private SelectedPokemon buildSelectedPokemon(ComboBox pokemonNameListComboBox, TextField levelPokemonTextField, TextField ivAttackTextField, TextField ivDefenseTextField, TextField ivStaminaTextField) {
        Pokemon pokemon = (Pokemon) pokemonNameListComboBox.getSelectionModel().getSelectedItem();
        Pokemon selectedPokemon = pokemonService.getPokemonByName(pokemon.getPokemonName());
        Double selectPokemonLevel = Double.parseDouble(levelPokemonTextField.getText());
        int selectedPokemonIVAttack = Integer.parseInt(ivAttackTextField.getText());
        int selectedPokemonIVDefense = Integer.parseInt(ivDefenseTextField.getText());
        int selectedPokemonIVStamina = Integer.parseInt(ivStaminaTextField.getText());

        return new SelectedPokemon(selectedPokemon, selectPokemonLevel, selectedPokemonIVAttack, selectedPokemonIVDefense, selectedPokemonIVStamina);
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
                ivTextField.setText("0");
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
                levelTextField.setText("1");
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

    private void autoSelectFirstMovesetInList(ComboBox pokemonFastMoveListComboBox, ComboBox pokemonChargedMoveListComboBox) {
        pokemonFastMoveListComboBox.getSelectionModel().selectFirst();
        pokemonChargedMoveListComboBox.getSelectionModel().selectFirst();
    }

    private void setDefaultPokemonSummaryValues(ComboBox<String> pokemonListComboBox, TextField pokemonLevelTextField, TextField pokemonAttackIVTextField, TextField pokemonDefenseIVTextField, TextField pokemonStaminaIVTextField, Text pokemonAttackStatText, Text pokemonDefenseStatText, Text pokemonStaminaStatText, Text pokemonCombatPowerText) {
        updateAttackText(pokemonListComboBox, pokemonLevelTextField, pokemonAttackIVTextField, pokemonDefenseIVTextField, pokemonStaminaIVTextField, pokemonAttackStatText);
        updateDefenseText(pokemonListComboBox, pokemonLevelTextField, pokemonAttackIVTextField, pokemonDefenseIVTextField, pokemonStaminaIVTextField, pokemonDefenseStatText);
        updateStaminaText(pokemonListComboBox, pokemonLevelTextField, pokemonAttackIVTextField, pokemonDefenseIVTextField, pokemonStaminaIVTextField, pokemonStaminaStatText);
        updateCPText(pokemonListComboBox, pokemonLevelTextField, pokemonAttackIVTextField, pokemonDefenseIVTextField, pokemonStaminaIVTextField, pokemonCombatPowerText);
    }

    private void updateCPText(ComboBox<String> pokemonOnePokemonListComboBox, TextField pokemonOneLevelTextField, TextField pokemonOneAttackIVTextField, TextField pokemonOneDefenseIVTextField, TextField pokemonOneStaminaIVTextField, Text pokemonOneCPText) {
        String combatPowerText = String.valueOf(SelectedPokemonService.getCombatPower(buildSelectedPokemon(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField)));
        pokemonOneCPText.setText(combatPowerText);
    }

    private void updateAttackText(ComboBox<String> pokemonOnePokemonListComboBox, TextField pokemonOneLevelTextField, TextField pokemonOneAttackIVTextField, TextField pokemonOneDefenseIVTextField, TextField pokemonOneStaminaIVTextField, Text pokemonOneAttackStatText) {
        String attackText = String.valueOf(SelectedPokemonService.getAttackStat(buildSelectedPokemon(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField)));
        pokemonOneAttackStatText.setText(attackText);
    }

    private void updateDefenseText(ComboBox<String> pokemonListComboBox, TextField pokemonLevelTextField, TextField pokemonAttackIVTextField, TextField pokemonDefenseIVTextField, TextField pokemonStaminaIVTextField, Text pokemonDefenseStatText) {
        String defenseText = String.valueOf(SelectedPokemonService.getDefenseStat(buildSelectedPokemon(pokemonListComboBox, pokemonLevelTextField, pokemonAttackIVTextField, pokemonDefenseIVTextField, pokemonStaminaIVTextField)));
        pokemonDefenseStatText.setText(defenseText);
    }

    private void updateStaminaText(ComboBox<String> pokemonTwoPokemonListComboBox, TextField pokemonTwoLevelTextField, TextField pokemonTwoAttackIVTextField, TextField pokemonTwoDefenseIVTextField, TextField pokemonTwoStaminaIVTextField, Text pokemonTwoStaminaStatText) {
        String staminaText = String.valueOf(SelectedPokemonService.getStaminaStat(buildSelectedPokemon(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField)));
        pokemonTwoStaminaStatText.setText(staminaText);
    }
}
