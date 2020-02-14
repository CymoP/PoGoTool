package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import utils.BattleSimulatorReport;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleSimulatorReportController implements Initializable {

    @FXML
    private TextArea reportText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportText.setText(BattleSimulatorReport.getInstance().getOutput());
    }
}
