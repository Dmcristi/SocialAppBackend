package entity;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private int userId;
    private String mesaj;
    private LocalDateTime createAtDate;

    public Comment() {
    }

    public Comment(int id, int userId, String mesaj, LocalDateTime createAtDate) {
        this.id = id;
        this.userId = userId;
        this.mesaj = mesaj;
        this.createAtDate = createAtDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", mesaj='" + mesaj + '\'' +
                ", createAtDate=" + createAtDate +
                '}';
    }
}
