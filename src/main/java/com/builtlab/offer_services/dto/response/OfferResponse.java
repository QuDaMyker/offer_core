package com.builtlab.offer_services.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferResponse {
    String id;
    String title;
    String description;
    double discountPercentage;
    double originalPrice;
    double discountedPrice;
    LocalDate createdAt;
    LocalDate updatedAt;
}
