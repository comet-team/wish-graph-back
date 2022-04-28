package com.comet.wishgraphback.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecommendedAuthorDto {
    List<NftDto> nft;
}
