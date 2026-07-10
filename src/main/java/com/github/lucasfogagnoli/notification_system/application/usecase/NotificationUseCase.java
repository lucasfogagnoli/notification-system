package com.github.lucasfogagnoli.notification_system.application.usecase;

import com.github.lucasfogagnoli.notification_system.api.dto.NotificationRequestDto;
import com.github.lucasfogagnoli.notification_system.api.mapper.NotificationMapper;
import com.github.lucasfogagnoli.notification_system.application.port.input.NotificationPortIn;
import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import com.github.lucasfogagnoli.notification_system.infrastructure.notification.email.EmailNotificationAdapter;
import com.github.lucasfogagnoli.notification_system.infrastructure.notification.push.PushNotificationAdapter;
import com.github.lucasfogagnoli.notification_system.infrastructure.notification.sms.SmsNotificationAdapter;
import com.github.lucasfogagnoli.notification_system.infrastructure.notification.whatsapp.WhatsAppNotificationAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationUseCase implements NotificationPortIn {

    //private final NotificationPortOut portOut;
    private final NotificationMapper mapper;
    private final EmailNotificationAdapter emailAdapter;
    private final SmsNotificationAdapter smsAdapter;
    private final WhatsAppNotificationAdapter whatsAppAdapter;

    private final PushNotificationAdapter pushAdapter;
    private static final String EXCEPTION_MESSAGE = "Notification not sent.";
    private static final String SUCCESS_MESSAGE = "Adapter called successful.";

    @Override
    public Mono<Notification> sendNotification(NotificationRequestDto requestDto) {
        Notification notification = mapper.toDomain(requestDto);

        return switch (notification.channel()) {
            case EMAIL -> emailAdapter.sendNotification(notification)
                    .filter(notificationSent -> notificationSent)
                    .switchIfEmpty(Mono.error(new IllegalArgumentException(EXCEPTION_MESSAGE)))
                    .doOnSuccess(success -> log.info(SUCCESS_MESSAGE))
                    .thenReturn(notification);
            case SMS -> smsAdapter.sendNotification(notification)
                        .filter(notificationSent -> notificationSent)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException(EXCEPTION_MESSAGE)))
                        .doOnSuccess(success -> log.info(SUCCESS_MESSAGE))
                        .thenReturn(notification);
            case WHATSAPP -> whatsAppAdapter.sendNotification(notification)
                        .filter(notificationSent -> notificationSent)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException(EXCEPTION_MESSAGE)))
                        .doOnSuccess(success -> log.info(SUCCESS_MESSAGE))
                        .thenReturn(notification);
            case PUSH -> pushAdapter.sendNotification(notification)
                        .filter(notificationSent -> notificationSent)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException(EXCEPTION_MESSAGE)))
                        .doOnSuccess(success -> log.info(SUCCESS_MESSAGE))
                        .thenReturn(notification);
        };
    }
}
