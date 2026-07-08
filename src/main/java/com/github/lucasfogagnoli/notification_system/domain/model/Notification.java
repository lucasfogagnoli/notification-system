package com.github.lucasfogagnoli.notification_system.domain.model;

import lombok.Builder;

@Builder
public record Notification(
        String message) {
}
