package client.windows;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController extends Controller implements Initializable {

    List<JFXButton> badges;

<<<<<<< HEAD
    @FXML
    Pane overview;
    @FXML
    LineChart<String, Integer> lineChart;
    @FXML private StackPane popup;
=======
    @FXML Pane overview;
    @FXML private Pane popup;
>>>>>>> 6156386e9ecfc070c6ed14bee2ce26143ef5e014
    @FXML private ScrollPane scrollBadges;
    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;
    private JFXButton button;
<<<<<<< HEAD
    private JFXDialog dialog;
    @FXML private JFXButton aabutton1;
    @FXML private JFXButton aabutton11;
    @FXML private JFXButton aabutton2;
=======
    private PopOver popOver = new PopOver();

>>>>>>> 6156386e9ecfc070c6ed14bee2ce26143ef5e014
    @Override
    public void update() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lineChart.getData().clear();
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        series.getData().add(new XYChart.Data<String, Integer>("Monday",200));
        series.getData().add(new XYChart.Data<String, Integer>("Friday",123));
        series.getData().add(new XYChart.Data<String, Integer>("Saturday",23));
        aabutton1.setOnMouseClicked(e -> lineChart.getData().add(series));
        series.setName("Week");



        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10, 10, 10, 15));

        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();

        badges = new ArrayList<>();

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
}
