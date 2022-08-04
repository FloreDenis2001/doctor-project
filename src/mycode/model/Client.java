package mycode.model;

public class Client extends User {

    public Client(int id, String lastName, String firstName, String username, String password) {
        super(id, lastName, firstName, username, password);
    }

    public Client(String lastName,String firstName){
        super(lastName,firstName);
    }

    public Client(String text) {
        super(text);
    }
}
