package com.comet.wishgraphback.service.impl;

import com.comet.wishgraphback.adapter.ItemsAdapter;
import com.comet.wishgraphback.adapter.OwnershipAdapter;
import com.comet.wishgraphback.mapper.Mapper;
import com.comet.wishgraphback.model.dto.response.*;
import com.comet.wishgraphback.service.NftRecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NftRecommendationServiceImpl implements NftRecommendationService {

    @Autowired
    private ItemsAdapter itemsAdapter;
    @Autowired
    private OwnershipAdapter ownershipAdapter;
    @Autowired
    private Mapper mapper;


    @Override
    public RecommendedItemsDto getRecommendedItems(String walletToken) {
        return getRecommendedItems(3, List.of(walletToken));
    }

    private RecommendedItemsDto getRecommendedItems(int depth, List<String> walletIds) {
        log.info("Depth: " + depth);
        log.info("Size of walletIds: " + walletIds.size());
        boolean isCurrentCreator = depth % 2 == 0;
        Map<String, List<ItemDto>> items = new HashMap<>();
        List<String> currentWallets = new ArrayList<>();

        if (isCurrentCreator) {
            for (String walletId : walletIds) {
                ItemsByOwnerDto itemsByCreator = itemsAdapter.getItemsByCreator(walletId);
                itemsByCreator.getItems().stream()
                        .limit(4)
                        .forEach(item -> {
                            OwnersResponseDto ownershipsByItem = ownershipAdapter.getOwnershipsByItem(item.getId());
                            if (ownershipsByItem.getOwnerships().size() != 0 && itemIsSoldOut(walletId, ownershipsByItem)) {
                                if (!items.containsKey(walletId)) {
                                    items.put(walletId, new ArrayList<>());
                                }
                                items.get(walletId).add(item);
                                currentWallets.add(ownershipsByItem.getOwnerships().get(0).getOwner());
                            }
                        });
            }
        } else {
            for (String walletId : walletIds) {
                ItemsByOwnerDto itemsByOwner = itemsAdapter.getItemsByOwner(walletId);
                itemsByOwner.getItems().stream()
                        .limit(4)
                        .forEach(item -> {
                            if (item.getCreators().size() != 0 && itemIsntUsersCreation(walletId, item)) {
                                currentWallets.addAll(item.getCreators().stream().map(CreatorDto::getAccount).collect(Collectors.toList()));
                            }
                        });
            }
        }

        if (depth == 0) {
            return mapper.mapRecommendedItemsDto(items);
        }

        return getRecommendedItems(depth - 1, currentWallets);
    }

    private boolean itemIsntUsersCreation(String walletId, ItemDto item) {
        return !item.getCreators().get(0).getAccount().equals(walletId);
    }

    private boolean itemIsSoldOut(String walletId, OwnersResponseDto ownershipsByItem) {
        return !ownershipsByItem.getOwnerships().get(0).getOwner().equals(walletId);
    }
}
