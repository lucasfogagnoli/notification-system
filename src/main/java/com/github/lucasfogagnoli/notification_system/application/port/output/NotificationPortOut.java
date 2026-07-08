package com.github.lucasfogagnoli.notification_system.application.port.output;

import reactor.core.publisher.Mono;

public interface NotificationPortOut {
    Mono<Boolean> sendNotification(String message);
}
