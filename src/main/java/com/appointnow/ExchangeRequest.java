package com.appointnow;

import com.appointnow.entity.Appointment;
import com.appointnow.entity.BaseEntity;
import com.appointnow.entity.ExchangeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exchanges")
public class ExchangeRequest extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "exchange_status")
    private ExchangeStatus status;

    @OneToOne
    @JoinColumn(name = "id_appointment_requestor")
    private Appointment requestor;

    @OneToOne
    @JoinColumn(name = "id_appointment_requested")
    private Appointment requested;

    public ExchangeRequest() {
    }

    public ExchangeRequest(Appointment requestor, Appointment requested, ExchangeStatus status) {
        this.status = status;
        this.requestor = requestor;
        this.requested = requested;
    }
}
