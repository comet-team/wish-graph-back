package com.comet.wishgraphback.service;

import com.comet.wishgraphback.model.dto.response.RecommendedItemsDto;

public interface NftRecommendationService {
    RecommendedItemsDto getRecommendedItems(String walletToken);
}
