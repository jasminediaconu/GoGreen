package client.user;

import java.util.Objects;

public class User {

    protected String username;
    protected String country;
    protected double totalCo2;

    /**
     * The abstract User constructor, used for all other users that are not the client.
     *
     * @param username the username of the user
     * @param country  the country of the user
     * @param totalCo2 the total co2 the user has saved
     */
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
     * This function compares this User with another User to check if they are equal.
     *
     * @param o Object type
     * @return a boolean, whether they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.totalCo2, totalCo2) == 0 &&
                Objects.equals(username, user.username) &&
                Objects.equals(country, user.country);
    }

}
