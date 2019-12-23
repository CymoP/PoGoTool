package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {

    private TabPane tabPane;
    private ProfilePane profilePane;

    public RootPane() {
        tabPane = new TabPane();
        profilePane = new ProfilePane();

        Tab profileTab = new Tab("Profile", profilePane);
        Tab dashboard = new Tab("Dashboard");

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(profileTab, dashboard);

        this.setCenter(tabPane);
    }

    public ProfilePane getProfilePane(){
        return profilePane;
    }

    public void changeTab(int index){
        tabPane.getSelectionModel().select(index);
    }
}
