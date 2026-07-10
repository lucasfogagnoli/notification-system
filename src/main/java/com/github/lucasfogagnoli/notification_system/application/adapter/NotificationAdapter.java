package com.github.lucasfogagnoli.notification_system.application.adapter;

import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationPortOut {
    @Override
    public Mono<Boolean> sendNotification(Notification notification) {
        switch (notification.channel()) {
            case EMAIL -> {
                return sendEmail();
            }
            case SMS -> {
                return sendSMS();
            }
            case WHATSAPP -> {
                return sendWhatsApp();
            }
            case PUSH -> {
                return sendPush();
            }
            default -> {
                log.error("Channel incorrect.");
                return Mono.just(Boolean.FALSE);
            }
        }

    }

    public Mono<Boolean> sendEmail() {
        return Mono.just(Boolean.TRUE);
    }

    public Mono<Boolean> sendSMS() {
        return Mono.just(Boolean.TRUE);
    }

    public Mono<Boolean> sendWhatsApp() {
        return Mono.just(Boolean.TRUE);
    }

    public Mono<Boolean> sendPush() {
        return Mono.just(Boolean.TRUE);
    }
}
