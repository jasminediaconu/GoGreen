package client.windows;

import client.ServerRequests;
import client.user.ClientUser;
import client.user.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class LeaderboardController extends Controller implements Initializable {

    @FXML public TableView<User> table;
    @FXML public TableColumn<User, String> usernameColumn;
    @FXML public TableColumn<User, String> countryColumn;
    @FXML public TableColumn<User, Double> totalCo2Column;
    @FXML public Button globalButton;
    @FXML public Button followingButton;

    ServerRequests serverRequests = new ServerRequests();
    ClientUser clientUser = new ClientUser();

    public void initialize(URL url, ResourceBundle rb) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
        totalCo2Column.setCellValueFactory(new PropertyValueFactory<User, Double>("totalCo2"));

        switchToGlobal();
        switchToFollowing();
        addFollowButtons();
        table.setItems(getGlobalUsers());
    }

    /**
     * The observable list contains the top 10 global users
     * @return the list of global users
     */
    public ObservableList<User> getGlobalUsers(){
        ObservableList<User> globalUsers = FXCollections.observableArrayList(serverRequests.getGlobalBestProfile());
        return globalUsers;
    }

    /**
     * The observable list contains the users the client is following
     * @return the list of following users
     */
    public ObservableList<User> getFollowingUsers(){
        ObservableList<User> followingUsers = FXCollections.observableArrayList(serverRequests.getFollowingProfile());
        return followingUsers;
    }

    /**
     * The globalButton switches the contents of the table with the global list
     */
    public void switchToGlobal(){
        globalButton.setOnAction((ActionEvent switchToGlobalEvent) -> {
            table.setItems(getGlobalUsers());
        });
    }

    /**
     * The followingButton switches the contents of the table with the following list
     */
    public void switchToFollowing(){
        followingButton.setOnAction((ActionEvent switchToFollowingEvent) -> {
            table.setItems(getFollowingUsers());
        });
    }

    public void addFollowButtons(){
        TableColumn<User, Void> followButtonColumn = new TableColumn<>("Follow");

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<>() {

                    private Button followButton = new Button("follow");

                    {
                        followButton.setOnAction((ActionEvent followUserEvent) -> {
                            //TODO follow user function
                            //TODO update list with setFollowing

                            //clientUser.addFollowing();

                            if (followButton.getText().equals("unfollow")){
                                followButton.setText("follow");
                            } else {
                                followButton.setText("unfollow");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        //TODO if clientuserid is userid of row then setGraphic(null)

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(followButton);
                        }
                    }
                };
                return cell;
            }
        };

        followButtonColumn.setCellFactory(cellFactory);

        table.getColumns().add(followButtonColumn);
    }

    @Override
    public void update() {

    }
}