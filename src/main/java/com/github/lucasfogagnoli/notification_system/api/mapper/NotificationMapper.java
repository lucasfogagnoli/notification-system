package com.github.lucasfogagnoli.notification_system.api.mapper;

import com.github.lucasfogagnoli.notification_system.api.dto.NotificationRequestDto;
import com.github.lucasfogagnoli.notification_system.domain.model.Notification;
import com.github.lucasfogagnoli.notification_system.domain.model.NotificationChannel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public abstract class NotificationMapper {
    @Mapping(target = "channel", source = "channel", qualifiedByName = "mapNotificationChannel")
    public abstract Notification toDomain(NotificationRequestDto dto);

    @Named("mapNotificationChannel")
    protected NotificationChannel mapNotificationChannel(String channel) {
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("channel is necessary.");
        }
        try {
            return NotificationChannel.valueOf(channel.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid channel: '%s'. Accepted values: %s",
                    channel, Arrays.toString(NotificationChannel.values())));
        }
    }
}
