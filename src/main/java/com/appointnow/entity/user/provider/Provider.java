package com.appointnow.entity.user.provider;

import com.appointnow.entity.Appointment;
import com.appointnow.entity.Work;
import com.appointnow.entity.WorkingPlan;
import com.appointnow.entity.user.Role;
import com.appointnow.entity.user.User;
import com.appointnow.model.UserForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "providers")
@PrimaryKeyJoinColumn(name = "id_provider")
public class Provider extends User {

    @OneToMany(mappedBy = "provider")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "works_providers", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_work"))
    private List<Work> works;

    @OneToOne(mappedBy = "provider", cascade = {CascadeType.ALL})
    private WorkingPlan workingPlan;

    public Provider() {
    }

    public Provider(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles, WorkingPlan workingPlan) {
        super(userFormDTO, encryptedPassword, roles);
        this.workingPlan = workingPlan;
        workingPlan.setProvider(this);
        this.works = userFormDTO.getWorks();
    }

    @Override
    public void update(UserForm updateData) {
        super.update(updateData);
        this.works = updateData.getWorks();
    }

    public List<Work> getCorporateWorks() {
        List<Work> corporateWorks = new ArrayList<>();
        for (Work work : works) {
            if (work.getTargetCustomer().equals("corporate"))
                corporateWorks.add(work);
        }
        return corporateWorks;
    }

    public List<Work> getRetailWorks() {
        List<Work> retailWorks = new ArrayList<>();
        for (Work work : works) {
            if (work.getTargetCustomer().equals("retail")) {
                retailWorks.add(work);
            }
        }
        return retailWorks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Provider provider = (Provider) o;
        return provider.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointments, works, workingPlan);
    }
}
