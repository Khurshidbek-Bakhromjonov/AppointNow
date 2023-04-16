package com.appointnow.entity;

import com.appointnow.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "messages")
public class ChatMessage extends BaseEntity implements Comparable<ChatMessage> {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String message;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "id_appointment")
    private Appointment appointment;

    public ChatMessage() {
    }

    @Override
    public int compareTo(@NotNull ChatMessage o) {
        return this.createdAt.compareTo(o.getCreatedAt());
    }
}
