package com.appointnow.entity;

import com.appointnow.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "works")
public class Work extends BaseEntity {

    private String name;

    private String description;

    private double price;

    private int duration;

    private boolean editable;

    @Column(name = "target")
    private String targetCustomer;

    @ManyToMany
    @JoinTable(name = "works_providers", joinColumns = @JoinColumn(name = "id_work"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> providers;

    public Work() {
    }

    public boolean getEditable() {
        return editable;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Work)) return false;
        var work = (Work) obj;
        return super.getId().equals(work.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
