package com.springstudy.projectmap.pharmacy.service;

import com.springstudy.projectmap.api.dto.DocumentDto;
import com.springstudy.projectmap.api.dto.KakaoApiResponseDto;
import com.springstudy.projectmap.api.service.KakaoAddressSearchService;
import com.springstudy.projectmap.direction.entity.Direction;
import com.springstudy.projectmap.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address: {}", address);
            return;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

//        List<Direction> directionList = directionService.buildDirectionList(documentDto); // 공공기관에서 제공받은 데이터 이용
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto); // 카카오 api 이용

        directionService.saveAll(directionList);
    }
}
