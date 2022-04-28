package com.comet.wishgraphback.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendedNftDto {
    private String id;
    private String url;
    private String title;
}
