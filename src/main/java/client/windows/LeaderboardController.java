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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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

        addFollowButtons();

        table.setItems(getUsers());

        //totalCo2Column.setSortType(TableColumn.SortType.DESCENDING);
    }

    /** The usernames, countries, total CO2 saved and follow buttons of all users are contained in this list
     * The dummy users should be replaced by users in the database
     * @return the observable list users
     */
    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();

        users.add(new User("Dinkleberg","United States", 527.24));
        users.add(new User("co2Warrior","France", 319.52));
        users.add(new User("sweetandround","Netherlands", 100.0));
        users.add(new User("PatriciaPaay","Netherlands", 213.78));
        users.add(new User("GreenMint","Greenland", 10.42));
        users.add(new User("00pp72","Netherlands", 72.0));
        users.add(new User("saverman","Mexico", 385.3));
        users.add(new User("hapapapa","Germany", 111.49));
        users.add(new User("GoofyGoober","United States", 73.99));
        users.add(new User("baguette","France", 152.56));

        return users;
    }

    /** This is a test event for the followButton
     * public void followUser(ActionEvent event){
     *     System.out.println("following user now");
     * }
     */

    public void addFollowButtons(){
        TableColumn<User, Void> followButtonColumn = new TableColumn("Follow");

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    private final Button followButton = new Button("follow");

                    {
                        followButton.setOnAction((ActionEvent event) -> {
                            User data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            if (followButton.getText() == "unfollow"){
                                followButton.setText("follow");
                            } else {
                                followButton.setText("unfollow");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
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
}
