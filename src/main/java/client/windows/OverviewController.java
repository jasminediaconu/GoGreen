package client.windows;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController extends Controller implements Initializable {

    @FXML
    private CategoryAxis x;
    List<JFXButton> badges;
    private ActionEvent event;
    @FXML
    Pane overview;
    @FXML private StackPane popup;
    @FXML private ScrollPane scrollBadges;
    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;
    @FXML private LineChart<String, Integer> lineChart;
    private JFXButton button;
    private JFXDialog dialog;
    @FXML private JFXButton aabutton1;
    @FXML private JFXButton aabutton2;
    @Override
    public void update() {
    }

    // Parameter: badge id (to be implemented)
    public void popupMessage() {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        Text heading = new Text("Solar panel");
        dialogLayout.setHeading(heading);

        JFXButton close = new JFXButton("Close");
        String css = "-fx-border-color:#95e743;-fx-border-radius:2;-fx-background-color:#ecffe6";
        close.setStyle(css);

        close.setOnMouseClicked(e -> dialog.close());
        String message = "Install your first solar panel.";
        dialogLayout.setBody(new Text(message), close);

        dialog = new JFXDialog(popup, dialogLayout, JFXDialog.DialogTransition.CENTER);
        dialogLayout.setActions(close);
        dialog.show();
        dialog.setOverlayClose(false);
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
        x.setCategories(FXCollections.observableArrayList("Monday","Friday","Saturday"));


        XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
        series.getData().add(new XYChart.Data<String, Integer>("July",3));
        series.getData().add(new XYChart.Data<String, Integer>("August",1232));
        series.getData().add(new XYChart.Data<String, Integer>("July",233));
        aabutton2.setOnMouseClicked(a -> lineChart.getData().add(series1));
        series.setName("Month");



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

        button.setStyle("-fx-opacity: 100%;");

        button.setOnMouseClicked(e -> popupMessage());

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
