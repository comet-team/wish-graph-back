package com.comet.wishgraphback.controller;

import com.comet.wishgraphback.model.dto.response.RecommendedItemsDto;
import com.comet.wishgraphback.model.dto.response.RecommendedNftDto;
import com.comet.wishgraphback.service.impl.NftRecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0.1")
public class RecommendedItemsController {

    @Autowired
    private NftRecommendationServiceImpl nftRecommendationService;

    @GetMapping("/recommend")
    public RecommendedItemsDto getRecommendedItems(@RequestParam(name = "walletToken") String walletToken){
        return nftRecommendationService.getRecommendedItems(walletToken);
    }
}
