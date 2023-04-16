package com.appointnow.service;

import com.appointnow.entity.Appointment;
import com.appointnow.entity.ChatMessage;
import com.appointnow.entity.Work;
import com.appointnow.model.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    void createNewAppointment(int workId, int providerId, int customerId, LocalDateTime start);
    void updateAppointment(Appointment appointment);
    void updateUserAppointmentsStatuses(int userId);
    void updateAllAppointmentsStatuses();
    void updateAppointmentsStatusesWithExpiredExchangeRequest();
    void deleteAppointmentById(int appointmentId);
    Appointment getAppointmentWithAuthorization(int id);
    Appointment getAppointmentById(int id);
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentByCustomerId(int customerId);
    List<Appointment> getAppointmentByProviderId(int providerId);
    List<Appointment> getAppointmentsByProviderAtDay(int providerId, LocalDate day);
    List<Appointment> getAppointmentsByCustomerAtDay(int customerId, LocalDate day);
    List<Appointment> getConfirmedAppointmentsByCustomerId(int customerId);
    List<Appointment> getCanceledAppointmentsByCustomerIdForCurrentMonth(int customerId);
    List<TimePeriod> getAvailableHours(int providerId, int customerId, int workId, LocalDate date);
    List<TimePeriod> calculateAvailableHours(List<TimePeriod> availableTimePeriods, Work work);
    List<TimePeriod> excludeAppointmentsFromTimePeriods(List<TimePeriod> periods, List<Appointment> appointments);
    String getCancelNotAllowedReason(int userId, int appointmentId);
    void cancelUserAppointmentById(int appointmentId, int userId);
    boolean isCustomerAllowedToRejectAppointment(int customerId, int appointmentId);
    boolean requestAppointmentRejection(int appointmentId, int customerId);
    boolean requestAppointmentRejection(String token);
    boolean isProviderAllowedToAcceptRejection(int providerId, int appointmentId);
    boolean acceptRejection(int appointmentId, int customerId);
    boolean acceptRejection(String token);
    void addMessageToAppointmentChat(int appointmentId, int authorId, ChatMessage chatMessage);
    int getNumberOfCanceledAppointmentsForUser(int userId);
    int getNumberOfScheduledAppointmentsForUser(int userId);
    boolean isAvailable(int workId, int providerId, int customerId, LocalDateTime start);
}
