package com.comet.wishgraphback.service;

import com.comet.wishgraphback.model.dto.response.GraphElementDto;

import java.util.List;

public interface GraphBuilderService {
    List<GraphElementDto> buildGraph(String walletId);
}
