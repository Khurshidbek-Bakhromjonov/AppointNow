package com.appointnow.service.impl;

import com.appointnow.dao.WorkRepository;
import com.appointnow.entity.Work;
import com.appointnow.entity.user.customer.Customer;
import com.appointnow.exception.WorkNotFoundException;
import com.appointnow.service.UserService;
import com.appointnow.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final UserService userService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void createNewWork(Work work) {
        workRepository.save(work);
    }

    @Override
    public Work getWorkById(int workId) {
        return workRepository.findById(workId)
                .orElseThrow(WorkNotFoundException::new);
    }

    @Override
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    @Override
    public List<Work> getWorksByProviderId(int providerId) {
        return workRepository.findByProviderId(providerId);
    }

    @Override
    public List<Work> getRetailCustomerWorks() {
        return workRepository.findByTargetCustomer("retail");
    }

    @Override
    public List<Work> getCorporateCustomerWorks() {
        return workRepository.findByTargetCustomer("corporate");
    }

    @Override
    public List<Work> getWorksForRetailCustomerByProviderId(int providerId) {
        return workRepository.findByTargetCustomerAndProviderId("retail", providerId);
    }

    @Override
    public List<Work> getWorksForCorporateCustomerByProviderId(int providerId) {
        return workRepository.findByTargetCustomerAndProviderId("corporate", providerId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void updateWork(Work workUpdateData) {
        Work work = getWorkById(workUpdateData.getId());
        work.setName(workUpdateData.getName());
        work.setPrice(workUpdateData.getPrice());
        work.setDuration(workUpdateData.getDuration());
        work.setDescription(workUpdateData.getDescription());
        work.setEditable(workUpdateData.getEditable());
        work.setTargetCustomer(workUpdateData.getTargetCustomer());
        workRepository.save(work);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteWorkById(int workId) {
        workRepository.deleteById(workId);
    }

    @Override
    public boolean isWorkForCustomer(int workId, int customerId) {
        Customer customer = userService.getCustomerById(customerId);
        Work work = getWorkById(workId);
        if (customer.hasRole("ROLE_CUSTOMER_RETAIL") && !work.getTargetCustomer().equals("retail")) {
            return false;
        } else
            return !customer.hasRole("ROLE_CUSTOMER_CORPORATE") || work.getTargetCustomer().equals("corporate");
    }
}
