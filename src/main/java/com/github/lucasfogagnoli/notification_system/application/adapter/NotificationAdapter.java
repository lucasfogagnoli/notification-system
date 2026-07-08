package com.github.lucasfogagnoli.notification_system.application.adapter;

import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationPortOut {
    @Override
    public Mono<Boolean> sendNotification(String message) {
        return !message.isEmpty() ? Mono.just(Boolean.TRUE) : Mono.just(Boolean.FALSE);
    }
}
