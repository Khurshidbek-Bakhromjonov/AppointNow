package com.appointnow.entity.user;

import com.appointnow.entity.BaseEntity;
import com.appointnow.entity.Notification;
import com.appointnow.model.UserForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String street;

    private String city;

    private String postcode;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(UserForm newUserForm, String encryptedPassword, Collection<Role> roles) {
        this.setUsername(newUserForm.getUsername());
        this.setFirstName(newUserForm.getFirstName());
        this.setLastName(newUserForm.getLastName());
        this.setEmail(newUserForm.getEmail());
        this.setCity(newUserForm.getCity());
        this.setStreet(newUserForm.getStreet());
        this.setPostcode(newUserForm.getPostcode());
        this.setPhoneNumber(newUserForm.getPhoneNumber());
        this.password = encryptedPassword;
        this.roles = roles;
    }

    public void update(UserForm updateData) {
        this.setEmail(updateData.getEmail());
        this.setFirstName(updateData.getFirstName());
        this.setLastName(updateData.getLastName());
        this.setPhoneNumber(updateData.getPhoneNumber());
        this.setCity(updateData.getCity());
        this.setStreet(updateData.getStreet());
        this.setPostcode(updateData.getPostcode());
    }

    public boolean hasRole(String roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }

}
