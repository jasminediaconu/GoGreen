package client.windows;

import client.Main;
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

    ArrayList<String> followingUsernames = new ArrayList<>();

    public void initialize(URL url, ResourceBundle rb) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
        totalCo2Column.setCellValueFactory(new PropertyValueFactory<User, Double>("totalCo2"));

        switchToGlobal();
        switchToFollowing();
        addFollowButtons();

        globalButton.setDisable(true);

        table.setItems(getFollowingUsers());
        arrayList(followingUsernames);

        table.setItems(getGlobalUsers());
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
            arrayList(followingUsernames);
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
            arrayList(followingUsernames);
            table.setItems(getFollowingUsers());

            globalButton.setDisable(false);
            followingButton.setDisable(true);
        });
    }

    /**
     * Add a follow button in each row
     * Set the appropriate graphics and text per button
     */
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
                                followButton.setStyle("-fx-background-color : #95e743;");

                                if (table.getItems().equals(getFollowingUsers())){
                                    table.getItems().remove(this.getTableRow());
                                }
                            } else {
                                followButton.setText("unfollow");
                                serverRequests.followUser(data);
                                followButton.setStyle("-fx-background-color : #8c8686");
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
                            else {
                                if(followingUsernames.contains(data)){
                                    setGraphic(followButton);
                                    followButton.setText("unfollow");
                                    followButton.setStyle("-fx-background-color: #8c8686");
                                }
                                else {
                                    setGraphic(followButton);
                                    followButton.setText("follow");
                                    followButton.setStyle("-fx-background-color : #95e743;");
                                }
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

    /**
     * Add only the usernames the clientUser is following to arrayList
     * @param followingUsernames usernames the client follows
     * @return the arrayList containing the following usernames
     */
    public ArrayList<String> arrayList(ArrayList<String> followingUsernames) {
        table.setItems(getFollowingUsers());
        for (User user : table.getItems()){

            followingUsernames.add(usernameColumn.getCellObservableValue(user).getValue());
        }
        return followingUsernames;
    }

    @Override
    public void update() {

    }
}