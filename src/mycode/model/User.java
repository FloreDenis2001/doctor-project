package mycode.model;

public class User implements Comparable<User> {
    private int id;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String type;

    public User(int id, String lastName, String firstName, String username, String password, String type) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String nume,String prenume){
        this.lastName=nume;
        this.firstName=prenume;
    }

    public User(int id, String lastName, String firstName, String username, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
    }




    public User(String text){
        String [] path = text.split(",");
        this.id=Integer.parseInt(path[0]);
        this.lastName=path[1];
        this.firstName=path[2];
        this.username=path[3];
        this.password=path[4];
        this.type=path[5];
  }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        User x = (User) o;
        if (this.password.compareTo(x.getPassword()) == 0) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String text = "ID : " + this.id + "\n";
        text += "Username : " + this.username + "\n";
        text += "Password : " + this.password + "\n";
        return text;
    }


    @Override
    public int compareTo(User o) {
        if (this.getLastName().compareTo(o.getLastName()) > 0) {
            return 1;
        } else if (this.getLastName().compareTo(o.getLastName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
