package entity;

import java.util.ArrayList;

public class User {

    private int id;
    private String nume;
    private String email;
    private int varsta;

    private ArrayList<Post> postsList = new ArrayList<>();

    public User() {
    }

    public User(int id, String nume, String email, int varsta) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.varsta = varsta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public ArrayList<Post> getPostsList() {
        return postsList;
    }

    public void setPostsList(ArrayList<Post> postsList) {
        this.postsList = postsList;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + varsta +
                ", \npostsList=" + postsList +
                '}';
    }
}
