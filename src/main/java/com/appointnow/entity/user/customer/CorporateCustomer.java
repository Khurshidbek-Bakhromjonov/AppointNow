package com.appointnow.entity.user.customer;

import com.appointnow.entity.user.Role;
import com.appointnow.model.UserForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "id_customer")
public class CorporateCustomer extends Customer {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;

    public CorporateCustomer() {
    }

    public CorporateCustomer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
        this.companyName = userFormDTO.getCompanyName();
        this.vatNumber = userFormDTO.getVatNumber();
    }

    @Override
    public void update(UserForm updateData) {
        super.update(updateData);
        this.companyName = updateData.getCompanyName();
        this.vatNumber = updateData.getVatNumber();
    }
}
