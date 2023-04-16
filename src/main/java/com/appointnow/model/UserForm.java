package com.appointnow.model;

import com.appointnow.entity.Work;
import com.appointnow.entity.user.User;
import com.appointnow.entity.user.customer.CorporateCustomer;
import com.appointnow.entity.user.customer.RetailCustomer;
import com.appointnow.entity.user.provider.Provider;
import com.appointnow.validation.FieldsMatches;
import com.appointnow.validation.UniqueUsername;
import com.appointnow.validation.groups.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@FieldsMatches(field = "password", matchingField = "matchingPassword", groups = {CreateUser.class})
public class UserForm {

    @NotNull(groups = {UpdateUser.class})
    @Min(value = 1, groups = {UpdateUser.class})
    private int id;

    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "First name cannot be empty")
    private String firstName;

    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "Last name cannot be empty")
    private String lastName;

    @Email(groups = {CreateUser.class, UpdateUser.class}, message = "Email not valid!")
    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "Email cannot be empty")
    private String email;

    @UniqueUsername(groups = {CreateUser.class})
    @Size(min = 5, max = 15, groups = {CreateUser.class}, message = "Username should have 5-15 letters")
    @NotBlank(groups = {CreateUser.class})
    private String username;

    @Pattern(groups = {CreateUser.class, UpdateUser.class}, regexp = "[0-9]{9}", message = "Please enter valid phone number")
    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "Phone number cannot be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 5, max = 30, message = "Wrong street!")
    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "Street cannot be empty")
    private String street;

    @Pattern(groups = {CreateUser.class, UpdateUser.class}, regexp = "[0-9]{2}-[0-9]{3}", message = "Please enter valid postcode")
    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "Post code cannot be empty")
    private String postcode;

    @NotBlank(groups = {CreateUser.class, UpdateUser.class}, message = "City cannot be empty")
    private String city;

    @Size(min = 5, max = 15, groups = {CreateUser.class}, message = "Password should have 5-15 letters")
    @NotBlank(groups = {CreateUser.class})
    private String password;

    @Size(min = 5, max = 15, groups = {CreateUser.class}, message = "Password should have 5-15 letters")
    @NotBlank(groups = {CreateUser.class})
    private String matchingPassword;

    /*
     * CorporateCustomer only:
     * */
    @NotBlank(groups = {CreateCorporateCustomer.class, UpdateCorporateCustomer.class}, message = "Company cannot be empty")
    private String companyName;

    @Pattern(groups = {CreateCorporateCustomer.class, UpdateCorporateCustomer.class}, regexp = "[0-9]{10}", message = "Please enter valid Polish VAT number")
    @NotBlank(groups = {CreateCorporateCustomer.class, UpdateCorporateCustomer.class}, message = "VAT number cannot be empty")
    private String vatNumber;

    /*
     * Provider only:
     * */
    @NotNull(groups = {CreateProvider.class, UpdateProvider.class})
    private List<Work> works;

    public UserForm() {
    }

    public UserForm(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setCity(user.getCity());
        this.setStreet(user.getStreet());
        this.setPostcode(user.getPostcode());
        this.setPhoneNumber(user.getPhoneNumber());
    }

    public UserForm(Provider provider) {
        this((User) provider);
        this.setWorks(provider.getWorks());
    }

    public UserForm(RetailCustomer retailCustomer) {
        this((User) retailCustomer);
    }

    public UserForm(CorporateCustomer corporateCustomer) {
        this((User) corporateCustomer);
        this.setCompanyName(corporateCustomer.getCompanyName());
        this.setVatNumber(corporateCustomer.getVatNumber());
    }

}
