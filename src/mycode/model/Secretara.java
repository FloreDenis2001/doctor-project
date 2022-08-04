package mycode.model;

public class Secretara extends User {


    public Secretara(int id, String lastName, String firstName, String username, String password) {
        super(id, lastName, firstName, username, password);
    }

    public Secretara(String text) {
        super(text);
    }
}
