package model;

import java.io.Serializable;

public class Login implements Serializable {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Login constructor
     * @param username
     * @param password
     * @param confirmPassword
     */
    public Login(String username, String password, String confirmPassword){
        if(password.equals(confirmPassword)){
            this.username = username;
            this.password = password;
        }
    }
}
