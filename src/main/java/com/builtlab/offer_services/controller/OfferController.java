package com.builtlab.offer_services.controller;


import com.builtlab.offer_services.dto.request.OfferCreationRequest;
import com.builtlab.offer_services.dto.request.OfferUpdateRequest;
import com.builtlab.offer_services.dto.response.ApiResponse;
import com.builtlab.offer_services.dto.response.OfferResponse;
import com.builtlab.offer_services.exception.ErrorCode;
import com.builtlab.offer_services.services.OfferService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OfferController {
    OfferService offerService;

    @PostMapping
    ApiResponse<OfferResponse> createOffer(@RequestBody OfferCreationRequest request) {
        ApiResponse<OfferResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(offerService.createOffer(request));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessage(ErrorCode.SUCCESS.getMessage());
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<Map<String, Object>> getAllOffers(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int offset) {

        if (limit <= 0) limit = 10;
        if (offset < 1) offset = 1;

        List<OfferResponse> offers = offerService.getAllOffers(limit, offset);

        long totalItems = offerService.getTotalOfferCount();

        int totalPages = (int) Math.ceil((double) totalItems / limit);

        Map<String, Object> result = new HashMap<>();
        result.put("offers", offers);
        result.put("limit", limit);
        result.put("offset", offset);
        result.put("totalItems", totalItems);
        result.put("totalPages", totalPages);

        // Create and populate ApiResponse
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(2000);
        apiResponse.setMessage("Success");
        apiResponse.setResult(result);

        return apiResponse;
    }



    @PutMapping("{offerId}")
    ApiResponse<OfferResponse> updateOffer(@PathVariable("offerId") String offerId, @RequestBody OfferUpdateRequest request) {
        return  ApiResponse.<OfferResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .result(offerService.updateOffer(offerId, request))
                .build();
    }

}
