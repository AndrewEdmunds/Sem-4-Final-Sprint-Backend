package com.keyin.finalsprint.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "query_logs")
public class QueryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "query_keyword")
    private String queryKeyword;

    @Column(name = "datetime")
    private LocalDateTime datetime;

      // Constructors
      public QueryLog() {
    }

    public QueryLog(User user, String queryKeyword, LocalDateTime datetime) {
        this.user = user;
        this.queryKeyword = queryKeyword;
        this.datetime = datetime;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQueryKeyword() {
        return queryKeyword;
    }

    public void setQueryKeyword(String queryKeyword) {
        this.queryKeyword = queryKeyword;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}