package com.comet.wishgraphback.service.impl;

import com.comet.wishgraphback.adapter.ItemsAdapter;
import com.comet.wishgraphback.adapter.OwnershipAdapter;
import com.comet.wishgraphback.model.dto.response.GraphElementDto;
import com.comet.wishgraphback.model.dto.response.ItemsByOwnerDto;
import com.comet.wishgraphback.model.dto.response.OwnersResponseDto;
import com.comet.wishgraphback.service.GraphBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphBuilderServiceImpl implements GraphBuilderService {

    @Autowired
    private ItemsAdapter itemsAdapter;
    @Autowired
    private OwnershipAdapter ownershipAdapter;

    @Override
    public List<GraphElementDto> buildGraph(String walletId) {
        return buildGraphFromCustomerToCreator(3, walletId, "");
    }

    private List<GraphElementDto> buildGraphFromCustomerToCreator(int depth, String customerWallet, String previousCreator) {
        List<GraphElementDto> elements = new ArrayList<>();
        List<String> creatorWallets = new ArrayList<>();

        ItemsByOwnerDto itemsByOwner = itemsAdapter.getItemsByOwner(customerWallet);
        itemsByOwner.getItems().stream()
                .limit(10)
                .forEach(item -> {
                    if (item.getCreators().size() > 0) {
                        String creatorId = item.getCreators().get(0).getAccount();
                        if (!creatorId.equals(previousCreator) && !creatorId.equals(customerWallet)) {
                            elements.add(GraphElementDto.builder()
                                    .source(customerWallet)
                                    .target(creatorId)
                                    .type(0)
                                    .build());
                            creatorWallets.add(creatorId);
                        }
                    }
                });

        for (String creatorWallet : creatorWallets) {
            elements.addAll(buildGraphFromCreatorToCustomer(depth - 1, creatorWallet, customerWallet));
        }

        return elements;
    }

    private List<GraphElementDto> buildGraphFromCreatorToCustomer(int depth, String creatorWallet, String previousHolder) {
        if (depth == 0) {
            return List.of();
        }

        List<GraphElementDto> elements = new ArrayList<>();
        List<String> customerWallets = new ArrayList<>();

        ItemsByOwnerDto itemsByCreator = itemsAdapter.getItemsByCreator(creatorWallet);
        itemsByCreator.getItems().stream()
                .limit(10)
                .forEach(item -> {
                    OwnersResponseDto ownershipsByItem = ownershipAdapter.getOwnershipsByItem(item.getId());
                    if (ownershipsByItem.getOwnerships().size() != 0) {
                        String customerId = ownershipsByItem.getOwnerships().get(0).getOwner();
                        if (!customerId.equals(previousHolder) && !customerId.equals(creatorWallet)) {
                            elements.add(GraphElementDto.builder()
                                    .source(creatorWallet)
                                    .target(customerId)
                                    .type(1)
                                    .build());
                            customerWallets.add(customerId);
                        }
                    }
                });

        for (String customerWallet : customerWallets) {
            elements.addAll(buildGraphFromCustomerToCreator(depth - 1, customerWallet, creatorWallet));
        }

        return elements;
    }
}
