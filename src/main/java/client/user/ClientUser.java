package client.user;

import java.util.ArrayList;
import java.util.Date;

public class ClientUser extends User {

    private ArrayList<User> friends;
    private int score = -1;
    private String car = "";
    private int daysGreen = -1;
    private int age = -1;
    private Date birthDate = null;


    public ClientUser() {

    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public User getFriend(int index) {
        return friends.get(index);
    }

    public boolean removeFriend(int index) {
        return friends.remove(index) != null;
    }


    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDaysGreen() {
        return daysGreen;
    }

    public void setDaysGreen(int daysGreen) {
        this.daysGreen = daysGreen;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
