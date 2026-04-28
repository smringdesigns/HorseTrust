package com.vargas.horsemarket.application.usecase;

import com.vargas.horsemarket.application.dto.ListingResponse;
import com.vargas.horsemarket.application.dto.PageResponse;
import com.vargas.horsemarket.application.service.ListingResponseMapper;
import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Caso de uso: Consulta paginada de anuncios.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ListingQueryUseCase {

    private final HorseListingRepository listingRepository;
    private final UserRepository userRepository;
    private final ListingResponseMapper responseMapper;

    @Transactional(readOnly = true)
    public PageResponse<ListingResponse> findPublishedListings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<HorseListing> listings = listingRepository.findByStatus(ListingStatus.PUBLISHED, pageable);
        return toPageResponse(listings);
    }

    @Transactional(readOnly = true)
    public PageResponse<ListingResponse> findVerifiedListings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<HorseListing> listings = listingRepository.findByStatus(ListingStatus.VERIFIED, pageable);
        return toPageResponse(listings);
    }

    @Transactional(readOnly = true)
    public PageResponse<ListingResponse> findByBreed(String breed, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<HorseListing> listings = listingRepository.findByBreedContainingIgnoreCase(breed, pageable);
        return toPageResponse(listings);
    }

    @Transactional(readOnly = true)
    public ListingResponse findById(Long id) {
        HorseListing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HorseListing", id));

        User seller = userRepository.findById(listing.getSellerId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor", listing.getSellerId()));

        return responseMapper.toResponse(listing, seller);
    }

    @Transactional(readOnly = true)
    public PageResponse<ListingResponse> findMyListings(String sellerEmail, int page, int size) {
        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<HorseListing> listings = listingRepository.findBySellerId(seller.getId(), pageable);
        return toPageResponse(listings);
    }

    private PageResponse<ListingResponse> toPageResponse(Page<HorseListing> page) {
        return PageResponse.<ListingResponse>builder()
                .content(page.getContent().stream()
                        .map(listing -> {
                            User seller = userRepository.findById(listing.getSellerId()).orElse(null);
                            return responseMapper.toResponse(listing, seller);
                        })
                        .toList())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}
