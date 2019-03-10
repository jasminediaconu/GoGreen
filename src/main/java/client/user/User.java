package client.user;

import java.awt.*;
import java.util.Objects;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;


public class User {

    protected SimpleStringProperty country;
    protected SimpleStringProperty username;
    protected SimpleDoubleProperty totalCo2;
    protected Button followButton;

    /**
     * The abstract User constructor, used for all other users that are not the client.
     * @param username the username of the user
     * @param country the country of the user
     * @param totalCo2 the total co2 the user has saved
     */
    public User(String username, String country, double totalCo2){
        this.username = new SimpleStringProperty(username);
        this.country = new SimpleStringProperty(country);
        this.totalCo2 = new SimpleDoubleProperty(totalCo2);
        this.followButton = new Button("Follow");
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public double getTotalCo2() {
        return totalCo2.get();
    }

    public void setTotalCo2(double totalCo2) {
        this.totalCo2.set(totalCo2);
    }

    /**
     * This function will hash the User class
     * @return the hashed User
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, country, totalCo2);
    }
}
