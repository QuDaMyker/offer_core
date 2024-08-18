package com.builtlab.offer_services.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfferCreationRequest {
    String title;
    String description;
    double discountPercentage;
    double originalPrice;
    LocalDateTime createdAt;
    LocalDateTime  updatedAt;
}
