package com.comet.wishgraphback.service;

import com.comet.wishgraphback.model.dto.response.GraphElementDto;
import com.comet.wishgraphback.model.dto.response.NftDto;

import java.util.List;

public interface GraphBuilderService {
    List<GraphElementDto> buildGraph(String walletId);

    List<NftDto> getItemsOfUser(String walletId);
}
