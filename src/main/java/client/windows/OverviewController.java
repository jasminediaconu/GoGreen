package client.windows;

import client.Main;
import client.ServerRequests;
import client.user.Achievement;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OverviewController extends Controller implements Initializable {

    List<JFXButton> badges = new ArrayList<>();
    List<Achievement> achievementList = new ArrayList<>();

    @FXML Pane overview;

    @FXML
    private Pane badgePopup;

    @FXML private Text title = new Text();
    @FXML private Text description = new Text();

    @FXML
    private ScrollPane scrollBadges = new ScrollPane();
    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;

    private JFXButton button;
    private PopOver popOver = new PopOver();

    @Override
    public void update() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10, 10, 10, 15));

        loadAchievements();

        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();

        String path = "/client/windows/images/badges/";
        for(int i = 0; i < achievementList.size(); i++) {
            int ii = i;
            button = new JFXButton("", new ImageView(path + achievementList.get(i).getPath() + ".png"));
            title.setText(achievementList.get(i).getTitle());
            description.setText(achievementList.get(i).getDescription());
            button.setOnMouseEntered(e -> popupBadges());
            badges.add(button);
        }

        //button.setStyle("-fx-opacity: 100%;");

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

    @FXML
    public void popupBadges() {
        String path = "/client/windows/fxml/popup.fxml";

        try {
            badgePopup = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(popOver.isShowing())) {
            popOver = new PopOver(badgePopup);
            popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
            popOver.setDetachable(false);
            popOver.show(button);
        }
    }

    /**
     * Loads the achivements in the list.
     */
    private void loadAchievements() {
        //Clears everything in the observable list
        achievementList = Main.achievements.stream().collect(Collectors.toList());
    }
}
