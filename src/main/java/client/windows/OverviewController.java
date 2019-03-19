package client.windows;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController extends Controller implements Initializable {

    List<JFXButton> badges;

    @FXML
    Pane overview;

    @FXML
    private ScrollPane scrollBadges;

    private VBox badgesBox;
    private HBox badgesColumn;

    @Override
    public void update() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(20, 0, 0, 20));

        badgesColumn = new HBox();

        badges = new ArrayList<>();

        String path = "/client/windows/images/badges/";
        JFXButton button = new JFXButton("", new ImageView(path + "solar-panel.png"));

        badges.add(button);


        for (int i = 0; i < badges.size(); i++ ) {
            badgesColumn.getChildren().add(i, badges.get(i));
        }

        badgesBox.getChildren().add(badgesColumn);
        scrollBadges.setContent(badgesBox);
    }
}
