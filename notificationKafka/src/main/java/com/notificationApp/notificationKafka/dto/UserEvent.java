package com.notificationApp.notificationKafka.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private String email;
    private String event;
}
