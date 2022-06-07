package controller;

public class BadLoginException extends Exception{
    String username;
    @Override
    /**
     * Own exception message
     */
    public String getMessage() {
        return "Wrong login details";
    }

    public BadLoginException(String username){
        super(username);
        this.username = username;
    }
}
