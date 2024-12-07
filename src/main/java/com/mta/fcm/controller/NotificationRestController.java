package com.mta.fcm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.mta.fcm.dto.NotificationDTO;
import com.mta.fcm.service.INotificationService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("notification")
@CrossOrigin("*")
@RequiredArgsConstructor
public class NotificationRestController {
    private final INotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationRestController.class);

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            notificationService.sendPushNotification(notificationDTO);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (FirebaseMessagingException e) {
            logger.error("Error sending notification : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending notification :" + e.getMessage());
        } catch (Exception e) {
            logger.error("Error sending notification : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending notification :" + e.getMessage());
        }
    }
}
