package com.mta.fcm.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("notification")
@CrossOrigin("*")
public class NotificationRestController {
    @Autowired
    private INotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        try {
            notificationService.sendPushNotification(notificationDTO);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending notification");
        }
    }
}
