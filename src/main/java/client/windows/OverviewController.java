
package client.windows;

import client.Main;
import client.objects.Activity;
import client.user.Achievement;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class OverviewController extends Controller implements Initializable {

    @FXML  private BarChart<String, Integer> barChart;
    @FXML private JFXButton aabutton1;
    @FXML private  JFXButton aabutton2;
    @FXML private JFXButton clearGraph;
    @FXML private CategoryAxis x;

    @FXML
    LineChart<String, Integer> lineChart;
    @FXML
    Pane overview;

    @FXML
    private Pane badgePopup;
    @FXML
    public Text title = new Text();
    @FXML
    public Text description = new Text();
    @FXML
    private ScrollPane scrollBadges = new ScrollPane();

    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;
    private JFXButton button;

    private PopOver popOver = new PopOver();
    List<JFXButton> badges = new ArrayList<>();
    private List<Achievement> achievementList;

    @Override
    public void update() {
        //title.setText(achievementList.get(index).getTitle());
        //description.setText(achievementList.get(index).getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        barChart.getData().clear();
//        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
//        series.getData().add(new XYChart.Data<String, Integer>("Monday",50));
//        series.getData().add(new XYChart.Data<String, Integer>("Tuesday",100));
//        series.getData().add(new XYChart.Data<String, Integer>("Wednesday",300));
//        series.getData().add(new XYChart.Data<String, Integer>("Thursday",123));
//        series.getData().add(new XYChart.Data<String, Integer>("Friday",123));
//        series.getData().add(new XYChart.Data<String, Integer>("Saturday",400));
//        series.getData().add(new XYChart.Data<String, Integer>("Sunday",321));
//        aabutton1.setOnMouseClicked(e ->barChart.getData().addAll(series));
//        series.setName("Week");
//
//        clearGraph.setOnMouseClicked(d ->barChart.getData().clear());
//
//        XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
//        series1.getData().add(new XYChart.Data<String, Integer>("January", 430));
//        series1.getData().add(new XYChart.Data<String, Integer>("February", 121));
//        series1.getData().add(new XYChart.Data<String, Integer>("March", 322));
//        series1.getData().add(new XYChart.Data<String, Integer>("April", 350));
//        series1.getData().add(new XYChart.Data<String, Integer>("May", 113));
//        series1.getData().add(new XYChart.Data<String, Integer>("June", 13));
//        series1.getData().add(new XYChart.Data<String, Integer>("July", 663));
//        series1.getData().add(new XYChart.Data<String, Integer>("August", 64));
//        series1.getData().add(new XYChart.Data<String, Integer>("September", 100));
//        series1.getData().add(new XYChart.Data<String, Integer>("October", 0));
//        series1.getData().add(new XYChart.Data<String, Integer>("November", 999));
//        series1.getData().add(new XYChart.Data<String, Integer>("December", 1));
//        aabutton2.setOnMouseClicked(q -> barChart.getData().addAll(series1));
//        series1.setName("Month");

        //x.setCategories(FXCollections.observableArrayList());

        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10, 10, 10, 15));

        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();

        loadAchievements();

        String path = "/client/windows/images/badges/";
        for (int i = 0; i < achievementList.size(); i++) {
            int ii = i;
            button = new JFXButton("", new ImageView(path + achievementList.get(i).getPath() + ".png"));
            badges.add(button);
            badges.get(i).setOnMouseEntered(e -> popupBadges(badges.get(ii), ii));
        }

        title.setText(achievementList.get(1).getTitle());
        description.setText(achievementList.get(1).getDescription());
        description.setText(achievementList.get(0).getDescription());
        // This checks if the badges are unlocked or not
        Badges.badge1(badges.get(0));
        Badges.badge2(badges.get(1));
        Badges.badge3(badges.get(2));
        Badges.badge4(badges.get(3));
        Badges.badge5(badges.get(4));
        Badges.badge6(badges.get(5));
        Badges.badge7(badges.get(6));
        Badges.badge8(badges.get(7));
        Badges.badge9(badges.get(8));
        Badges.badge10(badges.get(9));
        Badges.badge11(badges.get(10));
        Badges.badge12(badges.get(11));
        Badges.badge13(badges.get(12));
        Badges.badge14(badges.get(13));
        Badges.badge15(badges.get(14));

        // This adds the badges to the different rows of the VBOX
        for (int i = 0; i < 5; i++) {
            row.getChildren().add(i, badges.get(i));
            row2.getChildren().add(i, badges.get(i + 5));
            row3.getChildren().add(i, badges.get(i + 10));
        }

        badgesBox.getChildren().add(row);
        badgesBox.getChildren().add(row2);
        badgesBox.getChildren().add(row3);

        scrollBadges.setContent(badgesBox);
    }

    @FXML
    public void popupBadges(JFXButton btn, int index) {
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
            popOver.show(btn);
        }
    }

    /**
     * Loads the achivements in the list.
     */
    private void loadAchievements() {
        //Clears everything in the observable list
        achievementList = Main.achievements.stream().collect(Collectors.toList());
    }

    /**
     * This function will make a hashmap sorted by LocalDate or week or month.
     *
     * @param activityList List<Activity> type
     * @param period String type
     *
     * @return a HashMap<String, Double> with the correct values for the graph
     */
    private HashMap<String, Double> mapActivitiesToGraph(List<Activity> activityList, String period) {
        HashMap<String, Double> result = new HashMap<String, Double>();
        for (Activity activity:activityList) {
            String key = activity.getDate().toString();
            double value = Main.items.get(activity.getItemID()-1).getCo2();
            if (period.equals("m")) {
                key = "Week " + activity.getDate().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            } else if (period.equals("y") || period.equals("h")) {
                key = activity.getDate().getMonth().name();
            }

            if (!result.containsKey(key)) {
                result.put(key, value);
            } else {
                result.put(key, result.get(key) + value);
            }
        }
        return result;
    }
}
