package client.windows;


import client.user.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    @FXML public TableView<User> table;
    @FXML public TableColumn<User, SimpleStringProperty> usernameColumn;
    @FXML public TableColumn<User, SimpleStringProperty> countryColumn;
    @FXML public TableColumn<User, SimpleDoubleProperty> totalCo2Column;

    @FXML public Button followButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("username"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("country"));
        totalCo2Column.setCellValueFactory(new PropertyValueFactory<User, SimpleDoubleProperty>("totalCo2"));

        table.setItems(getUsers());
    }

    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();

        users.add(new User("Dinkleberg","United States", 527.24));
        users.add(new User("co2Warrior","France", 319.52));
        users.add(new User("sweetandround","Netherlands", 100.0));
        users.add(new User("PatriciaPaay","Netherlands", 213.78));

        return users;
    }
}
