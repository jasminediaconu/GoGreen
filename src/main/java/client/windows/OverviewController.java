
package client.windows;

import client.Main;
import client.ServerRequests;
import client.objects.Activity;
import client.user.Achievement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
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
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class OverviewController extends Controller implements Initializable {
    @FXML
    Pane overview;
    @FXML
    private Pane badgePopup;
    @FXML
    private Pane badgePopup2;
    @FXML
    private Pane badgePopup3;
    @FXML
    private Pane badgePopup4;
    @FXML
    private Pane badgePopup5;
    @FXML
    private Pane badgePopup6;
    @FXML
    private Pane badgePopup7;
    @FXML
    private Pane badgePopup8;
    @FXML
    private Pane badgePopup9;
    @FXML
    private Pane badgePopup10;
    @FXML
    private Pane badgePopup11;
    @FXML
    private Pane badgePopup12;
    @FXML
    private Pane badgePopup13;
    @FXML
    private Pane badgePopup14;
    @FXML
    private Pane badgePopup15;
    @FXML
    private Text title = new Text();
    @FXML
    private Text description = new Text();
    @FXML
    private Text title1 = new Text();
    @FXML
    private Text description1 = new Text();
    @FXML
    private Text title2 = new Text();
    @FXML
    private Text description2 = new Text();
    @FXML
    private Text title3 = new Text();
    @FXML
    private Text description3 = new Text();
    @FXML
    private Text title4 = new Text();
    @FXML
    private Text description4 = new Text();
    @FXML
    private Text title5 = new Text();
    @FXML
    private Text description5 = new Text();
    @FXML
    private Text title6 = new Text();
    @FXML
    private Text description6 = new Text();
    @FXML
    private Text title7 = new Text();
    @FXML
    private Text description7 = new Text();
    @FXML
    private Text title8 = new Text();
    @FXML
    private Text description8 = new Text();
    @FXML
    private Text title9 = new Text();
    @FXML
    private Text description9 = new Text();
    @FXML
    private Text title10 = new Text();
    @FXML
    private Text description10 = new Text();
    @FXML
    private Text title11 = new Text();
    @FXML
    private Text description11 = new Text();
    @FXML
    private Text title12 = new Text();
    @FXML
    private Text description12 = new Text();
    @FXML
    private Text title13 = new Text();
    @FXML
    private Text description13 = new Text();
    @FXML
    private Text title14 = new Text();
    @FXML
    private Text description14 = new Text();
    @FXML
    private Text progress = new Text();
    @FXML
    private Text progress1 = new Text();
    @FXML
    private Text progress2 = new Text();
    @FXML
    private Text progress3 = new Text();
    @FXML
    private Text progress4 = new Text();
    @FXML
    private Text progress5 = new Text();
    @FXML
    private Text progress6 = new Text();
    @FXML
    private Text progress7 = new Text();
    @FXML
    private Text progress8 = new Text();
    @FXML
    private Text progress9 = new Text();
    @FXML
    private Text progress10 = new Text();
    @FXML
    private Text progress11 = new Text();
    @FXML
    private Text progress12 = new Text();
    @FXML
    private Text progress13 = new Text();
    @FXML
    private Text progress14 = new Text();
    @FXML
    private ScrollPane scrollBadges = new ScrollPane();
    @FXML
    private BarChart<String, Double> barChart;


    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;
    private JFXButton button;
    private PopOver popOver = new PopOver();
    private List<JFXButton> badges = new ArrayList<>();
    private List<Achievement> achievementList
            = Main.achievements.stream().collect(Collectors.toList());
    private List<String> titleList = new ArrayList<>();
    private List<String> descriptionList = new ArrayList<>();
    @FXML
    private JFXComboBox<String> comboBox = new JFXComboBox<>();

    @Override
    public void update() {
        // This checks if the badges are unlocked or not
        ServerRequests sv = new ServerRequests();
        sv.getAchievements();
        updateProgress();
    }

    private void updateProgress() {
        progress.setText(Main.achievements.get(0).getProgress()
                + "/" + Main.achievements.get(0).getGoal());
        progress1.setText(Main.achievements.get(1).getProgress()
                + "/" + Main.achievements.get(1).getGoal());
        progress2.setText(Main.achievements.get(2).getProgress()
                + "/" + Main.achievements.get(2).getGoal());
        progress3.setText(Main.achievements.get(3).getProgress()
                + "/" + Main.achievements.get(3).getGoal());
        progress4.setText(Main.achievements.get(4).getProgress()
                + "/" + Main.achievements.get(4).getGoal());
        progress5.setText(Main.achievements.get(5).getProgress()
                + "/" + Main.achievements.get(5).getGoal());
        progress6.setText(Main.achievements.get(6).getProgress()
                + "/" + Main.achievements.get(6).getGoal());
        progress7.setText(Main.achievements.get(7).getProgress()
                + "/" + Main.achievements.get(7).getGoal());
        progress8.setText(Main.achievements.get(8).getProgress()
                + "/" + Main.achievements.get(8).getGoal());
        progress9.setText(Main.achievements.get(9).getProgress()
                + "/" + Main.achievements.get(9).getGoal());
        progress10.setText(Main.achievements.get(10).getProgress()
                + "/" + Main.achievements.get(10).getGoal());
        progress11.setText(Main.achievements.get(11).getProgress()
                + "/" + Main.achievements.get(11).getGoal());
        progress12.setText(Main.achievements.get(12).getProgress()
                + "/" + Main.achievements.get(12).getGoal());
        progress13.setText(Main.achievements.get(13).getProgress()
                + "/" + Main.achievements.get(13).getGoal());
        progress14.setText(Main.achievements.get(14).getProgress()
                + "/" + Main.achievements.get(14).getGoal());

        for (int i = 0; i < 15; i++) {
            if (Main.achievements.get(i).isAchieved()) {
                badges.get(i).setStyle("-fx-opacity: 100%;");
            }
        }
    }

    private void updateGraph(String period) {
        String sPeriod = "w";
        if (period.equals("Month")) {
            sPeriod = "m";
        } else if (period.equals("Half a year")) {
            sPeriod = "h";
        } else if (period.equals("Year")) {
            sPeriod = "y";
        }

        ServerRequests sv = new ServerRequests();
        List<Activity> activities = sv.retrieveActivities(sPeriod);

        //System.out.println("\n\n\n\n\n\n\nShowing graph data");
        //Iterator it = mapActivitiesToGraph(activities, period).entrySet().iterator();
        //while (it.hasNext()) {
        //  Map.Entry pair = (Map.Entry) it.next();
        //    System.out.println(pair.getKey() + " = " + pair.getValue());

        //    it.remove(); // avoids a ConcurrentModificationException
        //}

        if (barChart != null) {
            barChart.getData().clear();
            barChart.getData().addAll(activityMapToChart(activityListToMap(activities, period), period));
        }
    }

    /**
     * Add titles and descriptions to separate array lists.
     */
    public void retrieveAchievementsInfo() {
        for (int i = 0; i < achievementList.size(); i++) {
            titleList.add(achievementList.get(i).getTitle());
            descriptionList.add(achievementList.get(i).getDescription());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> periodList
                = FXCollections.observableArrayList("Week", "Month", "Half a year", "Year");

        comboBox.setValue("Week");
        comboBox.setItems(periodList);
        comboBox.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);
            }
        });

        updateGraph("Week");

        retrieveAchievementsInfo();

        badgesBox = new VBox();
        badgesBox.setPadding(new Insets(10, 10, 10, 15));

        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();

        String path = "/client/windows/images/badges/";
        for (int i = 0; i < achievementList.size(); i++) {
            int ii = i;
            button = new JFXButton("",
                    new ImageView(path + achievementList.get(i).getPath() + ".png"));
            badges.add(button);
            badges.get(i).setOnMouseEntered(e -> popupBadges(badges.get(ii), ii));
            badges.get(i).setOnMouseExited(e -> hidePopup());
        }

        updateProgress();

        title.setText(titleList.get(0));
        description.setText(descriptionList.get(0));
        title1.setText(titleList.get(1));
        description1.setText(descriptionList.get(1));
        title2.setText(titleList.get(2));
        description2.setText(descriptionList.get(2));
        title3.setText(titleList.get(3));
        description3.setText(descriptionList.get(3));
        title4.setText(titleList.get(4));
        description4.setText(descriptionList.get(4));
        title5.setText(titleList.get(5));
        description5.setText(descriptionList.get(5));
        title6.setText(titleList.get(6));
        description6.setText(descriptionList.get(6));
        title7.setText(titleList.get(7));
        description7.setText(descriptionList.get(7));
        title8.setText(titleList.get(8));
        description8.setText(descriptionList.get(8));
        title9.setText(titleList.get(9));
        description9.setText(descriptionList.get(9));
        title10.setText(titleList.get(10));
        description10.setText(descriptionList.get(10));
        title11.setText(titleList.get(11));
        description11.setText(descriptionList.get(11));
        title12.setText(titleList.get(12));
        description12.setText(descriptionList.get(12));
        title13.setText(titleList.get(13));
        description13.setText(descriptionList.get(13));
        title14.setText(titleList.get(14));
        description14.setText(descriptionList.get(14));

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

    private void updateGraph(ActionEvent event) {
        String period = comboBox.getValue();
        System.out.println(period);
        updateGraph(period);
    }

    //CHECKSTYLE:OFF
    // Supressing the warning [CyclomaticComplexityCheck] because there are more than 10 popups to load and the function cannot be splitted.
    /**
     * This function loads the popup for each button.
     *
     * @param btn   JFXButton
     * @param index int type
     */
    @FXML
    public void popupBadges(JFXButton btn, int index) {
        String path = "/client/windows/fxml/popup" + index + ".fxml";
        try {
            switch (index) {
                case 0:
                    badgePopup = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup);
                    break;
                case 1:
                    badgePopup2 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup2);
                    break;
                case 2:
                    badgePopup3 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup3);
                    break;
                case 3:
                    badgePopup4 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup4);
                    break;
                case 4:
                    badgePopup5 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup5);
                    break;
                case 5:
                    badgePopup6 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup6);
                    break;
                case 6:
                    badgePopup7 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup7);
                    break;
                case 7:
                    badgePopup8 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup8);
                    break;
                case 8:
                    badgePopup9 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup9);
                    break;
                case 9:
                    badgePopup10 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup10);
                    break;
                case 10:
                    badgePopup11 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup11);
                    break;
                case 11:
                    badgePopup12 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup12);
                    break;
                case 12:
                    badgePopup13 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup13);
                    break;
                case 13:
                    badgePopup14 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup14);
                    break;
                case 14:
                    badgePopup15 = FXMLLoader.load(getClass().getResource(path));
                    popOver = new PopOver(badgePopup15);
                    break;
                default:
                    System.out.println("Error parsing the popup");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(popOver.isShowing())) {
            popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
            popOver.setDetachable(false);
            popOver.show(btn);
        }
    }

    //CHECKSTYLE:ON

    /**
     * This function hides the popup.
     */
    @FXML
    public void hidePopup() {
        popOver.hide();
    }

    /**
     * This function will make a hashmap sorted by LocalDate or week or month.
     *
     * @param activityList List type
     * @param period       String type
     * @return a HashMap with the correct values for the graph
     */
    private HashMap<String, Double> activityListToMap(List<Activity> activityList, String period) {
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (Activity activity : activityList) {
            String key = activity.getDate().toString();
            double value = Main.items.get(activity.getItemID() - 1).getCo2() * activity.getAmount();
            if (period.equals("Month")) {
                key = "Week " + activity.getDate().get(WeekFields.of(
                        Locale.getDefault()).weekOfWeekBasedYear());
            } else if (period.equals("Year") || period.equals("Half a year")) {
                key = activity.getDate().getMonth().name();
            }

            if (!map.containsKey(key)) {
                map.put(key, value);
            } else {
                map.put(key, map.get(key) + value);
            }
        }
        return map;
    }

    private XYChart.Series activityMapToChart(HashMap<String, Double> map, String period) {
        XYChart.Series<String, Double> chart = new XYChart.Series<>();
        chart.setName(period);
        for(Map.Entry<String, Double> entry : map.entrySet()) {
            chart.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        return chart;
    }
}
