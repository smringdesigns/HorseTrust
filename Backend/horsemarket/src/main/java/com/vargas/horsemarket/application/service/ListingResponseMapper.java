package com.vargas.horsemarket.application.service;

import com.vargas.horsemarket.application.dto.ListingResponse;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.User;
import org.springframework.stereotype.Service;

/**
 * Servicio auxiliar para convertir HorseListing → ListingResponse DTO.
 */
@Service
public class ListingResponseMapper {

    public ListingResponse toResponse(HorseListing listing, User seller) {
        return ListingResponse.builder()
                .id(listing.getId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .horseName(listing.getHorseName())
                .breed(listing.getBreed())
                .ageYears(listing.getAgeYears())
                .sex(listing.getSex())
                .discipline(listing.getDiscipline())
                .level(listing.getLevel())
                .price(listing.getPrice() != null ? listing.getPrice().getAmount() : null)
                .currencyCode(listing.getPrice() != null ? listing.getPrice().getCurrencyCode() : "MXN")
                .location(listing.getLocation())
                .status(listing.getStatus())
                .premium(listing.isPremium())
                .sellerId(listing.getSellerId())
                .sellerName(seller != null ? seller.getFullName() : null)
                .imageUrls(listing.getImageUrls())
                .publishedAt(listing.getPublishedAt())
                .createdAt(listing.getCreatedAt())
                .build();
    }
}
