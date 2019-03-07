package client.user;

import com.google.gson.annotations.Expose;

/**
 * The type User.
 */
public class User {

    /**
     * The Username.
     */
    protected String username = "";
    /**
     * The Country.
     */
    protected String country = "";
    /**
     * The Total co2.
     */
    protected double totalCo2 = 0.0;

    /**
     * The abstract User constructor, used for all other users that are not the client.
     */
    public User() {
    }

    /**
     * This function will get the users country.
     *
     * @return the country of the user
     */
    public String getCountry() {
        return country;
    }

    /**
     * This function will get the users name.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * This function will get the users total co2 amount.
     *
     * @return returns the total co2 amount of the user
     */
    public double getTotalCo2() {
        return totalCo2;
    }

    /**
     * This function will set the totalCo2 amount to the co2 in the argument.
     *
     * @param co2 double to set the total co2 amount to
     */
    public void setTotalCo2(double co2) {
        this.totalCo2 = co2;
    }

    /**
     * This function will add the argument co2 amount to the totalCo2 amount.
     *
     * @param co2 double that will be added to the totalCo2 amount
     */
    public void increaseTotalCo2(double co2) {
        this.totalCo2 += co2;
    }


    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
