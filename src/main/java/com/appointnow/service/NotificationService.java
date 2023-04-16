package com.appointnow.service;

import com.appointnow.entity.Appointment;
import com.appointnow.entity.ChatMessage;
import com.appointnow.entity.ExchangeRequest;
import com.appointnow.entity.Invoice;
import com.appointnow.entity.Notification;
import com.appointnow.entity.user.User;

import java.util.List;

public interface NotificationService {

    void newNotification(String title, String message, String url, User user);
    void markAsRead(int notificationId, int userId);
    void markAllAsRead(int userId);
    Notification getNotificationById(int notificationId);
    List<Notification> getAll(int userId);
    List<Notification> getUnreadNotifications(int userId);
    void newAppointmentFinishedNotification(Appointment appointment, boolean sendEmail);
    void newAppointmentRejectionRequestedNotification(Appointment appointment, boolean sendEmail);
    void newNewAppointmentScheduledNotification(Appointment appointment, boolean sendEmail);
    void newAppointmentCanceledByCustomerNotification(Appointment appointment, boolean sendEmail);
    void newAppointmentCanceledByProviderNotification(Appointment appointment, boolean sendEmail);
    void newAppointmentRejectionAcceptedNotification(Appointment appointment, boolean sendEmail);
    void newChatMessageNotification(ChatMessage chatMessage, boolean sendEmail);
    void newInvoice(Invoice invoice, boolean sendEmail);
    void newExchangeRequestedNotification(Appointment oldAppointment, Appointment newAppointment, boolean sendEmail);
    void newExchangeAcceptedNotification(ExchangeRequest exchangeRequest, boolean sendEmail);
    void newExchangeRejectedNotification(ExchangeRequest exchangeRequest, boolean sendEmail);
}
