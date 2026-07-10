package com.github.lucasfogagnoli.notification_system.api.dto;

public record NotificationRequestDto(
        String channel,
        String recipient,
        String message) {
}
