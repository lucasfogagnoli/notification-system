package com.github.lucasfogagnoli.notification_system.infrastructure.notification.email;

import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import com.github.lucasfogagnoli.notification_system.domain.model.NotificationChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationAdapter implements NotificationPortOut {

    @Override
    public Mono<Boolean> sendNotification(Notification notification) {
        log.info(notification.channel().toString());
        return Mono.just(Boolean.TRUE);
    }

    @Override
    public NotificationChannel supports() {
        return NotificationChannel.EMAIL;
    }
}
