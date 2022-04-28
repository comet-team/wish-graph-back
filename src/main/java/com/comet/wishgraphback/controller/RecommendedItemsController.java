package com.comet.wishgraphback.controller;

import com.comet.wishgraphback.model.dto.response.GraphElementDto;
import com.comet.wishgraphback.model.dto.response.NftDto;
import com.comet.wishgraphback.model.dto.response.RecommendedItemsDto;
import com.comet.wishgraphback.service.GraphBuilderService;
import com.comet.wishgraphback.service.impl.NftRecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v0.1")
public class RecommendedItemsController {

    @Autowired
    private NftRecommendationServiceImpl nftRecommendationService;
    @Autowired
    private GraphBuilderService graphBuilderService;

    @GetMapping("/recommend")
    public RecommendedItemsDto getRecommendedItems(@RequestParam(name = "walletToken") String walletToken) {
        return nftRecommendationService.getRecommendedItems(walletToken);
    }

    @GetMapping("/graph")
    public List<GraphElementDto> getGrapth(@RequestParam(name = "walletToken") String walletToken) {
        return graphBuilderService.buildGraph(walletToken);
    }

    @GetMapping("/user-items")
    public List<NftDto> getItems(@RequestParam(name = "walletToken") String walletToken) {
        return graphBuilderService.getItemsOfUser(walletToken);
    }
}
