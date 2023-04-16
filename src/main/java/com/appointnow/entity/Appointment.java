package com.appointnow.entity;

import com.appointnow.entity.user.User;
import com.appointnow.entity.user.customer.Customer;
import com.appointnow.entity.user.provider.Provider;
import com.appointnow.model.AppointmentSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@JsonSerialize(using = AppointmentSerializer.class)
public class Appointment extends BaseEntity implements Comparable<Appointment> {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @OneToOne
    @JoinColumn(name = "id_canceler")
    private User canceler;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_work")
    private Work work;

    @OneToMany(mappedBy = "appointment")
    private List<ChatMessage> chatMessages;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @OneToOne(mappedBy = "requested", cascade = {CascadeType.ALL})
    private ExchangeRequest exchangeRequest;

    public Appointment() {
    }

    public Appointment(LocalDateTime start, LocalDateTime end, Customer customer, Provider provider, Work work) {
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.provider = provider;
        this.work = work;
    }

    @Override
    public int compareTo(@NotNull Appointment o) {
        return this.getStart().compareTo(o.getStart());
    }

    public List<ChatMessage> getChatMessages() {
        Collections.sort(chatMessages);
        return chatMessages;
    }
}
