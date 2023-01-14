package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {

    private int id;
    private String mesaj;
    private LocalDateTime createAtDate;
    private int userId;

    private ArrayList<Integer> likes = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();


    public Post() {
    }

    public Post(int id, String mesaj, LocalDateTime createAtDate, int userId) {
        this.id = id;
        this.mesaj = mesaj;
        this.createAtDate = createAtDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public LocalDateTime getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(LocalDateTime createAtDate) {
        this.createAtDate = createAtDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Integer> likes) {
        this.likes = likes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "\nPost{" +
                "id=" + id +
                ", mesaj='" + mesaj + '\'' +
                ", createAtDate=" + createAtDate +
                ", userId=" + userId +
                ", \nlikes=" + likes +
                ", \ncomments=" + comments +
                '}';
    }
}
