package com.github.lucasfogagnoli.notification_system.application.usecase;

import com.github.lucasfogagnoli.notification_system.application.port.input.NotificationPortIn;
import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationUseCase implements NotificationPortIn {

    private final NotificationPortOut portOut;

    @Override
    public Mono<Notification> sendNotification(String message) {
        return portOut.sendNotification(message)
                .filter(notificationSent -> notificationSent)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Notification not sent.")))
                .doOnSuccess(success -> log.info("Adapter called successful."))
                .thenReturn(Notification.builder().message(message).build());
    }
}
