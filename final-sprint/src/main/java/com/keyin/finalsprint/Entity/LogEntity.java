package com.keyin.finalsprint.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "query_logs")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "query_keyword")
    private String query_keyword;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    // Constructors
    public LogEntity() {
    }

    public LogEntity(Long id, String user_id, String query_keyword, LocalDateTime datetime) {
        this.id = id;
        this.user_id = user_id;
        this.query_keyword = query_keyword;
        this.datetime = datetime;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuery_keyword() {
        return query_keyword;
    }

    public void setQuery_keyword(String query_keyword) {
        this.query_keyword = query_keyword;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime dateTime) {
        this.datetime = dateTime;
    }
}
