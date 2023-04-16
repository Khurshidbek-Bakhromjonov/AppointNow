package com.appointnow.entity.user.customer;

import com.appointnow.entity.Appointment;
import com.appointnow.entity.user.Role;
import com.appointnow.entity.user.User;
import com.appointnow.model.UserForm;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id_customer")
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;

    public Customer() {
    }

    public Customer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
    }

    public String getType() {
        if (super.hasRole("ROLE_CUSTOMER_CORPORATE")) {
            return "corporate";
        } else if (super.hasRole("ROLE_CUSTOMER_RETAIL")) {
            return "retail";
        }
        return "";
    }
}
