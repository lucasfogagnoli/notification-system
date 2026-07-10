package com.github.lucasfogagnoli.notification_system.application.port.input;

import com.github.lucasfogagnoli.notification_system.api.dto.NotificationRequestDto;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import reactor.core.publisher.Mono;

public interface NotificationPortIn {
    Mono<Notification> sendNotification(NotificationRequestDto dto);
}
