package client.windows;

import client.Main;
import client.ServerRequests;
import client.user.Achievement;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OverviewController extends Controller implements Initializable {

    List<JFXButton> badges;

    @FXML Pane overview;
    @FXML private Pane popup;
    @FXML private ScrollPane scrollBadges;
    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;

    private JFXButton button;
    private PopOver popOver = new PopOver();

    private String path;
    private BufferedImage image;
    private int goal;

    @Override
    public void update() {
    }

    public void showAchievements(List<Achievement> achievements) {
        for (Achievement badges : achievements){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10, 10, 10, 15));

        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();


        String path = "/client/windows/images/badges/";
        button = new JFXButton("", new ImageView(path + "solar-panel.png"));
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

        button.setOnMouseEntered(this::popupBadges);
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

    @FXML
    public void popupBadges(javafx.scene.input.MouseEvent event) {
        String path = "/client/windows/fxml/popup.fxml";

        try {
            //title = new Text("Get title");
            //description = new Text("Get message");
            popup = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //popOver = new PopOver(popup);
        //popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        //popOver.show(button);
        if (!(popOver.isShowing())) {
            popOver = new PopOver(popup);

            popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
            popOver.setDetachable(false);
            popOver.show(button);
        }
    }

    /**
     * applyBadges event.
     * Applies the badges to the Overview page, still needs a restart of the application.
     */
//    public void applyBadges(String badgeName) {
//        ServerRequests sv = new ServerRequests();
//        String path = Main.achievements.stream().filter(x -> x.getTitle().equals(badgeName)).collect(Collectors.toList()).get(0).getPath();
//        String path = Main.achievements.stream().filter(x -> x.getTitle().equals(badgeName)).collect(Collectors.toList()).get(0).getPath();
//        int badgeID = Main.achievements.stream().filter(x -> x.getTitle().equals(badgeName)).collect(Collectors.toList()).get(0).getId();
//        Achievement achievement = new Achievement (badgeID, "", "", ", ", 3 );
//
//
//                //refresh agenda
//
//        }
}
