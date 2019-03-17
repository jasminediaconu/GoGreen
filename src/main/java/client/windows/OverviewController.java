package client.windows;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController extends Controller implements Initializable {

    @FXML
    Pane overview;

    @FXML
    private ScrollPane scrollBadges;

    private VBox badgesBox;
    private HBox badgesColumn;

    List<JFXButton> badges;


    @Override
    public void update() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final Tooltip tooltip = new Tooltip();
        final Tooltip tooltipDisable = new Tooltip();

        tooltip.setText("You logged in 5 days in a row!");
        tooltipDisable.setText("You don't have this badge yet.");

        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(20, 0, 0, 20));

        badgesColumn = new HBox();

        badges = new ArrayList<>();

        String path= "/client/windows/images/badges/";
        JFXButton button = new JFXButton("", new ImageView(path + "hands.png"));
        JFXButton button2 = new JFXButton("", new ImageView(path + "energy.png"));
        JFXButton button3 = new JFXButton("", new ImageView(path + "medal.png"));
        JFXButton button4 = new JFXButton("", new ImageView(path + "sprout.png"));

        button2.setTooltip(tooltip);
        button3.setTooltip(tooltipDisable);
        button4.setTooltip(tooltip);

        button3.setDisable(true);

        badges.add(button);
        badges.add(button2);
        badges.add(button3);
        badges.add(button4);

        for(int i=0; i<badges.size(); i++ ) {
            badgesColumn.getChildren().add(i, badges.get(i));
        }

        badgesBox.getChildren().add(badgesColumn);
        scrollBadges.setContent(badgesBox);
    }
}
