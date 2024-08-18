package com.builtlab.offer_services.mapper;

import com.builtlab.offer_services.dto.request.OfferCreationRequest;
import com.builtlab.offer_services.dto.request.OfferUpdateRequest;
import com.builtlab.offer_services.dto.response.OfferResponse;
import com.builtlab.offer_services.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    Offer toOffer(OfferCreationRequest request);
    OfferResponse toOfferResponse(Offer offer);
    void updateOffer(@MappingTarget Offer offer, OfferUpdateRequest request);
}
