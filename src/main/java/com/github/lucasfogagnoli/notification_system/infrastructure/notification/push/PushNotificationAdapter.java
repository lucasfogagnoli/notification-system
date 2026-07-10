package com.github.lucasfogagnoli.notification_system.infrastructure.notification.push;

import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class PushNotificationAdapter implements NotificationPortOut {

    @Override
    public Mono<Boolean> sendNotification(Notification notification) {
        return Mono.just(Boolean.TRUE);
    }
}
