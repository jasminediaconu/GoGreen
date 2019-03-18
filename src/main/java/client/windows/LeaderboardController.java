package client.windows;

import client.ServerRequests;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import client.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController extends Controller implements Initializable {

    @FXML public TableView<User> table;
    @FXML public TableColumn<User, String> usernameColumn;
    @FXML public TableColumn<User, String> countryColumn;
    @FXML public TableColumn<User, Double> totalCo2Column;

    public void initialize(URL url, ResourceBundle rb) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
        totalCo2Column.setCellValueFactory(new PropertyValueFactory<User, Double>("totalCo2"));

        table.setItems(getUsers());
    }

    public ObservableList<User> getUsers(){
        ServerRequests serverRequests = new ServerRequests();
        ObservableList<User> users = FXCollections.observableArrayList(serverRequests.getGlobalBestProfile());

        return users;
    }

//    public void addFollowButtons(){
//        TableColumn<User, Void> followButtonColumn = new TableColumn("Follow");
//
//        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
//            @Override
//            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
//                final TableCell<User, Void> cell = new TableCell<User, Void>() {
//
//                    private final Button followButton = new Button("follow");
//
//                    {
//                        followButton.setOnAction((ActionEvent event) -> {
//                            User data = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedData: " + data);
//                            if (followButton.getText() == "unfollow"){
//                                followButton.setText("follow");
//                            } else {
//                                followButton.setText("unfollow");
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(followButton);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        followButtonColumn.setCellFactory(cellFactory);
//
//        table.getColumns().add(followButtonColumn);
//    }


    @Override
    public void update() {

    }
}
