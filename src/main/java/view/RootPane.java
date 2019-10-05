package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {

    private TabPane tabPane = new TabPane();
    private ProfilePane profilePane = new ProfilePane();

    public RootPane() {
        Tab profileTab = new Tab("Profile", profilePane);
        Tab dashboard = new Tab("Dashboard");

        tabPane.getTabs().addAll(profileTab, dashboard);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.setCenter(tabPane);
    }
}
