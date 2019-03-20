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
    private HBox row;
    private HBox row2;
    private HBox row3;

    @Override
    public void update() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10, 10, 10, 15));

        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();

        badges = new ArrayList<>();

        String path = "/client/windows/images/badges/";
        JFXButton button = new JFXButton("", new ImageView(path + "solar-panel.png"));
        JFXButton button2 = new JFXButton("", new ImageView(path + "solar-panels.png"));
        JFXButton button3 = new JFXButton("", new ImageView(path + "burger.png"));
        JFXButton button4 = new JFXButton("", new ImageView(path + "vegetarian.png"));
        JFXButton button5 = new JFXButton("", new ImageView(path + "stalker.png"));
        JFXButton button6 = new JFXButton("", new ImageView(path + "top10.png"));
        JFXButton button7 = new JFXButton("", new ImageView(path + "thermometer.png"));
        JFXButton button8 = new JFXButton("", new ImageView(path + "travel.png"));
        JFXButton button9 = new JFXButton("", new ImageView(path + "activity.png"));
        JFXButton button10 = new JFXButton("", new ImageView(path + "one.png"));
        JFXButton button11 = new JFXButton("", new ImageView(path + "progress.png"));
        JFXButton button12 = new JFXButton("", new ImageView(path + "bronze-medal.png"));
        JFXButton button13 = new JFXButton("", new ImageView(path + "silver-medal.png"));
        JFXButton button14 = new JFXButton("", new ImageView(path + "gold-medal.png"));
        JFXButton button15 = new JFXButton("", new ImageView(path + "nomad.png"));

        badges.add(button);
        badges.add(button2);
        badges.add(button3);
        badges.add(button4);
        badges.add(button5);
        badges.add(button6);
        badges.add(button7);
        badges.add(button8);
        badges.add(button9);
        badges.add(button10);
        badges.add(button11);
        badges.add(button12);
        badges.add(button13);
        badges.add(button14);
        badges.add(button15);

        button.setStyle("-fx-opacity: 100%;");

        // This adds the badges to the different rows of the VBOX
        for(int i = 0; i < 5; i++){
            row.getChildren().add(i, badges.get(i));
            row2.getChildren().add(i, badges.get(i+5));
            row3.getChildren().add(i, badges.get(i+10));
        }

        badgesBox.getChildren().add(row);
        badgesBox.getChildren().add(row2);
        badgesBox.getChildren().add(row3);

        scrollBadges.setContent(badgesBox);
    }
}
