package server.helper;

public class UserClass {

    public String username;
    public String country;
    public double totalCo2;

    /**
     * This is the UserClass, all user classes will be populated here.
     *
     * @param username String type
     * @param country  String type
     * @param totalCo2 double type
     */
    public UserClass(String username, String country, double totalCo2) {
        this.username = username;
        this.country = country;
        this.totalCo2 = totalCo2;
    }
}
