package client.user;

import java.util.Objects;

/**
 * The type User.
 */
public class User {

    /**
     * The Username.
     */
    protected String username;

    /**
     * The Country.
     */
    protected String country;

    /**
     * The Total co2.
     */
    protected double totalCo2;

    /**
     * The abstract User constructor, used for all other users that are not the client.
     */
    public User() {
    }

    public User(String username, String country, double totalCo2) {
        this.username = username;
        this.country = country;
        this.totalCo2 = totalCo2;
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
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
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
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * This function compares this User with another User to check if they are equal.
     *
     * @param o Object type
     * @return a boolean, whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Double.compare(user.totalCo2, totalCo2) == 0
                && Objects.equals(username, user.username)
                && Objects.equals(country, user.country);
    }
}
