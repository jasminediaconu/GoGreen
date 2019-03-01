package client.user;

import java.util.Date;

abstract class User {

    //user details
    protected String userName = "";

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    protected String country = "";


    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }


}
