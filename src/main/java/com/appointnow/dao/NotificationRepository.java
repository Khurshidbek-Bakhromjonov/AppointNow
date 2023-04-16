package com.appointnow.dao;

import com.appointnow.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query("select n from Notification n join n.user u where u.id = :userId and n.isRead=false")
    List<Notification> getAllUnreadNotifications(@Param("userId") int userId);
}
