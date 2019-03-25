
package client.windows;

import client.Main;
import client.user.Achievement;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
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

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private JFXButton aabutton1;
    @FXML
    private JFXButton aabutton2;
    @FXML
    private JFXButton clearGraph;
    @FXML
    private CategoryAxis x;

    @FXML
    LineChart<String, Integer> lineChart;
    @FXML
    Pane overview;

    @FXML
    private Pane badgePopup = new Pane();
    @FXML
    private Pane badgePopup2 = new Pane();
    @FXML
    private Pane badgePopup3 = new Pane();
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
    private ScrollPane scrollBadges = new ScrollPane();

    private VBox badgesBox;
    private HBox row;
    private HBox row2;
    private HBox row3;
    private JFXButton button;

    private PopOver popOver = new PopOver();
    List<JFXButton> badges = new ArrayList<>();
    private List<Achievement> achievementList;

    public PopOver getPopOver() {
        return popOver;
    }

    @Override
    public void update() {
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
            badges.get(i).setOnMouseExited(e -> hidePopup());
        }

        title.setText(achievementList.get(0).getTitle());
        description.setText(achievementList.get(0).getDescription());
        title1.setText(achievementList.get(1).getTitle());
        description1.setText(achievementList.get(1).getDescription());
        title2.setText(achievementList.get(2).getTitle());
        description2.setText(achievementList.get(2).getDescription());
        title3.setText(achievementList.get(3).getTitle());
        description3.setText(achievementList.get(3).getDescription());
        title4.setText(achievementList.get(4).getTitle());
        description4.setText(achievementList.get(4).getDescription());
        title5.setText(achievementList.get(5).getTitle());
        description5.setText(achievementList.get(5).getDescription());
        title6.setText(achievementList.get(6).getTitle());
        description6.setText(achievementList.get(6).getDescription());
        title7.setText(achievementList.get(7).getTitle());
        description7.setText(achievementList.get(7).getDescription());
        title8.setText(achievementList.get(8).getTitle());
        description8.setText(achievementList.get(8).getDescription());
        title9.setText(achievementList.get(9).getTitle());
        description9.setText(achievementList.get(9).getDescription());
        title10.setText(achievementList.get(10).getTitle());
        description10.setText(achievementList.get(10).getDescription());
        title11.setText(achievementList.get(11).getTitle());
        description11.setText(achievementList.get(11).getDescription());
        title12.setText(achievementList.get(12).getTitle());
        description12.setText(achievementList.get(12).getDescription());
        title13.setText(achievementList.get(13).getTitle());
        description13.setText(achievementList.get(13).getDescription());
        title14.setText(achievementList.get(14).getTitle());
        description14.setText(achievementList.get(14).getDescription());

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

    /**
     * This function loads the popup for each button.
     * @param btn JFXButton
     * @param index int type
     */
    @FXML
    public void popupBadges(JFXButton btn, int index) {
        String path = "/client/windows/fxml/popup" + index +  ".fxml";
        switch (index) {
            case 0:
                try {
                    badgePopup = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup);
                break;
            case 1:
                try {
                    badgePopup2 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup2);
                break;
            case 2:
                try {
                    badgePopup3 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup3);
                break;
            case 3:
                try {
                    badgePopup4 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup4);
                break;
            case 4:
                try {
                    badgePopup5 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup5);
                break;
            case 5:
                try {
                    badgePopup6 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup6);
                break;
            case 6:
                try {
                    badgePopup7 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup7);
                break;
            case 7:
                try {
                    badgePopup8 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup8);
                break;
            case 8:
                try {
                    badgePopup9 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup9);
                break;
            case 9:
                try {
                    badgePopup10 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup10);
                break;
            case 10:
                try {
                    badgePopup11 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup11);
                break;
            case 11:
                try {
                    badgePopup12 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup12);
                break;
            case 12:
                try {
                    badgePopup13 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup13);
                break;
            case 13:
                try {
                    badgePopup14 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup14);
                break;
            case 14:
                try {
                    badgePopup15 = FXMLLoader.load(getClass().getResource(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popOver = new PopOver(badgePopup15);
                break;
        }
        if (!(popOver.isShowing())) {
            popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_CENTER);
            popOver.setDetachable(false);
            popOver.show(btn);
        }
    }

    /**
     * This function hides the popup.
     */
    @FXML
    public void hidePopup() {
        popOver.hide();
    }
    /**
     * Loads the achievements in the list.
     */
    private void loadAchievements() {
        //Clears everything in the observable list
        achievementList = Main.achievements.stream().collect(Collectors.toList());
    }
}
