package com.appointnow.service.impl;

import com.appointnow.dao.RoleRepository;
import com.appointnow.dao.user.UserRepository;
import com.appointnow.dao.user.customer.CorporateCustomerRepository;
import com.appointnow.dao.user.customer.CustomerRepository;
import com.appointnow.dao.user.customer.RetailCustomerRepository;
import com.appointnow.dao.user.provider.ProviderRepository;
import com.appointnow.entity.Work;
import com.appointnow.entity.WorkingPlan;
import com.appointnow.entity.user.Role;
import com.appointnow.entity.user.User;
import com.appointnow.entity.user.customer.CorporateCustomer;
import com.appointnow.entity.user.customer.Customer;
import com.appointnow.entity.user.customer.RetailCustomer;
import com.appointnow.entity.user.provider.Provider;
import com.appointnow.model.ChangePasswordForm;
import com.appointnow.model.UserForm;
import com.appointnow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ProviderRepository providerRepository;
    private final CustomerRepository customerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final RetailCustomerRepository retailCustomerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean userExists(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    @Override
    @PreAuthorize("#userId == principal.id")
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new  UsernameNotFoundException("User not found!"));
    }

    @Override
    public User getUserByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<User> getUsersByRoleName(String roleName) {
        return userRepository.findByRoleName(roleName);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @PreAuthorize("#passwordChangeForm.id == principal.id")
    public void updateUserPassword(ChangePasswordForm passwordChangeForm) {
        User user = userRepository.getOne(passwordChangeForm.getId());
        user.setPassword(passwordEncoder.encode(passwordChangeForm.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Provider getProviderById(int providerId) {
        return providerRepository.findById(providerId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<Provider> getProvidersWithRetailWorks() {
        return providerRepository.findAllWithRetailWorks();
    }

    @Override
    public List<Provider> getProvidersWithCorporateWorks() {
        return providerRepository.findAllWithCorporateWorks();
    }

    @Override
    public List<Provider> getProvidersByWork(Work work) {
        return providerRepository.findByWorks(work);
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public void saveNewProvider(UserForm userForm) {
        WorkingPlan workingPlan = WorkingPlan.generateDefaultWorkingPlan();
        Provider provider = new Provider(userForm, passwordEncoder.encode(userForm.getPassword()), getRolesForProvider(), workingPlan);
        providerRepository.save(provider);
    }

    @Override
    @PreAuthorize("#updateData.id==principal.id or hasRole('ADMIN')")
    public void updateProviderProfile(UserForm updateData) {
        Provider provider = providerRepository.getOne(updateData.getId());
        provider.update(updateData);
        providerRepository.save(provider);
    }

    @Override
    public Collection<Role> getRolesForProvider() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_CUSTOMER_CORPORATE"));
        roles.add(roleRepository.findByName("ROLE_CUSTOMER"));
        return roles;
    }

    @Override
    @PreAuthorize("#customerId == principal.id or hasRole('ADMIN')")
    public Customer getCustomerById(int customerId) {
        return customerRepository.getOne(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @PreAuthorize("#retailCustomerId == principal.id or hasRole('ADMIN')")
    public RetailCustomer getRetailCustomerById(int retailCustomerId) {
        return retailCustomerRepository.findById(retailCustomerId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public void saveNewRetailCustomer(UserForm userForm) {
        RetailCustomer retailCustomer = new RetailCustomer(userForm, passwordEncoder.encode(userForm.getPassword()), getRolesForRetailCustomer());
        retailCustomerRepository.save(retailCustomer);
    }

    @Override
    @PreAuthorize("#updateData.id == principal.id or hasRole('ADMIN')")
    public void updateRetailCustomerProfile(UserForm updateData) {
        RetailCustomer retailCustomer = retailCustomerRepository.getOne(updateData.getId());
        retailCustomer.update(updateData);
        retailCustomerRepository.save(retailCustomer);
    }

    @Override
    public Collection<Role> getRolesForRetailCustomer() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_CUSTOMER_RETAIL"));
        roles.add(roleRepository.findByName("ROLE_CUSTOMER"));
        return roles;
    }

    @Override
    @PreAuthorize("#corporateCustomerId == principal.id or hasRole('ADMIN')")
    public CorporateCustomer getCorporateCustomerById(int corporateCustomerId) {
        return corporateCustomerRepository.findById(corporateCustomerId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<RetailCustomer> getAllRetailCustomers() {
        return retailCustomerRepository.findAll();
    }

    @Override
    public void saveNewCorporateCustomer(UserForm userForm) {
        CorporateCustomer corporateCustomer = new CorporateCustomer(userForm, passwordEncoder.encode(userForm.getPassword()), getRoleForCorporateCustomers());
        corporateCustomerRepository.save(corporateCustomer);
    }

    @Override
    @PreAuthorize("#updateData.id == principal.id or hasRole('ADMIN')")
    public void updateCorporateCustomerProfile(UserForm updateData) {
        CorporateCustomer corporateCustomer = corporateCustomerRepository.getOne(updateData.getId());
        corporateCustomer.update(updateData);
        corporateCustomerRepository.save(corporateCustomer);
    }

    @Override
    public Collection<Role> getRoleForCorporateCustomers() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_PROVIDER"));
        return roles;
    }
}
