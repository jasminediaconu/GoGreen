package client.windows;

import client.user.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    /** This sets the table and columns from the FXML file
     *
     */
    @FXML public TableView<User> table;
    @FXML public TableColumn<User, SimpleStringProperty> usernameColumn;
    @FXML public TableColumn<User, SimpleStringProperty> countryColumn;
    @FXML public TableColumn<User, SimpleDoubleProperty> totalCo2Column;
    @FXML public TableColumn<User, Button> followButtonColumn;

    /** Connects the cells in the table to the right values
     *  table.setItems(getUsers()) initializes the values from the observable list
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("username"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("country"));
        totalCo2Column.setCellValueFactory(new PropertyValueFactory<User, SimpleDoubleProperty>("totalCo2"));
        followButtonColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("followButton"));

        table.setItems(getUsers());
    }

    /** The usernames, countries, total CO2 saved and follow buttons of all users are contained in this list
     * The dummy users should be replaced by users in the database
     * @return the observable list users
     */
    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();

        users.add(new User("Dinkleberg","United States", 527.24, new Button("followButton")));
        users.add(new User("co2Warrior","France", 319.52, new Button("Follow")));
        users.add(new User("sweetandround","Netherlands", 100.0, new Button()));
        users.add(new User("PatriciaPaay","Netherlands", 213.78, new Button("followUser")));

        return users;
    }

    /** This is a test event for the followButton
     *
     * @param event prints out "following user now"
     */
    public void followUser(ActionEvent event){
        System.out.println("following user now");
    }
}
