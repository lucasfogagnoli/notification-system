package com.github.lucasfogagnoli.notification_system.application.usecase;

import com.github.lucasfogagnoli.notification_system.api.dto.NotificationRequestDto;
import com.github.lucasfogagnoli.notification_system.api.mapper.NotificationMapper;
import com.github.lucasfogagnoli.notification_system.application.port.input.NotificationPortIn;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationUseCase implements NotificationPortIn {

    private final NotificationMapper mapper;
    private final NotificationSenderFactory factory;

    private static final String EXCEPTION_MESSAGE = "Notification not sent.";
    private static final String SUCCESS_MESSAGE = "Adapter called successful.";

    @Override
    public Mono<Notification> sendNotification(NotificationRequestDto requestDto) {
        Notification notification = mapper.toDomain(requestDto);

        return this.processSendingResult(factory.getNotificationSender(notification.channel()).sendNotification(notification), notification);
    }

    public Mono<Notification> processSendingResult(Mono<Boolean> notificationSentResponse, Notification notification) {
        return notificationSentResponse
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new IllegalArgumentException(EXCEPTION_MESSAGE)))
                .doOnSuccess(success -> log.info(SUCCESS_MESSAGE))
                .thenReturn(notification);
    }
}
