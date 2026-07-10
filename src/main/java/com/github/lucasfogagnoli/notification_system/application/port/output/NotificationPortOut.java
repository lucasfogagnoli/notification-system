package com.github.lucasfogagnoli.notification_system.application.port.output;

import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import reactor.core.publisher.Mono;

public interface NotificationPortOut {
    Mono<Boolean> sendNotification(Notification notification);
}
