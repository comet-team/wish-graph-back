package com.comet.wishgraphback.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NftDto {
    private String id;
    private String url;
    private String title;
}
