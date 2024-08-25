package com.mta.fcm.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.mta.fcm.dto.NotificationDTO;

public interface INotificationService {
    public void sendPushNotification(NotificationDTO notificationDTO) throws FirebaseMessagingException;
}