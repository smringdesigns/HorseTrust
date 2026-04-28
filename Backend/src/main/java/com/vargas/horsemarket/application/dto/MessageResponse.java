package com.vargas.horsemarket.application.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponse {
    private Long id;
    private Long listingId;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String content;
    private boolean read;
    private LocalDateTime createdAt;
}
