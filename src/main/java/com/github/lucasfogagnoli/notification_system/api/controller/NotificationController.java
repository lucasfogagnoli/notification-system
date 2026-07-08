package com.github.lucasfogagnoli.notification_system.api.controller;

import com.github.lucasfogagnoli.notification_system.api.dto.NotificationRequestDto;
import com.github.lucasfogagnoli.notification_system.application.port.input.NotificationPortIn;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationPortIn portIn;

    @PostMapping("/send")
    public Mono<ResponseEntity<Notification>> sendNotification(@Valid @RequestBody NotificationRequestDto notificationRequestDto) {
        log.info("Calling send notification");

        return portIn.sendNotification(notificationRequestDto.message())
                .flatMap(notification -> Mono.just(ResponseEntity.ok(notification)))
                .doOnSuccess(success -> log.info("Notification sent."))
                .onErrorResume(error -> {
                    log.error("Notification not sent.");
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

}
