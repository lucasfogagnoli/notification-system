package com.github.lucasfogagnoli.notification_system.application.usecase;

import com.github.lucasfogagnoli.notification_system.application.port.output.NotificationPortOut;
import com.github.lucasfogagnoli.notification_system.domain.model.NotificationChannel;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSenderFactory {
    private final List<NotificationPortOut> adapters;

    private final Map<NotificationChannel, NotificationPortOut> adaptersMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (NotificationPortOut adapter : adapters) {
            adaptersMap.put(adapter.supports(), adapter);
        }
    }


    public NotificationPortOut getNotificationSender(NotificationChannel channel) {
        return adaptersMap.get(channel);
    }
}
