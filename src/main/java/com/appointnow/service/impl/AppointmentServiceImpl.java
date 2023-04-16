package com.appointnow.service.impl;

import com.appointnow.dao.AppointmentRepository;
import com.appointnow.entity.Appointment;
import com.appointnow.entity.ChatMessage;
import com.appointnow.entity.Work;
import com.appointnow.model.TimePeriod;
import com.appointnow.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void createNewAppointment(int workId, int providerId, int customerId, LocalDateTime start) {

    }

    @Override
    public void updateAppointment(Appointment appointment) {

    }

    @Override
    public void updateUserAppointmentsStatuses(int userId) {

    }

    @Override
    public void updateAllAppointmentsStatuses() {

    }

    @Override
    public void updateAppointmentsStatusesWithExpiredExchangeRequest() {

    }

    @Override
    public void deleteAppointmentById(int appointmentId) {

    }

    @Override
    public Appointment getAppointmentWithAuthorization(int id) {
        return null;
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentByCustomerId(int customerId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentByProviderId(int providerId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByProviderAtDay(int providerId, LocalDate day) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByCustomerAtDay(int customerId, LocalDate day) {
        return null;
    }

    @Override
    public List<Appointment> getConfirmedAppointmentsByCustomerId(int customerId) {
        return null;
    }

    @Override
    public List<Appointment> getCanceledAppointmentsByCustomerIdForCurrentMonth(int userId) {
        return null;
    }

    @Override
    public List<TimePeriod> getAvailableHours(int providerId, int customerId, int workId, LocalDate date) {
        return null;
    }

    @Override
    public List<TimePeriod> calculateAvailableHours(List<TimePeriod> availableTimePeriods, Work work) {
        return null;
    }

    @Override
    public List<TimePeriod> excludeAppointmentsFromTimePeriods(List<TimePeriod> periods, List<Appointment> appointments) {
        return null;
    }

    @Override
    public String getCancelNotAllowedReason(int userId, int appointmentId) {
        return null;
    }

    @Override
    public void cancelUserAppointmentById(int appointmentId, int userId) {

    }

    @Override
    public boolean isCustomerAllowedToRejectAppointment(int customerId, int appointmentId) {
        return false;
    }

    @Override
    public boolean requestAppointmentRejection(int appointmentId, int customerId) {
        return false;
    }

    @Override
    public boolean requestAppointmentRejection(String token) {
        return false;
    }

    @Override
    public boolean isProviderAllowedToAcceptRejection(int providerId, int appointmentId) {
        return false;
    }

    @Override
    public boolean acceptRejection(int appointmentId, int providerId) {
        return false;
    }

    @Override
    public boolean acceptRejection(String token) {
        return false;
    }

    @Override
    public void addMessageToAppointmentChat(int appointmentId, int authorId, ChatMessage chatMessage) {

    }

    @Override
    public int getNumberOfCanceledAppointmentsForUser(int userId) {
        return 0;
    }

    @Override
    public int getNumberOfScheduledAppointmentsForUser(int userId) {
        return 0;
    }

    @Override
    public boolean isAvailable(int workId, int providerId, int customerId, LocalDateTime start) {
        return false;
    }
}
