package com.mta.fcm.service;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.mta.fcm.dto.NotificationDTO;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private final FirebaseMessaging firebaseMessaging;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Override
    public void sendPushNotification(NotificationDTO notificationDTO) throws FirebaseMessagingException {
        logger.info("Received notification request: {}", notificationDTO);
        if (Objects.isNull(notificationDTO)) {
            throw new IllegalArgumentException("NotificationDTO cannot be null");
        }
        if (Objects.isNull(notificationDTO.getDataMap())) {
            notificationDTO.setDataMap(new HashMap<>());
        }
        Message message = null;
        if (Objects.nonNull(notificationDTO.getTopic()) && !notificationDTO.getTopic().isEmpty()) {
            message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(notificationDTO.getTitle())
                            .setBody(notificationDTO.getBody())
                            .setImage(notificationDTO.getImageUrl())
                            .build())
                    .setTopic(notificationDTO.getTopic())
                    .putAllData(notificationDTO.getDataMap())
                    .build();
        } else {
            message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(notificationDTO.getTitle())
                            .setBody(notificationDTO.getBody())
                            .setImage(notificationDTO.getImageUrl())
                            .build())
                    .setToken(notificationDTO.getToken())
                    .putAllData(notificationDTO.getDataMap())
                    .build();
        }
        logger.info("Sending notification: {}", message);
        firebaseMessaging.send(message);
    }

}
