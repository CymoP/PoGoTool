package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import utils.BattleSimulatorReport;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * BattleSimulatorReportController is the class for controlling user interactions on the battle simulator report window to the model
 */
public class BattleSimulatorReportController implements Initializable {

    @FXML
    private TextArea reportText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportText.setText(BattleSimulatorReport.getInstance().getOutput());
    }
}
