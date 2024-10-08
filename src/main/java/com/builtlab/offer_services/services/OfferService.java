package com.builtlab.offer_services.services;

import com.builtlab.offer_services.dto.request.OfferCreationRequest;
import com.builtlab.offer_services.dto.request.OfferUpdateRequest;
import com.builtlab.offer_services.dto.response.OfferResponse;
import com.builtlab.offer_services.entity.Offer;
import com.builtlab.offer_services.exception.AppException;
import com.builtlab.offer_services.exception.ErrorCode;
import com.builtlab.offer_services.mapper.OfferMapper;
import com.builtlab.offer_services.repository.OfferRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OfferService {
    OfferRepository offerRepository;
    OfferMapper offerMapper;

    public long getTotalOfferCount() {
        return offerRepository.count();
    }

    public OfferResponse createOffer(OfferCreationRequest request) {

        Offer offer = offerMapper.toOffer(request);

        double originalPrice = offer.getOriginalPrice();
        double discountPercentage = offer.getDiscountPercentage();
        double discountedPrice = originalPrice - (originalPrice * discountPercentage / 100.0);
        offer.setDiscountedPrice(discountedPrice);
        offer.setCreatedAt(LocalDateTime.now());
        return offerMapper.toOfferResponse(offerRepository.save(offer));
    }

    public List<OfferResponse> getAllOffers(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset - 1, limit);

        Page<Offer> offerPage = offerRepository.findAll(pageable);

        return offerPage.stream().map(offerMapper::toOfferResponse).toList();
    }

    public OfferResponse getOffer(String id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        return offerMapper.toOfferResponse(offer);
    }

    public OfferResponse updateOffer(String id, OfferUpdateRequest request) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        offerMapper.updateOffer(offer, request);

        double originalPrice = offer.getOriginalPrice();
        double discountPercentage = offer.getDiscountPercentage();
        double discountedPrice = originalPrice - (originalPrice * discountPercentage / 100.0);
        offer.setDiscountedPrice(discountedPrice);

        offer.setUpdatedAt(request.getUpdatedAt() != null ? request.getUpdatedAt() : LocalDateTime.now());

        return offerMapper.toOfferResponse(offerRepository.save(offer));
    }


    public void deleteOffer(String id) {
        offerRepository.deleteById(id);
    }

    public Offer calculateInfoOfOffer(Offer offer) {

        return offer;
    }


}
