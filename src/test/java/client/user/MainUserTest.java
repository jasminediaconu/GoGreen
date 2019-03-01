package client.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MainUserTest {
    MainUser user;

    @BeforeEach
    void setUp() {
        user = new MainUser();
    }

    @Test
    void getUserName(){
        user.setUserName("test name");
        assertEquals("test name", user.getUserName());
    }

    @Test
    void setUserName(){
        user.setUserName("test name");
        assertEquals("test name", user.getUserName());
    }

    @Test
    void getCountry(){
        user.setCountry("test country");
        assertEquals("test country", user.getCountry());
    }

    @Test
    void setCountry(){
        user.setCountry("test country");
        assertEquals("test country", user.getCountry());
    }

    @Test
    void getCar() {
        user.setCar("test car");
        assertEquals("test car", user.getCar());
    }

    @Test
    void setCar() {
        user.setCar("test car");
        assertEquals("test car", user.getCar());
    }

    @Test
    void getFriend() {

    }

    @Test
    void removeFriend() {

    }

    @Test
    void setFriends() {
    }

    @Test
    void getScore() {
        user.setScore(99);
        assertEquals(99, user.getScore());
    }

    @Test
    void setScore() {
        user.setScore(99);
        assertEquals(99, user.getScore());
    }

    @Test
    void getDaysGreen() {
        user.setDaysGreen(98);
        assertEquals(98, user.getDaysGreen());
    }

    @Test
    void setDaysGreen() {
        user.setDaysGreen(98);
        assertEquals(98, user.getDaysGreen());
    }

    @Test
    void getAge() {
        user.setAge(58);
        assertEquals(58, user.getAge());
    }

    @Test
    void setAge() {
        user.setAge(58);
        assertEquals(58, user.getAge());
    }

    @Test
    void getBirthDate() {
        user.setBirthDate(new Date(9999));
        assertEquals(new Date(9999), user.getBirthDate());
    }

    @Test
    void setBirthDate() {
        user.setBirthDate(new Date(9999));
        assertEquals(new Date(9999), user.getBirthDate());
    }
}