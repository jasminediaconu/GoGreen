package client.windows;

import client.ServerRequests;
import client.user.ClientUser;
import client.user.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.PropertySheet;

public class LeaderboardController extends Controller implements Initializable {

    @FXML public TableView<User> table;
    @FXML public TableColumn<User, String> usernameColumn;
    @FXML public TableColumn<User, String> countryColumn;
    @FXML public TableColumn<User, Double> totalCo2Column;
    @FXML public Button globalButton;
    @FXML public Button followingButton;

    ServerRequests serverRequests = new ServerRequests();
    ClientUser clientUser = new ClientUser();
    User user = new User();

    ArrayList<String> stringArrayList = new ArrayList<>();


    public void initialize(URL url, ResourceBundle rb) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
        totalCo2Column.setCellValueFactory(new PropertyValueFactory<User, Double>("totalCo2"));

        switchToGlobal();
        switchToFollowing();
        addFollowButtons();
        table.setItems(getGlobalUsers());
        globalButton.setDisable(true);
        arrayList(stringArrayList);
    }

    /**
     * The observable list contains the top 10 global users
     * @return the list of global users
     */
    private ObservableList<User> getGlobalUsers(){
        ObservableList<User> globalUsers = FXCollections.observableArrayList(serverRequests.getGlobalBestProfile());
        return globalUsers;

    }

    /**
     * The observable list contains the users the client is following
     * @return the list of following users
     */
    private ObservableList<User> getFollowingUsers(){
        ObservableList<User> followingUsers = FXCollections.observableArrayList(serverRequests.getFollowingProfile());
        return followingUsers;
    }

    /**
     * The globalButton switches the contents of the table with the global list
     */
    private void switchToGlobal(){
        globalButton.setOnAction((ActionEvent switchToGlobalEvent) -> {
            table.getItems().clear();
            table.setItems(getGlobalUsers());

            globalButton.setDisable(true);
            followingButton.setDisable(false);
        });
    }

    /**
     * The followingButton switches the contents of the table with the following list
     */
    private void switchToFollowing(){
        followingButton.setOnAction((ActionEvent switchToFollowingEvent) -> {
            table.getItems().clear();
            table.setItems(getFollowingUsers());

            globalButton.setDisable(false);
            followingButton.setDisable(true);
        });
    }

    public void addFollowButtons(){
        TableColumn<User, Void> followButtonColumn = new TableColumn<>("Follow");

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<>() {

                    public JFXButton followButton = new JFXButton("follow");

                    {
                        followButton.setOnAction((ActionEvent followUserEvent) -> {
                            String data = usernameColumn.getCellObservableValue(this.getTableRow().getIndex()).getValue();

                            if (followButton.getText().equals("unfollow")){
                                followButton.setText("follow");
                                serverRequests.unFollowUser(data);

                                if (table.getItems().equals(getFollowingUsers())){
                                    table.getItems().remove(this.getTableRow());
                                }
                            } else {
                                followButton.setText("unfollow");
                                serverRequests.followUser(data);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        }  else {
                            //TODO change "admin" with clientUsername
                            String data = usernameColumn.getCellObservableValue(this.getTableRow().getIndex()).getValue();

                            if(data.equals("admin")){
                                setGraphic(null);
                            }
                            //TODO if user is already followed set text to "unfollow"
//                            if (getFollowingUsers().contains(data)){
//                                setGraphic(followButton);
//                                followButton.setText("unfollow");
//                            }
                            if(arrayList(stringArrayList).contains(data)){
                                setGraphic(followButton);
                                followButton.setText("unfollow");
                            }
                            else {
                                this.setGraphic(followButton);

                            }
                        }
                    }


                };
                return cell;
            }
        };

        followButtonColumn.setCellFactory(cellFactory);

        table.getColumns().add(followButtonColumn);
    }

    public ArrayList<String> arrayList(ArrayList<String> stringArrayList) {
        stringArrayList.add("GDWDfaJkCT");
        stringArrayList.add("pYucfKgVRG");
        stringArrayList.add("TestAccount6652");
        return stringArrayList;
    }

    @Override
    public void update() {

    }
}