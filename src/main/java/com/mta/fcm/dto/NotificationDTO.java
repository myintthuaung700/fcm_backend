package com.mta.fcm.dto;

import java.util.Map;

import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    private String title;
    private String body;
    private String token;
    private String topic;
    private String imageUrl;
    private Map<String, String> dataMap;
}
