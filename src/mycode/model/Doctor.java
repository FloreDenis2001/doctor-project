package mycode.model;

public class Doctor extends User{


    public Doctor(int id, String lastName, String firstName, String username, String password) {
        super(id, lastName, firstName, username, password);
    }

    public Doctor(String text) {
        super(text);
    }
}
