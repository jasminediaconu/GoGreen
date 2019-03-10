package client.user;

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
     * @param followButton the button to follow the user
     */
    public User(String username, String country, double totalCo2, Button followButton){
        this.username = new SimpleStringProperty(username);
        this.country = new SimpleStringProperty(country);
        this.totalCo2 = new SimpleDoubleProperty(totalCo2);
        this.followButton = new Button("Follow");
    }

    /** This function will get the country
     *  @return the country
     */
    public String getCountry() {
        return country.get();
    }

    /** This function will set the country
     * @param country
     */
    public void setCountry(String country) {
        this.country.set(country);
    }

    /** This function will get the username
     *  @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /** This function will set the username
     * @param username
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /** This function will get the total CO2 saved
     *  @return the total CO2 saved
     */
    public double getTotalCo2() {
        return totalCo2.get();
    }

    /** This function will set the totalCo2
     * @param totalCo2
     */
    public void setTotalCo2(double totalCo2) {
        this.totalCo2.set(totalCo2);
    }

    /** This function will get the follow button
     *  @return the button
     */
    public Button getFollowButton() {
        return followButton;
    }

    /** This function will set the followButton
     * @param followButton
     */
    public void setFollowButton(Button followButton) {
        this.followButton = followButton;
    }

    /**
     * This function will hash the User class
     * @return the hashed User
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, country, totalCo2, followButton);
    }
}
