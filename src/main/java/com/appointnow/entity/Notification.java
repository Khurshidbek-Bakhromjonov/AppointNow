package com.appointnow.entity;

import com.appointnow.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity{

    private String title;

    private String message;

    @Column(name = "created_at")
    private Date createdAt;

    private String url;

    @Column(name = "is_read")
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Notification() {
    }
}
