package com.builtlab.offer_services.controller;


import com.builtlab.offer_services.dto.request.OfferCreationRequest;
import com.builtlab.offer_services.dto.response.ApiResponse;
import com.builtlab.offer_services.dto.response.OfferResponse;
import com.builtlab.offer_services.exception.ErrorCode;
import com.builtlab.offer_services.services.OfferService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
