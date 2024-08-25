package com.mta.fcm.service;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.mta.fcm.dto.NotificationDTO;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Override
    public void sendPushNotification(NotificationDTO notificationDTO) throws FirebaseMessagingException {
        if (Objects.isNull(notificationDTO)) {
            throw new IllegalArgumentException("NotificationDTO cannot be null");
        }
        if (Objects.isNull(notificationDTO.getToken())) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        if (Objects.isNull(notificationDTO.getDataMap())) {
            notificationDTO.setDataMap(new HashMap<>());
        }
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(notificationDTO.getTitle())
                        .setBody(notificationDTO.getBody())
                        .setImage(notificationDTO.getImageUrl())
                        .build())
                .setToken(notificationDTO.getToken())
                .setTopic(notificationDTO.getTopic())
                .putAllData(notificationDTO.getDataMap())
                .build();
        firebaseMessaging.send(message);
    }

}
