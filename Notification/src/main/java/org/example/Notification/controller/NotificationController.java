package org.example.Notification.controller;

import org.example.Notification.dto.request.NotificationCreateDto;
import org.example.Notification.dto.request.NotificationUpdateDto;
import org.example.Notification.dto.response.NotificationDto;
import org.example.Notification.dto.response.NotificationStatusDto;
import org.example.Notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;


    @PostMapping("/create")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationCreateDto notificationCreateDto) {
        NotificationDto createdNotification = notificationService.createNotification(notificationCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdNotification);
    }

    @GetMapping("/get/{notificationId}")
    public ResponseEntity<NotificationDto> fetchNotification(@PathVariable("notificationId") String notificationId) {
        NotificationDto notification = notificationService.fetchNotification(notificationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notification);
    }

    @PutMapping("/update")
    public ResponseEntity<NotificationDto> updateNotificationSettings(@RequestBody NotificationUpdateDto notificationUpdateDto) {
        NotificationDto updatedNotification = notificationService.updateNotificationSettings(notificationUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedNotification);
    }

    @DeleteMapping("/delete-expired")
    public ResponseEntity<String> deleteExpiredNotifications(@RequestParam(defaultValue = "30") int retentionDays) {
        boolean deleted = notificationService.deleteExpiredNotifications(retentionDays);
        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Expired notifications deleted successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("No expired notifications found");
        }
    }

    @PostMapping("/resend-failed/{notificationId}")
    public ResponseEntity<String> resendFailedNotification(@PathVariable("notificationId") String notificationId) {
        String result = notificationService.resendFailedNotification(notificationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/schedule")
    public ResponseEntity<NotificationDto> scheduleNotification(@RequestBody NotificationCreateDto notificationCreateDto) {
        NotificationDto scheduledNotification = notificationService.scheduleNotification(notificationCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduledNotification);
    }

    @PostMapping("/send-sms/{notificationId}")
    public ResponseEntity<String> sendSmsNotification(@PathVariable("notificationId") String notificationId) {
        String result = notificationService.sendSmsNotification(notificationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/send-email/{notificationId}")
    public ResponseEntity<String> sendEmailNotification(@PathVariable("notificationId") String notificationId) {
        String result = notificationService.sendEmailNotification(notificationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PatchMapping("/mark-read/{notificationId}")
    public ResponseEntity<String> markAsRead(@PathVariable("notificationId") String notificationId) {
        String result = notificationService.markAsRead(notificationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/status/{notificationId}")
    public ResponseEntity<NotificationStatusDto> getNotificationStatus(@PathVariable("notificationId") String notificationId) {
        NotificationStatusDto status = notificationService.getNotificationStatus(notificationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(status);
    }

    @GetMapping("/history/{recipientId}")
    public ResponseEntity<List<NotificationDto>> getNotificationHistory(@PathVariable("recipientId") Long recipientId) {
        List<NotificationDto> history = notificationService.getNotificationHistory(recipientId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(history);
    }
}