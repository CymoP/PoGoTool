package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.ChargedMove;
import model.FastMove;
import model.Pokemon;
import model.SelectedPokemon;
import services.MoveService;
import services.PokemonService;
import services.SelectedPokemonService;
import utils.BattleSimulator;
import utils.BattleSimulatorReport;
import utils.ColourChooser;

import java.io.File;
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
    public ToggleGroup pokemonOneShields;

    @FXML
    public ToggleGroup pokemonTwoShields;

    @FXML
    public Tab summaryTab;

    @FXML
    public Tab graphicTab;

    @FXML
    public Tab fullLogTab;

    @FXML
    public TextArea fullLogTextArea;

    @FXML
    public TextArea summaryTextArea;

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
    protected void handleSimulateButtonAction() {
        SelectedPokemon opponentOne = buildSelectedPokemon(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox, pokemonOneShields);
        SelectedPokemon opponentTwo = buildSelectedPokemon(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox, pokemonTwoShields);

        battleSimulator.simulatorAlgorithm(opponentOne, opponentTwo);
        summaryTextArea.setText(battleSimulatorReport.getSummary());
        fullLogTextArea.setText(battleSimulatorReport.getOutput());
    }

    /**
     * Loads all the base data regarding the selected pokemon in position 1
     */
    @FXML
    public void handleLoadPokemonOneData() {
        loadPokemonMoveSetData(pokemonOnePokemonListComboBox, pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        loadPokemonImage(pokemonOnePokemonListComboBox, pokemonOneImageView);
        setPokemonComboBoxColourByType(pokemonOnePokemonListComboBox);
        autoSelectFirstMovesetInList(pokemonOneFastMoveListComboBox, pokemonOneChargedMoveListComboBox);
        setDefaultPokemonSummaryValues(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneAttackStatText, pokemonOneDefenseStatText, pokemonOneStaminaStatText, pokemonOneCPText);
    }

    /**
     * Loads all the base data regarding the selected pokemon in position 2
     */
    @FXML
    public void handleLoadPokemonTwoData() {
        loadPokemonMoveSetData(pokemonTwoPokemonListComboBox, pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);
        loadPokemonImage(pokemonTwoPokemonListComboBox, pokemonTwoImageView);
        setPokemonComboBoxColourByType(pokemonTwoPokemonListComboBox);
        autoSelectFirstMovesetInList(pokemonTwoFastMoveListComboBox, pokemonTwoChargedMoveListComboBox);
        setDefaultPokemonSummaryValues(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoAttackStatText, pokemonTwoDefenseStatText, pokemonTwoStaminaStatText, pokemonTwoCPText);
    }

    /**
     * Changes the colour of the fast move combobox for position 1
     */
    @FXML
    public void handlePokemonOneFastMoveComboBoxColor() {
        setFastMoveComboBoxColourByType(pokemonOneFastMoveListComboBox);
    }

    /**
     * Changes the colour of the charged move combobox for position 1
     */
    @FXML
    public void handlePokemonOneChargedMoveComboBoxColor() {
        setChargedMoveComboBoxColourByType(pokemonOneChargedMoveListComboBox);
    }

    /**
     * Changes the colour of the fast move combobox for position 2
     */
    @FXML
    public void handlePokemonTwoFastMoveComboBoxColor() {
        setFastMoveComboBoxColourByType(pokemonTwoFastMoveListComboBox);
    }

    /**
     * Changes the colour of the charged move combobox for position 2
     */
    @FXML
    public void handlePokemonTwoChargedMoveComboBoxColor() {
        setChargedMoveComboBoxColourByType(pokemonTwoChargedMoveListComboBox);
    }

    /**
     * Listener for the level input field for position 1
     */
    @FXML
    public void levelPokemonOneFieldListener() {
        verifyLevelTextFieldInput(pokemonOneLevelTextField);
    }

    /**
     * Listener for the level input field for position 2
     */
    @FXML
    public void levelPokemonTwoFieldListener() {
        verifyLevelTextFieldInput(pokemonTwoLevelTextField);
    }

    /**
     * Listener for the attack IV input field for position 1
     */
    @FXML
    public void ivAttackPokemonOneListener() {
        verifyIVTextFieldInput(pokemonOneAttackIVTextField);
    }

    /**
     * Listener for the defense IV input field for position 1
     */
    @FXML
    public void ivDefensePokemonOneListener() {
        verifyIVTextFieldInput(pokemonOneDefenseIVTextField);
    }

    /**
     * Listener for the stamina IV input field for position 1
     */
    @FXML
    public void ivStaminaPokemonOneListener() {
        verifyIVTextFieldInput(pokemonOneStaminaIVTextField);
    }

    /**
     * Listener for the attack IV input field for position 2
     */
    @FXML
    public void ivAttackPokemonTwoListener() {
        verifyIVTextFieldInput(pokemonTwoAttackIVTextField);
    }

    /**
     * Listener for the defense IV input field for position 2
     */
    @FXML
    public void ivDefensePokemonTwoListener() {
        verifyIVTextFieldInput(pokemonTwoDefenseIVTextField);
    }

    /**
     * Listener for the stamina IV input field for position 2
     */
    @FXML
    public void ivStaminaPokemonTwoListener() {
        verifyIVTextFieldInput(pokemonTwoStaminaIVTextField);
    }

    /**
     * Listener for the pokemon attack stat in the pokemon details titlepane for position 1
     */
    @FXML
    public void pokemonOneAttackStatListener() {
        updateAttackText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneAttackStatText);
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    /**
     * Listener for the pokemon defense stat in the pokemon details titlepane for position 1
     */
    @FXML
    public void pokemonOneDefenseStatListener() {
        updateDefenseText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneDefenseStatText);
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    /**
     * Listener for the pokemon stamina stat in the pokemon details titlepane for position 1
     */
    @FXML
    public void pokemonOneStaminaStatListener() {
        updateStaminaText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneStaminaStatText);
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    /**
     * Listener for the pokemon attack stat in the pokemon details titlepane for position 2
     */
    @FXML
    public void pokemonTwoAttackStatListener() {
        updateAttackText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoAttackStatText);
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    /**
     * Listener for the pokemon defense stat in the pokemon details titlepane for position 2
     */
    @FXML
    public void pokemonTwoDefenseStatListener() {
        updateDefenseText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoDefenseStatText);
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    /**
     * Listener for the pokemon stamina stat in the pokemon details titlepane for position 2
     */
    @FXML
    public void pokemonTwoStaminaStatListener() {
        updateStaminaText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoStaminaStatText);
        updateCPText(pokemonTwoPokemonListComboBox, pokemonTwoLevelTextField, pokemonTwoAttackIVTextField, pokemonTwoDefenseIVTextField, pokemonTwoStaminaIVTextField, pokemonTwoCPText);
    }

    /**
     * Listener for the pokemon cp field in the pokemon details titlepane for position 1
     */
    @FXML
    public void pokemonOneLevelListener() {
        updateCPText(pokemonOnePokemonListComboBox, pokemonOneLevelTextField, pokemonOneAttackIVTextField, pokemonOneDefenseIVTextField, pokemonOneStaminaIVTextField, pokemonOneCPText);
    }

    /**
     * Listener for the pokemon cp field in the pokemon details titlepane for position 2
     */
    @FXML
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

    private SelectedPokemon buildSelectedPokemon(ComboBox pokemonNameListComboBox, TextField levelPokemonTextField, TextField ivAttackTextField, TextField ivDefenseTextField, TextField ivStaminaTextField, ComboBox pokemonFastMoveListComboBox, ComboBox pokemonChargedMoveListComboBox, ToggleGroup pokemonShields) {
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
        RadioButton selectedButton = (RadioButton) pokemonShields.getSelectedToggle();
        int shieldCount = Integer.parseInt(selectedButton.getText());

        return new SelectedPokemon(selectedPokemon, selectPokemonLevel, selectedPokemonIVAttack, selectedPokemonIVDefense, selectedPokemonIVStamina, selectedPokemonFastMove, selectedPokemonChargedMove, shieldCount);
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
                int parsedInt = Integer.parseInt(newValue);

                if (parsedInt > 15 || parsedInt < 0) {
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
                double parsedDouble = Double.parseDouble(newValue);

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
